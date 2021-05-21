/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.Personne;
import com.mycompany.services.ServicePersonne;


/**
 *
 * @author Matoussi
 */
public class DeleteForm1 extends BaseForm {

    DeleteForm1(Personne t, Resources res) {
        setTitle("delete Personne");
        setLayout(BoxLayout.y());
//        Event t = new Event();
 //       ComboBox cb = new ComboBox();
        
        
        TextField tfID = new TextField("", "PersonneId");
        Button btnSubmit = new Button("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfID.getText().length() == 0)) {
                    Dialog.show("Alert", "please fill all fields", new Command("OK"));
                } else {
                    System.out.println(tfID.getText());
//                    Event t = new Event(tfID.getText());
                    System.out.println(t.getId());
                    System.out.println(t.getNom());
                    System.out.println(t.getPrenom());
                    System.out.println(t.getSex());
                    System.out.println(t.getDate());
                    System.out.println(t.getMdp());
                    System.out.println(t.getMail());
                    System.out.println(t.getType());
                    if (ServicePersonne.getInstance().deletePersonne(t)) {
                        Dialog.show("Success", "Connection Accepted", new Command("OK"));
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
