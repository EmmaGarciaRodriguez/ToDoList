package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


public class EditTask extends AppCompatActivity {


    private ListManager listManager;
    private String textousu, fechausu, fechausu2;
    private task t;
    private ListManager list;
    private int pos;
    private int day;
    private int month;
    private int year;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        Intent intent = getIntent();

        if(getIntent().getExtras() != null) {
            t = (task) intent.getSerializableExtra("task");
            list = (ListManager) intent.getSerializableExtra("list");
            pos = (int) intent.getSerializableExtra("index");
        }


        EditText texto = findViewById(R.id.textousuario);
        texto.setText(t.getText());


        DatePicker fecha= findViewById(R.id.fechausuario);

        fecha.init(t.getYear(),t.getMonth(),t.getDay(),null);

        Button btn = findViewById(R.id.aceptar);
        Button btn1 = findViewById(R.id.cancelar);

        loadData(); //Carga la lista de tareas


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(getContext(), SecondFragment.class);
                textousu = texto.getText().toString();
                day = fecha.getDayOfMonth();
                month = fecha.getMonth(); // Los meses comienzan en 0
                year = fecha.getYear();

                // Formatea la fecha seleccionada
                fechausu = String.format("%02d/%02d/%04d", day, month + 1, year);
                fechausu2 = String.format("%04d/%02d/%02d", year, month + 1, day);
                editTask();
                saveData();


                finish();
            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                //Mostrar mensaje "Cancelado"
            }
        });
    }

    private void saveData() {
        SharedPreferences sharedPreferences= this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(listManager);
        editor.putString("tasklist", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences= this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("tasklist", null);
        Type type = new TypeToken<ListManager>() {}.getType();
        listManager = gson.fromJson(json, type);

        if (listManager == null){ //Si la lista está vacía

            //Crear lista y guardar lista de tareas en listManager
            listManager = new ListManager();


            //Mostrar mensaje "se crea"
            int duration = Toast.LENGTH_SHORT;
            CharSequence text = "Se crea lista vacia";
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();

        }
    }


    private void editTask(){

        task tareaNueva = new task(textousu, fechausu,year,month,day,fechausu2);

        listManager.addTaskbyPos(tareaNueva,pos);

        //Mostrar  mensaje tarea guardada
        int duration = Toast.LENGTH_SHORT;
        CharSequence text = "Tarea guardada";
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();

    }
}