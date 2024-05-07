
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dalyk
 */
public class shipOrderDAO {
    String URL = "jdbc:mysql://localhost:3306/java_project"; 
    String USER = "root"; 
    String PASSWORD = ""; 
    String CONFIRM_PRODUCT_ORDER_SQL = "INSERT INTO shipping (IdCommande, IdProduct, IdUser, QteOrder, productName , productDesc, productPrice, Location, Bank_Details) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String SELECT_PRODUCTS_FROM_HISTORY_SQL = "SELECT IdShipping, IdCommande,IdProduct, IdUser, QteOrder, productName , productDesc, productPrice, shippingStatus, Location FROM shipping WHERE IdUser= ?";
    
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
    public boolean shipProduct(shipOrder shiporder){
        boolean SHIPPED = false;
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(CONFIRM_PRODUCT_ORDER_SQL);)
        {
            String Shipping = "Shipping In Progress...";
            ps.setInt(1, shiporder.getIdCommande());
            ps.setInt(2, shiporder.getIdProduct());
            ps.setInt(3, shiporder.getIdUser());
            ps.setInt(4, shiporder.getQteOrder());
            ps.setString(5, shiporder.getName());
            ps.setString(6, shiporder.getDescription());
            ps.setInt(7, shiporder.getPrice());
            ps.setString(8, shiporder.getLocation());
            ps.setString(9, shiporder.getBank_Details());
            SHIPPED = ps.executeUpdate()>0;
            
            
        }catch(Exception e){
            out.print("SQLException CONFIRM PRODUCT SHIPPING occurred: " + e.getMessage());
        }
        return SHIPPED;
    }
    public List<shipOrder> selectOrdersInHistory (int IdUser){
            List<shipOrder> shiporders = new ArrayList<>();
            try(Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_PRODUCTS_FROM_HISTORY_SQL);)
            {
                ps.setInt(1, IdUser);
                out.print(ps);
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    int IdShipping = rs.getInt("IdShipping");
                    int IdCommande = rs.getInt("IdCommande");
                    int IdProduct = rs.getInt("IdProduct");
                    IdUser = rs.getInt("IdUser");
                    int QteOrder = rs.getInt("QteOrder");
                    String productName = rs.getString("productName");
                    String productDesc = rs.getString("productDesc");
                    int productPrice = rs.getInt("productPrice");
                    String shippingStatus = rs.getString("shippingStatus");
                    String Location = rs.getString("Location");
                    shiporders.add(new shipOrder(IdShipping, IdCommande ,IdProduct, IdUser, QteOrder, productName, productDesc, productPrice,shippingStatus, Location));

                }

                con.close();
            }catch(Exception e){
                out.print("SQLException SELECT PRODUCTS FROM CART occurred: " + e.getMessage());
            }
            return shiporders;
        }
}
