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
import com.codename1.ui.FontImage;
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
public class AddEventForm extends BaseForm {

    Form current;

   public AddEventForm() {
        setTitle("add new Event");
        setLayout(BoxLayout.y());
        TextField tfName = new TextField("", "EventName");
        TextField tfnbr = new TextField("", "Event Nbr participant");
        
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
                if ((tfName.getText().length() == 0) || (tfDesc.getText().length() == 0)|| (tfnbr.getText().length() == 0) ) {
                    Dialog.show("Alert", "please fill all fields", new Command("OK"));
                } else {
                    Picker p = new Picker();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    System.out.println(format.format(datePicker.getDate()));
                    int x=Integer.valueOf( tfnbr.getText());
                    Event t = new Event(tfName.getText(), tfDesc.getText(), x,format.format(datePicker.getDate()));
                    if (ServiceEvent.getInstance().addEvent(t)) {
                        Dialog.show("Success", "Connection Accepted", new Command("OK"));
                        
                        
                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
                        
                    }

                }
            }
        });
        next.addActionListener(e -> new ListEventsForm(current));
        addAll(tfName, tfDesc,tfnbr,datePicker, btnSubmit,next);
    }

}
