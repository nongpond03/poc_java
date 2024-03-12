package com.ttb.example.demo;

import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ttb.example.demo.util.PemUtils;

public class DemoGenerateJWTToken {
	public static void main(String args[]) throws IOException {
		
		String privateKeyStr = "-----BEGIN PRIVATE KEY-----\r\n"
				+ "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC94eqB0qI29jwL\r\n"
				+ "H5KcxdFqNOyMigGQdxraTjLcIi7kO2ioorXQ2xZyDGFuBujeV1k2hmIIEPgITGtE\r\n"
				+ "7dYZtLHWmBStUlL4uQ50S1PHyBVIxHgu0PWsT/9GFzp1q/uNNG5+btRlSjf4ihHT\r\n"
				+ "A2Rg4YiMOog2AnU0YGYITzH+xUIPL0v/DNgUikL2D+VKdz2fd0lWpZdbKWDtWIXo\r\n"
				+ "sVNAlk6bZaASXO9FjNV4uFqgVgRXOGp4EY1yJLWC/o+kIORFUcaAP77rSpaxfjZF\r\n"
				+ "rULkeA/cGvKxPd1YFSv6Onrlcsoap7hmCKL1sNk47V6y8AboBxufTrRdzskb1AfQ\r\n"
				+ "DWfCnnf7AgMBAAECggEABNOzZG14VjFYQWgiD/4vXqXNxZzUgVmlwxrtzA7KZl7k\r\n"
				+ "QDUyrgaDwY5zA5sFXHGYAPzVjSLxSzS2HWB2atzfEHd5PxayqUP7DBXGIXQCpoDo\r\n"
				+ "FTiWpjS6Zou8nrUqheJpVKl4mMKUPas03MpfSN8y2225fLX/SL+SmG968+EL1trv\r\n"
				+ "t26ZU1sWKeZQVyLoPxgUVetYKRl1q4qbWV3AOgMhgPSw6bUtSGpQLrVx+1W7eZNz\r\n"
				+ "kU/ixX7hLPy6g2GyMAmfurv13whJyDx9YRsnu4wY1CTd6H47wFXbK+NlZKMvefZx\r\n"
				+ "5lhwU3Ko5Kf/aOKur59DoDfUHqSakYZ6/o/x3/HYwQKBgQDpQy0vxc8139fnHL+q\r\n"
				+ "tfR0G8TW28oBa/WfnwREn958mTP+7nYjl8M8w7osiDHS1IaK3UVfnXCcukGCItZf\r\n"
				+ "DLW06KOpWHb8trB+wOGU7JJaEty1W0ul1TIFmC3RhrLEZVNNQoO8h/v+Er8/r1ZM\r\n"
				+ "AJxTSdcjAeHrAGX+VhbkQZd9cQKBgQDQZDz3mpN69PRYTwYJOzV5peAgAbxfqK5d\r\n"
				+ "OdVhBtkV6eKq6c3AEDFhO1mn+0wFXMBzAJr+mSLv7j8gisk0IPmH20S2Jp/Yl83G\r\n"
				+ "o8DLXQhhPDqLL9D9CFNmCcK1CxHAsQoRR2yEt56tJ+MfzOIETq9uXifrVQ2QpFWt\r\n"
				+ "u+SfLhfGKwKBgQCZ58njsGdUi5nfkldgImflSAErJp91Kq9In1nokXbxzP/Cn8Hx\r\n"
				+ "IAwghp/LDB2blsAi1XYVbULFFhts6uF4m2E0uR5q+MShX0S6jTcJ1MGHjtRzEIas\r\n"
				+ "Jyu3Bz6L9Yz0CkrZOh7DgRGiBOqM8XpF8U0zow/AuFhAfuXM1y6JckGq8QKBgQDF\r\n"
				+ "mxeY7zIEOT1uc9jUbhnCNcG2eA7pYqmc61dkPzshJXxz92rMQk6g1cepm4i12QMA\r\n"
				+ "ykUvQYU4oOGpeRFb5hzYb9rTIwMWPrS5NNR/wsNMePFGxNvW5Ms4ie7hABMgdoCG\r\n"
				+ "bAUCQKAtsBIWOJDl5rtB2/TcmfVYAAzixrX/H5UZqwKBgQCUZkMfq+ajtS8Hw1gH\r\n"
				+ "WR0YedtknPHaIEIqudXZ1DPBd/+YET+eI9BGl4KLvwcUuDy82VFsmG8o13E5Mpjt\r\n"
				+ "EPN8eLD+M9R1kOXrrqoICMKVyqtRZQyETCIIOBD0KtAOfmNKUCBBxcElMPd00xVu\r\n"
				+ "7ZOyNFNiXR0p2J61s3l5d/KIuw==\r\n"
				+ "-----END PRIVATE KEY-----\r\n"
				+ "";
		
		byte[] privateKeyByte = PemUtils.parsePEMString(privateKeyStr);
		RSAPrivateKey rsaPrivateKey =  (RSAPrivateKey) PemUtils.getPrivateKey(privateKeyByte, "RSA"); 
	    Algorithm algorithm = Algorithm.RSA256(null, rsaPrivateKey);
	    
	    long expireTime = (new Date().getTime()) + (60 * 1000); // 1 minute 
	    Date expireDate = new Date(expireTime);
	    
	    String generatedToken = JWT.create().withIssuer("jwt-key-1").withExpiresAt(expireDate)
				.sign(algorithm);
	    
	    System.out.println(" ===> "+generatedToken);
	    
	    /*
	     * put token to http header Authorization: Bearer + token
	     *  
	     */
	}
}
