/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseConnection;

import backend.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author beauty
 */
public class connect {
    public static Connection conn= null;
    public static Connection database() throws ClassNotFoundException {
        try {
           // Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","");//("jdbc:derby://localhost:1527/beautyDB", "beauty247365", "beauty247365");
            System.out.println("connected");
            return conn;
           
            
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }
   
    
    public static void main(String args[]) throws ClassNotFoundException {
        database();
       
    }




}
