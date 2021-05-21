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
import com.codename1.ui.animations.CommonTransitions;
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
public class AddThemeForm extends BaseForm {

    Form current;

    public AddThemeForm(Form current) {
        
        //side menu
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
        // end side menu
        
        tempForm.setTitle("Add new theme");
        tempForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
        TextField tfName = new TextField("", "ThemeName");
        TextField tfDesc = new TextField("", "ThemeDescription");
        Button btnSubmit = new Button("Submit");
        Button cancel = new Button("Cancel ");
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length() == 0) || (tfDesc.getText().length() == 0)) {
                    Dialog.show("Alert", "please fill all fields", new Command("OK"));
                } else {

                    Themetest t = new Themetest(tfName.getText(), tfDesc.getText());
                    if (ServiceThemetest.getInstance().addThemetest(t)) {
                        Dialog.show("Success", "Theme added successfuly", new Command("OK"));
                        
                        //local notification api
                        LocalNotification ln = new LocalNotification();
                        ln.setId("LnMessage");
                        ln.setAlertTitle("Notice!");
                        ln.setAlertBody("A new Theme has been added! Check it out");
                        ln.setAlertSound("/notification_sound_notif.mp3");
                        
                        Display.getInstance().scheduleLocalNotification(ln, System.currentTimeMillis() + 10 * 1000, LocalNotification.REPEAT_NONE);
                        System.out.println(ln);
                        ToastBar.showMessage("A new Theme '' "+ t.getNom() + " '' has been added! Check it out", FontImage.MATERIAL_INFO);
                        //load after submuitting
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
        tempForm.show();
        tempForm.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 250));
        tempForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> new BaseForm().showBack()); // Revenir vers l'interface précédente
        
        addAll(tfName, tfDesc, btnSubmit, cancel);
        
    }

}
