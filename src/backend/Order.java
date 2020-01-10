/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import static databaseConnection.connect.conn;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author beauty
 */
public class Order {

    private int orderId;
    private int cartId;
    private String creditcardNum;
    private Date datee;
    private String customerid;
    public Date getDatee() {
        return datee;
    }

    public void setDatee(Date datee) {
        this.datee = datee;
    }
    
    public Order(int orderId, String customerid, String creditcardNum,Date date) {
        this.orderId = orderId;
        this.customerid = customerid;
        this.creditcardNum = creditcardNum;
        this.datee = date;
       
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getCreditcardNum() {
        return creditcardNum;
    }

    public void setCreditcardNum(String creditcardNum) {
        this.creditcardNum = creditcardNum;
    }
    
    public static ArrayList<Order> getAllOrders() {
        try {
            String sql = "SELECT * FROM  ORDERS";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Order> orderList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("ORDERID");
                String custID = resultSet.getString("CUSTOMERID");
                String card = resultSet.getString("CARDNUMBER");
                Date date = resultSet.getDate("DATE");
                Order ord = new Order(id,custID,card,date);
               orderList.add(ord);
            }
            return orderList;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    
    
    
}
