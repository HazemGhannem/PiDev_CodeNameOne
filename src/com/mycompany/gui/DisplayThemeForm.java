/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.mycompany.gui.BaseForm;
import com.mycompany.services.ServiceThemetest;
import com.mycompany.entity.Themetest;
import java.io.IOException;
import java.io.OutputStream;

import java.util.ArrayList;

/**
 *
 * @author Matoussi
 */
public class DisplayThemeForm extends BaseForm {

    Form current;

    public DisplayThemeForm(Form previous) {

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

        tempForm.setTitle("Themetest");
        tempForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        //************ ScreenPrint api **************
        Image screenshot = Image.createImage(getWidth(), getHeight());
        revalidate();
        setVisible(true);
        FloatingActionButton fa = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fa.createSubFAB(FontImage.MATERIAL_ADD_TASK, "").addActionListener(e -> {
            new AddThemeForm(current).show();
        });
        fa.createSubFAB(FontImage.MATERIAL_PRINT, "").addActionListener(e -> {
                paintComponent(screenshot.getGraphics(), true);
                String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "Content.png";
                try ( OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
                } catch (IOException err) {
                    Log.e(err);
                }            
        });
        fa.bindFabToContainer(tempForm.getContentPane());
        //********* End api **************

        tempForm.setTransitionOutAnimator(CommonTransitions.createEmpty());
        GridLayout gridLayout = new GridLayout(1, 4);

        Font fnt = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        Label labelLibelle = new Label("Nom");
        Label labelQtarticle = new Label("Description");
        Label labelPrix = new Label("");
        Label labelrate = new Label("");

        Container HeadConainter = new Container(gridLayout);
        HeadConainter.add(labelLibelle);

        HeadConainter.add(labelQtarticle);
        HeadConainter.add(labelPrix);
        HeadConainter.add(labelrate);

        tempForm.add(HeadConainter);

        ArrayList<Themetest> themes = ServiceThemetest.getInstance().afiichageThemetest();
        for (Themetest r : themes) {
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

                Dialog dig = new Dialog("Delete");

                if (dig.show("Delete", "would you liike to delete this Theme? this action will not be reverted", "Cancel", "OK")) {
                    dig.dispose();
                } else {
                    dig.dispose();
                    // appel de la fonction delete du service Experience
                    if (ServiceThemetest.getInstance().deleteTheme(r)) {

                        new DisplayThemeForm(current);
                    }
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
                new UpdateForm(r).show();
            });

            //  if (r.getNom_client_review().equals(Username)) {
            Container BodyConainter = new Container(gridLayout);
            BodyConainter.add(new Label(r.getNom()));
            String description = String.valueOf(r.getDescription());
            BodyConainter.add(new Label(description));
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

        tempForm.show();
        tempForm.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 250));
        tempForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> new BaseForm().showBack()); // Revenir vers l'interface précédente
        return tempForm;

    }
}
