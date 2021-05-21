/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.util.Date;
import java.io.InputStream;

import java.util.*;
import javafx.scene.control.DatePicker;
/**
 *
 * @author safouane
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Personne {

    private int id;
    private String nom;
    private String prenom;
    private String date;
    private String sex;
    private String mdp;
    private String mail;
    private String image;
    private String type;

    
    
    
    public Personne(String nom, String prenom, String date, String sex, String mdp, String mail, String image, String type) {
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.sex = sex;
        this.mdp = mdp;
        this.mail = mail;
        this.image = image;
        this.type = type;
    }

    public Personne(int id, String nom, String prenom, String date, String sex, String mdp, String mail, String image, String type) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.sex = sex;
        this.mdp = mdp;
        this.mail = mail;
        this.image = image;
        this.type = type;
    }

    public Personne() {
    }

 

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", date=" + date + ", sex=" + sex + ", mdp=" + mdp + ", mail=" + mail + ", image=" + image + ", type=" + type + '}';
    }

   
    
    
    
    
    
    
    
    
    
    
    
    
    
}
