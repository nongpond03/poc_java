package com.ttb.example.demo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PemUtils {
	private static final Logger logger = LoggerFactory.getLogger(PemUtils.class);

	private static byte[] parsePEMFile(File pemFile) throws IOException {
		if (!pemFile.isFile() || !pemFile.exists()) {
			throw new FileNotFoundException(String.format("The file '%s' doesn't exist.", pemFile.getAbsolutePath()));
		}
		PemReader reader = new PemReader(new FileReader(pemFile));
		PemObject pemObject = reader.readPemObject();
		return pemObject.getContent();
	}
	public static byte[] parsePEMString(String pemFile) throws IOException {
      StringReader input = new StringReader(pemFile);

      PemReader reader = new PemReader(input);
      PemObject pemObject = reader.readPemObject();
      try {
      	reader.close();
      }catch (Exception e) {
      	e.printStackTrace();
		}
      return pemObject.getContent();
  }

	private static PublicKey getPublicKey(byte[] keyBytes, String algorithm) {
		PublicKey publicKey = null;
		try {
			KeyFactory kf = KeyFactory.getInstance(algorithm);
			EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			publicKey = kf.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			logger.error("Could not reconstruct the public key, the given algorithm could not be found.");
		} catch (InvalidKeySpecException e) {
			logger.error("Could not reconstruct the public key");
		}

		return publicKey;
	}

	public static PrivateKey getPrivateKey(byte[] keyBytes, String algorithm) {
		PrivateKey privateKey = null;
		try {
			KeyFactory kf = KeyFactory.getInstance(algorithm);
			EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
			privateKey = kf.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			logger.error("Could not reconstruct the private key, the given algorithm could not be found.");
		} catch (InvalidKeySpecException e) {
			logger.error("Could not reconstruct the private key");
		}

		return privateKey;
	}

	public static PublicKey readPublicKeyFromFile(String filepath, String algorithm) throws IOException {
		byte[] bytes = PemUtils.parsePEMFile(new File(filepath));
		return PemUtils.getPublicKey(bytes, algorithm);
	}

	public static PrivateKey readPrivateKeyFromFile(String filepath, String algorithm) throws IOException {
		byte[] bytes = PemUtils.parsePEMFile(new File(filepath));
		return PemUtils.getPrivateKey(bytes, algorithm);
	}

}
