package com.exmple.serverlt.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @autor ws
 * @description
 * @date 2021/3/17 20:24
 **/
@WebFilter(urlPatterns = "/cas",filterName = "SSOFilter")
public class SSOFilter implements Filter {

    public SSOFilter(){
        System.out.println("SSOFilter 实例化");
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("SSOFilter init ……");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("SSOFilter doFilter ……");
        chain.doFilter(request,response);
    }

    public void destroy() {
        System.out.println("SSOFilter destroy ……");
    }
}
