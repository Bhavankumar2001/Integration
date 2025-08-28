package com.altrocks.river.hdfc.outgoingpayment;

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
	public static PrivateKey getPrivateKey(final String base64PrivateKey) {
		PrivateKey privateKey = null;
		final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
				Base64.getDecoder().decode(base64PrivateKey.getBytes()));
		KeyFactory keyFactory = null;
		try {
			keyFactory = KeyFactory.getInstance(RSA);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.log(Level.INFO, "Exception occur 1", e);
		}
		try {
			privateKey = keyFactory.generatePrivate(keySpec);
		} catch (InvalidKeySpecException e) {
			LOGGER.log(Level.INFO, "Exception occur 2", e);
		}
		return privateKey;
	}

	/** getPublicKey method */
	public static PublicKey getPublicKey(final String base64PublicKey) {
		PublicKey publicKey = null;
		try {
			final X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
					Base64.getDecoder().decode(base64PublicKey.getBytes()));
			final KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			publicKey = keyFactory.generatePublic(keySpec);
			return publicKey;
		} catch (NoSuchAlgorithmException e) {
			LOGGER.log(Level.INFO, "Exception occur 1", e);
		} catch (InvalidKeySpecException e) {
			LOGGER.log(Level.INFO, "Exception occur 2", e);
		}
		return publicKey;
	}
	public static String generateKid(PublicKey publickey) throws NoSuchAlgorithmException {
		byte[] publicKeybytes = publickey.getEncoded();
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		String kid = Base64.getUrlEncoder().withoutPadding().encodeToString(digest.digest(publicKeybytes));
		return kid;
	}
}
