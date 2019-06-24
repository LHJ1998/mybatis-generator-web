package com.lhj1998.mybatis.generator.web.component;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CrosFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String[] allowOrigins = {"http://localhost:8080", "http://127.0.0.1:8080"};
        String origin = request.getHeader("origin");
        boolean isValid = false;
        for(String allowOrigin : allowOrigins) {
            if(allowOrigin.equals(origin)) {
                isValid = true;
                break;
            }
        }

        response.setHeader("Access-Control-Allow-Origin", isValid ? origin : "null");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Credentials, Origin, X-Requested-With, Content-Type, Accept, x-admin-token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
