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
public class searchresultsServlet extends HttpServlet {

   

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("am in the serachresults servlet do get method ");
//        req.getRequestDispatcher("jsp/search.jsp").forward(req, resp);
//     }
    
   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        System.out.println("am in the serachresults servlet do get method ");
        String search = (String)req.getParameter("search");
        HttpSession session= req.getSession();
        session.setAttribute("search", search );
        List<String> cart1 = new ArrayList<>();
        session.setAttribute("cart", cart1 );
        
        Connection connection =DBConnection.getConnectionToDB();
        ApplicationDao dao = new ApplicationDao();
        List<Product> products=dao.searchProducts(search, connection);
        req.setAttribute("searchResults", products);
       
        req.getRequestDispatcher("jsp/search.jsp").forward(req, resp);
        
    } 
    
    
    
    
}
