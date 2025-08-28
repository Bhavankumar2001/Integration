package com.altrocks.river.hdfc.outgoingpayment;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;

/** Class with JWS Signature with RS256 */
public class Rsa256 {
	
	/** declaring verifyJWSWithRS256 method 
	 * @throws JoseException 
	 * @throws NoSuchAlgorithmException */
	public static String verifyJWSWithRS256(final PublicKey publicKey, final String encryptedContent, final String kid) throws JoseException, NoSuchAlgorithmException {
		final JsonWebSignature jws = new JsonWebSignature();
		jws.setAlgorithmConstraints(
				new AlgorithmConstraints(ConstraintType.PERMIT, AlgorithmIdentifiers.RSA_USING_SHA256));
		jws.setCompactSerialization(encryptedContent);
		jws.setKey(publicKey);
		final boolean signatureVerified = jws.verifySignature();
		if(!jws.getKeyIdHeaderValue().equals(kid)) {
			throw new IllegalArgumentException("Signature Failed : Invalid Kid at JWS");
		}
		if (signatureVerified) {
			return jws.getPayload();
		} else {
			throw new IllegalArgumentException("Signature Failed");
		}

	}
	/** generateJWSSignatureWithRS256  
	 * @throws NoSuchAlgorithmException */
	public static String generateJWSWithRS256(final String jweEncContent,final PrivateKey privateKey, String kid) throws JoseException, NoSuchAlgorithmException {
		final JsonWebSignature jws = new JsonWebSignature();
		jws.setPayload(jweEncContent);
		jws.setHeader("typ", "JWT");
		jws.setKeyIdHeaderValue(kid);
		jws.setKey(privateKey);
		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
		return jws.getCompactSerialization();
	}
	
}