/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Petiman
 */
public class DBConnection {
    public static void main(String[] args){
    
      getConnectionToDB();
    }
    
 public static Connection getConnectionToDB(){
   Connection connection = null;
    try{ 
     Class.forName("com.mysql.jdbc.Driver");
     connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hplus", "root", "pass");
        System.out.println("got connection");}
    catch(SQLException exception){
    exception.printStackTrace();
    }
    catch(ClassNotFoundException exc){
        System.out.println("Coulodnt find Mysql driver");
    exc.printStackTrace();
    }
 
 
 
 
 
 return connection;
 
 } 
    
    
    
    
    
    
}
