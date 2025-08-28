package com.altrocks.river.trail.hdfc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hdfc")
public class OutgoingPaymentController {

	@GetMapping("/outgoing/payment")
	public void outgoingPayment() {

		try {
			System.err.println("<---------- Json Creeation and encript with JWE Signature Details Start  ---------->");

//			JSONObject gererateJson = gererateJson();
			JSONObject gererateJson = generatePaymentJson();
			String content=gererateJson.toString();
			System.out.println("Gemerated Json : "+gererateJson);
			System.err.println("Content : "+content);

			//	Consumer Privatekey
			String clientPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCwAutn1sooaFdqZwpJ5tz4Ky6CxKOG0CMWDkH0EE6MeGu1N8yjKMy/keZXz6+GEhph9LI3trHg8IWJseQXxgAFSLdAEO0V6T4dmBPRV3DYqvccuXtsR0wTGMFBB2jfJNYFjm6xtLMtjOZC4MjLcmxnu6goxTgHWCf5ISv10YEOLLpLFf9+OGPF+ZCKP0uPvqvf7F8H9ks5dUNP1Fnn6X2x1wvC+JyuYY+xMumlPpZnJHBlgU/pGnpAStPuV6DNPQH/8Uagm75AGexp8ICzkmmfd5x1NnWRnrZkS9LyoriILf+kiuzliOBSqZPqQr/R2uQIOVeDFRIIn36HttLFizT9AgMBAAECggEAU98AR7ndGQYnaYdyKqb88rC4+A01kx8JKRBgMym2FE+pm2/cbn8ZmcuvRHatwChONBFC/zNqbbKPH38KHp32Eo6E691k7ET5mWaHQwD0v/ovyJtGCeGriYoJRLCAM5y882bcrodZvkHe002DwRZkO8X7KnpX6YADhD5fUUKkyZiSHJYiUwz+OoSF4/HIrONCZys3FHdlQopxMThxzHZ9SW8v0KeZC55rKyXQHjFI4nWnHl4lkYCXk5umfhIAUunVYIaYi3RKElZ6+ZVsL3bteXBBVos0uhTOsuIyUUCWVzyjxwpgfNMY32ZxVkAsIoGnxkrvbaVskM/Qg8GwCS3Z7QKBgQDKB6MEMHi9OFzX/P2HoI8PobcsBvYafHS2H6KUPQJlmrPuCtoXWOpGr8mEmO5vDyPu7UmHpgr/EapKZjQtKAQIiYZHOl6n+hePvz9/vcOcvUGQ+YOyJFd/apuupeA8rIaU5Sdbqqj2mftIBOXFhmL0pKWzV2auicsp5k2oHn0d9wKBgQDfB/EAsB27KmyjoL67NwFAQ3DiHQ6SMNf1qxP7c8OPWS488eAftSKhmXvcjSRQEjIe2niXoQb2nEI7dgSX1HSPTUh4G3ZZucMAORIY0NxX1ivTC4TlZzdBiUjgSiU+F1tJXrbmGvcU5MOzyy8zHUb6L338WssitWWpWvOZgjEXqwKBgEYEWYnlJqMlen/cjR1JFuuq9ymKDBbn1ScdVaIcds0xzXQNTMJUrXTsDWIoAlJ5I9H1aMtbT2pQ+/ym6SRRPvyadPAQmaeLtEi8Vkx2Pun+aU/OOccucyiUkPn5QPqmLEpEFNzfC4nTmdO+5scLKRZyTX8XJl4NIV0XFQjT8DbHAoGBAM2CH8wme/7OpLRRle4kTP7OJeH0LE0AvL4eGZeFoBkib7ywk3gkC/kj2EHtvBwb6BsN7DirpCLw8QahRLIyjgTNjY0+oNsklE5MSCSdr6swKcmZv6cyaHP28DPbnl2MYA1HPPjit/GEqd+txZwvygsWX9x8PNmKlFZs/DHjCSFpAoGAOvOLVDLjENzoC5SgsJnhO2MyXEDecCSw3E1H64J9o3FXK8pSsq7r3yBQwETWDCTPO0HKXq6W9miBclos+ts/MHtZoFhVhj/bqXXEmNsQTU1EjEei0fSPGyAJsQH+Iqt9pcXo8fDCwnCaf+EWPy6RPLaEsqKdjRZ8dO3yZnUosHo=";
			//			Consumer Publickey
//			String clientPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqHGHEslO8x6wKAKgvTFuhW4UbMustaVwdKXvUbaSC+eeaqEY0Qz3kw6aUmVfIH9SxI3v2+0VflfFelXdSc1yu2+ZPMckaT15xCxKmtE3sQ8rkH7U8xDTsTSALnGHCUOpepKATF2LXWykKSVeO6XLD+Hox51/WQClbZdXPpF8xt0HQGaUqoNenCy17kh3HKPwcflhyRmh+KCKJu19v/lTocMI5kL+dnBwLQTpQw5iyrBpmPM12y3H8PLAbUeHE1pcHCGkMYoM5eiymxywId9i1FCBcvly9j3dXJhykjFoUBA1dt/NWD6Px0O2esZ1sjYoacIrIeUKhhQL1FiRmDojIwIDAQAB";
//			String clientPublicKey=  "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqHGHEslO8x6wKAKgvTFuhW4UbMustaVwdKXvUbaSC+eeaqEY0Qz3kw6aUmVfIH9SxI3v2+0VflfFelXdSc1yu2+ZPMckaT15xCxKmtE3sQ8rkH7U8xDTsTSALnGHCUOpepKATF2LXWykKSVeO6XLD+Hox51/WQClbZdXPpF8xt0HQGaUqoNenCy17kh3HKPwcflhyRmh+KCKJu19v/lTocMI5kL+dnBwLQTpQw5iyrBpmPM12y3H8PLAbUeHE1pcHCGkMYoM5eiymxywId9i1FCBcvly9j3dXJhykjFoUBA1dt/NWD6Px0O2esZ1sjYoacIrIeUKhhQL1FiRmDojIwIDAQAB";
			
			
			//Bank Shared Correct Public Key
//			String clientPublicKey=  "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsALrZ9bKKGhXamcKSebc+CsugsSjhtAjFg5B9BBOjHhrtTfMoyjMv5HmV8+vhhIaYfSyN7ax4PCFibHkF8YABUi3QBDtFek+HZgT0Vdw2Kr3HLl7bEdMExjBQQdo3yTWBY5usbSzLYzmQuDIy3JsZ7uoKMU4B1gn+SEr9dGBDiy6SxX/fjhjxfmQij9Lj76r3+xfB/ZLOXVDT9RZ5+l9sdcLwvicrmGPsTLppT6WZyRwZYFP6Rp6QErT7legzT0B//FGoJu+QBnsafCAs5Jpn3ecdTZ1kZ62ZEvS8qK4iC3/pIrs5YjgUqmT6kK/0drkCDlXgxUSCJ9+h7bSxYs0/QIDAQAB";
			
			//Arjun Shared Public Key
			String clientPublicKey=  "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsALrZ9bKKGhXamcKSebc+CsugsSjhtAjFg5B9BBOjHhrtTfMoyjMv5HmV8+vhhIaYfSyN7ax4PCFibHkF8YABUi3QBDtFek+HZgT0Vdw2Kr3HLl7bEdMExjBQQdo3yTWBY5usbSzLYzmQuDIy3JsZ7uoKMU4B1gn+SEr9dGBDiy6SxX/fjhjxfmQij9Lj76r3+xfB/ZLOXVDT9RZ5+l9sdcLwvicrmGPsTLppT6WZyRwZYFP6Rp6QErT7legzT0B//FGoJu+QBnsafCAs5Jpn3ecdTZ1kZ62ZEvS8qK4iC3/pIrs5YjgUqmT6kK/0drkCDlXgxUSCJ9+h7bSxYs0/QIDAQAB";
			
			//	Banks PublicKey
			String bankPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtUlK8MdCzJb5ROqmfW6B/KnXsAhWaHM8JNV3XmY0yyzZw4QsQKaqGoAvujKSwQeS1Uq+uJGcRXvmoWrMlqWAcLeGxswGCCVptS/gu2JP/hQ+r3bo7Xv9Jb4KdVQN7IGJUt9BZ4lb9tWRjgseSTNxsicFUpVj68Xw+ZWYZXdhARm3TtkhYmNKuMstVe9rA7dTQdAj9D/MJFZ7r+axC9n0uj6M6I2QdS5EoV+Bvoerb669duen6dvgFBRJSp93dO0WpotJT+z9oeCbJEUIxgK/Td/mjUWgD0+DbR8KIkZ9OLCB2rFXH0UzkLCEpooWeGW7ZA8nmsU7/eQrPBcx3EdUxwIDAQAB";
			//			Banks PrivateKey
			String bankPrivateKey = "";

			//	get client's publickey to generate kid to add in JWS headers
			String clientKid = KeyUtils.generateKid(KeyUtils.getPublicKey(clientPublicKey));
			System.err.println("Client Kid : "+clientKid);
			//	get bank's publickey to generate kid to add in JWE headers
			String bankKid = KeyUtils.generateKid(KeyUtils.getPublicKey(bankPublicKey));
			System.err.println("Bank Kid :" +bankKid);
			String signature = Rsa256.generateJWSWithRS256(content,KeyUtils.getPrivateKey(clientPrivateKey),clientKid);
			String EncrpytedJWT = RsaOAepWithAesGcm.encryptWithRSAOAEP(signature, KeyUtils.getPublicKey(bankPublicKey), bankKid);
			System.out.println("JWS : "+signature);
			System.out.println("JWE : "+EncrpytedJWT);
			System.err.println("<---------- Json Creeation and encript with JWE Signature Details End  ---------->");


			//			<---------- Pem Cerificate Configuration & Bank API Call Details Start  ---------->
			//			System.err.println("<---------- Pem Cerificate Configuration & Bank API Call Details Start  ---------->");
			//			String filePath = "";
			//			Resource resource = new ClassPathResource(filePath);
			//			File file = resource.getFile();
			//			String cert = file.getAbsolutePath();
			//
			//			KeyStore clientStore=KeyStore.getInstance("PKCS12");
			//			clientStore.load(new FileInputStream(new File(cert)),"CERT PASSWORD".toCharArray());
			//
			//			KeyManagerFactory kmf=KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			//			kmf.init(clientStore,"CERT PASSWORD".toCharArray());
			//			KeyManager[] kms=kmf.getKeyManagers();
			//
			//			final SSLContext sslContext=SSLContext.getInstance("TLS");
			//			sslContext.init(kms,null, new SecureRandom());
			//			SSLContext.setDefault(sslContext);
			//
			//			HostnameVerifier hostnameVerifier=NoopHostnameVerifier.INSTANCE;
			//			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
			//			HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
			//
			//			
			//			URL url=new URL("https://api-uat.hdfcbank.com/api/v1/corp-initiatePayment");
			//			
			//			HttpsURLConnection con=(HttpsURLConnection) url.openConnection();
			//
			//			con.setRequestProperty("Content-Type","text/plain");
			//			con.setRequestMethod("POST");
			//			con.setDoOutput(true);
			//			
			//			try(OutputStream os=con.getOutputStream()) {
			//				byte[] input =EncrpytedJWT.getBytes(StandardCharset.UTF_8);
			//				os.write(input,0,input.length);
			//			} 
			//			
			//			int responseCode=con.getResponseCode();
			//			System.out.println("Response Code : "+responseCode);
			//			
			//			int status=responseCode;
			//			
			//			BufferedReader reader=new BufferedReader(new InputStreamReader(con.getInputStream()));
			//			String line;
			//			StringBuilder response=new StringBuilder();
			//			while ((line=reader.readLine())!=null) {
			//				response.append(line);
			//			}
			//			
			//			reader.close();
			//			System.out.println("Response :"+response.toString());
			//			System.err.println("<---------- Pem Cerificate Configuration & Bank API Call Details End  ---------->");
			//			<---------- Pem Cerificate Configuration & Bank API Call Details End  ---------->
			System.err.println("<---------- Response Decript and Verify Details Start  ---------->");
			

			//			String bankPublicKey = "";
			//			Consumer Publickey
			//			String clientPublicKey = "";
			//			get client's publickey to generate kid to verify JWE Content
			//			String clientKid = KeyUtils.generateKid(KeyUtils.getPublicKey(clientPublicKey));
			//			get bank's publickey to generate kid to verify JWS Content
			//			String bankKid1 = KeyUtils.generateKid(KeyUtils.getPublicKey(bankPublicKey));
			String response="eyJhbGciOiJSU0EtT0FFUC0yNTYiLCJlbmMiOiJBMjU2R0NNIiwidHlwIjoiSldFIiwia2lkIjoiNXBsME9yZUVJOHpScnliemtGOC1iazkwMm43eVVRVzh4SktKRjByRkxVSSJ9.lAYTY2iWCWioKkffPsNC94P-REw-bEsG3m4QH_o4UIZvIO266Gt-9vb4b5uU50qgp0VTXRB9VcYYeO-KMFOvI21MFZ5ZdNxi2aQO_16KGI6XGuzU5uiNc78blEWuEBA1fmxK2JXH69EA0tuqTCbsgzfshGordjn2SAq3gbIrRYOwVd_wAcIklWN0jCZST2n2wDmvAx-DYJUBMWsAYzjEIMQn0dyElLkw6CMEzewhwwOzmZucRs8ovBqgsCL2CKzCOZmDUNa-te3bebu04t5ngnlLER04ldhSgxNrKo5a75oNQpjuWfc_51B-8eNR1aGzGm5FtMo2zVbIN7ef4mu2fQ.z7qUC_mEVks_89Gc.z7uWReJpEuVFszY7TizwMAQJaKwaN-ZM1ZjnVpFgLN0Wt1E0OpGqSKZaHVJ8H_e_iBv_XXhp4P7svC-wqNCp1CBX_rJ5HPkdJJGaQZVI5TEhwV_UDxhh-_zk-NUWm9vVturxWerFlRCx7XKiZyMP5fWwmnAma544_0jRQCO6mzwMnu9VvTHa_RLGrJ4BCXFFCfcP12cx41sMulWYXA3WJGhTK6qxws5vr0xG_mlePm_CHCPJufDMu9YQm7R4ZtrGuJPoKwdPTRMYR1AWsUHv4HuY_EF-zwCMKJs2V1Jo1TbE7mpEwkId6Opdv7KZYjosV8e0Thw1k51JcfTgOrHzQW3jW_7DzwzHZPwWUIcgTUGhcoQMuggaqLEhhR3ybSAoXjtgWvcLsi6QAcngqlP7OjfEt2qzpeEnxt73IUKbPKmB6U32IAUJkMz-J5N-ceOvybUzFKrcI5CxCfMZ-xXqcdTBylll9rtCWk85KnIcAHr4CeKF8-9b6boPXrHRwKd5kqqRxtex-nyZmtXsvntzjYyaG8TxrPUZOlhyiIdjAu48eWzM4Yy6DPbh1T3lkQZpQxgEpu39bJiQ6iXrm1RNa5v3RqtgylRNmL3x1l8b99ukRfKjeW21h1lpkp6-mIGNT7egAX2ZdzQRWtrkGZa-zl7R10oENQTAtG7ILrpyJ2kWQYVgrVzt1AExnCjsd74VlyrtN8n6Z1Tf8rASB4rVvIwSvBCHc5qmBZMWZzyPi2vgmXEvoFXBh0mim2-uyC7710XU0cFmW5Xdv-v_YGzWwHDi6sk2jkoVqTjUvxHKV2oCTloPBqTuJKTQ5aEllB2sSamyn5D0zgzIopVfCGiVe9lEL5v1QW6o4gDzOBSlgBMkSXNzsbJtQD6jXdUw8Z2mgsfLwyGXq6QQ7ozW6JBaFzldgUtQ0QTWYoXyTJyAngMvFx8t_YBl9akjgwukZCqbuKBcOZpOUj7FEyUk8QCl0aFE_-GlgqwJNFQlfGpQ7YtHPG5c8Mn7uiU9_2ZLFyJv6bNOhfwS2rOXxjO03XqHKEilaq7yfJLlzz0A2bMU9aAhT1eC1sCbszLe8wBj-ZihrDLK2fkL-NLV5OpZTD4jyHCTk7G7bUGUBCEyO83dmTeN07Gkz4DCCUWlLV_ZDcKTNlyp_GfGlXbTkMUkJtsur9rnzQgMdQWJoMDUorJfdUbm01P6SwFZGDsAiceh-TeUH8TXlBsXH0MUEbnfrKb5Rm1zZEFOGJw-lRnCA-xMz-k6xIQF83fcm7iO4fZPGybPNa3ijvIpvBrWyqrKzPGXN1uBBCeFj8Ou0vmmXCGfptDtFehjcWetMbc-ZoAGU-Bf2pii29K8KE9dFpyHzoHlSdLqyOPDF0ZmITWgWsHzYvrr6Ke38n_9-awgvPLpnMHVQW4yu_c6jmnObOGWKt_LeyHMYRVMoZ1HUkkn_fL7VpxFp7Jij08jYaxAcTFZ-l8HQvnh8xpg-EXVv3gda-M8x5emU4l8-nMpDVkLRc7sg3Yz18PKd4ZwdT6SE9vn9uON9UJys4m8JKaAKoNSF77jxDJ0obg5LYsvaAwyjGvyIKrRsilAszWM-J1lrqpeezfG5T6aOGZINlkYQ_bDjcZ5Ye61DTnFFCM69rULPtvDwxjJcRrTuTe0-IcVPelbjzh5P_xMc4kiQgwgTG7W5CSfzYnm3WQj78D1J_hh1kjSOiBc_C_AGKNDas2P2ka4WPBQYpfbSewFxsTZfkYJRECRxd155oyiNw5g6AD45f4PWCTb7dtX2tWVNIpC-2eSzS4H-JnW76k7Vm8xgRxWDbtvsSPa_SDYeWA2KPOATwQwVcTDfWeZ94lcbavvlmQQuwWZ4Vrs4_emKtoz1n2f87Dnt4pbP-dZoN6lFCJjJ371590o9midvLVfsFqXgdoIdhEQHbWqo-BpOkQDYzn0b387gg6KT3MBzAD1ppkH8YnQfm8YJ1IxCoB-ITeA3RfwMNFmlZJeZezDT7r2XlnWoXXHTXn5ecN3VFyozOxfkLuCw62aMurT1J9xcYSePPGcIJcqh3BlvI3Iyf2ukMiiP9X7QpcRTwYomV77hgPkWT1-VnkqmDoe_315sGJtyuOG_A4mMs0mEpJSZ1L16awRfkyO52CwMLZIcZU7B1zsiOnRfEnnEgnWyqwrAJH_y2yG-b3-XfuSRfV-p-VO8E8LVoQnKE2ZkBkXJ99h6S7LlA_wnYnTwXxCM_Y3AbtBNPWjVGzxt0NLu8fpLJCu-0NTxdEiO679EgkXX5zhYxxpIQvQrR8CIlH0fHKfCgcH9wkoH0sfHcSPDOF9Hs9RMZGz9AGtIVEJTJkUbN10LEZTwm8xMC6OAQ4nBbm63z2S6a-r0wI5v5QuVifiToZR1i_2ldyfKuaXCm0nCrq7bj0JUnfBzIJf2TBWl2VAlAlz-wiengJZEEPxx0_y8h3z6GXSbDSzvVL0gJbZKpjXCUKh47kEwccM-JZNZ-rfUATCpqVuQIJzMfBjRpw3GPEonzi6rbG4LYqWQPYWlebBA2_fzi3_vD_kmg.NrEd49Nl4Jf8V-30Y9uRug";
			String decrpytedJWE = RsaOAepWithAesGcm.decryptWithRSAOAEP(response, KeyUtils.getPrivateKey(clientPrivateKey), clientKid);
			String decrpytedJWS = Rsa256.verifyJWSWithRS256(KeyUtils.getPublicKey(bankPublicKey), decrpytedJWE, bankKid);
			System.out.println("decrpytedJWS : " + decrpytedJWE);
			System.out.println("decrpytedResponse : " +decrpytedJWS);
			System.err.println("<---------- Response Decript and Verify Details End  ---------->");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//	@GetMapping("/response/decrypt")
	//	public void decryptResponse() {
	//		
	////		Consumer Privatekey
	//				String clientPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCwAutn1sooaFdqZwpJ5tz4Ky6CxKOG0CMWDkH0EE6MeGu1N8yjKMy/keZXz6+GEhph9LI3trHg8IWJseQXxgAFSLdAEO0V6T4dmBPRV3DYqvccuXtsR0wTGMFBB2jfJNYFjm6xtLMtjOZC4MjLcmxnu6goxTgHWCf5ISv10YEOLLpLFf9+OGPF+ZCKP0uPvqvf7F8H9ks5dUNP1Fnn6X2x1wvC+JyuYY+xMumlPpZnJHBlgU/pGnpAStPuV6DNPQH/8Uagm75AGexp8ICzkmmfd5x1NnWRnrZkS9LyoriILf+kiuzliOBSqZPqQr/R2uQIOVeDFRIIn36HttLFizT9AgMBAAECggEAU98AR7ndGQYnaYdyKqb88rC4+A01kx8JKRBgMym2FE+pm2/cbn8ZmcuvRHatwChONBFC/zNqbbKPH38KHp32Eo6E691k7ET5mWaHQwD0v/ovyJtGCeGriYoJRLCAM5y882bcrodZvkHe002DwRZkO8X7KnpX6YADhD5fUUKkyZiSHJYiUwz+OoSF4/HIrONCZys3FHdlQopxMThxzHZ9SW8v0KeZC55rKyXQHjFI4nWnHl4lkYCXk5umfhIAUunVYIaYi3RKElZ6+ZVsL3bteXBBVos0uhTOsuIyUUCWVzyjxwpgfNMY32ZxVkAsIoGnxkrvbaVskM/Qg8GwCS3Z7QKBgQDKB6MEMHi9OFzX/P2HoI8PobcsBvYafHS2H6KUPQJlmrPuCtoXWOpGr8mEmO5vDyPu7UmHpgr/EapKZjQtKAQIiYZHOl6n+hePvz9/vcOcvUGQ+YOyJFd/apuupeA8rIaU5Sdbqqj2mftIBOXFhmL0pKWzV2auicsp5k2oHn0d9wKBgQDfB/EAsB27KmyjoL67NwFAQ3DiHQ6SMNf1qxP7c8OPWS488eAftSKhmXvcjSRQEjIe2niXoQb2nEI7dgSX1HSPTUh4G3ZZucMAORIY0NxX1ivTC4TlZzdBiUjgSiU+F1tJXrbmGvcU5MOzyy8zHUb6L338WssitWWpWvOZgjEXqwKBgEYEWYnlJqMlen/cjR1JFuuq9ymKDBbn1ScdVaIcds0xzXQNTMJUrXTsDWIoAlJ5I9H1aMtbT2pQ+/ym6SRRPvyadPAQmaeLtEi8Vkx2Pun+aU/OOccucyiUkPn5QPqmLEpEFNzfC4nTmdO+5scLKRZyTX8XJl4NIV0XFQjT8DbHAoGBAM2CH8wme/7OpLRRle4kTP7OJeH0LE0AvL4eGZeFoBkib7ywk3gkC/kj2EHtvBwb6BsN7DirpCLw8QahRLIyjgTNjY0+oNsklE5MSCSdr6swKcmZv6cyaHP28DPbnl2MYA1HPPjit/GEqd+txZwvygsWX9x8PNmKlFZs/DHjCSFpAoGAOvOLVDLjENzoC5SgsJnhO2MyXEDecCSw3E1H64J9o3FXK8pSsq7r3yBQwETWDCTPO0HKXq6W9miBclos+ts/MHtZoFhVhj/bqXXEmNsQTU1EjEei0fSPGyAJsQH+Iqt9pcXo8fDCwnCaf+EWPy6RPLaEsqKdjRZ8dO3yZnUosHo=";
	////				Consumer Publickey
	//				String clientPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqHGHEslO8x6wKAKgvTFuhW4UbMustaVwdKXvUbaSC+eeaqEY0Qz3kw6aUmVfIH9SxI3v2+0VflfFelXdSc1yu2+ZPMckaT15xCxKmtE3sQ8rkH7U8xDTsTSALnGHCUOpepKATF2LXWykKSVeO6XLD+Hox51/WQClbZdXPpF8xt0HQGaUqoNenCy17kh3HKPwcflhyRmh+KCKJu19v/lTocMI5kL+dnBwLQTpQw5iyrBpmPM12y3H8PLAbUeHE1pcHCGkMYoM5eiymxywId9i1FCBcvly9j3dXJhykjFoUBA1dt/NWD6Px0O2esZ1sjYoacIrIeUKhhQL1FiRmDojIwIDAQAB";
	//				//	Banks PublicKey
	//				String bankPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtUlK8MdCzJb5ROqmfW6B/KnXsAhWaHM8JNV3XmY0yyzZw4QsQKaqGoAvujKSwQeS1Uq+uJGcRXvmoWrMlqWAcLeGxswGCCVptS/gu2JP/hQ+r3bo7Xv9Jb4KdVQN7IGJUt9BZ4lb9tWRjgseSTNxsicFUpVj68Xw+ZWYZXdhARm3TtkhYmNKuMstVe9rA7dTQdAj9D/MJFZ7r+axC9n0uj6M6I2QdS5EoV+Bvoerb669duen6dvgFBRJSp93dO0WpotJT+z9oeCbJEUIxgK/Td/mjUWgD0+DbR8KIkZ9OLCB2rFXH0UzkLCEpooWeGW7ZA8nmsU7/eQrPBcx3EdUxwIDAQAB";
	//
	//		String decrpytedJWE = RsaOAepWithAesGcm.decryptWithRSAOAEP(response.toString(), KeyUtils.getPrivateKey(clientPrivateKey), bankKid);
	//		String decrpytedJWS = Rsa256.verifyJWSWithRS256(KeyUtils.getPublicKey(bankPublicKey), decrpytedJWE, clientKid);
	//		System.out.println("decrpytedJWS : " + decrpytedJWE);
	//		System.out.println("decrpytedResponse : " +decrpytedJWS);
	//	}

	private JSONObject gererateJson() {
		try {
			JSONObject mandateDetails = new JSONObject();
			mandateDetails.put("LOGIN_ID", "APIUSER@CBXMGRT5");
			mandateDetails.put("INPUT_GCIF", "CBXMGRT5");
			mandateDetails.put("INPUT_DEBIT_ORG_ACC_NO", "00500340000261");
			mandateDetails.put("TRANSACTION_TYPE", "SINGLE");
			mandateDetails.put("INPUT_BUSINESS_PROD", "VENDBUS01");
			mandateDetails.put("PAYMENT_REF_NO", "TGA2APAYREF0004");
			mandateDetails.put("INPUT_VALUE_DATE", "08/04/2025");
			mandateDetails.put("TRANSFER_TYPE_DESC", "Intra Bank Transfer");
			mandateDetails.put("BENE_ID", "");
			mandateDetails.put("BENE_ACC_NAME", "SANTOSHRAI");
			mandateDetails.put("BENE_ACC_NO", "00600350101534");
			mandateDetails.put("BENE_IDN_CODE", "HDFC0000001");
			mandateDetails.put("INPUT_DEBIT_AMOUNT", "6000.00");
			mandateDetails.put("BENE_TYPE", "ADHOC");
			mandateDetails.put("BENE_BANK", "StateBankofIndia");
			mandateDetails.put("BENE_BRANCH", "GOVINDPURI");
			mandateDetails.put("ADDL_EMAIL", "");
			mandateDetails.put("PYMNT_CAT", "");
			mandateDetails.put("EMAIL_ADDR_VIEW", "");
			mandateDetails.put("PAYMENT_CATEGORY", "");
			mandateDetails.put("BENE_NARRATION", "");
			mandateDetails.put("LEI_CODE", "");
			mandateDetails.put("LEI_EXPIRY_DATE", "");
			mandateDetails.put("REMIT_NARRATION", "");
			mandateDetails.put("EXPIRY_DATE", "");
			mandateDetails.put("KYC", "");
			mandateDetails.put("ORG_REMIT_ACC_NO", "");
			mandateDetails.put("ORG_REMIT_ADDR", "");
			mandateDetails.put("ORG_REMIT_NAME", "");
			mandateDetails.put("ADDNL_FIELD_1", "");
			mandateDetails.put("ADDNL_FIELD_2", "");
			mandateDetails.put("ADDNL_FIELD_3", "");
			mandateDetails.put("ADDNL_FIELD_4", "");
			mandateDetails.put("ADDNL_FIELD_5", "");
			mandateDetails.put("IAT", "");
			mandateDetails.put("REMARKS", "");
			mandateDetails.put("Unknown tag 1", "");
			mandateDetails.put("Unknown tag 2", "");
			mandateDetails.put("Unknown tag 3", "");
			mandateDetails.put("Unknown tag 4", "");
			return mandateDetails;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	private JSONObject generatePaymentJson() {
		try {
			JSONObject mandateDetails = new JSONObject();
			mandateDetails.put("INPUT_GCIF", "CBXMGRT5");
			mandateDetails.put("LOGIN_ID", "APIUSER@CBXMGRT5");
			mandateDetails.put("FILTER1_VALUE_TXT", "Intra Bank Transfer");
			mandateDetails.put("CBX_API_REF_NO", "TGA2APAYREF0004");
			mandateDetails.put("TXNDATE", "2025-04-08");
			return mandateDetails;
		} catch (Exception e) {
			return  null;
		}
	}

	@GetMapping("/datechecking")
	public String Datefunction() {

		String date="12/11/2024";

		String InvoiceDate = date;
		LocalDate firstDay = LocalDate.now().withDayOfMonth(1);

		LocalDate fivdate = firstDay.plusDays(7);
		//		LocalDate fivdate = LocalDate.of(2025, 4, 03);
//		LocalDate SystemDate = LocalDate.now();
		LocalDate SystemDate =LocalDate.of(2025, 3, 30);
		fivdate.isAfter(SystemDate);
		int compareValue = fivdate.compareTo(SystemDate);
		System.err.println("compareValue : "+compareValue);
		if (compareValue < 0) {
			InvoiceDate = SystemDate.toString();
			System.out.println("checkmated today is earlier --" + SystemDate);
		}
		boolean result = fivdate.isAfter(SystemDate);
		System.err.println("My result  :"+result);

		return InvoiceDate;
	}
	
	@GetMapping("/backdatecheck")
	public static boolean BackDateChecking() {

		String dateToCheck="2025-04-08";

		Calendar aCalendar = Calendar.getInstance();
		aCalendar.set(Calendar.DATE, 1);
		aCalendar.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDateOfPreviousMonth = aCalendar.getTime();
		aCalendar.set(Calendar.DATE, 1);
		Date firstDateOfPreviousMonth = aCalendar.getTime();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = formatter.format(firstDateOfPreviousMonth);
		String endDate = formatter.format(lastDateOfPreviousMonth);

		boolean res = false;
		boolean res1 =false;
		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd"); // 2021-08-11
		SimpleDateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd"); // 22-05-2013
		try {
			Date requestDate = fmt2.parse(dateToCheck);
			Date fromDate = fmt1.parse(startDate);
			Date toDate = fmt1.parse(endDate);
			int n1=requestDate.compareTo(fromDate);
			int n2=requestDate.compareTo(toDate);
			res = requestDate.compareTo(fromDate) >= 0 && requestDate.compareTo(toDate) <= 0;
			res1 = (!requestDate.before(fromDate)) && (!requestDate.after(toDate));
			System.err.println("res "+res);
			System.err.println("res1 "+res1);
		} catch (ParseException pex) {
			pex.printStackTrace();
		}
		return res;
	}

	@GetMapping("/series")
	public void getNumberingSeries(boolean status, String date) throws ParseException {
		date="2025-03-21";

		LocalDate firstDay = LocalDate.now().withDayOfMonth(1);
		String NumeringSeriesType = "";
		SimpleDateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd");
		Date requestDate = fmt2.parse(date);

		LocalDate requestLocalDate = requestDate.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();

		Integer OrderMonth = requestDate.getMonth()+1;
		Integer CurrentMonth = firstDay.getMonthValue();


		//		System.err.println("Requested Date : "+requestLocalDate);
		LocalDate fivdate = firstDay.plusDays(7);
		//		System.err.println("fivdate : "+fivdate);	
//		LocalDate SystemDate = LocalDate.now();
		LocalDate SystemDate =LocalDate.of(2025, 4, 07);
		long daysBetween = ChronoUnit.DAYS.between(SystemDate, fivdate);
		//		int compareValue = fivdate.compareTo(SystemDate);
		//		System.err.println("Compare Value : "+compareValue);
		//		System.err.println("ChronoUnit difference: "+daysBetween);
		if (OrderMonth == CurrentMonth) {
			NumeringSeriesType = "SELECT MAX(n.\"Series\") AS \"Series\" ,n.\"SeriesName\" FROM NNM1 n WHERE \"BeginStr\" LIKE '%AFO/%' AND \"ObjectCode\" ='13' AND \"Indicator\" =(SELECT \"Indicator\" FROM OFPR WHERE CURRENT_DATE BETWEEN (\"F_RefDate\") AND (\"T_RefDate\")LIMIT 1)GROUP BY n.\"SeriesName\"\n";
			System.out.println("checkmated today is earlier --" + SystemDate);
						System.err.println("If Entering");
		} else if((OrderMonth < CurrentMonth) && (daysBetween <= 7) && (daysBetween>0) ) {
			NumeringSeriesType = "SELECT MAX(n.\"Series\") AS \"Series\" ,n.\"SeriesName\" FROM NNM1 n WHERE \"BeginStr\" LIKE '%AO/%' AND \"ObjectCode\" ='13' AND \"Indicator\" =(SELECT \"Indicator\" FROM OFPR WHERE CURRENT_DATE BETWEEN (\"F_RefDate\") AND (\"T_RefDate\")LIMIT 1)GROUP BY n.\"SeriesName\"\n";
						 System.err.println(" Else If Entering");
		} else {
			NumeringSeriesType = "SELECT MAX(n.\"Series\") AS \"Series\" ,n.\"SeriesName\" FROM NNM1 n WHERE \"BeginStr\" LIKE '%AFO/%' AND \"ObjectCode\" ='13' AND \"Indicator\" =(SELECT \"Indicator\" FROM OFPR WHERE CURRENT_DATE BETWEEN (\"F_RefDate\") AND (\"T_RefDate\")LIMIT 1)GROUP BY n.\"SeriesName\"\n";
						 System.err.println(" Else Entering");
		}

//		Map<String, Object> serices = jdbcTemplate.queryForMap(NumeringSeriesType);
//		Integer numberingSeries = (Integer) serices.get("Series");
//		return numberingSeries;
	}

}
