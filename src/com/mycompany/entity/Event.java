/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.util.Date;

import java.util.Date;

/**
 *
 * @author safouane
 */
public class Event {
    

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    private int id;
    private String name;
    private String description;
    private int nb_partiipent ;
    private String date;

    public Event() {
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", name=" + name + ", description=" + description + ", nb_partiipent=" + nb_partiipent + ", date=" + date + '}';
    }

    public Event(String name, String description, int nb_partiipent, String date) {
        this.name = name;
        this.description = description;
        this.nb_partiipent = nb_partiipent;
        this.date = date;
    }

    public Event(int id, String name, String description, int nb_partiipent, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.nb_partiipent = nb_partiipent;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNb_partiipent() {
        return nb_partiipent;
    }

    public void setNb_partiipent(int nb_partiipent) {
        this.nb_partiipent = nb_partiipent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
