
import com.mysql.cj.jdbc.Blob;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dalyk
 */
public class Order {
    public int Id;
    public int IdProduct;
    public int IdUser;
    public int QteOrder;
    public String Name;
    public String Description;
    public int Price;
    public String Status;
    
    public Order(){}
    
    public Order(int Id, int IdProduct, int IdUser, int QteOrder){
        this.Id = Id;
        this.IdProduct = IdProduct;
        this.IdUser = IdUser;
        this.QteOrder = QteOrder;
    }
    public Order(int Id, int IdProduct, int IdUser, int QteOrder, String Name, String Description, int Price){
        this.Id = Id;
        this.IdProduct = IdProduct;
        this.IdUser = IdUser;
        this.QteOrder = QteOrder;
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
    }
    public Order(int IdProduct, int IdUser, int QteOrder, String Name, String Description, int Price){
        this.IdProduct = IdProduct;
        this.IdUser = IdUser;
        this.QteOrder = QteOrder;
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
    }
    
    
    public Order(int Id, int IdProduct, int IdUser, int QteOrder, String Status){
        this.Id = Id;
        this.IdProduct = IdProduct;
        this.IdUser = IdUser;
        this.QteOrder = QteOrder;
        this.Status = Status;
    }

    public Order(int Id, int IdProduct, int IdUser, int QteOrder, String Name, String Description, int Price, String Status){
        this.Id = Id;
        this.IdProduct = IdProduct;
        this.IdUser = IdUser;
        this.QteOrder = QteOrder;
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
        this.Status = Status;
    }
    

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    public int getQteOrder() {
        return QteOrder;
    }

    public void setQteOrder(int QteOrder) {
        this.QteOrder = QteOrder;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(int IdProduct) {
        this.IdProduct = IdProduct;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }
    
    
}
