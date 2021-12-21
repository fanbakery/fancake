package com.fanbakery.fancake;

import com.fanbakery.fancake.api.nice.DataBody;
import com.fanbakery.fancake.api.nice.DataHeader;
import com.fanbakery.fancake.api.nice.PostData;
import com.fanbakery.fancake.api.nice.ResultData;
import com.fanbakery.fancake.api.payple.RequestVO;
import com.fanbakery.fancake.api.payple.ResponseVO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

//@SpringBootTest
class PayRegularByPayerId {

    @Test
    void contextLoads() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
    	
    	
    	//1. auth 획득 
    	//	ResponseVO responseVO 값을 받음.
    	//2. 정기결제 요청 
    	//	responseVO 값과 PCD_PAYER_ID(정기결제 등록시 발급받는 key)를 통해 결제 요청.

    	ResponseVO responseVO = null;
	    JSONObject jsonObject = new JSONObject();
	    JSONParser jsonParser = new JSONParser();
	    try {

	        String pURL = "https://democpay.payple.kr/php/auth.php";
	        // 발급받은 비밀키. 유출에 주의하시기 바랍니다.
	        String cst_id = "test";
	        String cust_key = "abcd1234567890";

	        JSONObject obj = new JSONObject();
	        obj.put("cst_id", cst_id);
	        obj.put("custKey", cust_key);

	        URL url = new URL(pURL);
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();

					/*  ※ Referer 설정 필독
					 *	TEST : referer에는 테스트 결제창을 띄우는 도메인을 넣어주셔야합니다. 
					 *				 결제창을 띄울 도메인과 referer값이 다르면 [AUTH0007] 응답이 발생합니다.
					 *  REAL : referer에는 가맹점 도메인으로 등록된 도메인을 넣어주셔야합니다. 
					 *         다른 도메인을 넣으시면 [AUTH0004] 응답이 발생합니다.
					 *         또한, TEST에서와 마찬가지로 결제창을 띄우는 도메인과 같아야 합니다. 
					 */
	        con.setRequestMethod("POST");
	        con.setRequestProperty("content-type", "application/json");
	        con.setRequestProperty("referer", "http://localhost:8080"); // 필수
	        con.setDoOutput(true);

	        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	        
//	        wr.writeBytes(obj.toString().getBytes());
	        wr.writeBytes(obj.toString());
	        wr.flush();
	        wr.close();

	        int responseCode = con.getResponseCode();
	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }

	        in.close();

	        // System.out.println("HTTP 응답 코드 : " + responseCode);
	        // System.out.println("HTTP Body : " + response.toString());
	        
	        Gson gson = new Gson();
	        responseVO = gson.fromJson(response.toString(), ResponseVO.class);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
    	
	    
        String urlRegular = responseVO.getPCD_PAY_HOST()+responseVO.getPCD_PAY_URL();
		  
		
    	RequestVO data = RequestVO.builder()
    	.PCD_CST_ID(responseVO.getCst_id())
    	.PCD_CUST_KEY(responseVO.getCustKey())
    	.PCD_AUTH_KEY(responseVO.getAuthKey())
    	.PCD_PAY_TYPE("card")
    	.PCD_PAYER_ID("QzBEeDhBdzQyc2dtbXY1YXNpelRFZz09") //등록 후 리턴받은 빌링키
    	.PCD_PAY_GOODS("상품1")
    	.PCD_PAY_TOTAL(100)
    	.PCD_PAY_OID("1")
    	.PCD_SIMPLE_FLAG("Y").build();
    	
    	System.out.println("요청 데이터___________________________");
    	System.out.println(data);
    	
	     jsonObject = new JSONObject();
	     jsonParser = new JSONParser();
	    try {

	        String pURL = urlRegular;
	        // 발급받은 비밀키. 유출에 주의하시기 바랍니다.
	        String cst_id = "test";
	        String cust_key = "abcd1234567890";

	        JSONObject obj = new JSONObject();
	        obj.put("PCD_CST_ID", responseVO.getCst_id());
	        obj.put("PCD_CUST_KEY", responseVO.getCustKey());
	        obj.put("PCD_AUTH_KEY", responseVO.getAuthKey());
	        obj.put("PCD_PAY_TYPE", "card");
	        obj.put("PCD_PAYER_ID", "QzBEeDhBdzQyc2dtbXY1YXNpelRFZz09");
	        obj.put("PCD_PAY_GOODS", "상품1");
	        obj.put("PCD_PAY_TOTAL", 100);
	        obj.put("PCD_PAY_OID", "1");
	        obj.put("PCD_SIMPLE_FLAG", "Y");
	        
	        

	        URL url = new URL(pURL);
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();

					/*  ※ Referer 설정 필독
					 *	TEST : referer에는 테스트 결제창을 띄우는 도메인을 넣어주셔야합니다. 
					 *				 결제창을 띄울 도메인과 referer값이 다르면 [AUTH0007] 응답이 발생합니다.
					 *  REAL : referer에는 가맹점 도메인으로 등록된 도메인을 넣어주셔야합니다. 
					 *         다른 도메인을 넣으시면 [AUTH0004] 응답이 발생합니다.
					 *         또한, TEST에서와 마찬가지로 결제창을 띄우는 도메인과 같아야 합니다. 
					 */
	        con.setRequestMethod("POST");
	        con.setRequestProperty("content-type", "application/json");
	        con.setRequestProperty("Cache-Control", "no-cache"); // 필수
	        con.setRequestProperty("referer", "http://localhost:8080");
	        con.setDoOutput(true);

	        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	        
//	        wr.writeBytes(obj.toString().getBytes());
	        wr.writeBytes(obj.toString());
	        wr.flush();
	        wr.close();

	        int responseCode = con.getResponseCode();
	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in.readLine()) != null) {
	        	System.out.println(inputLine);
	            response.append(inputLine);
	        }

	        in.close();

	        // System.out.println("HTTP 응답 코드 : " + responseCode);
	        // System.out.println("HTTP Body : " + response.toString());
	        
	        System.out.println(response.toString());
	        Gson gson = new Gson();
	        //responseVO = gson.fromJson(response.toString(), ResponseVO.class);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
//    	System.out.println("auth after");
//    	System.out.println("______________________________");
//    	System.out.println(responseVO);
//    	
//    	 HttpHeaders headers = new HttpHeaders();
//	        headers.setContentType(MediaType.APPLICATION_JSON);
//	        headers.setCacheControl("no-cache");
//			/**
//			 * TEST : https://democpay.payple.kr REAL : https://cpay.payple.kr
//			 */	        		 
//	        RestTemplate restTemplate = new RestTemplate();
////	        String url = "https://democpay.payple.kr/index.php?ACT_=PAYM&CPAYVER=202112170046";
////	        https://democpay.payple.kr/index.php?ACT_=PAYM&CPAYVER=202112170046
//	        String url = responseVO.getPCD_PAY_HOST()+responseVO.getPCD_PAY_URL();
//	        			  
//			
//	    	RequestVO data = RequestVO.builder()
//	    	.PCD_CST_ID(responseVO.getCst_id())
//	    	.PCD_CUST_KEY(responseVO.getCustKey())
//	    	.PCD_AUTH_KEY(responseVO.getAuthKey())
//	    	.PCD_PAY_TYPE("card")
//	    	.PCD_PAYER_ID("QzBEeDhBdzQyc2dtbXY1YXNpelRFZz09") //등록 후 리턴받은 빌링키
//	    	.PCD_PAY_GOODS("상품1")
//	    	.PCD_PAY_TOTAL(100)
//	    	.PCD_PAY_OID("1")
//	    	.PCD_SIMPLE_FLAG("Y").build();
//	    	
//	    	System.out.println("요청 데이터___________________________");
//	    	System.out.println(data);
//	    	
//	        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity(data,headers);
//	        String r = restTemplate.postForObject(url, entity, String.class);
//	        System.out.println(r);
//	        UserVO user = new UserVO();
//	        Gson gson = new Gson();
//	        JsonParser parser = new JsonParser();
//	    	JsonElement element = parser.parse(r);
//	    	String ret = element.getAsJsonObject().get("dataHeader").getAsJsonObject().get("GW_RSLT_CD").getAsString();
	    	

    	
    }

}
