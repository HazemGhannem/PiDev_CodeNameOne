/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;


/**
 *
 * @author R I B
 */
public class Article {
     private int article_id;
    private String title;
    private String description;
     private String nbr_like;
    private String image;
    private String date;


    public Article(String title, String description, String image,String date,String nbr_like) {
        this.title=title;
         this.description=description;
         this.image=image;
         this.date=date;
         this.nbr_like=nbr_like;


        
    }

    public Article() {
    }

    @Override
    public String toString() {
        return "Article{" + "article_id=" + article_id + ", title=" + title + ", description=" + description + ", nbr_like=" + nbr_like + ", image=" + image + ", date=" + date + '}';
    }

    public Article(String title) {
        this.title=title;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNbr_like() {
        return nbr_like;
    }

    public void setNbr_like(String nbr_like) {
        this.nbr_like = nbr_like;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
       
      public Article(int article_id, String title, String description,String image,String date,String nbr_like) {
       this.article_id=article_id;
       this.title=title;
       this.description=description;
       this.image=image;
       this.date=date;
       this.nbr_like=nbr_like;
      }
}