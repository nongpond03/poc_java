package com.ttb.example.demo;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DemoAESEncryption {
	public static void main(String args[]) throws Exception {
		
		String aesKey = "Is8mgZes5h11q9eSBLB3mrKnSRG/ej7lLU7i9gJgwww=";
		SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(aesKey), "AES");
		String jsonMessage = "{\r\n"
				+ "   \"unique_transaction_id\":\"22222023061900001\",\r\n"
				+ "   \"company_code\":\"2553\",\r\n"
				+ "   \"payer_name\":\"Look Mhee\",\r\n"
				+ "   \"debtor\":{\r\n"
				+ "      \"first_name_th\":\"แพรว\",\r\n"
				+ "      \"last_name_th\":\"ดีใจ\",\r\n"
				+ "      \"first_name_en\":\"PLEAW\",\r\n"
				+ "      \"last_name_en\":\"DEEJAI\",\r\n"
				+ "      \"id_type\":\"CI\",\r\n"
				+ "      \"id_value\":\"5998043166652\",\r\n"
				+ "      \"dob\":\"31051979\",\r\n"
				+ "      \"country_code\":\"TH\",\r\n"
				+ "      \"address_1\":\"ทดสอบ\",\r\n"
				+ "      \"address_2\":\"\",\r\n"
				+ "      \"address_3\":\"\",\r\n"
				+ "      \"address_4\":\"\",\r\n"
				+ "      \"city\":\"\",\r\n"
				+ "      \"country_income_source\":\"CA\",\r\n"
				+ "      \"national\":\"CA\",\r\n"
				+ "      \"country_of_birth\":\"TH\"\r\n"
				+ "      },\r\n"
				+ "\"agreement\":{\r\n"
				+ "     \"comp_id\":\"2553\",\r\n"
				+ "     \"ref_1\":\"123456\",\r\n"
				+ "     \"ref_2\":\"\"\r\n"
				+ "},\r\n"
				+ "   \"payment_info\":{\r\n"
				+ "      \"debit_account_number\":\"4662080383\",\r\n"
				+ "      \"payment_amount\":\"100.21\",\r\n"
				+ "      \"branch_code\":\"\",\r\n"
				+ "      \"company_account_number\":\"0019771609\",\r\n"
				+ "      \"charge_type\":\"OUR\"\r\n"
				+ "   },\r\n"
				+ "   \"transaction_purpose_code\":\"60\",\r\n"
				+ "   \"customer_reference\":\"\",\r\n"
				+ "   \"transaction_datetime\":\"2023-07-07T10:07:17+07:00\",\r\n"
				+ "   \"vendor_code_or_id\":\"\",\r\n"
				+ "   \"payment_details\":\"\"\r\n"
				+ "}";
		String encryptedMessage = encryptWithAES(jsonMessage,secretKey);
		System.out.println(" encryptedMessage ===> "+encryptedMessage);
		/*
		 * { "data": encryptedMessage } => send to API
		 */
		
		String decryptedMessage = decryptWithAES(Base64.getDecoder().decode(encryptedMessage),secretKey);

		System.out.println(" decryptedMessage ===> "+decryptedMessage);

	    
	}
	
	public static String encryptWithAES(String plaintext, SecretKey secretKey) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] res = cipher.doFinal(plaintext.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(res);
	}

	public static String decryptWithAES(byte[] encryptedData, SecretKey secretKey) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedBytes = cipher.doFinal(encryptedData);
		return new String(decryptedBytes, "UTF-8");
	}
}
