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
import com.mycompany.entity.Question;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Matoussi
 */
public class ServiceQuestion {

    boolean resultOK;
    public ArrayList<Question> comments;

    public static ServiceQuestion instance = null;
    private ConnectionRequest req;

    private ServiceQuestion() {
        req = new ConnectionRequest();
    }

    public static ServiceQuestion getInstance() {
        if (instance == null) {
            instance = new ServiceQuestion();
        }
        return instance;
    }
    //add

    public boolean addQuestion(Question t) {
        String url = Statics.BASE_URL + "/question/newjson?libelle=" + t.getLibelle() + "&id_themetest=" + t.getId_themetest();

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

    public ArrayList<Question> affichageQuestion() {
        ArrayList<Question> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/question/question";

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
                        //Création des tâches et récupération de données
                        Question t = new Question();
                        float Question_id = Float.parseFloat(obj.get("idQuestion").toString());
//                        float Themetest_id = Float.parseFloat(obj.get("idThemetest").toString());
                        String libelle = obj.get("libelle").toString();
                        String id_Themetest = obj.get("idThemetest").toString();
                        t.setId_question((int) Question_id);
                        t.setLibelle(libelle);
//                        try {
//                            int val = Integer.parseInt(id_Themetest);
//                        } catch (NumberFormatException e) {
//                            System.out.println("not a number");
//                        }
                        System.out.println("***************");
                        System.out.println(id_Themetest);
                        t.setNom(id_Themetest);
                        System.out.println("************");
                        System.out.println(t.getNom());
                        System.out.println("************");

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

    public boolean deleteQuestion(Question t) {
        String url = Statics.BASE_URL + "/question/delete/" + t.getId_question();
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

    public boolean updateQuestion(Question t) {
        String url = Statics.BASE_URL + "/question/edit/" + t.getId_question() + "?libelle=" + t.getLibelle() + "&idThemetest=" + t.getId_themetest();
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
}
