/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.servlets;

import com.test.Dao.ApplicationDao;
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
public class loginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("am in logIn servlet do get method");
        
        req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
        
        
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Am in the logIn servlet do post method ");
        
        String username=req.getParameter("username");
        String password=req.getParameter("password");

       ApplicationDao dao = new ApplicationDao();  
       boolean isvalidUser = dao.isValidUser(username, password);
        
       if(isvalidUser==true){
       
       HttpSession session=req.getSession();
       session.setAttribute("username", username);
       req.getRequestDispatcher("jsp/home.jsp").forward(req, resp);
       }        
       else{
               
        String errorMessage ="Invalid Login details Please Login again";       
         req.setAttribute("error", errorMessage);
         req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
               
               
              }
        
        
        
    }
    
    
    
   
    
    
    
    
    
}
