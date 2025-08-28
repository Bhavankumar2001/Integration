package com.altrocks.river.hdfc.outgoingpayment;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.lang.JoseException;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.lang.JoseException;




/** Class with JWE Encrption with RSAOAEP */
public class RsaOAepWithAesGcm {

	/** declaring RSA-OAEP-256 static variable */
	private final static String RSA_OAEP_256 = "RSA-OAEP-256";
	/** declaring A256GCM variable */
	private final static String AES_GCM = "A256GCM";

	/**
	 * decrypt JWE encryption with RSA_OAEP_256 Algorithm
	 */
	public static String decryptWithRSAOAEP(final String jwe, final PrivateKey privatekey, final String kid) {
		try {
			final JsonWebEncryption receiverJwe = new JsonWebEncryption();
			receiverJwe.setAlgorithmConstraints(
					new AlgorithmConstraints(ConstraintType.PERMIT, KeyManagementAlgorithmIdentifiers.RSA_OAEP_256));
			receiverJwe.setContentEncryptionAlgorithmConstraints(
					new AlgorithmConstraints(ConstraintType.PERMIT, ContentEncryptionAlgorithmIdentifiers.AES_256_GCM));
			receiverJwe.setKey(privatekey);
			receiverJwe.setCompactSerialization(jwe);
			System.out.println(receiverJwe.getKeyIdHeaderValue());
			System.out.println(kid);
			if(!receiverJwe.getKeyIdHeaderValue().equals(kid)) {
				throw new IllegalArgumentException("Invalid Kid at JWE");
			}
			return receiverJwe.getPayload();
		} catch (Exception je) {
			throw new IllegalArgumentException("JWEDecryptionFailed : "+je.getMessage());
		}
	}

	/**
	 * JWE encryption with RSA_OAEP_256 Algorithm
	 * @throws NoSuchAlgorithmException 
	 */
	public static String encryptWithRSAOAEP(final String plainText, final PublicKey publickey,final String kid) throws JoseException, NoSuchAlgorithmException {
		final JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setPayload(plainText);
		jwe.setAlgorithmHeaderValue(RSA_OAEP_256);
		jwe.setEncryptionMethodHeaderParameter(AES_GCM);
		jwe.setHeader("typ", "JWE");
		jwe.setKeyIdHeaderValue(kid);
		jwe.setKey(publickey);
		return jwe.getCompactSerialization();
	}
}
