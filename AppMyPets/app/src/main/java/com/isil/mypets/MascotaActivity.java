package com.isil.mypets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;

public class MascotaActivity extends AppCompatActivity {

    Spinner spinnerer;
    String[] contenedor;
    ArrayList<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota);
        spinnerer = (Spinner)findViewById(R.id.razaspinner);
        contenedor = new String[]{
            "doberman","sharpei","pug","labrador","pekines"
        };
        adapter = new ArrayList<String>(this,android.R.layout.simple_list_item_1,contenedor);
        spinnerer.setAdapter(adapter);
    }
}
