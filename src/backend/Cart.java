/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import databaseConnection.connect;
import static databaseConnection.connect.conn;
import static frontend.Login.currentUserId;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author beauty
 */
public class Cart {

    private int productid;
    private int quantity;
    private int cartid;
    private double price;
    private String productName;

    public Cart(int productid, int quantity) {
        this.productid = productid;
        this.quantity = quantity;
    }
    

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    
    public int getCartid() {
        return cartid;
    }

    public void setCartid(int cartid) {
        this.cartid = cartid;
    }

    
    public Cart(int cartid, String productName, int quantity, double price){
        this.cartid = cartid;
        this.quantity = quantity;
        this.price = price;
        this.productName = productName;
    }


    public Cart(){
        
    }
    public void deleteCartItem(int productid,String currentUserId) throws ClassNotFoundException{
    //delete from the table cart in database where
    //name equals the parameter
   // connect connecto = new connect();
   //DELETE FROM CART WHERE PRODUCTID_FK="+productid+" AND CUSTOMERID_FK='"+currentUserId+"'
    try{
        String sql = "DELETE FROM CART WHERE CARTID=" +productid ;
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        int i = preparedStatement.executeUpdate();
        System.out.println(i + "deletion successful");
       
    }
    catch(SQLException ex){
        System.out.println(ex);
        
    }

    }
    public static double SubTotal() throws SQLException{
        String sql = "SELECT SUM(PRODUCT.PRODUCTPRICE* CART.QUANTITY ) AS TOTAL FROM CART INNER JOIN PRODUCT ON CART.PRODUCTID= PRODUCT.PRODUCTID AND CART.CUSTOMERID = '"+currentUserId+"'";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            double sum = resultSet.getDouble("TOTAL");
        
        return sum;
        }
        else return 0;
        
    }
    public static ArrayList<Cart> getAllCartItems() throws ClassNotFoundException  {
        //"SELECT * FROM (SELECT * FROM CART inner join PRODUCT where CART.PRODUCTID_FK = PRODUCT.PRODUCTID and CART.CUSTOMERID_FK = 'tatie')AS X";//'"+currentUserId+"'
        //SELECT CART.QUANTITY,CART.PRODUCTID_FK,PRODUCT.PRODUCTNAME,PRODUCT.PRICE FROM CART inner join PRODUCT ON CART.PRODUCTID_FK = PRODUCT.PRODUCTID 
        try {
            String sql = "SELECT * FROM CART INNER JOIN PRODUCT ON CART.PRODUCTID= PRODUCT.PRODUCTID AND CART.CUSTOMERID = '"+currentUserId+"'";//and CART.CUSTOMERID_FK = 'tatie'
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Cart> cartItemsList = new ArrayList<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("CARTID");
                String productName = resultSet.getString("PRODUCTNAME");
                int quantity = resultSet.getInt("QUANTITY");
                double ProductPrice =resultSet.getDouble("PRODUCTPRICE");
                Cart mycart = new Cart(id,productName,quantity,ProductPrice);
                cartItemsList.add(mycart);
            }
            return cartItemsList;
        } catch (SQLException ex) {
        }
        return null;
    }
    public static boolean saveCartItem(Cart cartitem) {

        try {
            String sql = "INSERT INTO CART(CARTID,PRODUCTID,QUANTITY,CUSTOMERID) VALUES(?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, new Random().nextInt(10000));
            pstmt.setInt(2, cartitem.getProductid());
            pstmt.setInt(3, cartitem.getQuantity());
            pstmt.setString(4, currentUserId);
            int i = pstmt.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public static void DeleteCartItemsById(int productid )  {

        try {
            String sql = "DELETE FROM CART WHERE PRODUCTID = '"+productid+"'AND CUSTOMERID='"+currentUserId+"'";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
           
            return;
        } catch (SQLException ex) {
        }
        return;
    }
  //  public static void main(String[] args) throws ClassNotFoundException{
  //      System.out.println(getAllCartItems());
  //  }

    
    
}
