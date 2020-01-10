/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import databaseConnection.connect;
import static databaseConnection.connect.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author beauty
 */
public class Tutorial {
    public String tutorialName;
    public UserAccount influencer;
    public Product productPromoted;
    public String brand;
    public String url;
    public String tips;
    public int tutorialid;
    public int productid;
    public String influencerid;
    public int recommended;

    public int getRecommended() {
        return recommended;
    }

    public void setRecommended(int recommended) {
        this.recommended = recommended;
    }
    
        
    public Tutorial(){
        
    }
    public Tutorial(String tutorialName, UserAccount influencer, Product productPromoted, String brand, String url, String tips) {
        this.tutorialName = tutorialName;
        this.influencer = influencer;
        this.productPromoted = productPromoted;
        this.brand = brand;
        this.url = url;
        this.tips = tips;
    }
    public Tutorial(int tutorialid, int productid, String url, String tips, String influencerid,int recommended){
        this.url = url;
        this.tips = tips;
        this.influencerid = influencerid;
        this.productid = productid;
        this.tutorialid = tutorialid;
        this.recommended = recommended;
    }
    public Tutorial(int productid, String url, String tips, String influencerid){
        this.url = url;
        this.tips = tips;
        this.influencerid = influencerid;
        this.productid = productid;
    }
    
    public void setTutorialName(String tutorialName) {
        this.tutorialName = tutorialName;
    }

    public void setInfluencer(UserAccount influencer) {
        this.influencer = influencer;
    }

    public void setProductPromoted(Product productPromoted) {
        this.productPromoted = productPromoted;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getTutorialid() {
        return tutorialid;
    }

    public void setTutorialid(int tutorialid) {
        this.tutorialid = tutorialid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getInfluencerid() {
        return influencerid;
    }

    public void setInfluencerid(String influencerid) {
        this.influencerid = influencerid;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getTutorialName() {
        return tutorialName;
    }

    public UserAccount getInfluencer() {
        return influencer;
    }

    public Product getProductPromoted() {
        return productPromoted;
    }

    public String getBrand() {
        return brand;
    }

    public String getUrl() {
        return url;
    }

    public String getTips() {
        return tips;
    }

    public static boolean saveTutorial( Tutorial tutorial) {

        try {
            String sql = "INSERT INTO TUTORIALS(TUTORIALID,PRODUCTID,URL,TIPS,INFLUENCERID) VALUES(?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, new Random().nextInt(10000));
            pstmt.setInt(2, tutorial.getProductid());
            pstmt.setString(3, tutorial.getUrl());
            pstmt.setString(4, tutorial.getTips());
            pstmt.setString(5, tutorial.getInfluencerid());
            int i = pstmt.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
      public static boolean updateTutorial( int tutorialid) {

        try {
            String sql = "UPDATE TUTORIALS SET recommended = ? WHERE TUTORIALID="+tutorialid;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setBoolean(1, true);
            int i = pstmt.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public static ArrayList<Tutorial> getAllUserTutorials() {
        try {
            String sql = "SELECT * FROM  TUTORIALS";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Tutorial> TutorialList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("TUTORIALID");
                int tutID = resultSet.getInt("PRODUCTID");
                String proID = resultSet.getString("URL");
                String url = resultSet.getString("TIPS");
                String tips = resultSet.getString("INFLUENCERID");
                int recommended = resultSet.getInt("recommended");
                Tutorial tut = new Tutorial(id,tutID,proID,url,tips,recommended);
               TutorialList.add(tut);
            }
            return TutorialList;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static ArrayList<Tutorial> getTutorialById(String currentUser) {

        try {
            String sql = "SELECT * FROM TUTORIALS WHERE INFLUENCERID='"+currentUser+"'" ;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Tutorial> TutorialList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("TUTORIALID");
                int tutoID = resultSet.getInt("PRODUCTID");
                String proID = resultSet.getString("URL");
                String url = resultSet.getString("TIPS");
                String tips = resultSet.getString("INFLUENCERID");
                int recommended = resultSet.getInt("recommended");
                Tutorial tut = new Tutorial(id,tutoID,proID,url,tips,recommended);
                TutorialList.add(tut);
                
            }
            return TutorialList;
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
     
    }
    public static void deleteTutorial(int tutorialId) throws ClassNotFoundException{
    //delete from the table cart in database where
    //name equals the parameter
   // connect connecto = new connect();
    connect.database();
    try{
        String sql = "DELETE FROM TUTORIALS WHERE TUTORIALID='"+tutorialId+"'" ;
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        int i = preparedStatement.executeUpdate();
        System.out.println(i + "deletion successful");
       
    }
    catch(Exception e){
        System.out.println("Not deleted");
        
    }

    }
    public static ArrayList<Tutorial> getTutorialByProductId(int productid) {

        try {
            String sql = "SELECT * FROM TUTORIALS WHERE PRODUCTID="+productid ;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Tutorial> TutorialList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("TUTORIALID");
                int proID = resultSet.getInt("PRODUCTID");
                String url = resultSet.getString("URL");
                String tips = resultSet.getString("TIPS");
                String infl = resultSet.getString("INFLUENCERID");
                int recommended = resultSet.getInt("recommended");
                Tutorial tut = new Tutorial(id,proID,url,tips,infl,recommended);
                TutorialList.add(tut);
                
            }
            return TutorialList;
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
     
    }
    
}
