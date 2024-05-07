/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dalyk
 */
import static java.lang.System.out;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class userDAO {
    String URL = "jdbc:mysql://localhost:3306/java_project"; 
    String USER = "root"; 
    String PASSWORD = ""; 
    String INSERT_USERS_SQL = "INSERT INTO customers (Nom , Prenom, Email, Password) VALUES (?,?,?,?);";
    String SELECT_USER_BY_ID = "SELECT Id, Nom, Prenom, Email, Password FROM customers WHERE Id= ?;";
    String SELECT_USER_ID_BY_EMAIL_AND_PASSWORD = "SELECT Id FROM customers WHERE Email=? AND Password=?;";
    String SELECT_USERS_SQL = "SELECT * FROM `customers`;";
    String UPDATE_USERS_SQL = "UPDATE customers SET Nom= ?,Prenom= ?,Email= ?,Password= ? WHERE Id= ?;";
    String DELETE_USER_SQL = "DELETE FROM customers WHERE Id= ?;";
    
    Connection getConnection()
    {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");       
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            
            
        } catch (Exception e) {
            out.print("SQLException CONNECTION occurred: " + e.getMessage());
        }
        return con;
    }
    //Insert User
    public void insertUser(User user){
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_USERS_SQL);)
        {
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.executeUpdate();
            
            
        }catch(Exception e){
            out.print("SQLException INSERT occurred: " + e.getMessage());
        }
    
    }
    //Update User
    public boolean updateUser(User user){
        boolean Updated = false;
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_USERS_SQL);)
        {
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getId());
            Updated= ps.executeUpdate()>0;
            
            
        }catch(Exception e){
            out.print("SQLException UPDATE occurred: " + e.getMessage());
        }
        return Updated;
    }
    //Select User
    public int selectUserEmailPassword (String Email, String Password){
        int Id = 0;
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(SELECT_USER_ID_BY_EMAIL_AND_PASSWORD);)
        {
            ps.setString(1, Email);
            ps.setString(2, Password);
            out.print(ps);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Id = rs.getInt("Id");
            }
            
            
            
        }catch(Exception e){
            out.print("SQLException INSERT occurred: " + e.getMessage());
        }
        return Id;
    
    }
    public User selectUser (int oldId){
        User user = null;
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_ID);)
        {
            ps.setInt(1, oldId);
            out.print(ps);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                int Id = rs.getInt("Id");
                String Nom = rs.getString("Nom");
                String Prenom = rs.getString("Prenom");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                user = new User(Id, Nom, Prenom, Email, Password);

            }
            
            
            
        }catch(Exception e){
            out.print("SQLException INSERT occurred: " + e.getMessage());
        }
        return user;
    
    }
    //Select Users
    public List<User> selectUsers (){
        List<User> users = new ArrayList<>();
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(SELECT_USERS_SQL);)
        {
            out.print(ps);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                int Id = rs.getInt("Id");
                String Nom = rs.getString("Nom");
                String Prenom = rs.getString("Prenom");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                users.add(new User(Id, Nom, Prenom, Email, Password));

            }
            
            con.close();
        }catch(Exception e){
            out.print("SQLException INSERT occurred: " + e.getMessage());
        }
        return users;
    }
    //Delete User
    public boolean deleteUser(int Id){
        boolean Deleted = false;
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_USER_SQL);)
        {
            ps.setInt(1, Id);
            Deleted= ps.executeUpdate()>0;
            
            
        }catch(Exception e){
            out.print("SQLException DELETE occurred: " + e.getMessage());
        }
        return Deleted;
    }
    
}
