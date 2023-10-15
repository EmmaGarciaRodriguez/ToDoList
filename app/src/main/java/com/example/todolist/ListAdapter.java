package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<task> lst;

    public ListAdapter(Context context, ArrayList<task> lst) {
        this.context = context;
        this.lst = lst;
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        task t = lst.get(position);

        if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
        }

        textView = convertView.findViewById(R.id.nombreTarea);

        textView.setText(t.getText());

        return convertView;
    }
}
