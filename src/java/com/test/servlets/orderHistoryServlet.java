/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.servlets;

import com.test.Dao.ApplicationDao;
import com.test.beans.Order;
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
public class orderHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("am in the order history servlet do get method");
        HttpSession session = req.getSession();
        String username = (String)session.getAttribute("username");
        
        ApplicationDao dao = new ApplicationDao();
        List<Order> orders = new ArrayList<>();
        orders=dao.getOrderHistory(username);
        //int i=orders.size();
        //System.out.println("see here" +i);
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("jsp/home.jsp").forward(req, resp);
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
}
