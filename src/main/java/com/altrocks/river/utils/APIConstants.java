package com.altrocks.river.utils;

public class APIConstants {
	public class outgoingPayment {
		public static final String YY1_Outgoing_Payment_API = "api.s4hana.cloud.sap/sap/opu/odata/sap/YY1_OUTGOING_PAYMENT_API_CDS/YY1_Outgoing_Payment_API";

	}

	public class bankApi {
		public static final String corpPayment = "https://api-uat.hdfcbank.com/api/v1/corp-initiatePayment";
		public static final String paymentInq = "https://api-uat.hdfcbank.com/api/v1/corp-paymentInq";
		public static final String token = "https://api-uat.hdfcbank.com/auth/oauth/v1/token";

	}

	public class bankKey {
		public static final String bankPublicKey = "-----BEGIN PUBLIC KEY-----\r\n"
				+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtUlK8MdCzJb5ROqmfW6B\r\n"
				+ "/KnXsAhWaHM8JNV3XmY0yyzZw4QsQKaqGoAvujKSwQeS1Uq+uJGcRXvmoWrMlqWA\r\n"
				+ "cLeGxswGCCVptS/gu2JP/hQ+r3bo7Xv9Jb4KdVQN7IGJUt9BZ4lb9tWRjgseSTNx\r\n"
				+ "sicFUpVj68Xw+ZWYZXdhARm3TtkhYmNKuMstVe9rA7dTQdAj9D/MJFZ7r+axC9n0\r\n"
				+ "uj6M6I2QdS5EoV+Bvoerb669duen6dvgFBRJSp93dO0WpotJT+z9oeCbJEUIxgK/\r\n"
				+ "Td/mjUWgD0+DbR8KIkZ9OLCB2rFXH0UzkLCEpooWeGW7ZA8nmsU7/eQrPBcx3EdU\r\n" + "xwIDAQAB\r\n"
				+ "-----END PUBLIC KEY-----";
	}

	public class clientKey {
		public static final String clientPrivateKey = "-----BEGIN PRIVATE KEY-----\r\n"
				+ "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCwAutn1sooaFdq\r\n"
				+ "ZwpJ5tz4Ky6CxKOG0CMWDkH0EE6MeGu1N8yjKMy/keZXz6+GEhph9LI3trHg8IWJ\r\n"
				+ "seQXxgAFSLdAEO0V6T4dmBPRV3DYqvccuXtsR0wTGMFBB2jfJNYFjm6xtLMtjOZC\r\n"
				+ "4MjLcmxnu6goxTgHWCf5ISv10YEOLLpLFf9+OGPF+ZCKP0uPvqvf7F8H9ks5dUNP\r\n"
				+ "1Fnn6X2x1wvC+JyuYY+xMumlPpZnJHBlgU/pGnpAStPuV6DNPQH/8Uagm75AGexp\r\n"
				+ "8ICzkmmfd5x1NnWRnrZkS9LyoriILf+kiuzliOBSqZPqQr/R2uQIOVeDFRIIn36H\r\n"
				+ "ttLFizT9AgMBAAECggEAU98AR7ndGQYnaYdyKqb88rC4+A01kx8JKRBgMym2FE+p\r\n"
				+ "m2/cbn8ZmcuvRHatwChONBFC/zNqbbKPH38KHp32Eo6E691k7ET5mWaHQwD0v/ov\r\n"
				+ "yJtGCeGriYoJRLCAM5y882bcrodZvkHe002DwRZkO8X7KnpX6YADhD5fUUKkyZiS\r\n"
				+ "HJYiUwz+OoSF4/HIrONCZys3FHdlQopxMThxzHZ9SW8v0KeZC55rKyXQHjFI4nWn\r\n"
				+ "Hl4lkYCXk5umfhIAUunVYIaYi3RKElZ6+ZVsL3bteXBBVos0uhTOsuIyUUCWVzyj\r\n"
				+ "xwpgfNMY32ZxVkAsIoGnxkrvbaVskM/Qg8GwCS3Z7QKBgQDKB6MEMHi9OFzX/P2H\r\n"
				+ "oI8PobcsBvYafHS2H6KUPQJlmrPuCtoXWOpGr8mEmO5vDyPu7UmHpgr/EapKZjQt\r\n"
				+ "KAQIiYZHOl6n+hePvz9/vcOcvUGQ+YOyJFd/apuupeA8rIaU5Sdbqqj2mftIBOXF\r\n"
				+ "hmL0pKWzV2auicsp5k2oHn0d9wKBgQDfB/EAsB27KmyjoL67NwFAQ3DiHQ6SMNf1\r\n"
				+ "qxP7c8OPWS488eAftSKhmXvcjSRQEjIe2niXoQb2nEI7dgSX1HSPTUh4G3ZZucMA\r\n"
				+ "ORIY0NxX1ivTC4TlZzdBiUjgSiU+F1tJXrbmGvcU5MOzyy8zHUb6L338WssitWWp\r\n"
				+ "WvOZgjEXqwKBgEYEWYnlJqMlen/cjR1JFuuq9ymKDBbn1ScdVaIcds0xzXQNTMJU\r\n"
				+ "rXTsDWIoAlJ5I9H1aMtbT2pQ+/ym6SRRPvyadPAQmaeLtEi8Vkx2Pun+aU/OOccu\r\n"
				+ "cyiUkPn5QPqmLEpEFNzfC4nTmdO+5scLKRZyTX8XJl4NIV0XFQjT8DbHAoGBAM2C\r\n"
				+ "H8wme/7OpLRRle4kTP7OJeH0LE0AvL4eGZeFoBkib7ywk3gkC/kj2EHtvBwb6BsN\r\n"
				+ "7DirpCLw8QahRLIyjgTNjY0+oNsklE5MSCSdr6swKcmZv6cyaHP28DPbnl2MYA1H\r\n"
				+ "PPjit/GEqd+txZwvygsWX9x8PNmKlFZs/DHjCSFpAoGAOvOLVDLjENzoC5SgsJnh\r\n"
				+ "O2MyXEDecCSw3E1H64J9o3FXK8pSsq7r3yBQwETWDCTPO0HKXq6W9miBclos+ts/\r\n"
				+ "MHtZoFhVhj/bqXXEmNsQTU1EjEei0fSPGyAJsQH+Iqt9pcXo8fDCwnCaf+EWPy6R\r\n"
				+ "PLaEsqKdjRZ8dO3yZnUosHo=\r\n" + "-----END PRIVATE KEY-----\r\n" + "";

		public static final String clientPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsALrZ9bKKGhXamcKSebc+CsugsSjhtAjFg5B9BBOjHhrtTfMoyjMv5HmV8+vhhIaYfSyN7ax4PCFibHkF8YABUi3QBDtFek+HZgT0Vdw2Kr3HLl7bEdMExjBQQdo3yTWBY5usbSzLYzmQuDIy3JsZ7uoKMU4B1gn+SEr9dGBDiy6SxX/fjhjxfmQij9Lj76r3+xfB/ZLOXVDT9RZ5+l9sdcLwvicrmGPsTLppT6WZyRwZYFP6Rp6QErT7legzT0B//FGoJu+QBnsafCAs5Jpn3ecdTZ1kZ62ZEvS8qK4iC3/pIrs5YjgUqmT6kK/0drkCDlXgxUSCJ9+h7bSxYs0/QIDAQAB";
	}
}
