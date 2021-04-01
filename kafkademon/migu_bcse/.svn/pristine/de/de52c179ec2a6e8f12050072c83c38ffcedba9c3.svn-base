package com.chinamobile.cmss.bcse.validate.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.FileOperaUtil;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
import com.chinamobile.cmss.bcse.validate.authority.impl.RequestAuthorization;

/**
 * 
 * @ClassName: AuthorityFilter 
 * @Description: 请求的权限校验
 * @author: lijingjing
 * @date: 2017年3月8日 下午8:24:44
 */
public class AuthorityFilter implements Filter {



	private RequestAuthorization authService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext context = filterConfig.getServletContext();
		authService = (RequestAuthorization) WebApplicationContextUtils.getRequiredWebApplicationContext(context).getBean("authService");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String message = MsgConfig.AUTH_FAIL;
		response.setCharacterEncoding(Config.ENCODE_UTF8);
		response.setContentType(Config.CONTENT_TYPE_JSON);
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String url = httpRequest.getServletPath();
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, ">>>>>>>>>>>>request start and  url is :"+url+",method:"+httpRequest.getMethod()+">>>>>>>>>>>>");
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "authorization check start");
		if ((url!= null && ("/login".equals(url)|| "/evaluation/result".equals(url) || "/oaLogin".equals(url)) && "GET".equals(httpRequest.getMethod())) || "OPTIONS".equals(httpRequest.getMethod())) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "Login and /evaluation/result interface does not need to check");
			chain.doFilter(request, response);
			return;
		}
		final String FILE_TYPE= "Content-Type";
		String type  = httpRequest.getHeader(FILE_TYPE);
		
		if (type != null && type.contains(Config.CONTENT_TYPE_FILE)) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "file upload interface does not need to check or Content-Type value is null");
//			JSONObject json = FileOperaUtil.getFileFromValue(httpRequest);
//			System.out.println("file param:"+json.toString());
			chain.doFilter(request, response);
			return;
		}
		boolean flag = true;
		ResultBean resultBean = new ResultBean();
		
		try {
			BodyReaderHttpServletRequestWrapper wrapper = new BodyReaderHttpServletRequestWrapper(httpRequest);
			JSONObject jsonObject = new JSONObject();
			
			if("GET,DELETE".contains(httpRequest.getMethod())){
				Map<String, String[]> maps = httpRequest.getParameterMap();
				Set<String> keys = maps.keySet();
				for (String string : keys) {
					jsonObject.put(string, httpRequest.getParameter(string));
					LogUtil.loggerEntrance(LogUtil.WEB_LOG, string+":"+httpRequest.getParameter(string));
				}
//				jsonObject =JSONObject.parseObject(URLDecoder.decode(String.valueOf(jsonObject), "UTF-8").replace("%20", "+").replace("%2A", "*").replace("~", "%7E")) ;
			}else{
				flag =  false;
				jsonObject = wrapper.getBody();
				
			}
			
			
			// 执行过滤
			request.setCharacterEncoding(Config.ENCODE_UTF8);

			
			//
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "request params is "+jsonObject.toString());
			// 请求有效性校验
			
			if (!authService.verify(jsonObject,httpRequest,message)) {
				Tool.serviceException(resultBean, message);
				response.getWriter().write(((JSONObject) JSON.toJSON(resultBean)).toString());
				response.getWriter().flush();
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "*************url:"+url+",authorization check end,result is fail*************");
				return;
			}
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "authorization check end,result is success");
			if (flag) {
				chain.doFilter(request, response);
			}else{
				chain.doFilter(wrapper, response);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Tool.serviceException(resultBean, MsgConfig.SYS_EXCP);
			response.getWriter().write(((JSONObject) JSON.toJSON(resultBean)).toString());
			response.getWriter().flush();
			return;

		}finally {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "<<<<<<<<<<<<<< request end and url is :"+url+"<<<<<<<<<<<<<<");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
