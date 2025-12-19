package com.jakarta.udbl.jakartamission;

import business.UtilisateurEntrepriseBean;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 * Bean gérant l'ajout d'un utilisateur avec validation croisée des mots de passe.
 * @author joelm
 */
@Named(value = "utilisateurBean") // Doit correspondre à #{utilisateurBean} dans le XHTML
@RequestScoped
    public class UtilisateurBean implements Serializable {

        @EJB
        private UtilisateurEntrepriseBean utilisateurService;

        private String username;
        private String email;
        private String password;
        private String confirmPassword;
        private String description;

        public void ajouterUtilisateur() {
        FacesContext context = FacesContext.getCurrentInstance();

        // 1. Vérification de la correspondance des mots de passe
        if (password == null || !password.equals(confirmPassword)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Les mots de passe ne correspondent pas", null));
            return;
        }

        try {
            // 2. VÉRIFICATION : L'utilisateur existe-t-il déjà ?
            // On vérifie par l'email (ou vous pouvez ajouter une méthode pour vérifier le username)
            if (utilisateurService.trouverUtilisateurParEmail(email) != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Ce nom d'utilisateur et cette adresse existent déjà.", null));
                return; // On arrête tout ici, l'insertion n'est pas appelée
            }

            // 3. Insertion seulement si l'utilisateur n'existe pas
            utilisateurService.ajouterUtilisateurEntreprise(username, email, password, description);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Utilisateur ajouté avec succès", null));

            // Réinitialisation des champs
            this.username = "";
            this.email = "";
            this.password = "";
            this.confirmPassword = "";
            this.description = "";

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Erreur lors de l'insertion : " + e.getMessage(), null));
        }
    }
    // N'oubliez pas TOUS les Getters et Setters (surtout pour confirmPassword)
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}