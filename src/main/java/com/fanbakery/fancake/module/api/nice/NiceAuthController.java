package com.fanbakery.fancake.module.api.nice;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fanbakery.fancake.code.system.BidReqResultCd;
import com.fanbakery.fancake.common.utils.DateUtil;
import com.fanbakery.fancake.repository.mapper.MemberMapper;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fanbakery.fancake.api.nice.DataBody;
import com.fanbakery.fancake.api.nice.DataHeader;
import com.fanbakery.fancake.api.nice.FormData;
import com.fanbakery.fancake.api.nice.PostData;
import com.fanbakery.fancake.api.nice.RequestData;
import com.fanbakery.fancake.api.nice.ResultData;
import com.fanbakery.fancake.config.ApplicationConfig;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class NiceAuthController {

	private final ApplicationConfig _config;
	private final MemberMapper memberMapper;

    @GetMapping("/test")
    public String index(HttpServletRequest request, HttpSession session, Model model, @RequestParam(name="pageType", defaultValue = "adult") String pageType ) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnknownHostException {
    	
    	
    	
		 HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		long currentTimestamp = new Date().getTime()/1000;
//	        String Authorization = "abf22b73-5187-4af4-b4bb-31fab26789b1:"+currentTimestamp+":0c7348be-d9a5-480e-8106-f952ec856301";
		String Authorization = _config.getNiceAccessToken()+":"+currentTimestamp+":"+_config.getNiceClientId();

		headers.add("Authorization","bearer "+Base64Utils.encodeToString(Authorization.getBytes()));
//	        headers.add("client_id","0c7348be-d9a5-480e-8106-f952ec856301");
		headers.add("client_id",_config.getNiceClientId());
//	        headers.add("productID","2101979031");
		headers.add("productID",_config.getNiceProductId());

		RestTemplate restTemplate = new RestTemplate();
		String url = _config.getNiceAccessTokenUrl();
//	        String url = "https://svc.niceapi.co.kr:22001";
//	        String after_url ="/digital/niceid/api/v1.0/common/crypto/token";
		String after_url =_config.getNiceAccessTokenUrlAfter();

		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
		String req_dtim = sdf.format(new Date());
		//req_no 임의
		//enc_mode :1고정
		DataBody dataBody = DataBody.builder()
				.req_dtim(req_dtim)
				.req_no("REQ2021123199")
				.enc_mode("1").build();

		DataHeader dataHeader = DataHeader.builder().CNTY_CD("ko").build();
		PostData data = PostData.builder().
				dataBody(dataBody).dataHeader( dataHeader)
				.build();

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity(data,headers);

		String r = restTemplate.postForObject(url+after_url, entity, String.class);
//	        System.out.println(r);
//	        UserVO user = new UserVO();
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(r);
		String ret = element.getAsJsonObject().get("dataHeader").getAsJsonObject().get("GW_RSLT_CD").getAsString();
		DataBody resultDataBody = null;
		log.info(">> request: ret:"+ret);
		if(ret.equals("1200")) { //정상코드
			resultDataBody = gson.fromJson(element.getAsJsonObject().get("dataBody"), DataBody.class);
//	    		System.out.println("resultDataBody");
//	    		System.out.println(resultDataBody);
		}
		String resultVal = null;
		if(resultDataBody!= null) {
			String value = dataBody.getReq_dtim().trim() + dataBody.getReq_no().trim() + resultDataBody.getToken_val().trim();

			resultVal = makeSha256Base64(value);
		}
		log.info(">> request: resultVal:"+resultVal);

		int keyCount = 16;
		String key = new String(resultVal.getBytes(), 0, keyCount);
		log.info(">>> key:"+key);
		session.setAttribute("pageType", pageType);
		session.setAttribute("myauth_key", key);

		keyCount = 16;
		String iv = new StringBuffer(resultVal).toString();

		iv =resultVal.substring(resultVal.length()-keyCount);
		keyCount = 32;
		System.out.println("iv:"+iv);
		String hmac_key = new String(resultVal.getBytes(), 0, keyCount);

		session.setAttribute("myauth_iv", iv);

		RequestData requestData = RequestData.builder()
				.requestno("REQ2021123199")
//				.returnurl(_config.getNiceRetrunUrl())
				.returnurl(request.getScheme() + "://" + request.getServerName() + "/test/result")
				.sitecode(resultDataBody.getSite_code())
				.methodtype("get")
				.popupyn("N").
				build();


		String reqData = gson.toJson(requestData).toString();
		SecretKey secureKey = new SecretKeySpec(key.getBytes((StandardCharsets.UTF_8)), "AES");
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");

		c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(iv.getBytes()));
		byte[] encrypted = c.doFinal(reqData.toString().trim().getBytes());
		String reqDataEnc =Base64Utils.encodeToString(encrypted);

		byte[] hmacSha256 = hmac256(hmac_key.getBytes((StandardCharsets.UTF_8)), reqDataEnc.getBytes((StandardCharsets.UTF_8)));
		String intigrety_value = Base64Utils.encodeToString(hmacSha256);

		System.out.println("token_version_id");
		System.out.println(resultDataBody.getToken_version_id());
		System.out.println("reqDataEnc");
		System.out.println(reqDataEnc);
		System.out.println("intigrety_value");
		System.out.println(intigrety_value);

		FormData f = FormData.builder()
				.token_version_id(resultDataBody.getToken_version_id())
				.enc_data(reqDataEnc)
				.integrity_value(intigrety_value)
				.action(_config.getNiceUrl()).build();
//					.action("https://nice.checkplus.co.kr/CheckPlusSafeModel/service.cb").build();

		model.addAttribute("formData",f);

		return null;
      }
    
    @GetMapping("/result")
    public String result(HttpSession session, Model model, ResultData resultData) throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

		String key = (String) session.getAttribute("myauth_key");
		String iv = (String) session.getAttribute("myauth_iv");
		String pageType = (String) session.getAttribute("pageType");

		resultData.setIv(iv);
		resultData.setKey(key);
		log.info("get Key:" + resultData.getKey());

		ResultData responseData = getResponseData(resultData);

		/*System.out.println("token_version_id");
		System.out.println(resultData.getToken_version_id());
		System.out.println("reqDataEnc");
		System.out.println(resultData.getEnc_data());
		System.out.println("intigrety_value");
		System.out.println(resultData.getIntegrity_value());
		
		System.out.println("result");
		System.out.println(getResponseData(resultData).toString());*/
		/*log.info("api return  ");
		log.info("{}", resultData);*/

		/*ResultData request = ResultData.builder()
				.encData("jw/Dxqz+oXAr3/nBy/hIf5a84U7qbN/hIVYofhYuogluoNufaCSJlWrsgtLOoqKM5hMzS8htQDQP18haOGFEAU91/048j6ZAJuQClQjpb3MlH8lxre6dHljk88Fmg4S10qCagG+U1Xjx/n7qe92ObyrWEMoSYwEJDY1vfDprtlQFLREO7b4i6Dj2ktDIFhpbX4TwycyFuNjxvbY9Hv299LH65E7+MTTv3wYDy11gusmbcGvncOrVIABDODg8P8b0Sx0eZ28kPeQ5VEu+QKklhs0RSNPtFxyEeC5Aua9f1K2namz714MmFWCNo+OwTtMRxQhTGvWs4qvj1NPG+jJoh8jdCnon1FaOcszBsODlgCFpEanet8upeZH8zY5H9BjY6J4/1ETyurB1sOFzCHJNF/b9E6io+FOtsjnOQPEPTRX+AMVTqzHY7BEsx5QZAY4XrT4n/dIQY0SVQeBqLS3wCHVtgtx/St0ZKlsGVtXLjNatBL7iBYMBZXCA5bYMd2EOu0Ao9bpf7sZEO47md0J+KA==")
				.key("jepUC1bzoWM+bt42")
				.iv("Fk4/dbtZlH5Gq2c=").build();
		ResultData responseData = getResponseData(request);*/


		// 성인 체크
		if ("0000".equals(responseData.getResultcode()) && StringUtils.hasLength(responseData.getBirthdate())) {
			String birthYear = responseData.getBirthdate().substring(0, 4);
			int age = Integer.parseInt(DateUtil.getCurrentYear()) - Integer.parseInt(birthYear)+1;
			int isAdult = -1;
			if (age>=20) {
				model.addAttribute("isAdult", 1);
				isAdult = 1;
			}
			else {
				model.addAttribute("isAdult", 0);
				isAdult = 0;
			}

			if("adult".equals(pageType)) {
				ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
				memberMapper.updateMbAdultByMbNo(user.getMbNo(), isAdult);

				user.setMbAdult(isAdult);
				session.setAttribute("adult", user.getMbAdult());//user.mbSignature 가 user type 임.
				session.setAttribute("user", user);//user.mbSignature 가 user type 임.
			}
		}
		/*Calendar current = Calendar.getInstance();
		int currentYear  = current.get(Calendar.YEAR);*/

		model.addAttribute("responseData", responseData);



//    	 token_version_id=20211211112507G0-NCBCBV996-610F4-9C08A90008,
//    	enc_data=BDPGc5aFV7Smb+C30OaVcmxSOOiYMjpVtjmY/+jHwIB2f/9wUU4+CIeGPSwfYloXUxvNfCkpUCmGRzcBdfGIg6z1prZVccUjWFqWoRa0KKujagFsuqxLxLDaug6Vjj+4l7VuOEBjtxhufv9bJ0lPrgx6cFz9y7lQMplNonNPj9QK5AlaYYT67RW+PHHbEQ1Tpc4PUuSpkuCl27NfGYTnowQgEtibt3oKnDj/Yy9c3Z/+8Gs0BSQCwgkRPV8ylQPRWFQatV89MAXVP3XkhW491l5NH6qJNUDl4/f6DMAYuKvPz1xe420SJcMHV/QffsSd4IGPRlnrifKWs2Gm+sLPGgH3+L9r+qtzMGB24kwysuG0oTY/mi4bUza983Do0Rim7Zvr7yyJS2hFI/5U2HK7uTTWFSAgSO8A8hhZHvFsH5eIV8klb1h6ThlsIWC0GnOSkH9FzjIyNN2UBbmuy3PD8/jqAEGqnQPDbj2QLqjVa/G82xwwk97s0PNvG1fe6RQGcALsZOg4g/YBZMzC6JJ99Q==)

		return "test/myAuth_result";
      }

    public static String makeSha256Base64(String value) throws NoSuchAlgorithmException {
    	
	    MessageDigest  md = MessageDigest.getInstance("SHA-256");
	    md.update(value.getBytes());
	    byte[] arrHashValue = md.digest();
	    
//	    String resultVal = Base64.getEncoder().encodeToString(arrHashValue);
	    String resultVal = Base64Utils.encodeToString(arrHashValue);
	    
	    
	    return resultVal;
    }
    
    public static byte[] hmac256(byte[] secretKey,byte[] message){					
        byte[] hmac256 = null;					
        try{					
          Mac mac = Mac.getInstance("HmacSHA256");
          SecretKeySpec sks = new SecretKeySpec(secretKey, "HmacSHA256");					
          mac.init(sks);					
          hmac256 = mac.doFinal(message);					
          return hmac256;					
        }catch(Exception e){					
          throw new RuntimeException("Failed to generate HMACSHA256 encrypt ");					
        } 					
	}

	public ResultData getResponseData(ResultData requestDatas) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		log.info("get Key:" + requestDatas.getKey());
		SecretKey secureKey = new SecretKeySpec(requestDatas.getKey().getBytes(), "AES");
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(requestDatas.getIv().getBytes()));
		byte[] cipherEnc = Base64Utils.decode(requestDatas.getEnc_data().getBytes());
		String resData =   new String(c.doFinal(cipherEnc), "euc-kr");

		Gson gson = new Gson();
		ResultData result =	gson.fromJson(resData, ResultData.class);

		return result;
	}

	public String hostname() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostName();
	}
}
