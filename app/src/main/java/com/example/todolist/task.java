package com.example.todolist;

import android.widget.DatePicker;

import java.io.Serializable;

public class task implements Serializable {
    private String text;
    private String date;

    public task(String text, String date) {
        this.text = text;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
