/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.utils.Statics;
import com.mycompany.entity.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author R I B
 */
public class ServiceComment {
    public ArrayList<Comment> comments;
     public boolean resultOK;
    public static ServiceComment instance = null;
  private ConnectionRequest req;
    private ServiceComment() {
         req = new ConnectionRequest();
    }
      
     public static ServiceComment getInstance() {
        if (instance == null) {
            instance = new ServiceComment();
        }
        return instance;
    } 
     //add
      public boolean addComment(Comment t) {
        String url = Statics.BASE_URL + "/comment/new" + t.getContent() + "/" + t.getIdUser()+"/"+t.getArticle_id();
               
//création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
      
        public ArrayList<Comment> afiichagecomment() {
        ArrayList<Comment> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/comment/comment";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapQuestion = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapQuestion.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
               //Création des tâches et récupération de leurs données
                        Comment t = new Comment();
                     float commentId = Float.parseFloat(obj.get("commentId").toString());
                     

                        String content = obj.get("content").toString();
                       String title = obj.get("article").toString();
                        t.setComment_id((int)commentId);
                        t.setContent(content);
                    //    t.setTitle(title);
                    

                        result.add(t);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            }
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    } 
//affichage
         
//       public ArrayList<Comment> getAllComment(){
//        String url = Statics.BASE_URL+"/comment/comment";
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                comments = parseTasks(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return comments;
//    }
       //details
    public Comment Detailcomment( int comment_id,Comment comment){
        String url = Statics.BASE_URL + "/comment/show/" +comment_id;
        req.setUrl(url);
        String str=new String(req.getResponseData());
                req.addResponseListener(((evt) -> {
                JSONParser jsonp=new JSONParser();
                try{
             Map<String,Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
            comment.setContent(obj.get("title").toString());
            comment.setArticle_id(((int)Integer.parseInt(obj.get("article_id").toString())));
            comment.setIdUser(((int)Integer.parseInt(obj.get("idUser").toString())));
            

            
                }
                catch(IOException ex){
                    System.out.println("error related to sql!"+ex.getMessage()); 
                }
                
                    System.out.println("data ====="+str);
                }));

        NetworkManager.getInstance().addToQueueAndWait(req);
        return comment ;
       
    }
public boolean deleteComment(Comment t) {
        String url = Statics.BASE_URL +"/comment/deleted/"+t.getComment_id();
//création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(resultOK);
        return resultOK;
    }

}