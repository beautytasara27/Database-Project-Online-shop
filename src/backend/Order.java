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
import java.util.Random;

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
    private double amountdue;

    public double getAmountdue() {
        return amountdue;
    }

    public void setAmountdue(double amountdue) {
        this.amountdue = amountdue;
    }
    
    public Date getDatee() {
        return datee;
    }

    public void setDatee(Date datee) {
        this.datee = datee;
    }

    public Order(String creditcardNum, Date datee, String customerid, double amountdue) {
        this.creditcardNum = creditcardNum;
        this.datee = datee;
        this.customerid = customerid;
        this.amountdue = amountdue;
    }
    
    
    public Order(int orderId, String customerid, String creditcardNum,Date date,double amountdue) {
        this.orderId = orderId;
        this.customerid = customerid;
        this.creditcardNum = creditcardNum;
        this.datee = date;
        this.amountdue = amountdue;
       
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
                double amountdue = resultSet.getDouble("AMOUNTDUE");
                Order ord = new Order(id,custID,card,date,amountdue);
               orderList.add(ord);
            }
            return orderList;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static boolean saveOrder( Order order) {

        try {
            String sql = "INSERT INTO ORDERS(ORDERID,CUSTOMERID,CARDNUMBER,DATE,AMOUNTDUE) VALUES(?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, new Random().nextInt(10000));
            pstmt.setString(2, order.getCustomerid());
            pstmt.setString(3, order.getCreditcardNum());
            pstmt.setDate(4, order.getDatee());
            pstmt.setDouble(5, order.getAmountdue());
            int i = pstmt.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static ArrayList<Order> getOrdersByUsername(String customerid) {
        try {
            String sql = "SELECT * FROM  ORDERS WHERE CUSTOMERID ='"+customerid+"'";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Order> orderList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("ORDERID");
                String custID = resultSet.getString("CUSTOMERID");
                String card = resultSet.getString("CARDNUMBER");
                Date date = resultSet.getDate("DATE");
                double amountdue = resultSet.getDouble("AMOUNTDUE");
                Order ord = new Order(id,custID,card,date,amountdue);
               orderList.add(ord);
            }
            return orderList;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    
    
    
}
