package com.example.todolist;

import java.io.Serializable;
import java.util.ArrayList;

public class ListManager implements Serializable {

    private ArrayList<task> tasklist;

    public ListManager() {
        this.tasklist = new ArrayList<>();
    }

    public ArrayList<task> getTasklist() {
        return tasklist;
    }

    public void addTask(task t){
        tasklist.add(t);
    }
}
