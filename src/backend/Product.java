/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;
import databaseConnection.connect;
import static databaseConnection.connect.conn;
import static frontend.AmbDashboard.brandid;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author beauty
 */
public class Product {
    
    private int id;
    private String name;
    private double price;
    private int category;
    private int productid;
    private int brand;

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }
    
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
    


    private byte[] image;
    public Product(){}

    public Product(int productid, String name, double price, byte[] image, int category) {
        this.productid = productid;
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
    }

    public Product(String name, double price, int category, int brand) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.brand = brand;
    }

   
    
    public byte[] getImage() {
        return image;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }
    
    public static ArrayList<Product> getAllProducts() throws ClassNotFoundException {
        try {
            connect.database();
            String sql = "SELECT * FROM PRODUCT" ;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Product> ProductList= new ArrayList<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("PRODUCTID");
                String name = resultSet.getString("PRODUCTNAME");
                double price = resultSet.getDouble("PRODUCTPRICE");
                Integer category = resultSet.getInt("CATEGORY");
                byte[] image = resultSet.getBytes("IMAGE");
                Product p1=new Product(id,name,price,image,category);
                ProductList.add(p1) ;
            }
            System.out.println(ProductList);
            return ProductList;
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static ArrayList<Product> getProductsByBrand() throws ClassNotFoundException {
        try {
            String sql = "SELECT * FROM PRODUCT WHERE PRODUCT.BRAND="+brandid ;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Product> ProductList= new ArrayList<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("PRODUCTID");
                String name = resultSet.getString("PRODUCTNAME");
                double price = resultSet.getDouble("PRODUCTPRICE");
                Integer category = resultSet.getInt("CATEGORY");
                byte[] image = resultSet.getBytes("IMAGE");
                
                Product p1=new Product(id,name,price,image,category);
                ProductList.add(p1) ;
            }
            System.out.println(ProductList);
            return ProductList;
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean saveProduct( Product product) {

        try {
            String sql = "INSERT INTO PRODUCT(BRAND,PRODUCTID,PRODUCTNAME,PRODUCTPRICE,CATEGORY) VALUES(?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, product.getBrand());
            pstmt.setInt(2, new Random().nextInt(10000));
            pstmt.setString(3, product.getName());
            pstmt.setDouble(4, product.getPrice());
          //  pstmt.setBlob(5, product.getImage());
            pstmt.setInt(5, product.getCategory());
            int i = pstmt.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public static void deleteProduct(int productid) throws ClassNotFoundException{
    
        try{
            String sql = "DELETE FROM PRODUCT WHERE PRODUCTID="+productid ;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            int i = preparedStatement.executeUpdate();
            System.out.println(i + "deletion successful");

        }
        catch(Exception e){
            System.out.println("Not deleted");

        }

    }
}
