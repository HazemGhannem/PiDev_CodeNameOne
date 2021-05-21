/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.Personne;
import com.mycompany.services.ServicePersonne;

/**
 *
 * @author Matoussi
 */
public class UpdateForm1 extends BaseForm {

    UpdateForm1(Personne t) {
        setTitle("update Personne");
        setLayout(BoxLayout.y());

        TextField tfID = new TextField(String.valueOf(t.getId()), "PersonneID");
        tfID.setVisible(false);
        TextField TFNom = new TextField(t.getNom(), "PersonneNom");
        TextField TFPrenom = new TextField(t.getPrenom(), "PersonnePrenom");
        TextField TFSex = new TextField(t.getSex(), "PersonneSex");
        TextField TFDate = new TextField(t.getDate(), "PersonneDate");
        TextField TFMdp = new TextField(t.getMdp(), "Personne mdp");
        TextField TFMail = new TextField(t.getMail(), "Personne mail");
        TextField TFType = new TextField(t.getType(), "Personne type");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        String.valueOf(datePicker);

        Button btnSubmit = new Button("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfID.getText().length() == 0) || ((TFNom.getText().length() == 0)) || ((TFMail.getText().length() == 0)) || (TFPrenom.getText().length() == 0)) {
                    Dialog.show("Alert", "please fill all fields", new Command("OK"));
                } else {
                    System.out.println(tfID.getText());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Personne t = new Personne(Integer.parseInt(tfID.getText()), TFNom.getText(), TFPrenom.getText(), format.format(datePicker.getDate()),TFSex.getText(),TFMdp.getText(),TFMail.getText(),"logo",TFType.getText());
                    System.out.println(t.getId());
                    System.out.println(t.getNom());
                    System.out.println(t.getPrenom());
                    System.out.println(t.getSex());
                    System.out.println(t.getDate());
                    System.out.println(t.getMdp());
                    System.out.println(t.getMail());
                    System.out.println(t.getType());


                    if (ServicePersonne.getInstance().updatePersonne(t)) {
                        Dialog.show("Success", "Connection Accepted", new Command("OK"));
                        new ListPersonneForm(current);
                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
                    }

                }
            }
        });
        addAll(tfID, TFNom, TFPrenom, TFSex, datePicker, btnSubmit,TFMdp,TFMail,TFType);
    }

}
