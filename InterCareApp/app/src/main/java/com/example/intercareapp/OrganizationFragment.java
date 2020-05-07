package com.example.intercareapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class OrganizationFragment extends Fragment {
    TextView organizationName;
    TextView organizationAddress;
    TextView organizationEmail;
    TextView organizationRating;
    TextView organizationTreatments;
    String treatments = "";
    private View rootView;

    public OrganizationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Intent intent = this.getActivity().getIntent();
        System.out.println(intent.getStringExtra("organizationName"));
        rootView = inflater.inflate(R.layout.fragment_organization, container, false);
        organizationName = rootView.findViewById(R.id.nameTV);
        organizationAddress = rootView.findViewById(R.id.addressTV);
        organizationEmail = rootView.findViewById(R.id.emailTV);
        organizationRating = rootView.findViewById(R.id.ratingTV);
        organizationTreatments = rootView.findViewById(R.id.treatmentTV);
        // Set the text of each field.
        organizationName.setText(intent.getStringExtra("organizationName"));
        organizationAddress.setText(intent.getStringExtra("organizationAddress"));
        organizationEmail.setText(intent.getStringExtra("organizationEmail"));
        organizationRating.setText(Integer.toString(intent.getIntExtra("organizationRating", 0)));

       for (int i = 0; i < intent.getStringArrayExtra("organizationTreatments").length; i++) {
        treatments +=  intent.getStringArrayExtra("organizationTreatments")[i] + ", ";
        }
        organizationTreatments.setText(treatments);

        return rootView;
    }
}
