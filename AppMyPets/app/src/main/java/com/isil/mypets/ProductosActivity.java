package com.isil.mypets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;

public class ProductosActivity extends AppCompatActivity {

    Spinner spinnerer;
    String[] contenedor;
    ArrayList<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);spinnerer = (Spinner)findViewById(R.id.razaspinner);
        contenedor = new String[]{
                "comida","shampu","correas","juguetes","antipulgas"
        };
        adapter = new ArrayList<String>(this,android.R.layout.simple_list_item_1,contenedor);
        spinnerer.setAdapter(adapter);
    }
}
