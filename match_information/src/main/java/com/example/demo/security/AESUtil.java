package com.example.demo.security;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

@Service
public class AESUtil {

    public SecretKey generateAESKey(String key) {
        return new SecretKeySpec(key.getBytes(), "AES");
    }

    public IvParameterSpec generateIvParameterSpec(SecretKey secretKey) {
        String s = new String(secretKey.getEncoded(), StandardCharsets.UTF_8);
        return new IvParameterSpec(s.substring(0, 16).getBytes());
    }

    public byte[] encrypt(String data, SecretKey aesKey, IvParameterSpec ivParameterSpec) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey, ivParameterSpec);
            return cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        } catch (GeneralSecurityException ex) {
            throw new CryptographyFailureException("AES Encryption Error", ex);
        }
    }

    public byte[] decrypt(byte[] data, SecretKey aesKey, IvParameterSpec ivParameterSpec) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, aesKey, ivParameterSpec);
            return cipher.doFinal(data);
        } catch (GeneralSecurityException ex) {
            throw new CryptographyFailureException("AES Decryption Error", ex);
        }
    }

    public byte[] encryptWithoutIv(String data, SecretKey aesKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            return cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        } catch (GeneralSecurityException ex) {
            throw new CryptographyFailureException("AES Encryption Error", ex);
        }
    }

    public byte[] decryptWithoutIv(byte[] data, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (GeneralSecurityException ex) {
            throw new CryptographyFailureException("AES Decryption Error", ex);
        }
    }
}
