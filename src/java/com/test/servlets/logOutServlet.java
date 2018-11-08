/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Petiman
 */
public class logOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       HttpSession session = req.getSession();
                // closing current session
                session.invalidate();
                // alloows 120 sec inactive time or we can do it in web.xml
                // in web.xml 20 means 20 mins
                session.setMaxInactiveInterval(120);
                req.getRequestDispatcher("/index.html").forward(req, resp); 
        
        
        
        
        
    }
    
    
    
    
    
}
