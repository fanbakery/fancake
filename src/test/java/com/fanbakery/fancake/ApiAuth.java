package com.fanbakery.fancake;

import com.fanbakery.fancake.api.nice.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@SpringBootTest
class ApiAuth {

    @Test
    void contextLoads() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    	
		 HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
		 	
	        long currentTimestamp = new Date().getTime()/1000;
	        String Authorization = "abf22b73-5187-4af4-b4bb-31fab26789b1:"+currentTimestamp+":0c7348be-d9a5-480e-8106-f952ec856301";
	        
	        headers.add("Authorization","bearer "+Base64Utils.encodeToString(Authorization.getBytes()));
	        headers.add("client_id","0c7348be-d9a5-480e-8106-f952ec856301");
	        headers.add("productID","2101979031");

	        RestTemplate restTemplate = new RestTemplate();
	        String url = "https://svc.niceapi.co.kr:22001";
	        String after_url ="/digital/niceid/api/v1.0/common/crypto/token"; 
	        
			SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
			String req_dtim = sdf.format(new Date());
			
	        DataBody dataBody = DataBody.builder().req_dtim(req_dtim).req_no("REQ2021123199").enc_mode("1").build();
	        		
	        DataHeader dataHeader = DataHeader.builder().CNTY_CD("ko").build();
	        PostData data = PostData.builder().
	        		dataBody(dataBody).dataHeader( dataHeader)
	        		.build();

	        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity(data,headers);
	        
	        String r = restTemplate.postForObject(url+after_url, entity, String.class);
	        System.out.println(r);
//	        UserVO user = new UserVO();
	        Gson gson = new Gson();
	        JsonParser parser = new JsonParser();
	    	JsonElement element = parser.parse(r);
	    	String ret = element.getAsJsonObject().get("dataHeader").getAsJsonObject().get("GW_RSLT_CD").getAsString();
	    	DataBody resultDataBody = null;
	    	if(ret.equals("1200")) {
	    		resultDataBody = gson.fromJson(element.getAsJsonObject().get("dataBody"), DataBody.class);
	    		System.out.println("resultDataBody");
	    		System.out.println(resultDataBody);
	    	}
	    	String resultVal = null;
	    	if(resultDataBody!= null) {
	    		String value = dataBody.getReq_dtim().trim() + dataBody.getReq_no().trim() + resultDataBody.getToken_val().trim();
	    		
	    		resultVal = makeSha256Base64(value);
	    	}
	    	
	    	
	    	int keyCount = 16;
	    	String key = new String(resultVal.getBytes(), 0, keyCount);
	    	System.out.println("resultVal:"+resultVal);
	    	keyCount = 16;
	    	String iv = new StringBuffer(resultVal).toString();
	    	iv =resultVal.substring(resultVal.length()-keyCount);
	    	keyCount = 32;
	    	System.out.println("resultVal:"+resultVal);
	    	String hmac_key = new String(resultVal.getBytes(), 0, keyCount);
	    	
	    	System.out.println(key);
	    	System.out.println(iv);
	    	System.out.println("iv:"+iv);
	    	System.out.println(hmac_key);
	    	
	    	String key2 = resultVal.substring(0, 16);										
		    String iv2 = new StringBuffer(resultVal).reverse().toString().substring(0,16);			
		    System.out.println("iv2:"+iv2);
		    String hmac_key2 = resultVal.substring(0, 32);
//	    	
	    	RequestData requestData = RequestData.builder()
	    	    	.requestno("REQ2021123199")
	    	    	.returnurl("http://localhost/test/result")
	    	    	.sitecode(resultDataBody.getSite_code())
	    	    	.methodtype("post")
	    	    	.popupyn("Y").
	    	    	build();
//		    										
//		    // 암호화 					
		    
		    
			String str ="{\"returnurl\":\"http://localhost/test/result\", \"sitecode\":\""+resultDataBody.getSite_code()+"\", \"popupyn\":\"Y\",\"requestno\":\"REQ2021123199\"}";
			JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
			System.out.println(jsonObject);
			
		    
	    	String reqData = gson.toJson(requestData).toString();
//	    	System.out.println(reqData);
//	    	System.out.println(str);
	    	
//	    	System.out.println(reqData);
	    	SecretKey secureKey = new SecretKeySpec(key.getBytes((StandardCharsets.UTF_8)), "AES");										
		    Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
//		    Cipher c = Cipher.getInstance("AES128/CBC/PKCS7");
		    
		    c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(iv.getBytes()));
		    byte[] encrypted = c.doFinal(reqData.toString().trim().getBytes());
//		    byte[] encrypted = c.doFinal(jsonObject.toString().trim().getBytes());
//		    String reqDataEnc =Base64.getEncoder().encodeToString(encrypted);
		    String reqDataEnc =Base64Utils.encodeToString(encrypted);
		    
		    
//		    System.out.println("vvv");
//		    System.out.println(jsonObject.toString().trim());
//		    System.out.println("vvv en");
//		    System.out.println(reqDataEnc);
		    
		    byte[] hmacSha256 = hmac256(hmac_key.getBytes((StandardCharsets.UTF_8)), reqDataEnc.getBytes((StandardCharsets.UTF_8)));					
//		    String intigrety_value = Base64.getEncoder().encodeToString(hmacSha256);
		    String intigrety_value = Base64Utils.encodeToString(hmacSha256);
		    
//		    
		    System.out.println("token_version_id");
		    System.out.println(resultDataBody.getToken_version_id());
//		    
		    System.out.println("reqDataEnc");
		    System.out.println(reqDataEnc);
//		    
		    System.out.println("intigrety_value");
		    System.out.println(intigrety_value);
 	
 	
 	 
 	 FormData f = FormData.builder().token_version_id(resultDataBody.getToken_version_id()).enc_data(reqDataEnc).integrity_value(intigrety_value).action("3").build();
 	
		    
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
    public static String makeSha256Base64(String value) throws NoSuchAlgorithmException {
    	
	    MessageDigest  md = MessageDigest.getInstance("SHA-256");
	    md.update(value.getBytes());
	    byte[] arrHashValue = md.digest();
	    
	    String resultVal = Base64.getEncoder().encodeToString(arrHashValue);
	    
	    return resultVal;

    	
    }
    
}
