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
import com.mycompany.entity.Event;
import com.mycompany.services.ServiceEvent;

/**
 *
 * @author Matoussi
 */
public class UpdateFormEvent extends BaseForm{

    UpdateFormEvent(Event t) {
        setTitle("update Event");
        setLayout(BoxLayout.y());

        TextField tfID = new TextField(String.valueOf(t.getId()), "EventID");
        tfID.setVisible(false);
        TextField TFNom = new TextField(t.getName(),"EventNom");
        TextField TFDate = new TextField(t.getDate(),"EventDate");
        TextField TFDesc = new TextField(t.getDescription(),"EventDescription");
        TextField tfnbr = new TextField(String.valueOf(t.getNb_partiipent()), "Eventparcipant");
        Picker datePicker =new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        String.valueOf(datePicker);

                
        Button btnSubmit = new Button("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfID.getText().length() == 0) ||((TFNom.getText().length() == 0 )) || ((TFDesc.getText().length() == 0)) || (tfnbr.getText().length() == 0)) {
                    Dialog.show("Alert", "please fill all fields", new Command("OK"));
                } else {
                    System.out.println(tfID.getText());
                     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Event t = new Event(Integer.parseInt(tfID.getText()), TFNom.getText(), TFDesc.getText(),Integer.parseInt(tfnbr.getText()), format.format(datePicker.getDate()) );
                    System.out.println(t.getId());
                    System.out.println(t.getName());
                    System.out.println(t.getDescription());
                    System.out.println(t.getNb_partiipent());
                    System.out.println(t.getDate());


                    if (ServiceEvent.getInstance().updateEvent(t)) {
                        Dialog.show("Success", "Connection Accepted", new Command("OK"));
                        new ListEventsForm(current);
                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
                    }

                }
            }
        });
        addAll(tfID, TFNom,TFDesc,tfnbr,datePicker , btnSubmit);
    }
    
    
}
