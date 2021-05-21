/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
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
import com.mycompany.entity.Themetest;
import com.mycompany.services.ServiceThemetest;

/**
 *
 * @author Matoussi
 */
public class UpdateForm extends BaseForm {

    UpdateForm(Themetest t) {
        Toolbar.setGlobalToolbar(true);
        Form tempForm = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
        Toolbar tb = tempForm.getToolbar();
        Container topBar = BorderLayout.east(new Label(""));
        topBar.add(BorderLayout.SOUTH, new Label("Menu", "SidemenuTagline"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);
        
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            new HomeForm(current);
        });
        tb.addMaterialCommandToSideMenu("Article", FontImage.MATERIAL_HOME, e -> {
            
        });
        tb.addMaterialCommandToSideMenu("Theme", FontImage.MATERIAL_ADD_TASK, e -> {
            new DisplayThemeForm(current).show();
        });
        tb.addMaterialCommandToSideMenu("Questions", FontImage.MATERIAL_LIST, e -> {
            new TabQuestionForm(current);
        });
        tb.addMaterialCommandToSideMenu("Event", FontImage.MATERIAL_INFO, e -> {
        });

        setTitle("update theme");
        setLayout(BoxLayout.y());

        TextField tfID = new TextField(String.valueOf(t.getId_themetest()), "ThemeID");
        tfID.setVisible(false);
        TextField TFNom = new TextField(t.getNom(), "ThemeNom");
        TextField TFDesc = new TextField(t.getDescription(), "ThemeDescription");

        Button btnSubmit = new Button("Submit");
        Button cancel = new Button("Cancel ");
        String old = t.getNom();
        System.out.println(old);
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfID.getText().length() == 0) || ((TFNom.getText().length() == 0)) || ((TFDesc.getText().length() == 0))) {
                    Dialog.show("Alert", "please fill all fields", new Command("OK"));
                } else {
                    System.out.println(tfID.getText());
                    Themetest t = new Themetest(Integer.parseInt(tfID.getText()), TFNom.getText(), TFDesc.getText());
                    System.out.println(t.getId_themetest());
                    System.out.println(t.getNom());
                    System.out.println(t.getDescription());
                    if (ServiceThemetest.getInstance().updateTheme(t)) {
                        Dialog.show("Success", "Theme updated!", new Command("OK"));
                        //local notification api
                        LocalNotification ln = new LocalNotification();
                        ln.setId("LnMessage");
                        ln.setAlertTitle("Notice!");
                        ln.setAlertBody("Theme has been added updated");
                        ln.setAlertSound("/notification_sound_notif.mp3");
                        
                        Display.getInstance().scheduleLocalNotification(ln, System.currentTimeMillis() + 10 * 1000, LocalNotification.REPEAT_NONE);
                        System.out.println(ln.toString());
                        ToastBar.showMessage("The Theme '' "+ old + " '' has been updated to ''"+ t.getNom() + " '' !", FontImage.MATERIAL_INFO);
                        new DisplayThemeForm(current);
                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
                    }

                }
            }
        });
        cancel.addPointerPressedListener(l -> {

            Dialog dig = new Dialog("Cancel");

            if (dig.show("Cancel", "Would you like to exit without submitting?, some data maybe lost", "Cancel", "OK")) {
                dig.dispose();
            } else {
                dig.dispose();
                new DisplayThemeForm(current);
            };

        });

        addAll(tfID, TFNom, TFDesc, btnSubmit,cancel);
    }

}
