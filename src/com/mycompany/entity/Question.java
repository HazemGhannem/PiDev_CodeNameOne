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
public class Question {
    private String libelle;
    private int id_themetest;
    private int id_question;
    private String nom;
    
    
    public Question(int id_themetest, String libelle) {
     
        this.id_themetest = id_themetest;
        this.libelle = libelle;
    }

    public Question() {
    }
   

    public String getLibelle() {
        return libelle;
    }

    public Question(String libelle) {
        this.libelle = libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getId_themetest() {
        return id_themetest;
    }

    public void setId_themetest(int id_themetest) {
        this.id_themetest = id_themetest;
    }

    public int getId_question() {
        return id_question;
    }

    public Question(String libelle, int id_question) {
        this.libelle = libelle;
        this.id_question = id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Question(String libelle, int id_question, String nom) {
        this.libelle = libelle;
        this.id_question = id_question;
        this.nom = nom;
    }
    
    @Override
    public String toString() {
        return "question{" + "libelle=" + libelle + ", nom=" + nom + ", id_question=" + id_question + '}';

//    @Override
//    public String toString() {
//        return "question{" + "libelle=" + libelle + ", nom=" + nom + '}';
//    }

    }
}
