package com.example.intercareapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
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
    private TextView organizationName;
    private TextView organizationAddress;
    private TextView organizationEmail;
    private TextView organizationRating;
    private TextView organizationTreatments;
    private String treatments = "";
    private Button addressDetailsButton;
    private Button backButton;
    private Intent intent;
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
        if (getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            intent = this.getActivity().getIntent();
            organizationName.setText(intent.getStringExtra("organizationName"));
            organizationAddress.setText(intent.getStringExtra("organizationAddress"));
            organizationEmail.setText(intent.getStringExtra("organizationEmail"));
            organizationRating.setText(Integer.toString(intent.getIntExtra("organizationRating", 0)));
            if (intent.getStringArrayExtra("organizationTreatments") != null) {
                for (int i = 0; i < intent.getStringArrayExtra("organizationTreatments").length; i++) {
                    treatments += intent.getStringArrayExtra("organizationTreatments")[i] + ", ";
                }
            }
            organizationTreatments.setText(treatments);
            // Backbutton clicked functionality portrait.
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
            });

            // Here we implement what should happen if the orientation is landscape.
        } else if (getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // We add this check to ensure that users didn't rotate to landscape in the middle of address details.
            // If users rotate in the middle of address details in portrait, then they might see another screen and then we go back to mainActivity.
            if (getActivity().findViewById(R.id.organizationDetailsLayoutLand) == null) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
            // Backbutton clicked functionality landscape.
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
            });
        }

        addressDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Replace current fragment with new fragment upon clicking on address details.
                if (getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    getFragmentManager().beginTransaction().replace(R.id.OrganizationDetailsFragment, new AddressDetailsFragment()).addToBackStack(null).commit();
                } else if (getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    getFragmentManager().beginTransaction().replace(R.id.organizationDetailsLayoutLand, new AddressDetailsFragment()).addToBackStack(null).commit();
                }
            }
        });


        return rootView;
    }

    // We add the following methods to allow the adapter from the recycle view to change the values.
    // This is used for the landscape orientation.
    public void setOrganizationName(String organizationName) {
        this.organizationName.setText(organizationName);
    }
    public TextView getOrganizationAddress() {
        return organizationAddress;
    }

    public void setOrganizationAddress(String organizationAddress) {
        this.organizationAddress.setText(organizationAddress);
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
}
