/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jakarta.udbl.jakartamission;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.IOException;

@Named (value = "navigationController")
@RequestScoped
public class NavigationBean {
    public void voirApropos(){
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("Pages/a_propos.xhtml");
        }catch(IOException e){
                e.printStackTrace();
        } 
    }
   public void voirLieu(){
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("Pages/lieu.xhtml"); 
        }catch(IOException e){
                e.printStackTrace();
        } 
    }
}
