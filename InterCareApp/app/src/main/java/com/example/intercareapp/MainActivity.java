package com.example.intercareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView organizationsRecycleView;
    private ArrayList<Organization> organizations;
    private EditText searchField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        organizations = new ArrayList<Organization>();
        organizations.add(new Organization("Møens Klint", "Bananvej 22", "Email@test.dk", 0, new String[]{"Volvo", "BMW", "Ford", "Mazda"}));


        this.searchField = (EditText) findViewById(R.id.searchField);
        this.organizationsRecycleView = findViewById(R.id.organizationsRecycleView);
        MyAdapter myAdapter = new MyAdapter(this, this.organizations);
        this.organizationsRecycleView.setAdapter(myAdapter);
        this.organizationsRecycleView.setLayoutManager(new GridLayoutManager(this, 2));


    }
}
