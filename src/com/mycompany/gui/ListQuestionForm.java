/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.mycompany.entity.Question;
import com.mycompany.entity.Themetest;
import com.mycompany.services.ServiceQuestion;
import com.mycompany.services.ServiceThemetest;
import java.util.ArrayList;

/**
 *
 * @author Matoussi
 */
public class ListQuestionForm extends BaseForm{
    Form current;

    public ListQuestionForm(Form previous) {
        SpanLabel spa = new SpanLabel();
        ArrayList<Question>list = ServiceQuestion.getInstance().affichageQuestion();
        System.out.println(list.toString());
        for(Question t : list){
            spa.setText(list.toString());
        }
        add(spa);
    }
    
    
}
