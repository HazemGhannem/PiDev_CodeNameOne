/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;
import com.mycompany.utils.Statics;
import com.mycompany.entity.Article;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 *
 * @author R I B
 */
public class ServiceArticle {
    Boolean resultOK;
    public ArrayList<Article> articles;
    // public boolean resultOK;
    
    public HashMap<String,Article>stat;
    public static ServiceArticle instance = null;
  private ConnectionRequest req;
     private ServiceArticle() {
         req = new ConnectionRequest();
    }
       public static ServiceArticle getInstance() {
        if (instance == null) {
            instance = new ServiceArticle();
        }
        return instance;
    }
       public Boolean addArticle(Article t){
              String url = Statics.BASE_URL + "/article/new?title=" + t.getTitle() +"&description="+t.getDescription() +"&image=" + t.getImage()+
            
              "&date="+t.getDate()+"&nbr_like="+t.getNbr_like();
              req.setUrl(url);
               // req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
                ToastBar.showMessage("Votre article est ajoutée", FontImage.MATERIAL_ACCESS_TIME);

        return resultOK;

       }
       public ArrayList<Article>affichageArticle(){
           ArrayList<Article>result = new ArrayList<>();

               String url = Statics.BASE_URL+"/article/article/";
                req.setUrl(url);
                req.setPost(false);

                  req.addResponseListener(new ActionListener<NetworkEvent>() {
               @Override
               public void actionPerformed(NetworkEvent evt) {
                   JSONParser json;
                   json  = new JSONParser();
                   try{
                   Map<String,Object> articleListJson = json.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                   List<Map<String,Object>>listOfMaps= (List<Map<String,Object>>) articleListJson.get("root");
                   for(Map<String,Object> obj : listOfMaps){
//                //Création des tâches et récupération de leurs données
               Article t = new Article();
             float articleId = Float.parseFloat(obj.get("articleId").toString());
               String nbrLike = (obj.get("nbrLike").toString());
                String title = obj.get("title").toString();
                //float article_id = Float.parseFloat(obj.get("article_id").toString());
                String description = obj.get("description").toString();
                String image = obj.get("image").toString();
                String date = obj.get("date").toString();
                         t.setArticle_id((int) Float.parseFloat(obj.get("articleId").toString()));
               // t.setArticle_id((int)article_id);
              //t.setNbr_like(obj.get("nbr_like").toString());
              // t.setArticle_id((int)article_id);

                t.setTitle(obj.get("title").toString());
                t.setDescription(obj.get("description").toString());
                t.setImage(obj.get("image").toString());
                t.setNbr_like((obj.get("nbrLike").toString()));
                t.setDate(obj.get("date").toString());
//               
               result.add(t);
                   }
                   } catch(Exception ex){
                       ex.printStackTrace();
                   }
               }


                    });
                      NetworkManager.getInstance().addToQueueAndWait(req);
                      return result;

       } 
       public Article DetailArticle( int article_id,Article article){
          String url = Statics.BASE_URL + "/article/show/" +article_id;
      req.setUrl(url);
      String str = new String (req.getResponseData());
      req.addResponseListener(((e)-> {

                 JSONParser json;
                 json  = new JSONParser();
                  try{
          Map<String,Object> obj = json.parseJSON(new CharArrayReader(new String(str).toCharArray()));
          article.setTitle(obj.get("title").toString());
          article.setDescription(obj.get("description").toString());
          article.setImage(obj.get("image").toString());
          article.setNbr_like((obj.get("nbr_like").toString()));
          article.setDate(obj.get("date").toString());


                  }catch(IOException ex){
                      System.out.println(""+ex.getMessage());
                  }
           System.out.println("data==="+str);
       }));
        NetworkManager.getInstance().addToQueueAndWait(req);
                        ToastBar.showMessage("Votre article est supprimeé", FontImage.MATERIAL_ACCESS_TIME);

        return article;

       }
       public boolean updatearticle(Article t) {
        String url = Statics.BASE_URL + "/article/edit/" + t.getArticle_id()+"?title=" + t.getTitle()+"&description="+t.getDescription() +"&image=" + t.getImage()+
            
              "&date="+t.getDate()+"&nbr_like="+t.getNbr_like();
//création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener       
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(resultOK);
        return resultOK;
    }

//  public boolean addArticle(Article t) {
//        String url = Statics.BASE_URL + "/article/new" + t.getTitle() + "/" + t.getImage()+"/"+t.getDescription()+
//                "/"+t.getDate()+"/"+t.getNbr_like();
////création de l'URL
//        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this); //Supprimer cet actionListener
//                /* une fois que nous avons terminé de l'utiliser.
//                La ConnectionRequest req est unique pour tous les appels de 
//                n'importe quelle méthode du Service task, donc si on ne supprime
//                pas l'ActionListener il sera enregistré et donc éxécuté même si 
//                la réponse reçue correspond à une autre URL(get par exemple)*/
//                
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
//    public ArrayList<Article> parsearticle(String jsonText){
//        try {
//            articles=new ArrayList<>();
//            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
//
//            /*
//                On doit convertir notre réponse texte en CharArray à fin de
//            permettre au JSONParser de la lire et la manipuler d'ou vient 
//            l'utilité de new CharArrayReader(json.toCharArray())
//            
//            La méthode parse json retourne une MAP<String,Object> ou String est 
//            la clé principale de notre résultat.
//            Dans notre cas la clé principale n'est pas définie cela ne veux pas
//            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
//            qui est root.
//            En fait c'est la clé de l'objet qui englobe la totalité des objets 
//                    c'est la clé définissant le tableau de tâches.
//            */
//            Map<String,Object> articleListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            
//              /* Ici on récupère l'objet contenant notre liste dans une liste 
//            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
//            
//            Le format Json impose que l'objet soit définit sous forme
//            de clé valeur avec la valeur elle même peut être un objet Json.
//            Pour cela on utilise la structure Map comme elle est la structure la
//            plus adéquate en Java pour stocker des couples Key/Value.
//            
//            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
//            sa valeur est une liste d'objets Json, donc une liste de Map
//            */
//            List<Map<String,Object>> list = (List<Map<String,Object>>)articleListJson.get("root");
//            
//            //Parcourir la liste des tâches Json
//            for(Map<String,Object> obj : list){
//                //Création des tâches et récupération de leurs données
//                Article t = new Article();
//                float article_id = Float.parseFloat(obj.get("article_id").toString());
//                t.setArticle_id((int)article_id);
//                t.setNbr_like(((int)Integer.parseInt(obj.get("nbr_like").toString())));
//                t.setTitle(obj.get("title").toString());
//                t.setDescription(obj.get("description").toString());
//                t.setImage(obj.get("image").toString());
//                t.setDate(obj.get("date").toString());
//
//
//
//                //Ajouter la tâche extraite de la réponse Json à la liste
//                articles.add(t);
//            }
//            
//            
//        } catch (IOException ex) {
//            
//        }
//         /*
//            A ce niveau on a pu récupérer une liste des tâches à partir
//        de la base de données à travers un service web
//        
//        */
//        return articles;
//    }
    
//    public ArrayList<Article> getAllArticles(){
//        String url = Statics.BASE_URL+"/article/article/";
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                articles = parsearticle(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//          NetworkManager.getInstance().addToQueueAndWait(req);
//        return articles;
//    }
//    public Article DetailArticle( int article_id,Article article){
//        String url = Statics.BASE_URL + "/article/show/" +article_id;
//        req.setUrl(url);
//        String str=new String(req.getResponseData());
//                req.addResponseListener(((evt) -> {
//                JSONParser jsonp=new JSONParser();
//                try{
//             Map<String,Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
//            article.setTitle(obj.get("title").toString());
//            article.setDescription(obj.get("description").toString());
//            article.setImage(obj.get("image").toString());
//            article.setNbr_like(((int)Integer.parseInt(obj.get("nbr_like").toString())));
//            article.setDate(obj.get("date").toString());
//
//            
//                }
//                catch(IOException ex){
//                    System.out.println("error related to sql!"+ex.getMessage()); 
//                }
//                
//                    System.out.println("data ====="+str);
//                }));
//
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return article ;
//       
//    }
       public boolean deleteArticle(Article t) {
        String url = Statics.BASE_URL +"/article/deleted/"+t.getArticle_id();
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

   private HashMap<String, Article> parseStatEtat(String s) {
        HashMap<String,Article> hashMap = new HashMap<>() ;
        JSONParser j = new JSONParser();
                       Article t = new Article();

        Map<String, Object> tasksListJson = null;
        try {
            tasksListJson = j.parseJSON(new CharArrayReader(s.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
      System.out.print(""+t);
        String nbrLike = (String) tasksListJson.get("ok");
//        double nbrLike1 = (double) tasksListJson.get("3 Etoiles");
//        double nbrLike2 = (double) tasksListJson.get("4 Etoiles");
//        double nbrLike3 = (double) tasksListJson.get("5 Etoiles");
        hashMap.put(("nbrLike"),t) ;
//        hashMap.put("3 Etoiles",(int) nbrLike1) ;
//        hashMap.put("4 Etoiles",(int) nbrLike2) ;
//        hashMap.put("5 Etoiles",(int) nbrLike3) ;
        return hashMap ;
    }

public HashMap<String,Article> statetat(){
          String url = Statics.BASE_URL + "/statistiques" ;
       req.setPost(false);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    stat = parseStatEtat(new String(req.getResponseData()));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return stat;
    }

}