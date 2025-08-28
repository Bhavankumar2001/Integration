//package com.hdfcbank.jose;
//
//import java.security.NoSuchAlgorithmException;
//
//import org.jose4j.lang.JoseException;
//
//public class GenerateJoseMain {
//
//	public static void main(String[] args) throws NoSuchAlgorithmException, JoseException {
//		// TODO Auto-generated method stub
//		
////		Request Content
//		String content = "{\r\n"
//				+ "    \"BANK\" : \"HDFC\"\r\n"
//				+ "}";
////		Consumer Privatekey
//		String clientPrivateKey = "";
////		Banks PublicKey
//		String bankPublicKey = "";
////		Consumer Publickey
//		String clientPublicKey = "";
////		get client's publickey to generate kid to add in JWS headers
//		String clientKid = KeyUtils.generateKid(KeyUtils.getPublicKey(clientPublicKey));
////		get bank's publickey to generate kid to add in JWE headers
//		String bankKid = KeyUtils.generateKid(KeyUtils.getPublicKey(bankPublicKey));
//		String signature = Rsa256.generateJWSWithRS256(content,KeyUtils.getPrivateKey(clientPrivateKey),clientKid);
//		String EncrpytedJWT = RsaOAepWithAesGcm.encryptWithRSAOAEP(signature, KeyUtils.getPublicKey(bankPublicKey), bankKid);
//		System.out.println("JWS : "+signature);
//		System.out.println("JWE : "+EncrpytedJWT);
//	}
//
//}


