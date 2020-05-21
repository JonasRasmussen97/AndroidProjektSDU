package com.example.intercareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class OrganizationDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_details);
        // We add the fragment programmatically to allow us to replace it later.
        getSupportFragmentManager().beginTransaction().replace(R.id.OrganizationDetailsFragment, new OrganizationFragment()).addToBackStack(null).commit();

    }


}
