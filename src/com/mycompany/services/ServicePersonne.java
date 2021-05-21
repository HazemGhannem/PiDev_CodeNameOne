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
import com.mycompany.entity.Personne;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author R I B
 */
public class ServicePersonne {

    boolean resultOK;
    public ArrayList<Personne> comments;

    public static ServicePersonne instance = null;
    private ConnectionRequest req;

    private ServicePersonne() {
        req = new ConnectionRequest();
    }

    public static ServicePersonne getInstance() {
        if (instance == null) {
            instance = new ServicePersonne();
        }
        return instance;
    }
    //add

    public boolean addPersonne(Personne t) {
        String url = Statics.BASE_URL + "/personne/personne_new?mail=" + t.getMail() + "&mdp=" + t.getMdp() + "&nom=" + t.getNom() + "&prenom=" + t.getPrenom() + "&sex=" + t.getSex() + "&type=" + t.getType();

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

    public ArrayList<Personne> affichagePersonne() {
        ArrayList<Personne> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/personne";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapPersonne = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapPersonne.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        //Création des tâches et récupération de leurs données
                        Personne t = new Personne();
                        System.out.println(obj.get("id"));
                        String Themetest_id = obj.get("id").toString();
                        System.out.println(Themetest_id);
                        float id = Float.parseFloat(Themetest_id);
                        System.out.println(id);
                        t.setId((int) id);
                        t.setMail(obj.get("mail").toString());
                        t.setMdp(obj.get("mdp").toString());
                        t.setNom(obj.get("nom").toString());
                        t.setPrenom(obj.get("prenom").toString());
                        t.setSex(obj.get("sex").toString());
                        t.setType(obj.get("type").toString());
                        t.setImage(obj.get("image").toString());

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
    
    
         public boolean updatePersonne(Personne t) {
        String url = Statics.BASE_URL + "/personne/edit/" + t.getId()+"?mail=" + t.getMail() + "&mdp=" + t.getMdp() + "&nom=" + t.getNom() + "&prenom=" + t.getPrenom() + "&sex=" + t.getSex() + "&type=" + t.getType();
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
         
             public boolean deletePersonne(Personne t) {
        String url = Statics.BASE_URL + "/personne/delete/" + t.getId();
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
    
    
}
