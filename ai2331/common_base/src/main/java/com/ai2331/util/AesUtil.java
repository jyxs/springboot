package com.ai2331.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {

	public static String encrypt(String content, String key) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(genSecretKey(key), "AES");// 转换为AES专用密钥
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");

			cipher.init(Cipher.ENCRYPT_MODE, keySpec);// 初始化为加密模式的密码器

			byte[] result = cipher.doFinal(byteContent);// 加密
			return new String(Base64.getEncoder().encode(result));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static String decrypt(String content, String key) {
		try {
			byte[] decode = Base64.getDecoder().decode(content.getBytes());
			SecretKeySpec keySpec = new SecretKeySpec(genSecretKey(key), "AES");// 转换为AES专用密钥
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, keySpec);// 初始化为加密模式的密码器
			byte[] result = cipher.doFinal(decode);// 解密
			return new String(result);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	private static byte[] genSecretKey(String key) throws NoSuchAlgorithmException {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
		kgen.init(128, new SecureRandom(key.getBytes()));// 利用用户密码作为随机数初始化出
		SecretKey secretKey = kgen.generateKey();// 根据key，生成一个密钥
		return secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
	}

	public static void main(String[] args) {
		String key = "123";
		String content = "abc";
		String encode = encrypt(content, key);
		System.out.println(encode);
		String decode = decrypt(encode, key);
		System.out.println(decode);

	}

}
