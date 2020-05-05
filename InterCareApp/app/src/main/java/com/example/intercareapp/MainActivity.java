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
    private ArrayList<String> organizationNames;
    private EditText searchField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        organizationNames = new ArrayList<String>();
        organizationNames.add("Aleris-Hamlet Privathospital");
        organizationNames.add("Capio CFR Odense");
        organizationNames.add("PrivatHospitalet H. C. Andersen");
        organizationNames.add("Privathospitalet Mølholm");
        organizationNames.add("Aleris-Hamlet Privathospital");
        organizationNames.add("Capio CFR Odense");
        organizationNames.add("PrivatHospitalet H. C. Andersen");
        organizationNames.add("Privathospitalet Mølholm");
        organizationNames.add("Aleris-Hamlet Privathospital");
        organizationNames.add("Capio CFR Odense");
        organizationNames.add("PrivatHospitalet H. C. Andersen");
        organizationNames.add("Privathospitalet Mølholm");
        organizationNames.add("Aleris-Hamlet Privathospital");
        organizationNames.add("Capio CFR Odense");


        this.searchField = (EditText) findViewById(R.id.searchField);
        this.organizationsRecycleView = findViewById(R.id.organizationsRecycleView);
        MyAdapter myAdapter = new MyAdapter(this, this.organizationNames);
        this.organizationsRecycleView.setAdapter(myAdapter);
        this.organizationsRecycleView.setLayoutManager(new GridLayoutManager(this, 2));


    }
}
