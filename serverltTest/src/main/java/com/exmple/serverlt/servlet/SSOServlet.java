package com.exmple.serverlt.servlet;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @autor ws
 * @description for test sso
 * @date 2021/1/5 20:25
 **/
@WebServlet(name = "SSOServlet",urlPatterns = "/SSO",loadOnStartup = 1,asyncSupported = true)
public class SSOServlet extends HttpServlet {

    public SSOServlet(){
        System.out.println("创建SSOServlet实例");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
//        Map<String,String> map = new HashMap();
//
//        //单点登陆相关信息
//        String ipAddress=request.getParameter("ipAddress");
//        String macAddress=request.getParameter("macAddress");
//        String cpuSerial=request.getParameter("cpuSerial");
//        String hostName=request.getParameter("hostName");
//        String appAcctId = request.getParameter("appAcctId");
//        String token=request.getParameter("token");
//        String flag=request.getParameter("flag");
//        String clientIp = getRemoteHost(request);

//        map.put("LOG_ID", LogParamUtil.generateLogId());
//        String sessionId = LogParamUtil.generateSessionIp();
//        map.put("SESSION_ID",sessionId);
//        map.put("IF_INPUT_ARGS",appendInputArgs(request));
//        map.put("IF_INVOKE_NAME",request.getContextPath());
//        if(StringUtils.isNotBlank("clientIp")){
//            map.put("IF_INVOKE_IP",clientIp);
//        } else{
//            map.put("client_address",ipAddress);
//
//        }

        String params = appendInputArgs(request);
        System.out.println(params);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    /**
     * 获取目标主机的ip
     * @param request
     * @return
     */
    public static String getRemoteHost(HttpServletRequest request) {
        String ip = "";
        if (request != null) {
            ip = request.getHeader("X-Real-IP");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Forwarded-For");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }


    private String appendInputArgs(HttpServletRequest request){
        StringBuilder inputArgs = new StringBuilder("");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String key = parameterNames.nextElement();
            String value = request.getParameter(key);
            inputArgs.append(key).append(":").append(value).append(",");
        }
        if(StringUtils.isNotBlank(inputArgs)){
            return inputArgs.substring(0,inputArgs.lastIndexOf(","));
        }
        return inputArgs.toString();
    }


    @Override
    public void init() throws ServletException {
        System.out.println("SSOservlet init....");
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("SSOServelet正在被销毁");
        super.destroy();
    }
}
