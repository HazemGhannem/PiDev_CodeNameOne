/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Matoussi
 */
public class HomeForm extends BaseForm {

    Form current;

    public HomeForm(Form current) {
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
            new ListArticlesForm(current);
        });
        tb.addMaterialCommandToSideMenu("Comment", FontImage.MATERIAL_HOME, e -> {
           new ListCommentForm(current);
        });
        tb.addMaterialCommandToSideMenu("Theme", FontImage.MATERIAL_ADD_TASK, e -> {
            new DisplayThemeForm(current).show();
        });
        tb.addMaterialCommandToSideMenu("Questions", FontImage.MATERIAL_LIST, e -> {
            new TabQuestionForm(current);
        });
        tb.addMaterialCommandToSideMenu("Event", FontImage.MATERIAL_INFO, e -> {
            new ListEventsForm(current);
        });
        tb.addMaterialCommandToSideMenu("Personne", FontImage.MATERIAL_HOME, e -> {
           new ListPersonneForm(current);
        });
        setTitle("Home");
        setLayout(BoxLayout.y());
        Button Theme = new Button("Theme");
        Theme.addActionListener(e -> new DisplayThemeForm(current));
        Button Question = new Button("Question");
        Question.addActionListener(e -> new TabQuestionForm(current));
        Button Personne = new Button("Personne");
        Personne.addActionListener(e -> new ListPersonneForm(current));
        Button Event = new Button("Event");
        Event.addActionListener(e -> new ListEventsForm(current));
        Button Article = new Button("Article");
        Article.addActionListener(e -> new ListArticlesForm(current));
        Button Comment = new Button("Comment");
        Comment.addActionListener(e -> new ListCommentForm(current));
        addAll(Theme,Question,Personne,Event,Article,Comment);
    }

}
