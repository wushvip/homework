package com.chinamobile.cmss.bcse.index.tool;

import java.security.MessageDigest;

/**
 * 
 * @ClassName: MD5
 * @Description: 对字符串进行MD5编码
 * @author: jinjing
 * @date: 2016年2月16日 下午3:22:08
 */
public class MD5 {

	/**
	 * 
	 * @Title: getMD5
	 * @Description: 入口
	 * @param s
	 * @return
	 * @return: String
	 */
	public static String getMD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
