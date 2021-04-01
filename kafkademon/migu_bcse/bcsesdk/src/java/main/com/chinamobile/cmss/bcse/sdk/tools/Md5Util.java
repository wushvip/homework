package com.chinamobile.cmss.bcse.sdk.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.plaf.synth.SynthSpinnerUI;



/**
 * @author Quan
 * 
 */
public class Md5Util {

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		System.out.println(decrypt("76%77%78%79%80%81%"));

		System.out.println(encrypt("admin"));
		
		
	}*/

	/**
	 * 用户名解密
	 * 
	 * @param ssoToken
	 *            字符串
	 * @return String 返回加密字符串
	 */
	public static String decrypt(String ssoToken) {
		try {
			String name = new String();
			java.util.StringTokenizer st = new java.util.StringTokenizer(
					ssoToken, "%");
			while (st.hasMoreElements()) {
				int asc = Integer.parseInt((String) st.nextElement()) - 27;
				name = name + (char) asc;
			}

			return name;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 用户名加密
	 * 
	 * @param ssoToken
	 *            字符串
	 * @return String 返回加密字符串
	 */
	/*public static String encrypt(String ssoToken) {
		try {
			byte[] _ssoToken = ssoToken.getBytes("ISO-8859-1");
			String name = new String();
			// char[] _ssoToken = ssoToken.toCharArray();cd bin
			for (int i = 0; i < _ssoToken.length; i++) {
				int asc = _ssoToken[i];
				_ssoToken[i] = (byte) (asc + 27);
				name = name + (asc + 27) + "%";
			}
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
	public static String encrypt(String ssoToken) {

		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
//			md.update(ssoToken.getBytes());
			md.update(ssoToken.getBytes("UTF-8"));
			byte b[] = md.digest();

			int i;

			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return buf.toString();

	}
	
	
}
