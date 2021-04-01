package string_test;

public class StringTool {

    public static final String COLON_SEPARATOR=":";


    public static String appendWithColon(String... strs){
        if(strs.length==0){
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        for(String str:strs){
            if(i==0){
                stringBuffer.append(str);
            }else{
                stringBuffer.append(COLON_SEPARATOR).append(str);
            }
            i++;
        }
        return stringBuffer.toString();
    }


    public static boolean isNotEmpty(String str){
        return str !=null && !"".equals(str);
    }
}
