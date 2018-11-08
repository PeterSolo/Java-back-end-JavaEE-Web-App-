/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.Dao;

import com.test.beans.Order;
import com.test.beans.Product;
import com.test.beans.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Petiman
 */
public class ApplicationDao {
 
    
 // this method checks login details   
public boolean isValidUser(String username, String password){
 boolean isValid=false;
   try{ 
   Connection connection = DBConnection.getConnectionToDB();
   String sql = "select username,password from users where username=? and password=?";
   java.sql.PreparedStatement statement = connection.prepareStatement(sql);
   statement.setString(1, username);
   statement.setString(2, password);
   
   ResultSet set = statement.executeQuery();
    while(set.next()){
     isValid=true;
     }
   
   
   }
   catch(SQLException exception){
   exception.printStackTrace();
   }



return isValid;
} 

   // this method will register user
   public int registerUser(User user){
   int rowsAffected=0;
       try{
    Connection connection =DBConnection.getConnectionToDB();   
    String insertQuery = "insert into users values(?,?,?,?,?,?)";   
    java.sql.PreparedStatement statement = connection.prepareStatement(insertQuery);
    statement.setString(1,user.getUsername());
    statement.setString(2,user.getPassword());
    statement.setString(3, user.getFirstName());
    statement.setString(4, user.getLastName());
    statement.setInt(5, user.getAge());
    statement.setString(6, user.getActivity());

    rowsAffected = statement.executeUpdate();
       }catch(SQLException exc){
       exc.printStackTrace();
       }
       
       return rowsAffected;


}
   
 // this method will get proucts from data base
   
  // deals with getting products from database
    public List<Product> searchProducts(String searchString, Connection connection){
       Product product = null;
       List<Product> products = new ArrayList<>();
      // connecting to DBConnection class
      //Connection connection = DBConnection.getConnectionToDB();
     

    // writing sql query
     String sql = "select * from products where product_name like '%"+searchString+"%' ";
       try {
           Statement statement = connection.createStatement();
           ResultSet set = statement.executeQuery(sql);
           while(set.next()){
           product = new Product();
           product.setProductId(set.getInt("product_id"));
           product.setProductImgPath(set.getString("image_path"));
           product.setProductName(set.getString("product_name"));
           products.add(product);
           System.out.println("added product");
           }
           
     } catch (SQLException ex) {
           ex.printStackTrace();
           Logger.getLogger(ApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
       }
     

       return products;
   }  
    
    
    public User getProfileDetails(String username){
       User user =null;
      
       try{
         // get connection to database
         Connection connection = DBConnection.getConnectionToDB();
         
         // write query to get profile details
         
         String sql = "select * from users where username=?";
         PreparedStatement statement = connection.prepareStatement(sql);
         statement.setString(1,username);
         
         // execute query, get resultset and return user info
         ResultSet set = statement.executeQuery();
         while(set.next()){
         user = new User();
         user.setUsername(set.getString("username"));
         user.setFirstName(set.getString("first_name"));
         user.setLastName(set.getString("last_name"));
         user.setActivity(set.getString("activity"));
         user.setAge(set.getInt("age"));
          }
    }
   catch(SQLException exception){
   exception.printStackTrace();
  }
    return user;
  
}
    
  
    public List<Order> getOrderHistory(String username){
    List<Order> orders = new ArrayList<>();
     Order order; 
     int i=0;
  try{
    Connection connection =DBConnection.getConnectionToDB();
    String sqlQuery= "select * from orders where user_name=?";
    PreparedStatement statement = connection.prepareStatement(sqlQuery);
    statement.setString(1, username);
    ResultSet set = statement.executeQuery();
      
     while(set.next()){
   order = new Order();
     order.setOrderId(set.getInt("order_id"));
     order.setProductName(set.getString("product_name"));
     order.setProductImgPath(set.getString("image_path"));
     order.setOrderDate(new Date(set.getDate("order_date").getTime()));
     order.setUsername(set.getString("user_name"));
   orders.add(order);
   //i++;  
     } 
     
      
      
      
  }catch(SQLException exc){
   exc.printStackTrace();
  }  
  
    
    //System.out.println("see here : " + i);
    
    return orders ;
    }
   
    
    
  public List<Product> getProductsForCart(ArrayList<String> names){
   ArrayList<Product> newProducts= new ArrayList<>();
  for(String itemName: names){
   try{
       Connection connection =DBConnection.getConnectionToDB();
       String sql = "select * from products where product_name=?";
       PreparedStatement statement = connection.prepareStatement(sql);
       statement.setString(1, itemName);
       ResultSet set=statement.executeQuery();
       while(set.next()){
       Product product = new Product();
       product.setProductImgPath(set.getString("image_path"));
       product.setProductName(itemName);
       newProducts.add(product);
       }
       
   }
  catch(SQLException exc){
  exc.printStackTrace();
  }
  
  }
 // looping through names from cart ends 
  
 return newProducts;
  }  
    
    




    
    
    
    
    
    
}
