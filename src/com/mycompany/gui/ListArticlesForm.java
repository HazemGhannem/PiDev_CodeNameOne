/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.gui.BaseForm;
import com.mycompany.services.ServiceArticle;
import com.mycompany.entity.Article;

import java.util.ArrayList;

/**
 *
 * @author mouna
 */
public class ListArticlesForm extends Form {

    Form current;

    public ListArticlesForm(Form previous) {

        current = createForm();

    }

    public Form createForm() {
        Toolbar.setGlobalToolbar(true);
        Form tempForm = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
        Toolbar tb = tempForm.getToolbar();
        Container topBar = BorderLayout.east(new Label(""));
        topBar.add(BorderLayout.SOUTH, new Label("Menu", "SidemenuTagline"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);
                tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            new HomeForm(current).show();
        });
        tb.addMaterialCommandToSideMenu("Article", FontImage.MATERIAL_ARTICLE, e -> {
            new ListArticlesForm(current);
        });
        tb.addMaterialCommandToSideMenu("Comment", FontImage.MATERIAL_COMMENT, e -> {
           new ListCommentForm(current);
        });
        tb.addMaterialCommandToSideMenu("Theme", FontImage.MATERIAL_DASHBOARD, e -> {
            new DisplayThemeForm(current);
        });
        tb.addMaterialCommandToSideMenu("Questions", FontImage.MATERIAL_LIST, e -> {
            new TabQuestionForm(current);
        });
        tb.addMaterialCommandToSideMenu("Event", FontImage.MATERIAL_EVENT, e -> {
            new ListEventsForm(current);
        });
        tb.addMaterialCommandToSideMenu("Personne", FontImage.MATERIAL_PEOPLE, e -> {
           new ListPersonneForm(current);
        });
        
        FloatingActionButton fa = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fa.createSubFAB(FontImage.MATERIAL_ADD_TASK, "").addActionListener(e -> {
            new AddArticleForm(current).show();
        });
        fa.createSubFAB(FontImage.MATERIAL_CALENDAR_TODAY, "").addActionListener(e -> {new CalendarForm().show();
        });
        fa.bindFabToContainer(tempForm.getContentPane());
        
        tempForm.setTitle("Article");
        tempForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        tempForm.setTransitionOutAnimator(CommonTransitions.createEmpty());
        GridLayout gridLayout = new GridLayout(1, 6);

        Font fnt = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        Label labelLibelle = new Label("title");
        Label labelQtarticle = new Label("des");
        Label labelPrix = new Label("date");
        Label labelrate = new Label("likes");
        Label labelEdit = new Label("likes");
        Label labelDelete = new Label("likes");

//        Label labelEdit = new Label(" ");
//        Label labelDelete = new Label(" ");
        labelLibelle.getUnselectedStyle().setFont(fnt);
        labelQtarticle.getUnselectedStyle().setFont(fnt);
        labelPrix.getUnselectedStyle().setFont(fnt);
        labelrate.getUnselectedStyle().setFont(fnt);
        labelEdit.getUnselectedStyle().setFont(fnt);
        labelDelete.getUnselectedStyle().setFont(fnt);
        Container HeadConainter = new Container(gridLayout);
        HeadConainter.add(labelLibelle);

        HeadConainter.add(labelQtarticle);
        HeadConainter.add(labelPrix);
        HeadConainter.add(labelrate);
        HeadConainter.add(labelEdit);
        HeadConainter.add(labelDelete);
//        tempForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
//                 e -> new HomeReview());
        tempForm.add(HeadConainter);

        ArrayList<Article> Articles = ServiceArticle.getInstance().affichageArticle();
        for (Article r : Articles) {
            //delete button
            Label lSupprimer = new Label(" ");
            lSupprimer.setUIID("NewsTopLine");
            Style supprimerStyle = new Style(lSupprimer.getUnselectedStyle());
            supprimerStyle.setFgColor(0xf21f1f);
            FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
            lSupprimer.setIcon(supprimerImage);
            lSupprimer.setTextPosition(RIGHT);
            //click delete icon
            lSupprimer.addPointerPressedListener(l -> {

                Dialog dig = new Dialog("Suppresion");

                if (dig.show("Suppression", "Voulez vous supprimer l'article ?", "Annuler", "OK")) {
                    dig.dispose();
                } else {
                    dig.dispose();
                }

                // appel de la fonction delete du service Experience
                if (ServiceArticle.getInstance().deleteArticle(r)) {

                    new ListArticlesForm(current);
                }
            });
            //update Button
            Label lModifier = new Label(" ");
            lModifier.setUIID("NewsTopLine");
            Style modifierStyle = new Style(lModifier.getUnselectedStyle());
            modifierStyle.setFgColor(0xf7ad02);
            FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
            lModifier.setIcon(mFontImage);
            lModifier.setTextPosition(LEFT);
            //click update button
            lModifier.addPointerPressedListener(l -> {
                new UpdateFormArticle(r).show();
            });
            Container BodyConainter = new Container(gridLayout);
            BodyConainter.add(new Label(r.getTitle()));
            String description = String.valueOf(r.getDescription());
            String date = String.valueOf(r.getDate());
            String like = String.valueOf(r.getNbr_like());
            BodyConainter.add(new Label(description));
            BodyConainter.add(new Label(date));
            BodyConainter.add(new Label(like));
//           BodyConainter.add(labelEdit);
//           BodyConainter.add(labelDelete);
// 
            BodyConainter.add(lSupprimer);
            BodyConainter.add(lModifier);
            tempForm.add(BodyConainter);

        }
        tempForm.getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : tempForm.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                tempForm.getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : tempForm.getContentPane()) {
                    Container mb = (Container) cmp;
                    if (!(mb.getComponentAt(0) instanceof Button)) {
                        Label label1 = (Label) mb.getComponentAt(0);
                        String line1 = label1.getText();
                        Label label2 = (Label) mb.getComponentAt(1);
                        String line2 = label2.getText();
                        Label label3 = (Label) mb.getComponentAt(2);
                        String line3 = label3.getText();
                        Label label4 = (Label) mb.getComponentAt(3);
                        String line4 = label4.getText();
                        boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                                || line2 != null && line2.toLowerCase().indexOf(text) > -1
                                || line3 != null && line3.toLowerCase().indexOf(text) > -1
                                || line4 != null && line4.toLowerCase().indexOf(text) > -1;
                        mb.setHidden(!show);
                        mb.setVisible(show);
                    }
                }
                tempForm.getContentPane().animateLayout(150);
            }
        }, 4);

                Button Comment = new Button("ajouter Article");
        Comment.addActionListener(e -> new AddArticleForm(current));
        tempForm.show();
        tempForm.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 250));
        tempForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> new HomeForm(current).showBack()); // Revenir vers l'interface précédente
        return tempForm;

    }
}
