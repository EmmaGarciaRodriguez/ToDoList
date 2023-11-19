package com.example.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

import com.google.gson.Gson;

public class ShowTask extends AppCompatActivity {

    private task t;
    private ListManager list;
    private Button borrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);

        TextView name = findViewById(R.id.name);
        TextView date = findViewById(R.id.date);
        borrar = findViewById(R.id.borrar);

        Intent intent = getIntent();

        if(getIntent().getExtras() != null) {
            t = (task) intent.getSerializableExtra("task");
            list = (ListManager) intent.getSerializableExtra("list");
            int pos = (int) intent.getSerializableExtra("index");
            name.setText(t.getText());
            date.setText(t.getDate());
            saveData();


        } else {
            name.setText("Ha ocurrido un error");
        }

    }

    private void saveData() {
        SharedPreferences sharedPreferences= this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("tasklist", json);
        editor.apply();


    borrar.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = getIntent();
            int pos = (int) intent.getSerializableExtra("index");
            removeTask(pos);
        }
});
}

private void removeTask(int pos){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage("¿Está seguro que desea borrar esta tarea?");

    // Add the buttons
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            list.borrar(pos);
            saveData();
            finish();
        }
    });
    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            // User cancelled the dialog
        }
    });
    AlertDialog dialog = builder.create();
    dialog.show();
}
}