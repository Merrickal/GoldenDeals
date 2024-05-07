/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dalyk
 */
public class User {
    public int Id;
    public String Nom;
    public String Prenom;
    public String Email;
    public String Password;
    
    public User(){};
    
    public User(int Id, String Nom, String Prenom, String Email, String Password) {
        this.Id = Id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
        this.Password = Password;
    }
    public User(String Nom, String Prenom, String Email, String Password) {
        super();
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
        this.Password = Password;
    }

    public int getId() {
        return Id;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
}
