/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.servlets;

import com.test.Dao.ApplicationDao;
import com.test.beans.User;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Petiman
 */
public class viewProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
     HttpSession session=req.getSession();
     String username = (String)session.getAttribute("username");
     System.out.println("logged in as:" + username);   
        
              ApplicationDao dao = new ApplicationDao();
              User user = dao.getProfileDetails(username);
              
            Map<String, Double> weightSummary = new HashMap<>();
              weightSummary.put("January", 67.9);
              weightSummary.put("February", 65.9);
              weightSummary.put("March", 64.8);
             
        req.setAttribute("user", user);
        
        req.setAttribute("weightSummary", weightSummary);
         System.out.println("am here");
       req.getRequestDispatcher("jsp/profile.jsp").forward(req, resp); 
        
        
        
        
        
        
        
    }
    
    
    
    
    
}
