/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.services.ServiceArticle;
import com.mycompany.entity.Article;

/**
 *
 * @author R I B
 */
public class AddArticleForm extends BaseForm{
    Form current;
    public AddArticleForm(Form previous){
             setTitle("Add");
             setLayout(BoxLayout.y());
             TextField tftitle= new TextField("","ArticleTitre");
             TextField tfdes= new TextField("","ArticleDescription");
             TextField tfimage= new TextField("","ArticleImage");
             TextField tfdate= new TextField("","Articledate");
             TextField tflike= new TextField("","Articleliek");
        

             Button btnSubmit = new Button ("Submit");
             btnSubmit.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent evt) {
                     if((tftitle.getText().length()==0) || (tfdes.getText().length()==0)|| (tfimage.getText().length()==0))
                      //Dialog.show("Alert", newCommand("ok"));
                         Dialog.show("Alert", "please", new Command("ok"));
                     else{
                         
                             Article t = new Article(tftitle.getText(),tfdes.getText(),tfimage.getText(),tfdate.getText(),tflike.getText());
                             if(ServiceArticle.getInstance().addArticle(t)){
                                Dialog.show("okay ", "connection ok ", new Command("ok"));
                             }else {
                                    Dialog.show("Errur", "CoonectionFailed", new Command("ok"));
   
                                } 
                             
                             
                         
                     }
                 }    

             });
             addAll(tftitle,tfdes,tfimage,tfdate,tflike,btnSubmit);
             this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());             




    }
}