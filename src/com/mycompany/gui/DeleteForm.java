/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.Themetest;
import com.mycompany.services.ServiceThemetest;

/**
 *
 * @author Matoussi
 */
public class DeleteForm extends BaseForm {

    DeleteForm(Themetest t, Resources res) {
        setTitle("delete theme");
        setLayout(BoxLayout.y());
//        Themetest t = new Themetest();
 //       ComboBox cb = new ComboBox();
        
        
        TextField tfID = new TextField("", "ThemeID");
        Button btnSubmit = new Button("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfID.getText().length() == 0)) {
                    Dialog.show("Alert", "please fill all fields", new Command("OK"));
                } else {
                    System.out.println(tfID.getText());
                    Themetest t = new Themetest(tfID.getText());
                    System.out.println(t.getId_themetest());
                    System.out.println(t.getNom());
                    System.out.println(t.getDescription());
                    if (ServiceThemetest.getInstance().deleteTheme(t)) {
                        //local notification api
                        LocalNotification ln = new LocalNotification();
                        ln.setId("LnMessage");
                        ln.setAlertTitle("Notice!");
                        ln.setAlertBody("Theme has been deleted");
                        ln.setAlertSound("/notification_sound_notif.mp3");
                        
                        Display.getInstance().scheduleLocalNotification(ln, System.currentTimeMillis() + 10 * 1000, LocalNotification.REPEAT_NONE);
                        System.out.println(ln.toString());
                        ToastBar.showMessage("Theme '' "+ t.getNom() + " '' has been deleted successfully!", FontImage.MATERIAL_INFO);
                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
                    }

                }
            }
        });
        addAll(tfID, btnSubmit);
//        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
