package com.chinamobile.cmss.bcse.validate.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 * Servlet Filter implementation class CorsFilter
 */
@WebFilter("/CorsFilter")
public class CorsFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public CorsFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String curOrigin = httpRequest.getHeader("Origin");
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "request Origin is:"+curOrigin);
		if (Config.CROS_SERVICE_HOST == null) {
			chain.doFilter(req, res);
			return;
		}
		String[] originProperties = Config.CROS_SERVICE_HOST.split(",");
		if (curOrigin != null) {
			for (int i = 0; i < originProperties.length; i++) {
				if (curOrigin.equals(originProperties[i])) {
					httpResponse.setHeader("Access-Control-Allow-Origin", curOrigin);
					break;
				}
			}
		} else { // 对于无来源的请求(比如在浏览器地址栏直接输入请求的)，那就只允许我们自己的机器可以吧
			httpResponse.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1");
		}
//		response.setHeader("Access-Control-Allow-Origin", "http://172.16.208.86:9000");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		// 设置Methods非必须。试过不设置也能成功。具体原因还没弄清楚。
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"x-requested-with, content-type,authorization,se-date, se-user");
		chain.doFilter(req, res);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
