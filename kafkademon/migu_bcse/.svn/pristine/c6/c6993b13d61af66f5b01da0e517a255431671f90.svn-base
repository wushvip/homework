package com.chinamobile.cmss.bcse.tool.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Quan
 * 
 */
public class Md5Util {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println(decrypt("0bfb1fd7dd2c1edddda4086de46db845"));

		System.out.println(encrypt("{appDescribe:测试鉴权,appName:cctest7,userId:26e508a621e04a2fa7b847af6d408f75}"));
	}

	/**
	 * 用户名解密
	 * 
	 * @param ssoToken
	 *            字符串
	 * @return String 返回加密字符串（32位小写）
	 */
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
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, e.getMessage());
		}
		return buf.toString();

	}
}
