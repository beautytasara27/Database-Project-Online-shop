/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import static databaseConnection.connect.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author beauty
 */
public class Brand {
    private int brandid;
    private String brandname;

    public Brand(int brandid, String brandname) {
        this.brandid = brandid;
        this.brandname = brandname;
    }

    public int getBrandid() {
        return brandid;
    }

    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }
    public static ArrayList<Brand> getBrand() throws ClassNotFoundException {
        try {
            String sql = "SELECT * FROM BRAND";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Brand> BrandList= new ArrayList<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("BRANDID");
                String name = resultSet.getString("BRANDNAME");
                Brand b1 = new Brand(id,name);
                BrandList.add(b1) ;
            }
            return BrandList;
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}
