/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.googlemaps.MapContainer;
import com.codename1.l10n.SimpleDateFormat;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.Personne;
import com.mycompany.services.ServicePersonne;


/**
 *
 * @author Matoussi
 */
public class AddPersonneForm1 extends BaseForm {

    Form current;

   public AddPersonneForm1() {
       
       
               Toolbar.setGlobalToolbar(true);
        Form tempForm = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
        Toolbar tb = tempForm.getToolbar();
        Container topBar = BorderLayout.east(new Label(""));
        topBar.add(BorderLayout.SOUTH, new Label("Menu", "SidemenuTagline"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);

        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            new ListPersonneForm(current);
        });
        tb.addMaterialCommandToSideMenu("Add", FontImage.MATERIAL_ADD_TASK, e -> {
            new AddPersonneForm1().show();
        });
        MapContainer map = new MapContainer();
        tb.addMaterialCommandToSideMenu("map", FontImage.MATERIAL_LIST, e -> {
            GoogleMapsTestApp m= new GoogleMapsTestApp() ;
            m.start();
        });
        tb.addMaterialCommandToSideMenu("About", FontImage.MATERIAL_INFO, e -> {
        });
       
       
        setTitle("add new Personne");
        setLayout(BoxLayout.y());
        
        TextField TFNom = new TextField("", "PersonneNom");
        TextField TFPrenom = new TextField("", "PersonnePrenom");
        TextField TFSex = new TextField("", "PersonneSex");
        TextField TFDate = new TextField("", "PersonneDate");
        TextField TFMdp = new TextField("", "Personne mdp");
        TextField TFMail = new TextField("", "Personne mail");
        TextField TFType = new TextField("", "Personne type");
        
        
        Picker datePicker =new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        String.valueOf(datePicker);
        System.out.println(datePicker.getDate());
        
        
        TextField tfDesc = new TextField("", "EventDescription");
        Button btnSubmit = new Button("Submit");
        Button next = new Button("next");
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((TFNom.getText().length() == 0) || (TFPrenom.getText().length() == 0)|| (TFSex.getText().length() == 0)|| (TFMdp.getText().length() == 0)|| (TFMail.getText().length() == 0)|| (TFType.getText().length() == 0) ) {
                    Dialog.show("Alert", "please fill all fields", new Command("OK"));
                } else {
                    Picker p = new Picker();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    System.out.println(format.format(datePicker.getDate()));
                    
                    Personne t = new Personne(TFNom.getText(), TFPrenom.getText(),format.format(datePicker.getDate()), TFSex.getText(),TFMdp.getText(),TFMail.getText(),"logo1",TFType.getText());
                    if (ServicePersonne.getInstance().addPersonne(t)) {
                        Dialog.show("Success", "Connection Accepted", new Command("OK"));
                        
                        
                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
                        
                    }

                }
            }
        });
        next.addActionListener(e -> new ListPersonneForm(current));
        addAll(TFNom, TFPrenom,TFSex,datePicker,TFMdp, TFMail,TFType, btnSubmit,next);
    }

}
