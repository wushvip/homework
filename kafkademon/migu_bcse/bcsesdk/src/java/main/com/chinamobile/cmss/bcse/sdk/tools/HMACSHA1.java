package com.chinamobile.cmss.bcse.sdk.tools;


import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.chinamobile.cmss.bcse.sdk.exception.ExceptionConstants;



public class HMACSHA1 {
	private static final Logger LOG = Logger.getLogger(HMACSHA1.class);

	private static final String HMAC_SHA1 = "HmacSHA1";
	private static final String ENCODE_UTF8 = "UTF-8";
	public static String getStringToSign(String httpVerb,String md5,String type,String date,
			String canonicalizedHeaders,String canonicalizedResource) {
		String StringToSign =httpVerb +"\n"+
		(md5==null?"":md5) +"\n"+
        (type==null?"":type)+"\n"+
        (date==null?"":date) +"\n"+
        (canonicalizedHeaders==null?"":canonicalizedHeaders)+ "\n"+
        (canonicalizedResource==null?"":canonicalizedResource);
		//System.out.println("StringToSign:"+StringToSign);
		return StringToSign;
	
	}
	
	public static String getSignature(String key, String data) {
		String keyUtf8 = key;
		String dataUtf8 = data;
		byte[] keyBytes = keyUtf8.getBytes();
		SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1);
		
		try {
			Mac mac;
			mac = Mac.getInstance(HMAC_SHA1);
			
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(dataUtf8.getBytes());
			String signature = new String(Base64.encodeBase64(rawHmac), ENCODE_UTF8);
			return signature;
		} catch (NoSuchAlgorithmException e) {
			LOG.error(String.format("Wrong algorithm: %s", HMAC_SHA1), e);
		} catch (InvalidKeyException e) {
			LOG.error(String.format("Invalid key: %s:%s", keyUtf8, dataUtf8), e);
			LogUtil.loggerEntrance(LogUtil.CLIENT_LOG, ExceptionConstants.FAIL_SYS_CODE+"\n"+e.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    return null;
	}

	public static void main(String[] args) throws Exception {
		String auth = "CMCC " + "accessID:"
				+ "asfffffffffffffffffffffffafasdfadf";
		System.out.println(auth);

		String strArray[] = auth.split(" ");

		String tt = strArray[1];
		String strArray1[] = tt.split(":");
		String accessID = strArray1[0];
		String signature = strArray1[1];

		System.out.println(accessID);
		System.out.println(signature);
		String str =getSignature("key","value");
		System.out.println(str);
		String test = new String("t");
		str = new String(Base64.encodeBase64(test.getBytes()), "UTF-8");
		System.out.println(str);
	}
}
