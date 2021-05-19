package com.zero.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class IOUtils {
	
	//读取文本，返回字符串
	public static String ReadFromLocal(File file,String encode) throws IOException{
		BufferedReader bw=new BufferedReader(new FileReader(file));
		StringBuffer sb=new StringBuffer();
		String line=null;
		while((line=bw.readLine())!=null){
			sb.append(line).append("\n");
		}
		return transcoding(sb.toString(), encode);
	}
	
	//读取文本，返回字符串
		public static String ReadFromLocal(File file) throws IOException{
			return ReadFromLocal(file,"UTF-8");
		}
	
	/**
	 * 将字符串转换成指定编码格式
	 * 
	 * @param str
	 * @param encode
	 * @return
	 */
	public static String transcoding(String str, String encode) {
		String df = "ISO-8859-1";
		try {
			String en = getEncode(str);
			if (en == null)
				en = df;
			return new String(str.getBytes(en), encode);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	// 这里可以提供更多地编码格式,另外由于部分编码格式是一致的所以会返回 第一个匹配的编码格式 GBK 和 GB2312
	public static final String[] encodes = new String[] { "UTF-8", "GBK", "GB2312", "ISO-8859-1", "ISO-8859-2" };

	/**
	 * 获取字符串编码格式
	 * 
	 * @param str
	 * @return
	 */
	public static String getEncode(String str) {
		byte[] data = str.getBytes();
		byte[] b = null;
		a:for (int i = 0; i < encodes.length; i++) {
			try {
				b = str.getBytes(encodes[i]);
				if (b.length!=data.length)
					continue;
				for (int j = 0; j < b.length; j++) {
					if (b[j] != data[j]) {
						continue a;
					}
				}
				return encodes[i];
			} catch (UnsupportedEncodingException e) {
				continue;
			}
		}
		return null;
	}
}
