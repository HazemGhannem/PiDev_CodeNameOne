/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

/**
 *
 * @author Matoussi
 */
public class Themetest {
    private int id_themetest;
    private String nom;
    private String description;
   
    
    public Themetest(int id_themetest, String nom, String description){
        this.id_themetest = id_themetest;
        this.nom = nom;
        this.description = description;
    }

    public Themetest(String nom) {
        this.nom = nom;
    }
     public Themetest(int id_themetest) {
        this.id_themetest = id_themetest;
    }

    public Themetest() {
//        this.dbconx = DataSource.getInstance().getCnx();
    }

    public Themetest(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public int getId_themetest() {
        return id_themetest;
    }

    public void setId_themetest(int id_themetest) {
        this.id_themetest = id_themetest;
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
    
    @Override
    public String toString() {
        return "themetest{" + "id_themetest=" + id_themetest + ", nom=" + nom + ",description=" + description + '}';
    }
    

}
