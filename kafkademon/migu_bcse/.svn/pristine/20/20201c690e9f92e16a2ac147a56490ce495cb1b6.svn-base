package com.chinamobile.cmss.bcselogAnalyse.hadoop;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


/** 
 * @ClassName: costMapper 
 * @Description: 解析日志文件，提取搜索耗时信息，进行mapper计算
 * @author: yangjing
 * @date: 2016年2月1日 上午9:57:05  
 */
public class costMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private IntWritable value= new IntWritable();
    private Text key = new Text();
	
	public void map(LongWritable row, Text content, Context context)
            throws IOException, InterruptedException {      
        String line = content.toString();
        
        String str="GET /jf.gif?log_type=searchlog";
        if(line.contains(str)){          	    	
    	    String[] arr=line.split("&");
    	    Map<String, String> fields=new HashMap<String, String>();
    	    for(int j=0;j<arr.length;j++){
    	    	String[] field = null;
    	    	try {
	    	    	field=java.net.URLDecoder.decode(arr[j].trim(),"UTF-8").split("=");
	    	    	if(field.length==2){
	    	    		//将数据转换成小时
	    	    		if(field[0].equals("oper_time"))
	    	    		{
	    	    			String hour=field[1];
	    	    			hour=hour.substring(0, hour.indexOf(":"));
	    	    			fields.put(field[0], hour);
	    	    		}else{
	    	    		fields.put(field[0],field[1]);}
	    	    		}
    	    	} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
    	    }
    	    if(fields.containsKey("keywords")){
				String sk=fields.get("oper_time")+" "+fields.get("user_id")+" "+fields.get("app_id")+" "+fields.get("flag");
				key.set(sk);     
				String tc=fields.get("time_cost");
				value.set(new Integer(Integer.parseInt(tc.substring(0,tc.length()-2))));  
				context.write(key, value);  
    	    }
        }
       
	
	}
	

}




