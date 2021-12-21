package com.fanbakery.fancake.module.api.pay;

import com.fanbakery.fancake.config.ApplicationConfig;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PaypleController {

	@Autowired
	ApplicationConfig applicationConfig;

	/**
	 * 파트너 인증 메소드
	 * 
	 * @param params // 상황별 파트너 인증 요청 파라미터(계정정보 제외)
	 * @return JSONObject // 파트너 인증 응답값
	 */
	public JSONObject payAuth(Map<String, String> params) {
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {

			// 파트너 인증 Request URL
			String pURL = "https://democpay.payple.kr/php/auth.php"; // TEST (테스트)
			// String pURL = "https://cpay.payple.kr/php/auth.php"; // REAL (운영)

			// 계정정보
			String cst_id = "test";
			String cust_key = "abcd1234567890";

			JSONObject obj = new JSONObject();
			obj.put("cst_id", cst_id);
			obj.put("custKey", cust_key);

			// 상황별 파트너 인증 요청 파라미터
			
			 if (params != null) { 
				 for (Map.Entry<String, String> elem : params.entrySet()) { 
					 obj.put(elem.getKey(), elem.getValue()); 
				 } 
			}
			 

			System.out.println("파트너 인증 Request: " + obj.toString());

			URL url = new URL(pURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			/*
			 * ※ Referer 설정 방법
			 * TEST : referer에는 테스트 결제창을 띄우는 도메인을 넣어주셔야합니다.
			 * 		  결제창을 띄울 도메인과 referer값이 다르면 [AUTH0007] 응답이 발생합니다.
			 * REAL : referer에는 가맹점 도메인으로 등록된 도메인을 넣어주셔야합니다.
			 * 		  다른 도메인을 넣으시면 [AUTH0004] 응답이 발생합니다.
			 * 		  또한, TEST에서와 마찬가지로 결제창을 띄우는 도메인과 같아야 합니다.
			 */
			con.setRequestMethod("POST");
			con.setRequestProperty("content-type", "application/json");
			con.setRequestProperty("charset", "UTF-8");
			//test 서버에서는 8080,80,443 port 지원
			//운영 서버에서는 8080,80,443 port 지원
			//이외 포트는 지원 안함.
			con.setRequestProperty("referer", applicationConfig.getPgRefererDomain());
			con.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(obj.toString().getBytes());
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

			jsonObject = (JSONObject) jsonParser.parse(response.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;

	}
	
}
