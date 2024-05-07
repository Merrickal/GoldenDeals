/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.mysql.cj.jdbc.Blob;
import java.io.Serializable;

/**
 *
 * @author ghazi
 */
public class Product implements Serializable  {
    public int Id;
    public String Name;
    public String Description;
    public int Qte;
    public int Price;
    public String productCategorie;
    
    public Product(){};
     
    public Product(String Name, String Description, int Qte,int Price, String productCategorie) {
        this.Name = Name;
        this.Description = Description;
        this.Qte = Qte;
        this.Price = Price;
        this.productCategorie = productCategorie;
    }
    public Product(String Name, String Description, int Qte,int Price) {
        this.Name = Name;
        this.Description = Description;
        this.Qte = Qte;
        this.Price = Price;
    }
    public Product(int Id, String Name, String Description, int Qte, int Price, String productCategorie) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.Qte = Qte;
        this.Price = Price;
        this.productCategorie = productCategorie;
    }
    public Product(int Id, String Name, String Description, int Qte, int Price) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.Qte = Qte;
        this.Price = Price;
    }
    

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
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
    public int getQte() {
        return Qte;
    }

    public void setQte(int Qte) {
        this.Qte = Qte;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }
    
    public String getproductCategorie() {
        return productCategorie;
    }
    public void setproductCategorie(String productCategorie) {
        this.productCategorie = productCategorie;
    }


}
