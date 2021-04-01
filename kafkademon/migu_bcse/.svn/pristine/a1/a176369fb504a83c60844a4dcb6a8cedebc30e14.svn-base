package com.chinamobile.cmss.bcselogAnalyse.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

/** 
 * @ClassName: ExecuteShell 
 * @Description: 执行shell
 * @author: yangjing
 * @date: 2016年4月21日 上午10:28:16  
 */
public class ExecuteShell  {
	private static Logger logger = Logger.getLogger(ExecuteShell.class);
	/** 
	 * @Title: run 
	 * @Description: 执行shell,打印关键信息
	 * @param cmdarray
	 * @param envp
	 * @param dir
	 * @throws IllegalThreadStopException
	 * @throws IOException
	 * @throws InterruptedException
	 * @return: void
	 */
	public static String run(String[] cmdarray,String[] envp,File dir) throws IOException,InterruptedException {
		StringBuffer stringBuffer = new StringBuffer();
		StringBuffer result = new StringBuffer();
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(cmdarray,envp,dir);
			if(null != process){
				stringBuffer.append("进程号：").append(process.toString()).append("\r\n");
				stringBuffer.append("shell命令：").append(Tools.arrayToString(cmdarray)).append("\r\n");
				bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()), 1024);			
				process.waitFor();			
			}else{
				 stringBuffer.append("没有进程号\r\n");
			}			
			String line = null;
			while (bufferedReader != null && (line = bufferedReader.readLine()) != null) {			
				result.append(line).append("\r\n");
	        }
		} catch (IOException e) {
			throw new IOException("shell进程异常，进程号为："+process.toString(),e);
		} catch (InterruptedException e) {
			throw new InterruptedException("shell进程异常，进程号为："+process.toString()+"\n"+e);
		} finally{
			if( null != result && result.toString().length()>0){	
				stringBuffer.append("shell命令执行完毕，返回数据为：");
				stringBuffer.append(result.toString());							
			}else{
				stringBuffer.append("shell命令执行完毕，没有返回数据");
			}
			logger.info(stringBuffer.toString());	
			try {
				bufferedReader.close();
				bufferedReader = null;
			} catch (IOException e) {
				logger.error(e);
			}
		}
		return result.toString();
			
	}
	

}

