/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.services.ServiceArticle;
import com.mycompany.entity.Article;

/**
 *
 * @author Matoussi
 */
public class UpdateFormArticle extends BaseForm{
  
    UpdateFormArticle(Article t) {
        setTitle("update Article");
        setLayout(BoxLayout.y());

        TextField tfID = new TextField(String.valueOf(t.getArticle_id()), "ArtilceID");
        tfID.setVisible(false);
        TextField TFNom = new TextField(t.getTitle(),"ArticleTitle");
        TextField TFDesc = new TextField(t.getDescription(),"ArticleDescription");
        TextField TFimage = new TextField(t.getImage(),"ArticleImage");
        TextField TFDate = new TextField(t.getDate(),"ArticleDate");
        TextField TFlike = new TextField(t.getNbr_like(),"ArticleLikes");

        Button btnSubmit = new Button("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfID.getText().length() == 0) ||((TFNom.getText().length() == 0 )) || ((TFDesc.getText().length() == 0))) {
                    Dialog.show("Alert", "please fill all fields", new Command("OK"));
                } else {
                    System.out.println(tfID.getText());
                    Article t = new Article(Integer.parseInt(tfID.getText()), TFNom.getText(), TFDesc.getText(),TFimage.getText(),TFDate.getText(),TFlike.getText());
                    System.out.println(t.getArticle_id());
                    System.out.println(t.getTitle());
                    System.out.println(t.getDescription());
                    System.out.println(t.getImage());
                                        System.out.println(t.getDate());
                                        System.out.println(t.getNbr_like());



                    if (ServiceArticle.getInstance().updatearticle(t)) {
                        Dialog.show("Success", "Connection Accepted", new Command("OK"));
                        new ListArticlesForm(current);
                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
                    }

                }
            }
        });
        addAll(tfID, TFNom, TFDesc,TFimage,TFDate,TFlike, btnSubmit);
    }
    
    
}