package com.example.intercareapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    Button addressDetailsButton;
    Button backButton;
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
        rootView = inflater.inflate(R.layout.fragment_organization, container, false);
        addressDetailsButton = rootView.findViewById(R.id.addressDetailsButton);
        organizationName = rootView.findViewById(R.id.nameTV);
        organizationAddress = rootView.findViewById(R.id.addressTV);
        organizationEmail = rootView.findViewById(R.id.emailTV);
        organizationRating = rootView.findViewById(R.id.ratingTV);
        organizationTreatments = rootView.findViewById(R.id.treatmentTV);
        backButton = rootView.findViewById(R.id.backToOrganization);
        // We set the text of the fields using intent, only in portrait mode. This is because in landscape mode the fragment is in same activity.
        if(getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            intent = this.getActivity().getIntent();
            System.out.println(intent.getStringExtra("organizationName"));
            organizationName.setText(intent.getStringExtra("organizationName"));
            organizationAddress.setText(intent.getStringExtra("organizationAddress"));
            organizationEmail.setText(intent.getStringExtra("organizationEmail"));
            organizationRating.setText(Integer.toString(intent.getIntExtra("organizationRating", 0)));
            if(intent.getStringArrayExtra("organizationTreatments") != null) {
                for (int i = 0; i < intent.getStringArrayExtra("organizationTreatments").length; i++) {
                    treatments += intent.getStringArrayExtra("organizationTreatments")[i] + ", ";
                }
            }
            organizationTreatments.setText(treatments);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        // Here we implement what should happen if the orientation is landscape.
        } else if(getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

        }

        addressDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Replace current fragment with new fragment upon clicking on address details.
        if(getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            getFragmentManager().beginTransaction().replace(R.id.OrganizationDetailsFragment, new AddressDetailsFragment()).addToBackStack(null).commit();
        } else if(getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getFragmentManager().beginTransaction().replace(R.id.organizationDetailsLayoutLand, new AddressDetailsFragment()).addToBackStack(null).commit();
        }

            }
        });
       

        return rootView;
    }

    // We add the following methods to allow the adapter from the recycle view to change the values.
    // This is used for the landscape orientation.
    public TextView getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName.setText(organizationName);
    }

    public TextView getOrganizationAddress() {
        return organizationAddress;
    }

    public void setOrganizationAddress(String organizationAddress) {
        this.organizationAddress.setText(organizationAddress);
    }

    public TextView getOrganizationEmail() {
        return organizationEmail;
    }

    public void setOrganizationEmail(String organizationEmail) {
        this.organizationEmail.setText(organizationEmail);
    }



    public void setOrganizationRating(String organizationRating) {
        this.organizationRating.setText(organizationRating);
    }


    public void setOrganizationTreatments(String[] organizationTreatments) {
        treatments = "";
        for (int i = 0; i < organizationTreatments.length; i++) {
            treatments += organizationTreatments[i] + ", ";
        }
        this.organizationTreatments.setText(treatments);
    }

    public String getTreatments() {
        return treatments;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }

    public Button getAddressDetailsButton() {
        return addressDetailsButton;
    }

    public void setAddressDetailsButton(Button addressDetailsButton) {
        this.addressDetailsButton = addressDetailsButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public void setBackButton(Button backButton) {
        this.backButton = backButton;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}
