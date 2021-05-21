/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.Question;
import com.mycompany.services.ServiceQuestion;
/**
 *
 * @author Matoussi
 */
public class UpdateqForm extends BaseForm {

    UpdateqForm(Question t) {
//        Toolbar.setGlobalToolbar(true);
//        Form tempForm = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
//        Toolbar tb = tempForm.getToolbar();
//        Container topBar = BorderLayout.east(new Label(""));
//        topBar.add(BorderLayout.SOUTH, new Label("Menu", "SidemenuTagline"));
//        topBar.setUIID("SideCommand");
//        tb.addComponentToSideMenu(topBar);
//        
//        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
//            new HomeForm(current);
//        });
//        tb.addMaterialCommandToSideMenu("Article", FontImage.MATERIAL_HOME, e -> {
//            
//        });
//        tb.addMaterialCommandToSideMenu("Theme", FontImage.MATERIAL_ADD_TASK, e -> {
//            new DisplayThemeForm(current).show();
//        });
//        tb.addMaterialCommandToSideMenu("Questions", FontImage.MATERIAL_LIST, e -> {
//            new TabQuestionForm(current);
//        });
//        tb.addMaterialCommandToSideMenu("Event", FontImage.MATERIAL_INFO, e -> {
//        });
//
//        setTitle("update theme");
//        setLayout(BoxLayout.y());
//
//        TextField tfID = new TextField(String.valueOf(t.getId_question()), "ThemeID");
//        tfID.setVisible(false);
//        TextField TFNom = new TextField(t.getNom(), "ThemeNom");
//        TextField TFDesc = new TextField(t.getId_themetest(), "ThemeDescription");
//
//        Button btnSubmit = new Button("Submit");
//        Button cancel = new Button("Cancel ");
//        btnSubmit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                if ((tfID.getText().length() == 0) || ((TFNom.getText().length() == 0)) || ((TFDesc.getText().length() == 0))) {
//                    Dialog.show("Alert", "please fill all fields", new Command("OK"));
//                } else {
//                    Question t = new Question(Integer.parseInt(tfID.getText()), TFNom.getText(), TFDesc.getText());
//                   
//                    if (ServiceQuestion.getInstance().updateTheme(t)) {
//                        Dialog.show("Success", "Connection Accepted", new Command("OK"));
//                        new TabQuestionForm(current);
//                    } else {
//                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
//                    }
//
//                }
//            }
//        });
//        cancel.addPointerPressedListener(l -> {
//
//            Dialog dig = new Dialog("Cancel");
//
//            if (dig.show("Cancel", "Would you like to exit without submitting?, some data maybe lost", "Cancel", "OK")) {
//                dig.dispose();
//            } else {
//                dig.dispose();
//                new TabQuestionForm(current);
//            };
//
//        });
//
//        addAll(tfID, TFNom, TFDesc, btnSubmit,cancel);
    }
//
//    
}
