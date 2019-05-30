package com.example.product.filter;

import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@Component
@Order(1)
public class SimpleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
            throws IOException, ServletException {
        System.out.println("Remote Host:"+request.getRemoteHost());
        System.out.println("Remote Address:"+request.getRemoteAddr());
        HttpServletRequest req = (HttpServletRequest)request;
        System.out.println("RequestURI: "+req.getRequestURI());
        System.out.println("Auth Header: "+req.getHeader("Authorization"));
//        Enumeration<String> headerNames = req.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            System.out.println("next: " + headerNames.nextElement());
//        }
        System.out.println("JWT token (from auth): " + getAuthorizationToken());
        filterchain.doFilter(request, response);
    }

    private String getAuthorizationToken() {
        String token = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
            token = oauthDetails.getTokenValue();
        }
        return token;
    }

    @Override
    public void destroy() {

    }
}
