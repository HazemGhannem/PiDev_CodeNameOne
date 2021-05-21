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
import com.mycompany.entity.Themetest;
import com.mycompany.utils.Statics;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Matoussi
 */
public class ServiceThemetest {

    Boolean resultOK;

    public ArrayList<Themetest> Themetests;
    // public boolean resultOK;
    public static ServiceThemetest instance = null;
    private ConnectionRequest req;

    private ServiceThemetest() {
        req = new ConnectionRequest();
    }

    public static ServiceThemetest getInstance() {
        if (instance == null) {
            instance = new ServiceThemetest();
        }
        return instance;
    }

    public Boolean addThemetest(Themetest t) {
        String url = Statics.BASE_URL + "/themetest/new?nom=" + t.getNom() + "&description=" + t.getDescription();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Themetest> afiichageThemetest() {
        ArrayList<Themetest> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/themetest/themetest";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapThemetest = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapThemetest.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
//                //Création des tâches et récupération de leurs données
                        Themetest t = new Themetest();
                        float Themetest_id = Float.parseFloat(obj.get("idThemetest").toString());
                        String Nom = obj.get("nom").toString();
                        String Description = obj.get("description").toString();
                        t.setId_themetest((int) Themetest_id);
                        t.setNom(Nom);
                        t.setDescription(Description);
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

    public boolean deleteTheme(Themetest t) {
        String url = Statics.BASE_URL + "/themetest/delete/" + t.getId_themetest();
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
     public boolean updateTheme(Themetest t) {
        String url = Statics.BASE_URL + "/themetest/edit/" + t.getId_themetest()+"?nom=" + t.getNom()+"&description=" + t.getDescription();
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

    public Themetest DetailThemetest(int Themetest_id, Themetest Themetest) {
        String url = Statics.BASE_URL + "/themetest/show/" + Themetest_id;
        req.setUrl(url);
        String str = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
            JSONParser jsonp;
            jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                Themetest.setNom(obj.get("nom").toString());
                Themetest.setDescription(obj.get("description").toString());

            } catch (IOException ex) {
                System.out.println("error related to sql :" + ex.getMessage());
            }
            System.out.println("data === " + str);
        }));
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Themetest;
    }
//  public boolean addThemetest(Themetest t) {
//        String url = Statics.BASE_URL + "/Themetest/new" + t.getNom()+ "/" + t.getDescription();
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
//    public ArrayList<Themetest> parseTasks(String jsonText){
//        try {
//            Themetests=new ArrayList<>();
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
//            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
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
//            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("themetest");
//            
//            //Parcourir la liste des tâches Json
//            for(Map<String,Object> obj : list){
//                //Création des tâches et récupération de leurs données
//                Themetest t = new Themetest();
//                float Themetest_id = Float.parseFloat(obj.get("id_themetest").toString());
//                t.setId_themetest((int)Themetest_id);
//                t.setNom(obj.get("nom").toString());
//                t.setDescription(obj.get("description").toString());
//                
//
//
//
//                //Ajouter la tâche extraite de la réponse Json à la liste
//                Themetests.add(t);
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
//        return Themetests;
//    }
//    
//    public ArrayList<Themetest> getAllThemetests(){
//        String url = Statics.BASE_URL+"/themetest/themetest";
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                Themetests = parseTasks(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return Themetests;
//    }
//    public Themetest DetailThemetest( int Themetest_id,Themetest Themetest){
//        String url = Statics.BASE_URL + "/themetest/show/" +Themetest_id;
//        req.setUrl(url);
//        String str=new String(req.getResponseData());
//                req.addResponseListener(((evt) -> {
//                JSONParser jsonp=new JSONParser();
//                try{
//             Map<String,Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
//            Themetest.setNom(obj.get("title").toString());
//            Themetest.setDescription(obj.get("description").toString());
//           
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
//        return Themetest ;
//       
//    }
}
