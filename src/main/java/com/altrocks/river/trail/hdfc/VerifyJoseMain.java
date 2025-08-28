//package com.hdfcbank.jose;
//
//import java.security.NoSuchAlgorithmException;
//
//import org.jose4j.lang.JoseException;
//
//public class VerifyJoseMain {
//
//	public static void main(String[] args) throws NoSuchAlgorithmException, JoseException {
//		// TODO Auto-generated method stub
////		Encrypted JWE Content
//		String EncrpytedContent="eyJhbGciOiJSU0EtT0FFUC0yNTYiLCJlbmMiOiJBMjU2R0NNIiwidHlwIjoiSldFIiwia2lkIjoiLUlMdXBjemFCUGxnUEdTVzF1VW84RldLM3d3N3ByWG9FcC11LTdLMHA3VSJ9.nl1-WxPSSJN1pdE-azijIgUYM6mN3ceQRwJzgxi7El8HqBtjR5QKEjQSphKOCYFwDeKtjEGZQHZE8NpcTPORn4eDwYDWnt6ZXp-1n4qeIRCPK-nl1HdhvMmzp9BkldHTf43Ht-1CthuHDVW820-zT1SQqvukQkWNMUySC6XrR1A47T8eBZq6e44fyUDLfH6s9MX6lS08imiYuSph972mWJptLoufEpzoqgwgkEwo3m55XgGzMxvIrOloHAqlDzmjheMEagT9oXatlKPrjPbZza_0uuSp0aEsFJDfV4jb4GvSm_AaBGhVh1OXxg9weRiChZbD9wiXiwns3NlYeXLhhw.Jv83pOwW2Fiz7Bsl.er2Vg83SaIeUqbRJAU0qA1HzHMmQmEP0_8pgyAZDHME3jbvFGr1o7IuCUJQWXqpa_x9M6QYVVX350WDC096pb-IKrgWUW12OUBQjf-cE54TzDKZdHGtida06n7Vs70WGn8vDMBiS28rx_oXqqix04fOX-ySic9cPqvbuUqNOi3YMS_UlKt7c92khq3dhGa3E3jMA-gAZPxXUf-Kqc3V9c-scw5e0uSP5cGehZ3iTwrbRZCPRKqn7bdWWQT-h96dAjeSUNENiRFYiebuQkUUQ84ddh4l_wObejoeo5UT8NGHcVxOUB_dRkdnzTHDBKtYGC2bZn2UzPq3j-O_m8EMOjo7C9X_CHtkpIQkd6bDCtkvSz4A0fNWmVrzKtF32qNq4Tsr9Zrr-LMu5zk7cxWvWbmhI9oytZrrqvevnE1TuSsTmIvvUW43zMrpFwYNy432ofpLlQ_Wh9VmiKvwzn8YFkuuBb5gfIpq3JsAHnFzp9g4OjfhZPQPnp5HRPjHHs2DCLbhROiFPtJczYd24JDkFAAglRFO-syeXDvMlFtC2y1xEa76MMCuZWmfi09lqaC9a-jjsmWEaoK7iebVLTNzWFTx_upU65YqgvnvKS34uv-4JR-WQrVMcCAkV_goKsHV_wlwirA.x_rkf89-2ntFH-oQEGpd8Q";
//		String bankPrivateKey = "";
//		String bankPublicKey = "";
////		Consumer Publickey
//		String clientPublicKey = "";
////		get client's publickey to generate kid to verify JWE Content
//		String clientKid = KeyUtils.generateKid(KeyUtils.getPublicKey(clientPublicKey));
////		get bank's publickey to generate kid to verify JWS Content
//		String bankKid = KeyUtils.generateKid(KeyUtils.getPublicKey(bankPublicKey));
//		String decrpytedJWE = RsaOAepWithAesGcm.decryptWithRSAOAEP(EncrpytedContent, KeyUtils.getPrivateKey(bankPrivateKey), bankKid);
//		String decrpytedJWS = Rsa256.verifyJWSWithRS256(KeyUtils.getPublicKey(clientPublicKey), decrpytedJWE, clientKid);
//		System.out.println("decrpytedJWS : " + decrpytedJWE);
//		System.out.println("decrpytedResponse : " +decrpytedJWS);
//	}
//
//}


