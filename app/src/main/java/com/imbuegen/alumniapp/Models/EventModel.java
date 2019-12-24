package com.imbuegen.alumniapp.Models;

import com.google.firebase.database.DatabaseReference;

public class EventModel {
    public String title;
    public String body;
    public String date;

    public EventModel() {
    }

    public EventModel(String title, String body, String date) {
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
