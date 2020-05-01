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



        this.organizationsRecycleView = findViewById(R.id.organizationsRecycleView);
        MyAdapter myAdapter = new MyAdapter(this, this.organizationNames);
        this.organizationsRecycleView.setAdapter(myAdapter);
        this.organizationsRecycleView.setLayoutManager(new GridLayoutManager(this, 2));

    }



}
