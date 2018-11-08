/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Petiman
 */
public class AuthenticationFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("am inside the filter");
        // cast the req argumnet to HttpServlet request
         HttpServletRequest request1 = (HttpServletRequest)request;
        if(request1.getRequestURI().startsWith("WebAppFinal/orderHistory") || request1.getRequestURI().startsWith("WebAppFinal/viewProfile")){
        HttpSession session = request1.getSession();
        if(session.getAttribute("username")==null){
        request1.getRequestDispatcher("jsp/login.jsp").forward(request1, response);
             }
        
        }
     chain.doFilter(request1, response);   
        
        
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
