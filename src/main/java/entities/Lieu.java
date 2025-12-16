package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 * Entité JPA représentant la table "lieu" en base de données.
 */
@Entity
@Table(name = "lieu")
public class Lieu implements Serializable {

    // Identifiant de version pour la sérialisation
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nom;
    private String description;
    private double latitude;  // Note: j'ai inversé l'ordre pour plus de cohérence
    private double longitude;

    // Constructeur sans argument : OBLIGATOIRE pour JPA
    public Lieu() {
    }

    // Constructeur utilisé par l'EJB (LieuEntrepriseBean)
    // ATTENTION : l'ordre ici est Nom, Description, Latitude, Longitude
    public Lieu(String nom, String description, double latitude, double longitude) {
        this.nom = nom;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // --- Getters et Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    // Méthode pour faciliter le débogage dans la console
    @Override
    public String toString() {
        return "Lieu[ id=" + id + ", nom=" + nom + " ]";
    }
}