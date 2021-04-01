//package jiami;
//
//import com.sun.org.apache.xpath.internal.operations.String;
//import org.apache.commons.codec.binary.Base64;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.UnsupportedEncodingException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//public class AESUtil {
//	private static final byte[] DES_KEY = { 21, 1, -110, 82, -32, -85, -128, -65 ,10 , 11, 2, 3, 4, 5, 6, 7};
//	/**
//	 * 解密数据
//	 *
//	 * @param strIn
//	 *  必须是16位的字符串
//	 * @return
//	 * @throws Exception
//	 */
//	public static String decrypt(String strIn) throws Exception {
//		SecretKeySpec skeySpec = new SecretKeySpec(DES_KEY, "AES");
//		Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5Padding");
//		IvParameterSpec iv = new IvParameterSpec(DES_KEY);
//		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//		byte[] bdata = strIn.getBytes();
//		byte[] encrypted1 = new Base64().decode(bdata);
//		byte[] original = cipher.doFinal(encrypted1);
//		String originalString = new String(original, "UTF-8");
//		return originalString;
//	}
//
//	/**
//	 * 加密数据
//	 *
//	 * @param strIn
//	 * @param strKey
//	 *            必须是16位的字符串
//	 * @return
//	 */
//	public static String encrypt(String strIn) throws Exception {
//		SecretKeySpec sKeySpec = new SecretKeySpec(DES_KEY, "AES");
//		Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5Padding");
//		IvParameterSpec iv = new IvParameterSpec(DES_KEY);
//		cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
//		byte[] encrypted = cipher.doFinal(strIn.getBytes("UTF-8"));
//		return new String(new Base64().encode(encrypted));
//	}
//	//sha256加密
//  	public static String getSHA256Str(String str){
//  		 MessageDigest messageDigest;
//  	        String encodeStr = "";
//  	        try {
//  	        	if(str!=null){
//  	        		messageDigest = MessageDigest.getInstance("SHA-256");
//  	        		messageDigest.update(str.getBytes("UTF-8"));
//  	        		encodeStr = byte2Hex(messageDigest.digest());
//  	        	}
//  	        } catch (NoSuchAlgorithmException e) {
//  	            e.printStackTrace();
//  	        } catch (UnsupportedEncodingException e) {
//  	            e.printStackTrace();
//  	        }
//  	        return encodeStr;
//      }
//  	 private static String byte2Hex(byte[] bytes){
//  	        StringBuffer stringBuffer = new StringBuffer();
//  	        String temp = null;
//  	        for (int i=0;i<bytes.length;i++){
//  	            temp = Integer.toHexString(bytes[i] & 0xFF);
//  	            if (temp.length()==1){
//  	                stringBuffer.append("0");
//  	            }
//  	            stringBuffer.append(temp);
//  	        }
//  	        return stringBuffer.toString();
//  	    }
//  	/**
//      * 生成32位md5码
//      * @param password
//      * @return
//      */
//     public static String md5Password(String password) {
//         try {
//             // 得到一个信息摘要器
//             MessageDigest digest = MessageDigest.getInstance("md5");
//             byte[] result = digest.digest(password.getBytes());
//             StringBuffer buffer = new StringBuffer();
//             // 把每一个byte 做一个与运算 0xff;
//             for (byte b : result) {
//                 // 与运算
//                 int number = b & 0xff;// 加盐
//                 String str = Integer.toHexString(number);
//                 if (str.length() == 1) {
//                     buffer.append("0");
//                 }
//                 buffer.append(str);
//             }
//             // 标准的md5加密后的结果
//             return buffer.toString();
//         } catch (NoSuchAlgorithmException e) {
//             e.printStackTrace();
//             return "";
//         }
//     }
//	public static void main(String[] args) throws Exception {
//		String str = "2c8a1304dd9bfe072fc29cd0a3364fca2b6ce09f53cc45c18495a84f395bd3f3";
//		String result = AESUtil.encrypt(str);
//		System.out.println(result);
//		String md5Password = AESUtil.md5Password(result);
//		System.out.println("md5 password:" + md5Password);
//		String sha256Str = AESUtil.getSHA256Str(md5Password);
//		System.out.println(sha256Str);
//
//		String test = AESUtil.decrypt(sha256Str);
//		String pwd = AESUtil.getSHA256Str(AESUtil.md5Password(test));
//		System.out.println(pwd);
//		String pwd1 = "1qaz!QAZ";
//		String des = AESUtil.encrypt(pwd1);
//		System.out.println("加密后： " + des);
//
//		String originl = AESUtil.decrypt(des);
//		System.out.println("解密后：" + originl);
//		System.out.println(AESUtil.getSHA256Str(AESUtil.md5Password(AESUtil.decrypt(des))));
//
//		String md5Password = AESUtil.md5Password(des);
//		System.out.println(md5Password);
//		String sha256Str = AESUtil.getSHA256Str(md5Password);
//		System.out.println(sha256Str);
//		String pwd = "17e24dd0c68e9bc1d716d7ecde5590a7";
//		String result = AESUtil.decrypt(sha256Str);
//		System.out.println(result);
//
//		System.out.println(RSAEncryptUtils.encrypt(sha256Str,RSAEncryptUtils.public_key));
//		System.out.println(AESUtil.md5Password(AESUtil.encrypt("7ui8&UI*")));
//	}
//}
