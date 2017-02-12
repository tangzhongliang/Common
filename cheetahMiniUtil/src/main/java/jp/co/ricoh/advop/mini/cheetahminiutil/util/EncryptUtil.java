package jp.co.ricoh.advop.mini.cheetahminiutil.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import android.annotation.SuppressLint;
import android.util.Base64;

public class EncryptUtil {
	private static String encoding = "UTF-8";
	private static final String DES = "DES";
	private static final String DEFKEY = "~!@#$%^&";

	private EncryptUtil() {
	}

	/**
	 * Encrypt a string.
	 * @param key A String for decrypt,and it can be NULL,if so it will equals a default value which named {@link #DEFKEY}.The effective length is eight bits.
	 * @param input A string will be encrypted.
	 * @return An encrypted string.
	 */
	@SuppressLint("TrulyRandom")
	public static String encryptByDES(String key, String input) {
		if (input == null || input.equals("")) {
			return null;
		}
		if (key == null || key.equals("")) {
			key = DEFKEY;
		}
		String tmp = null;
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			SecretKey securekey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance(DES);
			cipher.init(Cipher.ENCRYPT_MODE, securekey, new SecureRandom());
			byte[] b = cipher.doFinal(input.getBytes());
			tmp = new String(Base64.encode(b, Base64.DEFAULT), encoding);
		} catch (Exception e) {
			LogC.e("Encrypt By DES Error : " + e.getMessage());
		}
		return tmp;
	}

	/**
	 * Decrypt an encrypted string
	 * @param key A String for decrypt,and it can be NULL,if so it will equals a default value which named {@link #DEFKEY}.The effective length is eight bits.
	 * @param password An encrypted string
	 * @return An decrypted string
	 */
	public static  String decryptByDES(String key, String password) {
		String tmp = null;
		if (key == null || key.equals("")) {
			key = DEFKEY;
		}
		if (password == null || password.equals("")) {
			return null;
		}
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			SecretKey securekey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance(DES);
			cipher.init(Cipher.DECRYPT_MODE, securekey, new SecureRandom());
			byte[] b = cipher.doFinal(Base64.decode(password.getBytes(), Base64.DEFAULT));
			tmp = new String(b, encoding);
		} catch (Exception e) {
			LogC.e("Decrypt By DES Error : " + e.getMessage());
		}
		return tmp;
	}
}
