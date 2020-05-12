package com.example.intercareapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


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
    Button mapsButton;
    Intent intent;
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

        intent = this.getActivity().getIntent();
        System.out.println(intent.getStringExtra("organizationName"));
        rootView = inflater.inflate(R.layout.fragment_organization, container, false);
        mapsButton = rootView.findViewById(R.id.mapsButton);
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

       mapsButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               // Switch to new fragment upon clicking on maps.
             getFragmentManager().beginTransaction().replace(R.id.addOrganizationDetailsFragment, new addressDetailsFragment()).addToBackStack(null).commit();


           }
       });

        return rootView;
    }


}
