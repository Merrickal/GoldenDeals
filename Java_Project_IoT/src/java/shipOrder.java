/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dalyk
 */
public class shipOrder {
    public int IdShipping;
    public int IdCommande;
    public int IdProduct;
    public int IdUser;
    public int QteOrder;
    public String Name;
    public String Description;
    public int Price;
    public String Status;
    public String Location;
    public String Bank_Details;
    
    public shipOrder(){}

    public shipOrder(int IdShipping, int IdCommande,int IdProduct, int IdUser, int QteOrder, String Name, String Description, int Price, String Status, String Location, String Bank_Details) {
        this.IdShipping = IdShipping;
        this.IdCommande = IdCommande;
        this.IdProduct = IdProduct;
        this.IdUser = IdUser;
        this.QteOrder = QteOrder;
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
        this.Status = Status;
        this.Location = Location;
        this.Bank_Details = Bank_Details;
    }
    public shipOrder(int IdCommande, int IdProduct, int IdUser, int QteOrder, String Name, String Description, int Price, String Status, String Location, String Bank_Details) {
        this.IdCommande = IdCommande;
        this.IdProduct = IdProduct;
        this.IdUser = IdUser;
        this.QteOrder = QteOrder;
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
        this.Status = Status;
        this.Location = Location;
        this.Bank_Details = Bank_Details;
    }

    public shipOrder(int IdShipping, int IdCommande, int IdProduct, int IdUser, int QteOrder, String Name, String Description, int Price, String Status, String Location) {
        this.IdShipping = IdShipping;
        this.IdCommande = IdCommande;
        this.IdProduct = IdProduct;
        this.IdUser = IdUser;
        this.QteOrder = QteOrder;
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
        this.Status = Status;
        this.Location = Location;
    }
    public shipOrder(int IdShipping, int IdCommande, int IdProduct, int IdUser, int QteOrder, String Name, String Description, int Price, String Status) {
        this.IdShipping = IdShipping;
        this.IdCommande = IdCommande;
        this.IdProduct = IdProduct;
        this.IdUser = IdUser;
        this.QteOrder = QteOrder;
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
        this.Status = Status;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public int getIdCommande() {
        return IdCommande;
    }

    public void setIdCommande(int IdCommande) {
        this.IdCommande = IdCommande;
    }
    public String getBank_Details() {
        return Bank_Details;
    }

    public void setBank_Details(String Bank_Details) {
        this.Bank_Details = Bank_Details;
    }
      

    public int getIdShipping() {
        return IdShipping;
    }

    public void setIdShipping(int IdShipping) {
        this.IdShipping = IdShipping;
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
}
