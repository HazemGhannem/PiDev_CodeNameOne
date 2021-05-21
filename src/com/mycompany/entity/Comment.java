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
public class Comment {
     private int comment_id;
    private String content;
    private int article;
    private  int idUser;
    private String title;

    public Comment() {
    }

    public Comment(int comment_id, String content, int article, int idUser) {
        this.comment_id = comment_id;
        this.content = content;
        this.article = article;
        this.idUser = idUser;
        
    }

    public Comment(int comment_id, String content, String title) {
        this.comment_id = comment_id;
        this.content = content;
        this.title = title;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getArticle_id() {
        return article;
    }

    public void setArticle_id(int article) {
        this.article = article;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Comment{" + "comment_id=" + comment_id + ", content=" + content + ", title=" + title + '}';
    }

   
    
}