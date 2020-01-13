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
public class UserAccount {
    private int id;

    private String username;

    private String password;

    public String role;

    private String firstName;

    private String lastName;

    private String email;
    private Boolean isAdmin;
    private Boolean isCustomer;
    private Boolean isAmbassador;
    private Boolean isInfluencer;

    public UserAccount(String username, String password, String firstName, String lastName, Boolean isAdmin, Boolean isCustomer, Boolean isAmbassador, Boolean isInfluencer) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
        this.isCustomer = isCustomer;
        this.isAmbassador = isAmbassador;
        this.isInfluencer = isInfluencer;
    }
    

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsCustomer() {
        return isCustomer;
    }

    public void setIsCustomer(Boolean isCustomer) {
        this.isCustomer = isCustomer;
    }

    public Boolean getIsAmbassador() {
        return isAmbassador;
    }

    public void setIsAmbassador(Boolean isAmbassador) {
        this.isAmbassador = isAmbassador;
    }

    public Boolean getIsInfluencer() {
        return isInfluencer;
    }

    public void setIsInfluencer(Boolean isInfluencer) {
        this.isInfluencer = isInfluencer;
    }
    
    
    public UserAccount() {

    }

    protected UserAccount(String role) {
        this.role = role;
    }

    public UserAccount(String firstName, String lastName, String username, String password, String role ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    //getters and setters for the attributes
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
    public static ArrayList<UserAccount> getAllUserAccounts() {

        try {
            String sql = "SELECT * FROM  USERS";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<UserAccount> userAccountsList = new ArrayList<>();
            while (resultSet.next()) {
                String username = resultSet.getString("USERNAME");
                String password = resultSet.getString("PASSWORD");
                String firstname = resultSet.getString("FIRSTNAME");
                String lastname = resultSet.getString("LASTNAME");
                Boolean isAdmin = resultSet.getBoolean("ISADMIN");
                Boolean isINF = resultSet.getBoolean("ISINFLUENCER");
                Boolean isCUST = resultSet.getBoolean("ISCUSTOMER");
                Boolean isAMB = resultSet.getBoolean("ISAMBASSADOR");
                UserAccount account = new UserAccount();
                account.setUsername(username);
                account.setFirstName(firstname);
                account.setLastName(lastname);
                account.setPassword(password);
                account.setIsAdmin(isAdmin);
                account.setIsCustomer(isCUST);
                account.setIsAmbassador(isAMB);
                account.setIsInfluencer(isINF);
               userAccountsList.add(account);
            }
            return userAccountsList;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static UserAccount getUserAccountByUserName(String query) {

        try {
            String sql = "SELECT * FROM USERS WHERE USERNAME='"+query+"'" ;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("USERNAME");
                String password = resultSet.getString("PASSWORD");
                String firstname = resultSet.getString("FIRSTNAME");
                String lastname = resultSet.getString("LASTNAME");
                Boolean isADMIN = resultSet.getBoolean("ISADMIN");
                Boolean isCUSTOMER = resultSet.getBoolean("ISCUSTOMER");
                Boolean isINFLUENCER = resultSet.getBoolean("ISINFLUENCER");
                Boolean isAMBASSADOR = resultSet.getBoolean("ISAMBASSADOR");
                UserAccount account = new UserAccount();
                account.setUsername(username);
                account.setFirstName(firstname);
                account.setLastName(lastname);
                account.setPassword(password);
                account.setIsAdmin(isADMIN);
                account.setIsCustomer(isCUSTOMER);
                account.setIsInfluencer(isINFLUENCER);
                account.setIsAmbassador(isAMBASSADOR);
                return account;

            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
     
    }
    public static boolean saveUserAccount(UserAccount userAccount) {

        try {
            String sql = "INSERT INTO USERS(FIRSTNAME,LASTNAME,USERNAME,PASSWORD,ISADMIN,ISCUSTOMER,ISINFLUENCER,ISAMBASSADOR) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userAccount.getFirstName());
            pstmt.setString(2, userAccount.getLastName());
            pstmt.setString(3, userAccount.getUsername());
            pstmt.setString(4, userAccount.getPassword());
            pstmt.setBoolean(5, userAccount.getIsAdmin());
            pstmt.setBoolean(6, userAccount.getIsCustomer());
            pstmt.setBoolean(7, userAccount.getIsInfluencer());
            pstmt.setBoolean(8, userAccount.getIsAmbassador());
            int i = pstmt.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static void deleteUserAccount(String userid) throws ClassNotFoundException{
    
    try{
        String sql = "DELETE FROM USERS WHERE USERNAME='"+userid+"'" ;
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        int i = preparedStatement.executeUpdate();
        System.out.println(i + "deletion successful");
       
    }
    catch(Exception e){
        System.out.println("Not deleted");
        
    }

    }
  //  public static void main(String[]args){
  //      getUserAccountByUserName("tatie");
  //  }
    
}
