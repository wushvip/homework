package com.chinamobile.cmss.bcse.tool.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.app.bean.FtpInfoBean;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.exception.FileException;

public class FileOperaUtil {

	
	public static JSONObject getFileFromValue(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
        //获取上传文件类型
        if (ServletFileUpload.isMultipartContent(request)){
            //创建ServletFileUpload实例
            ServletFileUpload fileUpload = new ServletFileUpload();
            try {
                //解析request请求 返回FileItemStream的iterator实例
                FileItemIterator iter = fileUpload.getItemIterator(request);
                InputStream is = null;//输出流
                //迭代取出
                while (iter.hasNext()){
                    FileItemStream item = iter.next();//获取文件流
                    String name = item.getFieldName();//返回表单中标签的name值
                    is = item.openStream();//得到对应表单的输出流
                    if (item.isFormField()){//如果是非文件域,设置进入map,这里要注意多值处理
                        jsonObject.put(name, Streams.asString(is));
                    }
                }
 
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return jsonObject;
	} 
	
    private Map<String,String[]> setFormParam(String name, InputStream is) {
        //存储参数的集合
        Map<String,String[]> params = new HashMap<>();
        try {
            if (params.containsKey(name)){//判断当前值name是否已经存储过
                String[] values = params.get(name);//取出已经存储过的值
                values = Arrays.copyOf(values,values.length+1);//把当前数组扩大
                values[values.length-1] = Streams.asString(is);//增加新值
                params.put(name,values);//重新添加到map中
            }else {
                params.put(name,new String[]{Streams.asString(is)});//直接存入参数中
            }
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return params;
    }
	/**
	 * 本地上传文件（form表单上传）
	 * 
	 * @return boolean
	 * @date:2015-11-11下午6:49:32
	 * @author:lijingjing
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public static boolean localUploadFile(String userId, String appId, MultipartFile file, String savePath)
			throws Exception {

		if (file == null) {
			return false;
		}

		boolean fileFlag = false;
		String fileName = file.getOriginalFilename();
		File targetFile = new File(savePath, fileName);
		if (!targetFile.exists()) {
			boolean flag = targetFile.mkdirs();
			if (!flag) {
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "创建路径失败:" + savePath);
				return false;
			}
		}

		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		fileFlag = true;
		return fileFlag;

	}

	/**
	 * 本地上传文件（form表单上传）
	 * 
	 * @return boolean
	 * @date:2015-11-11下午6:49:32
	 * @author:lijingjing
	 */
	public static boolean localAnalysisFile(String userId, String appId, MultipartFile file, String savePath) {

		if (file == null) {
			return false;
		}

		boolean fileFlag = false;
		try {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "savePath:" + savePath);
			File targetFile = new File(savePath, "ext.dic");
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存

			file.transferTo(targetFile);
			fileFlag = true;

		} catch (Exception e) {
			fileFlag = false;
			LogUtil.loggerEntrance(userId, appId, ExceptionConstants.FileException, LogUtil.WEB_LOG, e);
			throw new FileException(ExceptionConstants.FileException);

		}
		return fileFlag;

	}

	/**
	 * ftp的形式，将由一个服务器的文件上传到另一个服务器的文件
	 * 
	 * @return boolean
	 * @date:2015-11-11下午7:20:19
	 * @author:lijingjing
	 */
	public static boolean ftpUploadFile(FtpInfoBean ftpInfo, String userId, String appId, String tableId,
			String savePath) {

		// 上传文件的执行结果
		boolean uploadFlag = true;

		// 远程服务器保存文件的路径
		String remotePath = ftpInfo.getSourceDir();

		// 截取出文件名
		String fileName = remotePath.substring(remotePath.lastIndexOf("/") + 1);

		File filePath = new File(savePath);
		// 判断上传文件的保存目录是否存在
		if (!filePath.exists() && !filePath.isDirectory()) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, savePath + "目录不存在，需要创建");
			// 创建目录
			boolean flag = filePath.mkdirs();
			if (flag) {
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "创建新路径成功！");
			} else {
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "创建新路径失败！");
				uploadFlag = false;
				return uploadFlag;

			}
		}

		FTPClient ftpClient = new FTPClient();

		try {
			ftpClient.connect(ftpInfo.getFtpHost(), Integer.parseInt(ftpInfo.getFtpPort()));
			ftpClient.login(ftpInfo.getFtpUserName(), ftpInfo.getFtpPassword());

			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			// 设置传输模式为被动模式
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "FTP server refused connection.");

			}
			savePath = savePath + File.separator + fileName;

			File file = new File(savePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream out = null;
			out = new FileOutputStream(file, true);
			uploadFlag = ftpClient.retrieveFile(remotePath, out);
			if (!uploadFlag) {
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "文件不存在");
			}
			out.flush();
		} catch (Exception e) {
			uploadFlag = false;
			e.printStackTrace();
			LogUtil.loggerEntrance(userId, appId, "0", LogUtil.WEB_LOG, e);
		} finally {

			try {
				if (ftpClient.isConnected()) {
					ftpClient.disconnect();
				}

			} catch (IOException e) {
				e.printStackTrace();
				LogUtil.loggerEntrance(userId, appId, "0", LogUtil.WEB_LOG, e);
			}

		}
		return uploadFlag;

	}

}
