package com.fanbakery.fancake;

import com.fanbakery.fancake.api.nice.ResultData;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.util.Base64Utils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

//@SpringBootTest
class ApiAuthAfter {

    @Test
    void contextLoads() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
    	

    	//request data 
    	String encData = "jw/Dxqz+oXAr3/nBy/hIf5a84U7qbN/hIVYofhYuogluoNufaCSJlWrsgtLOoqKM5hMzS8htQDQP18haOGFEAU91/048j6ZAJuQClQjpb3MlH8lxre6dHljk88Fmg4S10qCagG+U1Xjx/n7qe92ObyrWEMoSYwEJDY1vfDprtlQFLREO7b4i6Dj2ktDIFhpbX4TwycyFuNjxvbY9Hv299LH65E7+MTTv3wYDy11gusmbcGvncOrVIABDODg8P8b0Sx0eZ28kPeQ5VEu+QKklhs0RSNPtFxyEeC5Aua9f1K2namz714MmFWCNo+OwTtMRxQhTGvWs4qvj1NPG+jJoh8jdCnon1FaOcszBsODlgCFpEanet8upeZH8zY5H9BjY6J4/1ETyurB1sOFzCHJNF/b9E6io+FOtsjnOQPEPTRX+AMVTqzHY7BEsx5QZAY4XrT4n/dIQY0SVQeBqLS3wCHVtgtx/St0ZKlsGVtXLjNatBL7iBYMBZXCA5bYMd2EOu0Ao9bpf7sZEO47md0J+KA==";
    	String key = "jepUC1bzoWM+bt42"; // 요청 시 암호화한 key와 동일
    	String iv = "Fk4/dbtZlH5Gq2c="; // 요청 시 암호화한 iv와 동일

//    	//인증완료후 데이터
//    	private String token_version_id;
//    	private String enc_data;
//    	private String integrity_value;
    	
//    	요정값과 결과값 비교 후, 복호화 진행.
//    	복호화는 request data로 진행
    	
    	
    	ResultData request = ResultData.builder()
    	.encData("jw/Dxqz+oXAr3/nBy/hIf5a84U7qbN/hIVYofhYuogluoNufaCSJlWrsgtLOoqKM5hMzS8htQDQP18haOGFEAU91/048j6ZAJuQClQjpb3MlH8lxre6dHljk88Fmg4S10qCagG+U1Xjx/n7qe92ObyrWEMoSYwEJDY1vfDprtlQFLREO7b4i6Dj2ktDIFhpbX4TwycyFuNjxvbY9Hv299LH65E7+MTTv3wYDy11gusmbcGvncOrVIABDODg8P8b0Sx0eZ28kPeQ5VEu+QKklhs0RSNPtFxyEeC5Aua9f1K2namz714MmFWCNo+OwTtMRxQhTGvWs4qvj1NPG+jJoh8jdCnon1FaOcszBsODlgCFpEanet8upeZH8zY5H9BjY6J4/1ETyurB1sOFzCHJNF/b9E6io+FOtsjnOQPEPTRX+AMVTqzHY7BEsx5QZAY4XrT4n/dIQY0SVQeBqLS3wCHVtgtx/St0ZKlsGVtXLjNatBL7iBYMBZXCA5bYMd2EOu0Ao9bpf7sZEO47md0J+KA==")
    	.key("jepUC1bzoWM+bt42")
    	.iv("Fk4/dbtZlH5Gq2c=").build();
    	ResultData response = getResponseData(request);
    	
    	System.out.println(response);
    	
    }

    public static ResultData getResponseData(ResultData requestDatas) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		
    	SecretKey secureKey = new SecretKeySpec(requestDatas.getKey().getBytes(), "AES");
    	Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
    	c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(requestDatas.getIv().getBytes()));
    	byte[] cipherEnc = Base64Utils.decode(requestDatas.getEncData().getBytes());
    	String resData =   new String(c.doFinal(cipherEnc), "euc-kr");
    	
    	Gson gson = new Gson();
    	ResultData result =	gson.fromJson(resData, ResultData.class);
    	
    	return result;
    }
    
}
