package com.example.intercareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView organizationsRecycleView;
    private ArrayList<String> organizationNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        organizationNames = new ArrayList();
        organizationNames.add("Bobs Juice Bar");
        organizationNames.add("Rollos Bokseklub");
        organizationNames.add("De fem fede sømænd");

        this.organizationsRecycleView = findViewById(R.id.organizationsRecycleView);
        MyAdapter myAdapter = new MyAdapter(this, this.organizationNames);
        this.organizationsRecycleView.setAdapter(myAdapter);
        this.organizationsRecycleView.setLayoutManager(new LinearLayoutManager(this));

    }



}
