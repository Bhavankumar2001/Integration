package com.altrocks.river.hdfc.outgoingpayment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altrocks.river.trail.hdfc.KeyUtils;
import com.altrocks.river.trail.hdfc.Rsa256;
import com.altrocks.river.trail.hdfc.RsaOAepWithAesGcm;
import com.altrocks.river.utils.APIConstants;
import com.altrocks.river.utils.UtilsService;

@RestController
@RequestMapping("/hdfc")
public class HdfcOutgoingPaymentController {

	@Autowired
	OutgoingPaymentService authService;
	
	@Autowired 
	UtilsService Utils;
	
	@Autowired
	PaymentResponseRepository paymentResponseRepository;


    @Scheduled(fixedRate = 9000)
//	@GetMapping("/outgoing/payment/testing")
	public void outgoingPayment() {
//    	System.out.println("entering :"+ "Hiiiii");
		  Set<String> sentPaymentRefNos = new HashSet<>();
		try {
			   List<OutgoingPaymentEntity> records = authService.getAllScheduledPayments();
			   for (OutgoingPaymentEntity payloadapi : records) {
				   String paymentRefNo =payloadapi.getDocumentReferenceID();
				   int matchid = payloadapi.getId(); 
//						   payloadapi.getDocumentReferenceID(); // Get PAYMENT_REF_NO
//				   payloadapi.getId();
				   String postingDateStr = payloadapi.getPostingDate();  // "2025-04-05 00:00:00"
//				   System.out.println(" postingDateStr :"+ postingDateStr);

				// 1. Parse it correctly
				DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime dateTime = LocalDateTime.parse(postingDateStr, inputFormatter);

				// 2. Format it to "dd/M/yyyy"
				DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				DateTimeFormatter outputFormatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				String formattedDate = dateTime.format(outputFormatter);
				String formattedDate1 = dateTime.format(outputFormatter1);
//				System.out.println("formattedDate :" + formattedDate);
				   
				   // Check if paymentRefNo is empty
				    if (paymentRefNo == null || paymentRefNo.isEmpty()) {
//				        System.out.println("Skipping record because PAYMENT_REF_NO is empty for ID: " + matchid);
				        continue; // skip this record
				    }
				   
		            // Check if the PAYMENT_REF_NO has already been processed
		            if (sentPaymentRefNos.contains(paymentRefNo)) {
//		                System.out.println("Payment with PAYMENT_REF_NO " + paymentRefNo + " has already been sent. Skipping...");
		                continue; // Skip this iteration if the payment is already sent
		            }
		            
		            // Add PAYMENT_REF_NO to the set of sent payments
					sentPaymentRefNos.add(paymentRefNo);
			JSONObject payloadString = new JSONObject();
			payloadString.put("LOGIN_ID",Utils.appIntegrationloginId);
			payloadString.put("INPUT_GCIF", Utils.appIntegrationScopeId);
			payloadString.put("TRANSFER_TYPE_DESC",payloadapi.getPaymentMethodsList());
			payloadString.put("INPUT_DEBIT_AMOUNT",payloadapi.getAmountInTransactionCurrency()); 
			payloadString.put("INPUT_VALUE_DATE",formattedDate); 
			payloadString.put("TRANSACTION_TYPE",payloadapi.getAccountingDocumentType()); 
			payloadString.put("INPUT_DEBIT_ORG_ACC_NO","00500340000261");
			payloadString.put("INPUT_BUSINESS_PROD","VENDBUS01");
			payloadString.put("BENE_TYPE","ADHOC"); 
			payloadString.put("BENE_ACC_NO",payloadapi.getBankAccount()); 
			payloadString.put("BENE_BRANCH",payloadapi.getBankNumber()); 
			payloadString.put("PAYMENT_REF_NO",paymentRefNo); 

			String payloadJson = payloadString.toString();
			
//			String payloadJson ="{\r\n"
//					+ "    \"BENE_TYPE\": \"ADHOC\",\r\n"
//					+ "    \"LEI_EXPIRY_DATE\": \"\",\r\n"
//					+ "    \"BENE_ID\": \"\",\r\n"
//					+ "    \"INPUT_BUSINESS_PROD\": \"VENDBUS01\",\r\n"
//					+ "    \"INPUT_VALUE_DATE\": \"25/04/2025\",\r\n"
//					+ "    \"REMARKS\": \"\",\r\n"
//					+ "    \"EXPIRY_DATE\": \"\",\r\n"
//					+ "    \"BENE_NARRATION\": \"\",\r\n"
//					+ "    \"LEI_CODE\": \"\",\r\n"
//					+ "    \"INPUT_DEBIT_ORG_ACC_NO\": \"00500340000261\",\r\n"
//					+ "    \"KYC\": \"\",\r\n"
//					+ "    \"Unknown tag 4\": \"\",\r\n"
//					+ "    \"Unknown tag 1\": \"\",\r\n"
//					+ "    \"Unknown tag 2\": \"\",\r\n"
//					+ "    \"Unknown tag 3\": \"\",\r\n"
//					+ "    \"ORG_REMIT_NAME\": \"\",\r\n"
//					+ "    \"ADDL_EMAIL\": \"\",\r\n"
//					+ "    \"INPUT_GCIF\": \"CBXMGRT5\",\r\n"
//					+ "    \"BENE_ACC_NAME\": \"SANTOSHRAI\",\r\n"
//					+ "    \"BENE_IDN_CODE\": \"HDFC0000001\",\r\n"
//					+ "    \"EMAIL_ADDR_VIEW\": \"\",\r\n"
//					+ "    \"ADDNL_FIELD_4\": \"\",\r\n"
//					+ "    \"ADDNL_FIELD_3\": \"\",\r\n"
//					+ "    \"LOGIN_ID\": \"APIUSER@CBXMGRT5\",\r\n"
//					+ "    \"ADDNL_FIELD_2\": \"\",\r\n"
//					+ "    \"ADDNL_FIELD_1\": \"\",\r\n"
//					+ "    \"TRANSACTION_TYPE\": \"SINGLE\",\r\n"
//					+ "    \"PYMNT_CAT\": \"\",\r\n"
//					+ "    \"ORG_REMIT_ADDR\": \"\",\r\n"
//					+ "    \"TRANSFER_TYPE_DESC\": \"Intra Bank Transfer\",\r\n"
//					+ "    \"PAYMENT_CATEGORY\": \"\",\r\n"
//					+ "    \"REMIT_NARRATION\": \"\",\r\n"
//					+ "    \"BENE_BANK\": \"StateBankofIndia\",\r\n"
//					+ "    \"BENE_ACC_NO\": \"00600350101534\",\r\n"
//					+ "    \"PAYMENT_REF_NO\": \"TGA2APAYREF0006\",\r\n"
//					+ "    \"ORG_REMIT_ACC_NO\": \"\",\r\n"
//					+ "    \"INPUT_DEBIT_AMOUNT\": \"6000.00\",\r\n"
//					+ "    \"BENE_BRANCH\": \"GOVINDPURI\",\r\n"
//					+ "    \"ADDNL_FIELD_5\": \"\",\r\n"
//					+ "    \"IAT\": \"\"\r\n"
//					+ "}";
			JSONObject payloadString1 = new JSONObject();
			payloadString1.put("INPUT_GCIF",Utils.appIntegrationScopeId);
			payloadString1.put("LOGIN_ID", Utils.appIntegrationloginId);
			payloadString1.put("FILTER1_VALUE_TXT",payloadapi.getPaymentMethodsList());
			payloadString1.put("CBX_API_REF_NO",paymentRefNo); 
			payloadString1.put("TXNDATE",formattedDate1); 
			
			String payloadJson1 = payloadString1.toString();
//			String payloadJsoninquiry = "{\r\n"
//					+ "    \"INPUT_GCIF\": \""+Utils.appIntegrationScopeId+"\",\r\n"
//					+ "    \"LOGIN_ID\": \"APIUSER@"+Utils.appIntegrationScopeId+"\",\r\n"
//					+ "    \"FILTER1_VALUE_TXT\": \"NEFT\",\r\n"
//					+ "    \"CBX_API_REF_NO\": \"TGA2APAYREF0015\",\r\n"
//					+ "    \"TXNDATE\": \"2025-04-28\"\r\n"
//					+ "}";
			String clientId =Utils.appIntegrationClientId;
			String clientSecret = Utils.appIntegrationClientSecret;

			String scope = Utils.appIntegrationScopeId;
			String grantType =Utils.appIntegrationGrantType;
			String clientPublicKey =APIConstants.clientKey.clientPublicKey;

			String clientPrivateKey = APIConstants.clientKey.clientPrivateKey;
			
			String bankPublicKey = APIConstants.bankKey.bankPublicKey;
			String apiKey =Utils.appIntegrationapiKey ; // your actual API key
		    
			String clientKid = KeyUtils.generateKid(KeyUtils.getPublicKey(clientPublicKey));
//			System.out.println("clientKid :" + clientKid);
			String bankKid = KeyUtils.generateKid(KeyUtils.getPublicKey(bankPublicKey));
			
			//corp payment
			String signature = Rsa256.generateJWSWithRS256(payloadJson, KeyUtils.getPrivateKey(clientPrivateKey),
					clientKid);
			//inq payment
			String signatureinquiry = Rsa256.generateJWSWithRS256(payloadJson1, KeyUtils.getPrivateKey(clientPrivateKey),
					clientKid);
			System.out.println("signature :"+signature);
			String EncrpytedJWT = RsaOAepWithAesGcm.encryptWithRSAOAEP(signature, KeyUtils.getPublicKey(bankPublicKey),
					bankKid);
			String EncrpytedInquiryJWT = RsaOAepWithAesGcm.encryptWithRSAOAEP(signatureinquiry, KeyUtils.getPublicKey(bankPublicKey),
					bankKid);
			String apiUrl = APIConstants.bankApi.corpPayment;
			String paymentInqUrl =  APIConstants.bankApi.paymentInq;

			String token1 = authService.getAccessToken(clientId, clientSecret, scope, grantType);
			String token2 = authService.getAccessToken(clientId, clientSecret, scope, grantType);
			
			String transactionId = generateRandomString(10);

//			System.out.println("Token: " + token);

			// First Call - Corp Payment
			  // First API Call - Corp Payment (get response body)
	        String responseBody = authService.callInitiatePaymentAPI(apiUrl, token1, EncrpytedJWT, apiKey, transactionId, scope,
	                clientPrivateKey, bankPublicKey, clientKid, bankKid);

	        // If the responseBody is not null, proceed with the second API call
	        // Check if the payment initiation was successful
	        if (responseBody.trim().startsWith("{")) {
	            // Assuming the response contains relevant fields, update CorpPaymentResponseEntity
	            JSONObject jsonResponse = new JSONObject(responseBody);
	            String paymentRefNoResponse = jsonResponse.optString("CBX_API_REF_NO");
	            String transactionStatus = jsonResponse.optString("Transaction");

	            // Find the corresponding OutgoingPaymentEntity by DocumentReferenceID
	            OutgoingPaymentEntity outgoingPaymentEntity = payloadapi; // Replace with appropriate lookup logic if needed
	            CorpPaymentResponseEntity corpPaymentResponse = new CorpPaymentResponseEntity();

	            // Set the response data and link to the correct payment record
	            corpPaymentResponse.setId(matchid); // Assuming DocumentReferenceID is the unique ID
	            corpPaymentResponse.setApiRefNo(paymentRefNoResponse);
	            corpPaymentResponse.setTransactionStatus(transactionStatus);

	            // Persist the CorpPaymentResponseEntity to the database
	            paymentResponseRepository.save(corpPaymentResponse);
	        }

			// Second Call - Inquiry 
			authService.callInitiatePaymentAPI(paymentInqUrl, token2, EncrpytedInquiryJWT, apiKey, transactionId, scope,
			        clientPrivateKey, bankPublicKey, clientKid, bankKid);
			   }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 public static String generateRandomString(int length) {
	        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	        StringBuilder result = new StringBuilder();
	        Random random = new Random();

	        for(int i = 0; i < length; i++) {
	            result.append(characters.charAt(random.nextInt(characters.length())));
	        }

	        return result.toString();
	    }

}
