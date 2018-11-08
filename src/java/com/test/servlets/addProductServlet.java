/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.servlets;

import com.test.Dao.ApplicationDao;
import com.test.Dao.DBConnection;
import com.test.beans.Product;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Petiman
 */
public class addProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("am in add product servlet do get method");
      HttpSession session = req.getSession();
     
      
      List<String> cart;
      cart = (ArrayList<String>)session.getAttribute("cart");
        
      if(cart == null){
      cart = new ArrayList<>();
      }  
      if(req.getParameter("product")!= null){
    
      cart.add(req.getParameter("product"));
      
          System.out.println("just added product");
          System.out.println("hello" + req.getParameter("product"));
         
      }  
        
      session.setAttribute("cart", cart);
     
       String search = (String)session.getAttribute("search");
        
        // get connection instance from listener and get application dao
        //Connection connection = (Connection)getServletContext().getAttribute("dbconnection");
        Connection connection =DBConnection.getConnectionToDB();
        ApplicationDao dao = new ApplicationDao();
        List<Product> newproducts = new ArrayList<>();
        newproducts = dao.searchProducts(search,connection);
        
        // set the search results in request scope
        
        req.setAttribute("searchResults", newproducts);
        
       req.getRequestDispatcher("jsp/search.jsp").forward(req, resp);
        
        
        
    }
    
   
    
    
    
    
}
