package com.altrocks.river.trail.hdfc;

import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyUtils {

	/** declaring LOGGER variable */
	private final static Logger LOGGER = Logger.getLogger(KeyUtils.class.getName());
	/** declaring RSA instance variable */
	private final static String RSA = "RSA";

	/** getPrivateKey method */
//	public static PrivateKey getPrivateKey(final String base64PrivateKey) {
//		PrivateKey privateKey = null;
//		final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
//				Base64.getDecoder().decode(base64PrivateKey.getBytes()));
//		KeyFactory keyFactory = null;
//		try {
//			keyFactory = KeyFactory.getInstance(RSA);
//		} catch (NoSuchAlgorithmException e) {
//			LOGGER.log(Level.INFO, "Exception occur 1", e);
//		}
//		try {
//			privateKey = keyFactory.generatePrivate(keySpec);
//		} catch (InvalidKeySpecException e) {
//			LOGGER.log(Level.INFO, "Exception occur 2", e);
//		}
//		return privateKey;
//	}

	public static PrivateKey getPrivateKey(final String base64PrivateKey) {
		try {
			// Clean up PEM headers and whitespace
			String cleanedKey = base64PrivateKey.replace("-----BEGIN PRIVATE KEY-----", "")
					.replace("-----END PRIVATE KEY-----", "").replaceAll("\\s+", ""); // Remove all whitespace (spaces,
																						// newlines, tabs)

			// Decode the base64 string
			byte[] keyBytes = Base64.getDecoder().decode(cleanedKey);

			// Generate the private key from decoded bytes
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			System.out.println(keyFactory.generatePrivate(keySpec));
			return keyFactory.generatePrivate(keySpec);
		} catch (Exception e) {
			throw new RuntimeException("Error while generating private key from string", e);
		}
	}

	/** getPublicKey method */
	public static PublicKey getPublicKey(final String base64PublicKey) {
		try {
			// Clean the PEM headers/footers and remove whitespace
			String cleanedKey = base64PublicKey.replace("-----BEGIN PUBLIC KEY-----", "")
					.replace("-----END PUBLIC KEY-----", "").replaceAll("\\s+", ""); // Remove spaces, line breaks, tabs

			// Decode the base64 key
			byte[] keyBytes = Base64.getDecoder().decode(cleanedKey);

			// Generate PublicKey from decoded bytes
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");

			return keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			throw new RuntimeException("Error while generating public key from string", e);
		}
	}

	public static String generateKid(PublicKey publickey) throws NoSuchAlgorithmException {
		byte[] publicKeybytes = publickey.getEncoded();
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		String kid = Base64.getUrlEncoder().withoutPadding().encodeToString(digest.digest(publicKeybytes));
		return kid;
	}
}
