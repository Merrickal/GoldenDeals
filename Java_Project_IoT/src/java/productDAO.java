/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dalyk
 */
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import static java.lang.System.out;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import com.mysql.cj.jdbc.Blob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
public class productDAO {
    String URL = "jdbc:mysql://localhost:3306/java_project"; 
    String USER = "root"; 
    String PASSWORD = ""; 
    String INSERT_PRODUCTS_SQL = "INSERT INTO products (productName , productDesc, productQte, productPrice, productCategorie) VALUES (?,?,?,?,?);";
    String SELECT_PRODUCT_BY_ID = "SELECT productName , productDesc, productQte, productPrice, productCategorie FROM products WHERE Id= ?;";
    String SELECT_PRODUCTS_SQL = "SELECT * FROM products;";
    String UPDATE_PRODUCT_SQL = "UPDATE products SET  productName= ?,productDesc= ?,productQte= ?,productPrice= ?, productCategorie= ? WHERE Id= ?;";
    String DELETE_PRODUCT_SQL = "DELETE FROM products WHERE Id= ?;";
    String QUERY_PRODUCTS_BY_NAME_SQL = "SELECT * FROM products WHERE productName= ?;";
    String QUERY_PRODUCTS_BY_CATEGORIE_SQL = "SELECT * FROM products WHERE productCategorie= ?;";
    String QUERY_PRODUCTS_BY_PRICE_SQL = "SELECT * FROM products WHERE productPrice > ?;";
    String QUERY_PRODUCTS_BY_ALL_SQL = "SELECT * FROM products WHERE productPrice > ? AND productCategorie= ?;";

    
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
    public void insertProduct(Product product){
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_PRODUCTS_SQL);)
        {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setInt(3, product.getQte());
            ps.setInt(4, product.getPrice());
            ps.setString(5, product.getproductCategorie());
            ps.executeUpdate();
            
            
        }catch(Exception e){
            out.print("SQLException INSERT occurred: " + e.getMessage());
        }
    
    }
    //Update User
    public boolean updateProduct(Product product){
        boolean Updated = false;
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_PRODUCT_SQL);)
        {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setInt(3, product.getQte());
            ps.setInt(4, product.getPrice());
            ps.setString(5, product.getproductCategorie());
            ps.setInt(6, product.getId());
            Updated= ps.executeUpdate()>0;
            
            
        }catch(Exception e){
            out.print("SQLException UPDATE occurred: " + e.getMessage());
        }
        return Updated;
    }
    //Select User
    public Product selectProduct (int Id){
        Product product = null;
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(SELECT_PRODUCT_BY_ID);)
        {
            ps.setInt(1, Id);
            out.print(ps);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                String productName = rs.getString("productName");
                String productDesc = rs.getString("productDesc");
                int productQte = rs.getInt("productQte");
                int productPrice = rs.getInt("productPrice");
                String productCategorie = rs.getString("productCategorie");
                product = new Product(Id,productName, productDesc, productQte, productPrice, productCategorie);

            }
            
            
            
        }catch(Exception e){
            out.print("SQLException INSERT occurred: " + e.getMessage());
        }
        return product;
    
    }
    //Select Users
    public List<Product> selectProducts (){
        List<Product> products = new ArrayList<>();
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(SELECT_PRODUCTS_SQL);)
        {
            out.print(ps);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                int Id = rs.getInt("Id");
                String productName = rs.getString("productName");
                String productDesc = rs.getString("productDesc");
                int productQte = rs.getInt("productQte");
                int productPrice = rs.getInt("productPrice");
                String productCategorie = rs.getString("productCategorie");
                products.add(new Product(Id, productName, productDesc, productQte, productPrice, productCategorie));

            }
            
            con.close();
        }catch(Exception e){
            out.print("SQLException INSERT occurred: " + e.getMessage());
        }
        return products;
    }
    //Delete User
    public boolean deleteProduct(int Id){
        boolean Deleted = false;
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_PRODUCT_SQL);)
        {
            ps.setInt(1, Id);
            Deleted= ps.executeUpdate()>0;
            
            
        }catch(Exception e){
            out.print("SQLException DELETE occurred: " + e.getMessage());
        }
        return Deleted;
    }
    public List<Product> queryProductsByName (String productName){
        List<Product> products = new ArrayList<>();
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(QUERY_PRODUCTS_BY_NAME_SQL);)
        {
            ps.setString(1, productName);
            out.print(ps);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                int Id = rs.getInt("Id");
                productName = rs.getString("productName");
                String productDesc = rs.getString("productDesc");
                int productQte = rs.getInt("productQte");
                int productPrice = rs.getInt("productPrice");
                String productCategorie = rs.getString("productCategorie");
                products.add(new Product(Id, productName, productDesc, productQte, productPrice, productCategorie));

            }
            
            con.close();
        }catch(Exception e){
            out.print("SQLException INSERT occurred: " + e.getMessage());
        }
        return products;
    }
    public List<Product> queryProductsByCategorie (String productCategorie){
        List<Product> products = new ArrayList<>();
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(QUERY_PRODUCTS_BY_CATEGORIE_SQL);)
        {
            ps.setString(1, productCategorie);
            out.print(ps);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                int Id = rs.getInt("Id");
                String productName = rs.getString("productName");
                String productDesc = rs.getString("productDesc");
                int productQte = rs.getInt("productQte");
                int productPrice = rs.getInt("productPrice");
                productCategorie = rs.getString("productCategorie");
                products.add(new Product(Id, productName, productDesc, productQte, productPrice, productCategorie));

            }
            
            con.close();
        }catch(Exception e){
            out.print("SQLException INSERT occurred: " + e.getMessage());
        }
        return products;
    }
    public List<Product> queryProductsByPrice (int productPrice){
        List<Product> products = new ArrayList<>();
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(QUERY_PRODUCTS_BY_PRICE_SQL);)
        {
            ps.setInt(1, productPrice);
            out.print(ps);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                int Id = rs.getInt("Id");
                String productName = rs.getString("productName");
                String productDesc = rs.getString("productDesc");
                int productQte = rs.getInt("productQte");
                productPrice = rs.getInt("productPrice");
                String productCategorie = rs.getString("productCategorie");
                products.add(new Product(Id, productName, productDesc, productQte, productPrice, productCategorie));

            }
            
            con.close();
        }catch(Exception e){
            out.print("SQLException INSERT occurred: " + e.getMessage());
        }
        return products;
    }
    public List<Product> queryProductsByAll (int productPrice, String productCategorie){
        List<Product> products = new ArrayList<>();
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(QUERY_PRODUCTS_BY_ALL_SQL);)
        {
            ps.setInt(1, productPrice);
            ps.setString(2, productCategorie);
            out.print(ps);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                int Id = rs.getInt("Id");
                String productName = rs.getString("productName");
                String productDesc = rs.getString("productDesc");
                int productQte = rs.getInt("productQte");
                productPrice = rs.getInt("productPrice");
                productCategorie = rs.getString("productCategorie");
                products.add(new Product(Id, productName, productDesc, productQte, productPrice, productCategorie));

            }
            
            con.close();
        }catch(Exception e){
            out.print("SQLException INSERT occurred: " + e.getMessage());
        }
        return products;
    }
    
    
}
