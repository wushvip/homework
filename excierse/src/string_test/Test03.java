/**
 * 
 */
package string_test;

import org.apache.commons.lang3.StringUtils;

/**
 * @author wushuai
 * @date 2018��8��21��
 * @Description	TODO
 */
public class Test03 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String bu = "      dsadsdsfsdw     ewwrkfkfk";
//		System.out.println(removeSpace(bu));

		String original = "9999,8000,7877,8080";
		String result = subString(original, "8000");
		System.out.println(result);

	}

	public static String subString(String original,String subStr){
		if(StringUtils.isBlank(original)){
			return null;
		}
		int i = original.indexOf(subStr);
		if(StringUtils.isBlank(subStr) || i<0){
			return original;
		}
		String result = null;
		if(i==0){
			result = original.substring(subStr.length()+1);
		}else if((i+subStr.length())==original.length()){
			result = original.substring(0,i-1);
		}else{
			result = original.substring(0,i) + original.substring(i+subStr.length()+1);
		}
		System.out.println(i);
		return result;
	}

	
	public static String removeSpace(String str) {
		String result = str;
		if (str !=null && str !="") {
			result = str.replaceAll("\\s+", "");
		}
		return result;
	}

}
