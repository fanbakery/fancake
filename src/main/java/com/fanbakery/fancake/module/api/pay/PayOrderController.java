package com.fanbakery.fancake.module.api.pay;

import com.fanbakery.fancake.code.system.BidReqResultCd;
import com.fanbakery.fancake.config.ApplicationConfig;
import com.fanbakery.fancake.module.bidding.service.BiddingService;
import com.fanbakery.fancake.repository.model.ItItemEntity;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class PayOrderController extends PaypleController{

	private final BiddingService bidService;
	private final ApplicationConfig applicationConfig;

	/*
	 * payOrder.html : 주문 페이지  
	 */
	
    @GetMapping("/payOrder")
    public String payorder(@RequestParam(name = "itemNo", required = true) Long itemNo,
						   Model model, HttpSession session) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException{

		ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
		Long mbNo = user.getMbNo();

		//1. get bidding item info
		ItItemEntity bidItem = bidService.getBiddingItem(itemNo);
		if(bidItem == null) {
			log.error("[LOAD_BID] Not Bidding Product Info::ITEM_SEQ_"+ itemNo);
			return "redirect:/";
		}

		Long lastBidPrice = bidService.getLastBidPrice(bidItem.getItemSeq());
		Long myBidPrice = 0l;
		if(lastBidPrice == null) {
			myBidPrice = bidItem.getItemSellStartPrice();
		} else  {
			myBidPrice = lastBidPrice + bidItem.getItemSellAddPrice();
		}

		//이미 입찰진행 신청 여부 확인
		//현재 입찰 가격 확인

    	/*model.addAttribute("payer_no", "1234"); 					// 파트너 회원 고유번호
		model.addAttribute("payer_name", "홍길동"); 				// 결제자 이름(선택: 미리알고 있는경우)
		model.addAttribute("payer_hp", "01012345678"); 			// 결제자 휴대전화번호(선택: 알림톡과 연결되어있음.)
		model.addAttribute("payer_email", "test@payple.kr"); 	// 결제자 이메일(선택:*/


		model.addAttribute("payer_no", "1234"); 					// 파트너 회원 고유번호
		model.addAttribute("payer_name", user.getMbName()); 	 				// 결제자 이름(선택: 미리알고 있는경우)
		model.addAttribute("payer_hp", user.getMbHp()); 			// 결제자 휴대전화번호(선택: 알림톡과 연결되어있음.)
		model.addAttribute("payer_email", user.getMbEmail()); 	// 결제자 이메일(선택:
		
		//필수값
		//파트너사에서 정해져 있는 상품명 및 금액을 넣어줘야 함.
		/*model.addAttribute("pay_goods", "휴대폰"); 				// 상품명
		model.addAttribute("pay_total", "1000"); 				// 결제요청금액*/

		model.addAttribute("pay_goods", bidItem.getItemName()); 				// 상품명
		model.addAttribute("pay_total", Long.toString(myBidPrice)); 				// 결제요청금액

		return null;
      }
    
	/*
	 * orderConfirm.html : 결제확인 페이지
	 * post
	 */
    @PostMapping(value = "/payOrderConfirm")
	public String order_confirm(HttpServletRequest request, Model model) {

		model.addAttribute("pay_type", request.getParameter("pay_type")); 				// 결제수단 (transfer|card)
		model.addAttribute("pay_work", request.getParameter("pay_work")); 				// 결제요청 방식 (AUTH | PAY | CERT)
		model.addAttribute("payer_id", request.getParameter("payer_id")); 				// 결제자 고유 ID (빌링키)
		model.addAttribute("payer_no", request.getParameter("payer_no")); 				// 파트너 회원 고유번호
		model.addAttribute("payer_name", request.getParameter("payer_name")); 			// 결제자 이름
		model.addAttribute("payer_hp", request.getParameter("payer_hp")); 				// 결제자 휴대전화번호
		model.addAttribute("payer_email", request.getParameter("payer_email")); 		// 결제자 이메일
		model.addAttribute("pay_goods", request.getParameter("pay_goods")); 			// 상품명
		model.addAttribute("pay_total", request.getParameter("pay_total")); 			// 결제요청금액
		model.addAttribute("pay_taxtotal", request.getParameter("pay_taxtotal")); 		// 부가세(복합과세 적용 시)
		model.addAttribute("pay_istax", request.getParameter("pay_istax")); 			// 과세 여부 (과세:Y 비과세:N)
		model.addAttribute("pay_oid", request.getParameter("pay_oid")); 				// 주문번호
		model.addAttribute("taxsave_flag", request.getParameter("taxsave_flag")); 		// 현금영수증 발행요청 (Y|N)
		model.addAttribute("simple_flag", request.getParameter("simple_flag")); 		// 간편결제 여부 (Y|N)
		model.addAttribute("card_ver", request.getParameter("card_ver")); 				// 카드 세부 결제방식
		model.addAttribute("payer_authtype", request.getParameter("payer_authtype")); 	// 비밀번호 결제 인증방식 (pwd : 패스워드 인증)
		model.addAttribute("is_direct", request.getParameter("is_direct")); 			// 결제창 호출 방식 (DIRECT: Y | POPUP: N)
		model.addAttribute("hostname", System.getenv("HOSTNAME"));

		// 파트너 인증
		JSONObject obj = new JSONObject();
		obj = payAuth(null);
		//{"result":"success","result_msg":"사용자 인증 완료!!","server_name":"democpay.payple.kr","custKey":"Y3NQeU93Zll4cWNESkNSUXZkYXdhZz09","PCD_PAY_URL":"\/index.php?ACT_=PAYM&CPAYVER=202112102248","AuthKey":"K0VnWlZ5TWZSaGNla1Vpay96YnNQRmJ5eHRmeTNYTzlwdkpxeUdUUXYybXdMbDRZSjJSOEdlWTdFd3VYZE1zMWlEb3JDZUwvOFpsSCt2U3cxaHcrVkd4ampKbE5wdFNpU1F1OXMzMHQwb3RYUUZxRE5odW0wNm5tbm0wY25sNWhmNitDbklCblAzSlJCa2VOU2EzSUdHZzNGT1prTWIza2g4NTNUeWpreFJZMjljVUI1cmx0VFliTnI5aUxMQlBwT1c5dS90NkdXa3lCbVF5SFZqM1hpVGdLa2pqdWVmNWVwYnJGckJWemVIMm9sZFdzejJYMGlBbHE4NmZkYm1jU3lwNDk2MTlNVnVFTCs2M0JHM2tkOEE9PQ==","PCD_PAY_HOST":"https:\/\/democpay.payple.kr","return_url":"https:\/\/democpay.payple.kr\/index.php?ACT_=PAYM&CPAYVER=202112102248","cst_id":"M0k4ZHJiOTNGOXRDbzNlalpaa3RMdz09"}

		// 파트너 인증 후 결제요청 시 필요한 필수 파라미터
		model.addAttribute("authKey", obj.get("AuthKey")); 		// 인증 키
		model.addAttribute("payReqURL", obj.get("return_url")); // 결제요청 URL

		return null;
	}
    
	/*
	 * orderResult.jsp : 결제결과 확인 페이지
	 */
	@RequestMapping(value = "/payOrderResult")
	public String order_result(HttpServletRequest request, Model model) {

		// 1. 결제결과 모두 출력
		Enumeration<String> params = request.getParameterNames();
		String result = "";

		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			result += name + " => " + request.getParameter(name) + "<br>";
		}
		model.addAttribute("result", result);

		// 2. 결제결과 파라미터로 받기 - 응답 파라미터를 받아서 활용해보세요.
		model.addAttribute("pay_rst", request.getParameter("PCD_PAY_RST")); 					// 결제요청 결과 (success | error)
		model.addAttribute("pay_code", request.getParameter("PCD_PAY_CODE")); 					// 결제요청 결과 코드
		model.addAttribute("pay_msg", request.getParameter("PCD_PAY_MSG")); 					// 결제요청 결과 메세지
		model.addAttribute("pay_type", request.getParameter("PCD_PAY_TYPE")); 					// 결제수단 (transfer|card)
		model.addAttribute("card_ver", request.getParameter("PCD_CARD_VER")); 					// 카드 세부 결제방식
		model.addAttribute("pay_work", request.getParameter("PCD_PAY_WORK")); 					// 결제요청 방식 (AUTH | PAY | CERT)
		model.addAttribute("auth_key", request.getParameter("PCD_AUTH_KEY")); 					// 결제요청 파트너 인증 토큰 값
		model.addAttribute("pay_reqkey", request.getParameter("PCD_PAY_REQKEY")); 				// (CERT방식) 최종 결제요청 승인키
		model.addAttribute("pay_cofurl", request.getParameter("PCD_PAY_COFURL")); 				// (CERT방식) 최종 결제요청 URL

		model.addAttribute("payer_id", request.getParameter("PCD_PAYER_ID")); 					// 결제자 고유 ID (빌링키)
		model.addAttribute("payer_no", request.getParameter("PCD_PAYER_NO")); 					// 결제자 고유번호 (파트너사 회원 회원번호)
		model.addAttribute("payer_name", request.getParameter("PCD_PAYER_NAME")); 				// 결제자 이름
		model.addAttribute("payer_hp", request.getParameter("PCD_PAYER_HP")); 					// 결제자 휴대전화번호
		model.addAttribute("payer_email", request.getParameter("PCD_PAYER_EMAIL")); 			// 결제자 이메일 (출금결과 수신)
		model.addAttribute("pay_oid", request.getParameter("PCD_PAY_OID")); 					// 주문번호
		model.addAttribute("pay_goods", request.getParameter("PCD_PAY_GOODS")); 				// 상품명
		model.addAttribute("pay_total", request.getParameter("PCD_PAY_TOTAL")); 				// 결제요청금액
		model.addAttribute("pay_taxtotal", request.getParameter("PCD_PAY_TAXTOTAL")); 			// 부가세(복합과세 적용 시)
		model.addAttribute("pay_istax", request.getParameter("PCD_PAY_ISTAX")); 				// 과세 여부 (과세:Y 비과세:N)
		model.addAttribute("pay_date", request.getParameter("PCD_PAY_TIME") == null ? "": 
			request.getParameter("PCD_PAY_TIME").substring(0, 8)); 								// 결제완료 일자
		model.addAttribute("pay_bankacctype", request.getParameter("PCD_PAY_BANKACCTYPE")); 	// 고객 구분 (법인 | 개인 or 개인사업자)

		model.addAttribute("pay_bank", request.getParameter("PCD_PAY_BANK")); 					// 은행코드
		model.addAttribute("pay_bankname", request.getParameter("PCD_PAY_BANKNAME")); 			// 은행명
		model.addAttribute("pay_banknum", request.getParameter("PCD_PAY_BANKNUM")); 			// 계좌번호
		model.addAttribute("taxsave_rst", request.getParameter("PCD_TAXSAVE_RST")); 			// 현금영수증 발행결과 (Y|N)

		model.addAttribute("pay_cardname", request.getParameter("PCD_PAY_CARDNAME")); 			// 카드사명
		model.addAttribute("pay_cardnum", request.getParameter("PCD_PAY_CARDNUM")); 			// 카드번호
		model.addAttribute("pay_cardtradenum", request.getParameter("PCD_PAY_CARDTRADENUM"));	// 카드 거래번호
		model.addAttribute("pay_cardauthno", request.getParameter("PCD_PAY_CARDAUTHNO")); 		// 카드 승인번호
		model.addAttribute("pay_cardreceipt", request.getParameter("PCD_PAY_CARDRECEIPT")); 	// 카드 매출전표 URL

		return null;
	}
	
	/*
	 * payRefund : 결제취소
	 */
	@ResponseBody
	@PostMapping(value = "/payRefund")
	public JSONObject payRefund(HttpServletRequest request) {

		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();

		// 결제취소 전 파트너 인증
		Map<String, String> refundParams = new HashMap<>();
		refundParams.put("PCD_PAYCANCEL_FLAG", "Y");

		JSONObject authObj = new JSONObject();
		authObj = payAuth(refundParams);

		// 파트너 인증 응답값
		String cstId = (String) authObj.get("cst_id"); 			// 파트너사 ID
		String custKey = (String) authObj.get("custKey"); 		// 파트너사 키
		String authKey = (String) authObj.get("AuthKey"); 		// 인증 키
		String payRefURL = (String) authObj.get("return_url"); 	// 결제취소요청 URL

		// 결제취소 요청 파라미터
		String refund_key = "a41ce010ede9fcbfb3be86b24858806596a9db68b79d138b147c3e563e1829a0"; // (필수) 환불키
		String pay_oid = request.getParameter("PCD_PAY_OID"); 									// (필수) 주문번호
		String pay_date = request.getParameter("PCD_PAY_DATE"); 								// (필수) 원거래 결제일자
		String refund_total = request.getParameter("PCD_REFUND_TOTAL"); 						// (필수) 결제취소 요청금액
		String refund_taxtotal = request.getParameter("PCD_REFUND_TAXTOTAL"); 					// 결제취소 부가세

		try {

			// 결제취소 요청 전송
			JSONObject refundObj = new JSONObject();

			refundObj.put("PCD_CST_ID", cstId);
			refundObj.put("PCD_CUST_KEY", custKey);
			refundObj.put("PCD_AUTH_KEY", authKey);
			refundObj.put("PCD_REFUND_KEY", refund_key);
			refundObj.put("PCD_PAYCANCEL_FLAG", "Y");
			refundObj.put("PCD_PAY_OID", pay_oid);
			refundObj.put("PCD_PAY_DATE", pay_date);
			refundObj.put("PCD_REFUND_TOTAL", refund_total);
			refundObj.put("PCD_REFUND_TAXTOTAL", refund_taxtotal);

			URL url = new URL(payRefURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("POST");
			con.setRequestProperty("content-type", "application/json");
			con.setRequestProperty("referer", applicationConfig.getPgRefererDomain());
			con.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(refundObj.toString());
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
	
	
	/*
	 * payCertSend : 결제요청 재컨펌 (CERT)
	 */
	@ResponseBody
	@PostMapping(value = "/payCertSend")
	public JSONObject payCertSend(HttpServletRequest request) {

		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		
		// 결제요청 재컨펌(CERT) 데이터 - 필수
		String auth_key = request.getParameter("PCD_AUTH_KEY"); 	// 파트너 인증 토큰 값
		String payer_id = request.getParameter("PCD_PAYER_ID"); 	// 결제자 고유 ID (빌링키)
		String pay_reqkey = request.getParameter("PCD_PAY_REQKEY"); // 최종 결제요청 승인키
		String pay_cofurl = request.getParameter("PCD_PAY_COFURL"); // 최종 결제요청 URL
		
		try {

			// 결제요청 재컨펌(CERT) 요청 전송
			JSONObject refundObj = new JSONObject();

			refundObj.put("PCD_CST_ID", "test"); 				// 파트너사 cst_id
			refundObj.put("PCD_CUST_KEY", "abcd1234567890"); 	// 파트너사 custKey
			refundObj.put("PCD_AUTH_KEY", auth_key); 
			refundObj.put("PCD_PAYER_ID", payer_id); 
			refundObj.put("PCD_PAY_REQKEY", pay_reqkey);

			URL url = new URL(pay_cofurl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("POST");
			con.setRequestProperty("content-type", "application/json");
			con.setRequestProperty("referer", applicationConfig.getPgRefererDomain());
			con.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(refundObj.toString());
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

		}catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject;
	}
	
	
	
	// 정기결제 재결제(빌링키결제) (paySimpleSend.jsp)
	@RequestMapping(value = "/paySimpleSend")
	public String paySimpleSendRoute(Model model) {

		model.addAttribute("payer_id", ""); 					// 결제자 고유 ID (빌링키)
		model.addAttribute("pay_goods", "휴대폰"); 				// 상품명
		model.addAttribute("pay_total", "1000"); 				// 결제요청금액
		model.addAttribute("payer_no", "1234"); 				// 결제자 고유번호 (파트너사 회원 회원번호)
		model.addAttribute("payer_email", "test@payple.kr"); 	// 결제자 이메일

		return "paySimpleSend";
	}
	
	
	/*
	 * paySimpleSend : 정기결제 재결제(빌링키 결제)
	 */
	@ResponseBody
	@PostMapping(value = "/paySimpleSend")
	public JSONObject paySimpleSend(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();

		// 정기결제 재결제 전 파트너 인증
		Map<String, String> bilingParams = new HashMap<>();
		bilingParams.put("PCD_PAY_TYPE", request.getParameter("PCD_PAY_TYPE"));
		bilingParams.put("PCD_SIMPLE_FLAG", "Y");

		JSONObject authObj = new JSONObject();
		authObj = payAuth(bilingParams);

		System.out.println(authObj.toString());

		// 파트너 인증 응답값
		String cstId = (String) authObj.get("cst_id"); 			// 파트너사 ID
		String custKey = (String) authObj.get("custKey"); 		// 파트너사 키
		String authKey = (String) authObj.get("AuthKey"); 		// 인증 키
		String bilingURL = (String) authObj.get("return_url"); 	// 카드 정기결제 재결제 요청 URL

		// 정기결제 재결제 요청 파라미터
		String pay_type = request.getParameter("PCD_PAY_TYPE"); 			// (필수) 결제수단 (card | transfer)
		String payer_id = request.getParameter("PCD_PAYER_ID"); 			// (필수) 결제자 고유 ID (빌링키)
		String pay_goods = request.getParameter("PCD_PAY_GOODS"); 			// (필수) 상품명
		String pay_total = request.getParameter("PCD_PAY_TOTAL"); 			// (필수) 결제요청금액
		String pay_oid = request.getParameter("PCD_PAY_OID"); 				// 주문번호
		String payer_no = request.getParameter("PCD_PAYER_NO"); 			// 결제자 고유번호 (파트너사 회원 회원번호)
		String payer_name = request.getParameter("PCD_PAYER_NAME"); 		// 결제자 이름
		String payer_hp = request.getParameter("PCD_PAYER_HP");				// 결제자 휴대전화번호
		String payer_email = request.getParameter("PCD_PAYER_EMAIL"); 		// 결제자 이메일
		String pay_istax = request.getParameter("PCD_PAY_ISTAX"); 			// 과세여부
		String pay_taxtotal = request.getParameter("PCD_PAY_TAXTOTAL"); 	// 부가세(복합과세 적용 시)

		try {
			// 정기결제 재결제 요청 전송
			JSONObject bilingObj = new JSONObject();

			bilingObj.put("PCD_CST_ID", cstId);
			bilingObj.put("PCD_CUST_KEY", custKey);
			bilingObj.put("PCD_AUTH_KEY", authKey);
			bilingObj.put("PCD_PAY_TYPE", pay_type);
			bilingObj.put("PCD_PAYER_ID", payer_id);
			bilingObj.put("PCD_PAY_GOODS", pay_goods);
			bilingObj.put("PCD_SIMPLE_FLAG", "Y");
			bilingObj.put("PCD_PAY_TOTAL", pay_total);
			bilingObj.put("PCD_PAY_OID", pay_oid);
			bilingObj.put("PCD_PAYER_NO", payer_no);
			bilingObj.put("PCD_PAYER_NAME", payer_name);
			bilingObj.put("PCD_PAYER_HP", payer_hp);
			bilingObj.put("PCD_PAYER_EMAIL", payer_email);
			bilingObj.put("PCD_PAY_ISTAX", pay_istax);
			bilingObj.put("PCD_PAY_TAXTOTAL", pay_taxtotal);

			System.out.println(bilingObj.toString());

			URL url = new URL(bilingURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("POST");
			con.setRequestProperty("content-type", "application/json");
			con.setRequestProperty("charset", "UTF-8");
			con.setRequestProperty("referer", applicationConfig.getPgRefererDomain());
			con.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(bilingObj.toString().getBytes());
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
