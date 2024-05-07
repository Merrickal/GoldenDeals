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
import com.mysql.cj.jdbc.Blob;

public class orderDAO {
    String URL = "jdbc:mysql://localhost:3306/java_project"; 
    String USER = "root"; 
    String PASSWORD = ""; 
    String CONFIRM_PRODUCT_ORDER_SQL = "INSERT INTO shipping (IdProduct, IdUser, QteOrder, productName , productDesc, productPrice, shippingStatus) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String SELECT_PRODUCTS_FROM_HISTORY_SQL = "SELECT IdShipping, IdProduct, IdUser, QteOrder, productName , productDesc, productPrice, shippingStatus FROM shipping WHERE IdUser= ?";
    String ADD_PRODUCT_TO_CART_SQL = "INSERT INTO commandes (IdProduct, IdUser, QteOrder, productName , productDesc, productPrice) VALUES (?, ?, ?, ?, ?, ?)";
    String SELECT_PRODUCT_FROM_CART_SQL = "SELECT IdProduct, IdUser, QteOrder , productName, productDesc, productPrice FROM commandes WHERE IdCommande= ?;";
    String SELECT_PRODUCTS_FROM_CART_SQL = "SELECT IdCommande, IdProduct, IdUser, QteOrder, productName, productDesc, productPrice FROM commandes WHERE IdUser= ?;";
    String DELETE_PRODUCT_FROM_CART_SQL = "DELETE FROM commandes WHERE IdCommande= ?;";
    
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
    
    
    public Order selectOrder (int IdCommande){
        Order order = null;
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(SELECT_PRODUCT_FROM_CART_SQL);)
        {
            ps.setInt(1, IdCommande);
            out.print(ps);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                int IdProduct = rs.getInt("IdProduct");
                int IdUser = rs.getInt("IdUser");
                int QteOrder = rs.getInt("QteOrder");
                String productName = rs.getString("productName");
                String productDesc = rs.getString("productDesc");
                int productPrice = rs.getInt("productPrice");
                order = new Order(IdCommande,IdProduct, IdUser, QteOrder, productName, productDesc, productPrice, "Shipping");

            }
  
        }catch(Exception e){
            out.print("SQLException SELECT PRODUCT FROM CART occurred: " + e.getMessage());
        }
        return order;
    
    }
    public List<Order> selectOrders (int IdUser){
        List<Order> orders = new ArrayList<>();
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(SELECT_PRODUCTS_FROM_CART_SQL);)
        {
            ps.setInt(1, IdUser);
            out.print(ps);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                int IdCommande = rs.getInt("IdCommande");
                int IdProduct = rs.getInt("IdProduct");
                IdUser = rs.getInt("IdUser");
                int QteOrder = rs.getInt("QteOrder");
                String productName = rs.getString("productName");
                String productDesc = rs.getString("productDesc");
                int productPrice = rs.getInt("productPrice");
                orders.add(new Order(IdCommande, IdProduct, IdUser, QteOrder, productName, productDesc, productPrice));

            }
            
            con.close();
        }catch(Exception e){
            out.print("SQLException SELECT PRODUCTS FROM CART occurred: " + e.getMessage());
        }
        return orders;
    }
    public boolean addProductToCart(Order order){
        boolean ADDED = false;
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(ADD_PRODUCT_TO_CART_SQL);)
        {
            ps.setInt(1, order.getIdProduct());
            ps.setInt(2, order.getIdUser());
            ps.setInt(3, order.getQteOrder());
            ps.setString(4, order.getName());
            ps.setString(5, order.getDescription());
            ps.setInt(6, order.getPrice());
            ADDED = ps.executeUpdate()>0;
            
            
        }catch(Exception e){
            out.print("SQLException ADD PRODUCT TO CART occurred: " + e.getMessage());
        }
        return ADDED;
    }
    public boolean deleteProductFromCart(int IdCommande){
        boolean Deleted = false;
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_PRODUCT_FROM_CART_SQL);)
        {
            ps.setInt(1, IdCommande);
            Deleted= ps.executeUpdate()>0;
            
            
        }catch(Exception e){
            out.print("SQLException DELETE PRODUCT FROM CART occurred: " + e.getMessage());
        }
        return Deleted;
    }
    
    public List<Order> selectOrdersInHistory (int IdUser){
            List<Order> orders = new ArrayList<>();
            try(Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_PRODUCTS_FROM_HISTORY_SQL);)
            {
                ps.setInt(1, IdUser);
                out.print(ps);
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    int IdShipping = rs.getInt("IdShipping");
                    int IdProduct = rs.getInt("IdProduct");
                    IdUser = rs.getInt("IdUser");
                    int QteOrder = rs.getInt("QteOrder");
                    String productName = rs.getString("productName");
                    String productDesc = rs.getString("productDesc");
                    int productPrice = rs.getInt("productPrice");
                    String shippingStatus = rs.getString("shippingStatus");
                    orders.add(new Order(IdShipping,IdProduct, IdUser, QteOrder, productName, productDesc, productPrice,shippingStatus));

                }

                con.close();
            }catch(Exception e){
                out.print("SQLException SELECT PRODUCTS FROM CART occurred: " + e.getMessage());
            }
            return orders;
        }
}
