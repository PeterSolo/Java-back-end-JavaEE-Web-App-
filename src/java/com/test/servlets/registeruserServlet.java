/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.servlets;

import com.test.Dao.ApplicationDao;
import com.test.beans.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Petiman
 */
public class registeruserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("am in the register servlet do get method");
        req.getRequestDispatcher("jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String Info="";
        System.out.println("am in the register servlet do post method");
       
      String username =req.getParameter("username"); 
      String password =req.getParameter("password");
      String fname =req.getParameter("fname"); 
      String lname =req.getParameter("lname"); 
      int age =Integer.parseInt(req.getParameter("age"));
      String activity=req.getParameter("activity");
       
      User user = new User(username,password,fname,lname,age,activity); 
      
      ApplicationDao dao = new ApplicationDao();
      int rowsAffected = dao.registerUser(user);
       
      if(rowsAffected==0){
      Info="Sorry error happened register again later";
      
      }
       
      else{
      Info="Registered Successfully!!, Please login";
      }
       
     req.setAttribute("Info", Info);    
     req.getRequestDispatcher("jsp/register.jsp").forward(req, resp);
    }
    
    
    
    
    
    
    
    
}
