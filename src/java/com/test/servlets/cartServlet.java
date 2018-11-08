/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.servlets;

import com.test.Dao.ApplicationDao;
import com.test.beans.Product;
import java.io.IOException;
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
public class cartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<String> products = new ArrayList<>();
        HttpSession session = req.getSession();
        products = (ArrayList<String>)session.getAttribute("cart");
       // to confirm added products in console
          for(String name: products){
          System.out.println(name);}
  
      // getting products
      ApplicationDao dao = new ApplicationDao();
     List<Product> newProducts= new ArrayList<>();
     newProducts=dao.getProductsForCart(products);

    req.setAttribute("cartProducts", newProducts);
    req.getRequestDispatcher("jsp/cart.jsp").forward(req, resp);
        
        
        
    }
    
   
    
    
    
}
