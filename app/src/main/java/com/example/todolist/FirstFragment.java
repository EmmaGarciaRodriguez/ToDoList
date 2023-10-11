package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private HomeScreen homeScreen;
    String[][] datos = new String[100][2];

    public FirstFragment(HomeScreen homeScreen) {
        // Required empty public constructor
        this.homeScreen = homeScreen;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2, HomeScreen homeScreen) {
        FirstFragment fragment = new FirstFragment(homeScreen);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        EditText texto = view.findViewById(R.id.textousuario);
        DatePicker fecha= view.findViewById(R.id.fechausuario);
        Button btn = view.findViewById(R.id.aceptar);
        Button btn1 = view.findViewById(R.id.cancelar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), SecondFragment.class);
                String textousu = texto.getText().toString();
                int day = fecha.getDayOfMonth();
                int month = fecha.getMonth() + 1; // Los meses comienzan en 0
                int year = fecha.getYear();

                // Formatea la fecha seleccionada
                String fechausu = String.format("%02d/%02d/%04d", day, month, year);
                datos[datos.length][0] = textousu;
                datos[datos.length][1] = fechausu;

                startActivity(intent);
            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeScreen.onBackPressed();
                //Intent intent = new Intent(getContext(), SecondFragment.f);
                //startActivity(intent);
            }
        });
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

}