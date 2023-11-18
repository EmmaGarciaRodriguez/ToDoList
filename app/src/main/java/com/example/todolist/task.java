package com.example.todolist;

import android.widget.DatePicker;

import java.io.Serializable;

public class task implements Serializable {
    private String text;
    private String date;
    private int day;
    private int month;
    private int year;

    public task(String text, String date,int year, int month, int day) {
        this.year = year;
        this.month= month;
        this.day= day;
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
