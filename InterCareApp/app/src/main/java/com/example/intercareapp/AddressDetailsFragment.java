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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class AddressDetailsFragment extends Fragment {
    private String APIurl;
    private RequestQueue queue;
    private Intent intent;
    private TextView streetTV;
    private TextView municipalityTV;
    private TextView regionTV;
    private TextView postalCodeTV;
    private TextView statusTV;
    private TextView latTV;
    private TextView lonTV;
    private Button backButton;
    private View rootView;

    public AddressDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // We set the rootView early, so that we can use the object to find views.
        rootView = inflater.inflate(R.layout.fragment_address_details, container, false);
        // We set all of the views.
        queue = Volley.newRequestQueue(getContext());
        streetTV = rootView.findViewById(R.id.streetTV);
        municipalityTV = rootView.findViewById(R.id.municipalityTV);
        regionTV = rootView.findViewById(R.id.regionTV);
        postalCodeTV = rootView.findViewById(R.id.postalCodeTV);
        latTV = rootView.findViewById(R.id.latTV);
        lonTV = rootView.findViewById(R.id.lonTV);
        statusTV = rootView.findViewById(R.id.statusTV);
        backButton = rootView.findViewById(R.id.backToMainButton);
        // What to do if the orientation is in portrait mode.
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            intent = this.getActivity().getIntent();
            APIurl = "https://www.mapquestapi.com/geocoding/v1/address?key=lewCeujjljEGN6DPdaKt08AxRLX7pA7d&location=" + intent.getStringExtra("organizationAddress");

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, APIurl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject obj = new JSONObject(response);
                                JSONArray obj2 = obj.getJSONArray("results");
                                JSONObject obj3 = obj2.getJSONObject(0);
                                JSONArray obj4 = obj3.getJSONArray("locations");
                                JSONObject obj5 = obj4.getJSONObject(0);
                                JSONObject obj6 = obj5.getJSONObject("latLng");
                                latTV.setText(obj6.get("lat").toString());
                                lonTV.setText(obj6.get("lng").toString());
                                streetTV.setText(obj5.get("street").toString());
                                municipalityTV.setText(obj5.get("adminArea4").toString());
                                regionTV.setText(obj5.get("adminArea3").toString());
                                postalCodeTV.setText(obj5.get("postalCode").toString());
                                statusTV.setText("Successfully fetched data!");
                            } catch (JSONException e) {
                                e.printStackTrace();
                                statusTV.setText("Unable to fetch data. Please try again later!");
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error);
                    statusTV.setText("Unable to fetch data. Please try again later!");
                }
            });

            // We add the request to the RequestQueue.
            queue.add(stringRequest);
            // We set a clickListener on backButton to defined what should happen when it is clicked.
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // We replace the current fragment with the previous one that the user came from.
                    getFragmentManager().beginTransaction().replace(R.id.OrganizationDetailsFragment, new OrganizationFragment()).addToBackStack(null).commit();
                }
            });
            // What to do if the orientation is in landscape mode.
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // We make the API call and use the information returned from the API to set the details on the different views.
            // We convert everything from JSON to objects in order to retrieve the data.
            if (getArguments().getString("addressTransaction") != "") {
                APIurl = "https://www.mapquestapi.com/geocoding/v1/address?key=lewCeujjljEGN6DPdaKt08AxRLX7pA7d&location=" + getArguments().getString("addressTransaction");
                StringRequest stringRequest = new StringRequest(Request.Method.GET, APIurl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject obj = new JSONObject(response);
                                    JSONArray obj2 = obj.getJSONArray("results");
                                    JSONObject obj3 = obj2.getJSONObject(0);
                                    JSONArray obj4 = obj3.getJSONArray("locations");
                                    JSONObject obj5 = obj4.getJSONObject(0);
                                    JSONObject obj6 = obj5.getJSONObject("latLng");
                                    latTV.setText(obj6.get("lat").toString());
                                    lonTV.setText(obj6.get("lng").toString());
                                    streetTV.setText(obj5.get("street").toString());
                                    municipalityTV.setText(obj5.get("adminArea4").toString());
                                    regionTV.setText(obj5.get("adminArea3").toString());
                                    postalCodeTV.setText(obj5.get("postalCode").toString());
                                    statusTV.setText("Successfully fetched data!");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    statusTV.setText("Unable to fetch data. Please try again later!");
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                        statusTV.setText("Unable to fetch data. Please try again later!");
                    }
                });
                // We add the request to the RequestQueue.
                queue.add(stringRequest);
                backButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // We replace the current fragment with the previous one that the user came from.
                        getFragmentManager().beginTransaction().replace(R.id.organizationDetailsLayoutLand, new OrganizationFragment(), "replacedOrganization").addToBackStack(null).commit();
                    }
                });
            } else {
                statusTV.setText("Unable to fetch data. Please try again later!");
                backButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // We replace the current fragment with the previous one that the user came from.
                        getFragmentManager().beginTransaction().replace(R.id.organizationDetailsLayoutLand, new OrganizationFragment(), "replacedOrganization").addToBackStack(null).commit();
                    }
                });
            }
        }
        return rootView;
    }
}
