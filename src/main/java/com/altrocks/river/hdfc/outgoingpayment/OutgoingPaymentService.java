package com.altrocks.river.hdfc.outgoingpayment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.altrocks.river.trail.hdfc.KeyUtils;
import com.altrocks.river.utils.APIConstants;
import com.altrocks.river.utils.UtilsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class OutgoingPaymentService {

	@Autowired
	UtilsService Utils;
	@Autowired
	PaymentResponseRepository paymentResponseRepository;

	@Autowired
	OutgoingPaymentsRepository outgoingPaymentsRepository;

	@Value("${ssl.key.store}")
	public String keyStorePath;

	@Value("${ssl.key.store.password}")
	public String keyStorePassword;

	public List<OutgoingPaymentPropertiesDAO> getoutgoingpayments() throws UnirestException, IOException {

		com.mashape.unirest.http.HttpResponse<String> jsonresponse = Utils
				.ApiCall("https://" + Utils.port + "-" + APIConstants.outgoingPayment.YY1_Outgoing_Payment_API);
		JsonNode getJsonValues = Utils.XmlToJsonConversionTestContent(jsonresponse.getBody());

//				System.out.println(jsonresponse.getBody());

		ObjectMapper objectMapper = new ObjectMapper();

		List<OutgoingPaymentDAO> entryNodesValues = objectMapper.readValue(getJsonValues.traverse(),
				objectMapper.getTypeFactory().constructCollectionType(List.class, OutgoingPaymentDAO.class));

		List<OutgoingPaymentPropertiesDAO> customerList = entryNodesValues.stream()
				.map(e -> e.getContent().getProperties()).collect(Collectors.toList());
		System.out.println("customerList" + customerList);
		return customerList;

	}
	  public List<OutgoingPaymentEntity> getAllScheduledPayments() {
	        return outgoingPaymentsRepository.findAll(); // Add filtering if required
	    }

//
////	@GetMapping("/getoutgoingpay")
		public void saveOutgoingPayments() throws UnirestException, IOException {

		List<OutgoingPaymentPropertiesDAO> outgoingpayment = this.getoutgoingpayments();

		List<OutgoingPaymentEntity> masterEntityList = new ArrayList<>();

		if (outgoingpayment.size() > 0 && outgoingpayment != null) {
			for (OutgoingPaymentPropertiesDAO materialObj : outgoingpayment) {

				System.err.println(materialObj.getAccountingDocument());
				if (!outgoingPaymentsRepository.existsByAccountingDocument(materialObj.getAccountingDocument())
						&& !outgoingPaymentsRepository.existsByAccountingDocument(materialObj.getFiscalYear())) {

					System.out.println("Save Test:");

					OutgoingPaymentEntity entity = new OutgoingPaymentEntity();
					entity.setCompanyCode(materialObj.getCompanyCode());
					entity.setFiscalYear(materialObj.getFiscalYear());
					entity.setDocumentDate(materialObj.getDocumentDate());
					entity.setPostingDate(materialObj.getPostingDate());
					entity.setAccountingDocumentType(materialObj.getAccountingDocumentType());
					entity.setAccountingDocument(materialObj.getAccountingDocument());
					entity.setDocumentReferenceID(materialObj.getDocumentReferenceID());
					entity.setTransactionCurrency(materialObj.getTransactionCurrency());
					entity.setAmountInTransactionCurrency(materialObj.getAmountInTransactionCurrency());
					entity.setBankAccount(materialObj.getBankAccount());
					entity.setBankNumber(materialObj.getBankNumber());
					entity.setPaymentMethodsList(materialObj.getPaymentMethodsList());
					entity.setAccountAssignment(materialObj.getAccountAssignment());
					entity.setSupplier(materialObj.getSupplier());
					entity.setGLAccount(materialObj.getGLAccount());
					entity.setProfitCenter(materialObj.getProfitCenter());
					entity.setCostCenter(materialObj.getCostCenter());
					entity.setAccountingDocumentHeaderText(materialObj.getAccountingDocumentHeaderText());
					entity.setClearingJournalEntry(materialObj.getClearingJournalEntry());
					// entity.setClearingDate(materialObj.getClearingDate());

					entity.setAccountingDocument(materialObj.getAccountingDocument());

					outgoingPaymentsRepository.save(entity);
//					masterEntityList.add(entity);
				}

			}

			// outgoingPaymentsRepository.saveAll(masterEntityList);
			System.out.println("masterEntityList" + masterEntityList);
		}

	}

	private void setupSSLContext() throws Exception {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");

		try (InputStream keyStoreStream = new FileInputStream(keyStorePath)) {
			keyStore.load(keyStoreStream, keyStorePassword.toCharArray());
		}

		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		kmf.init(keyStore, keyStorePassword.toCharArray());

		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

		HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
	}

	@Description(value = "Token Generation process")
	public String getAccessToken(String clientId, String clientSecret, String scope, String grantType) {
		try {
			// Step 1: Setup SSL
			setupSSLContext(); // Assume this sets SSL context if needed

			// Step 2: Prepare Basic Auth
			String auth = clientId + ":" + clientSecret;
			String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Basic " + encodedAuth);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)); // Optional

			// Step 3: Make GET request with query parameters
			String url = APIConstants.bankApi.token + "?scope="
					+ URLEncoder.encode(scope, StandardCharsets.UTF_8) + "&grant_type="
					+ URLEncoder.encode(grantType, StandardCharsets.UTF_8);

			HttpEntity<String> entity = new HttpEntity<>(headers);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<TokenDAO> response = restTemplate.exchange(url, HttpMethod.POST, entity, TokenDAO.class);

			System.out.println("Response: " + response);
			return response.getBody().getAccess_token();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// One method, two different calls
//	public void callInitiatePaymentAPI(String apiUrl, String bearerAccessToken, String encryptedJWT, String apiKey,
//	        String transactionId, String scope, String clientPrivateKey, String bankPublicKey, String clientKid,
//	        String bankKid) throws NoSuchAlgorithmException {
//	    try {
//	        KeyStore keyStore = KeyStore.getInstance("PKCS12");
//	        keyStore.load(new FileInputStream("C:\\certs\\keystore.p12"), "123456789".toCharArray());
//
//	        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//	        kmf.init(keyStore, "123456789".toCharArray());
//
//	        URL url = new URL(apiUrl);
//	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//	        connection.setRequestMethod("POST");
//	        connection.setRequestProperty("Content-Type", "application/jose");
//	        connection.setRequestProperty("apikey", apiKey.trim());
//	        connection.setRequestProperty("transactionId", transactionId);
//	        connection.setRequestProperty("Authorization", "Bearer " + bearerAccessToken);
//	        connection.setRequestProperty("scope", scope);
//	        connection.setRequestProperty("Custom-Header", "value");
//
//	        connection.setDoOutput(true);
//	        try (OutputStream os = connection.getOutputStream()) {
//	            byte[] input = encryptedJWT.getBytes("utf-8");
//	            os.write(input, 0, input.length);
//	        }
//
//	        int responseCode = connection.getResponseCode();
//	        System.out.println("HTTP Response Code: " + responseCode);
//
//	        StringBuffer httpResponseBuffer = new StringBuffer();
//	        if (responseCode == HttpURLConnection.HTTP_OK) {
//	            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
//	                String inputLine;
//	                while ((inputLine = in.readLine()) != null) {
//	                    httpResponseBuffer.append(inputLine);
//	                }
//	            }
//	            String responseBody = httpResponseBuffer.toString();
//	            System.out.println("Response Body: " + responseBody);
//
//	            String decryptedResponse = decryptBankResponse(responseBody, clientPrivateKey, clientKid,
//	                    bankPublicKey, bankKid);
//	        } else {
//	            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
//	                String inputLine;
//	                while ((inputLine = in.readLine()) != null) {
//	                    httpResponseBuffer.append(inputLine);
//	                }
//	            }
//	            System.err.println("Error Response Body: " + httpResponseBuffer.toString());
//	        }
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//	}

//	@Description(value = "Caling the corp payment api to get encrpted value")
	public String callInitiatePaymentAPI(String apiUrl, String bearerAccessToken, String encryptedToken, String apiKey,
			String transactionId, String scope, String clientPrivateKey, String bankPublicKey, String clientKid,
			String bankKid) throws NoSuchAlgorithmException {
    try {
        // Load PKCS12 keystore
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(new FileInputStream("C:\\certs\\keystore.p12"), "123456789".toCharArray());

        // Initialize KeyManager
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(keyStore, "123456789".toCharArray());

        // Setup SSL Context with KeyManager (if needed)
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), null, null);

        // Create HttpClient with custom SSL context
        HttpClient client = HttpClient.newBuilder().sslContext(sslContext).build();

        // Create the request
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)) // <-- API URL from method parameter
                .header("Content-Type", "application/jose").header("apikey", apiKey.trim())
                .header("transactionId", transactionId).header("Authorization", "Bearer " + bearerAccessToken)
                .header("scope", scope).header("Custom-Header", "value")
                .POST(HttpRequest.BodyPublishers.ofString(encryptedToken, StandardCharsets.UTF_8)).build();

        // Send the request
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

//        System.out.println("HTTP Response Code: " + response.statusCode());

        if (response.statusCode() == 200) {
            String responseBody = response.body();
            System.out.println("Response Body: " + responseBody);

            // 🔽 Decrypt the response if necessary
            String decryptedResponse = decryptBankResponse(responseBody, clientPrivateKey, clientKid, bankPublicKey,
                    bankKid);

            // Return the response body for use in subsequent steps
            return responseBody; // This will return the response body to the calling method
        } else {
            System.err.println("Error Response Body: " + response.body());
            return null; // If there's an error, return null
        }

    } catch (Exception e) {
        e.printStackTrace();
        return null; // In case of an exception, return null
    }
}


	@Description(value = "decrption process")
	public String decryptBankResponse(String responseBody, String clientPrivateKey, String clientKid,
			String bankPublicKey, String bankKid) {
		try {
			String decryptedJWE = RsaOAepWithAesGcm.decryptWithRSAOAEP(responseBody,
					KeyUtils.getPrivateKey(clientPrivateKey), clientKid);
			String decryptedJWS = Rsa256.verifyJWSWithRS256(KeyUtils.getPublicKey(bankPublicKey), decryptedJWE,
					bankKid);
			// Parse the decrypted response and save it into the DB
            saveDecryptedResponseToDB(decryptedJWS); // Saving to DB
//			System.out.println("Decrypted JWE: " + decryptedJWE);
//			System.out.println("Decrypted JWS: " + decryptedJWS);

			return decryptedJWS;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
    public void saveDecryptedResponseToDB(String decryptedResponse) {
        // Parse decryptedResponse (e.g., JSON) into fields, assuming it's a JSON string
        try {
            JSONObject jsonResponse = new JSONObject(decryptedResponse);
            String apiRefNo = jsonResponse.optString("CBX_API_REF_NO");
            String transactionStatus = jsonResponse.optString("Transaction");

            // Create a new entity object and set the parsed values
            CorpPaymentResponseEntity responseEntity = new CorpPaymentResponseEntity();
            
            responseEntity.setApiRefNo(apiRefNo);
            responseEntity.setTransactionStatus(transactionStatus);

            // Save the entity to the database using the repository
            paymentResponseRepository.save(responseEntity);
            System.out.println("Decrypted response saved to DB");

        } catch (Exception e) {
            System.err.println("Error parsing response or saving to DB: " + e.getMessage());
        }
    }

}
