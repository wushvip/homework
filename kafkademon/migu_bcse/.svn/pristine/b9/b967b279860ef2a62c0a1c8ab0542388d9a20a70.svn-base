//package com.chinamobile.cmss.bcse.search.test;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.List;
//import java.util.Map;
//
//import net.sf.json.JSONObject;
//
///**
// *
// * @author chenmin
// * @date   2015年10月18日
// *
// * TODO
// *
// */
//public class TestApi {
//	 /**
//     * 向指定URL发送GET方法的请求
//     * 
//     * @param url
//     *            发送请求的URL
//     * @param param
//     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
//     * @return URL 所代表远程资源的响应结果
//     */
//    public static String sendGet(String url, String param) {
//        String result = "";
//        BufferedReader in = null;
//        try {
//            String urlNameString = url + "?" + param;
//            URL realUrl = new URL(urlNameString);
//            // 打开和URL之间的连接
//            URLConnection connection = realUrl.openConnection();
//            // 设置通用的请求属性
//            connection.setRequestProperty("accept", "*/*");
//            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 建立实际的连接
//            connection.connect();
//            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
//            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
//            // 定义 BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(new InputStreamReader(
//                    connection.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            System.out.println("发送GET请求出现异常！" + e);
//            e.printStackTrace();
//        }
//        // 使用finally块来关闭输入流
//        finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 向指定 URL 发送POST方法的请求
//     * 
//     * @param url
//     *            发送请求的 URL
//     * @param param
//     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
//     * @return 所代表远程资源的响应结果
//     */
//    public static void sendPost(String ADD_URL) {
//    	 try { 
//             //创建连接 
//             URL url = new URL(ADD_URL); 
//             HttpURLConnection connection = (HttpURLConnection) url 
//                     .openConnection(); 
//             connection.setDoOutput(true); 
//             connection.setDoInput(true); 
//             connection.setRequestMethod("POST"); 
//             connection.setUseCaches(false); 
//             connection.setInstanceFollowRedirects(true); 
//             connection.setRequestProperty("Content-Type", 
//                     "application/x-www-form-urlencoded"); 
//             connection.connect(); 
//             //POST请求 
//             OutputStreamWriter out = new OutputStreamWriter(
//            		 connection.getOutputStream(), "utf-8");
//             JSONObject obj = new JSONObject(); 
//             obj.element("app_name", "TEST"); 
//             obj.element("user_name", "zj950@qq.com"); 
//             obj.element("search_query", "植物"); 
//             obj.element("rank_type", "0"); 
//             obj.element("page_id", "1"); 
//             //obj.element("cl_filter", "[CA:Store]&[CB:log]"); 
//             ///obj.element("facet_rule", "GAME_TYPE_ID"); 
//            // obj.element("cl_filter", "[CONTENTNAME:横扫狂尸]");
//            // obj.element("num_filter", "[ALL_DOWN_CNT#3000#4000]|[ALL_DOWN_CNT#1000#3000]");
//             obj.element("num_per_page", "10"); 
//             //obj.element("query_encode", "1"); 
//             //obj.element("enable_highlight", "1");
//             //obj.element("sort_rule", "0"); 
//             obj.element("fields_search", "FEE"); 
//             out.write(obj.toString()); 
//             out.flush(); 
//             out.close(); 
//
//             //读取响应 
//             BufferedReader reader = new BufferedReader(new InputStreamReader( 
//                     connection.getInputStream())); 
//             String lines; 
//             StringBuffer sb = new StringBuffer(""); 
//             while ((lines = reader.readLine()) != null) { 
//                 lines = new String(lines.getBytes()); 
//                 sb.append(lines); 
//             } 
//             System.out.println(sb); 
//             reader.close(); 
//             // 断开连接 
//             connection.disconnect(); 
//         } catch (MalformedURLException e) { 
//             // TODO Auto-generated catch block 
//             e.printStackTrace(); 
//         } catch (UnsupportedEncodingException e) { 
//             // TODO Auto-generated catch block 
//             e.printStackTrace(); 
//         } catch (IOException e) { 
//             // TODO Auto-generated catch block 
//             e.printStackTrace(); 
//         } 
//
//    }    
//	//测试get请求
//	public static void main(String[] args){
//		//发送 GET 请求
//        /*String s=TestApi.sendGet("http://localhost:8080/QueryApi", "key=123&v=456");
//        System.out.println(s);*/
//        //发送 POST 请求223.68.205.132
//       TestApi.sendPost("http://localhost:8080/bcseSearch/QueryApi");
//        //TestApi.sendPost("http://10.133.16.179:8999/bcseSearch/QueryApi");
//		//TestApi.sendPost("http://172.16.208.196:8080/bcseSearch/QueryApi");
//	}
//
//}
