package com.example.intercareapp;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView organizationsRecycleView;
    private ArrayList<Organization> organizations;
    private EditText searchField;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        organizations = new ArrayList<Organization>();
        organizations.add(new Organization("H.C. Andersen Klinikken", "Langelinie 29, 5230 Odense", "Andersen@klinik.dk", 10, new String[]{"Brystløft", "Botox", "Ny hofte", "Pandeløft"}));
        organizations.add(new Organization("Capio CFR Odense", "Pantheonsgade 25, 5000 Odense", "Capio@odense.dk", 0, new String[]{"Volvo", "BMW", "Ford", "Mazda"}));
        organizations.add(new Organization("Privathospitalet Mølholm", "Brummersvej 1, 7100 Vejle", "Mølholm@klinik.dk", 0, new String[]{"Volvo", "BMW", "Ford", "Mazda"}));



        this.searchField = (EditText) findViewById(R.id.searchField);
        this.organizationsRecycleView = findViewById(R.id.organizationsRecycleView);
        MyAdapter myAdapter = new MyAdapter(this, this.organizations);
        this.organizationsRecycleView.setAdapter(myAdapter);
        this.organizationsRecycleView.setLayoutManager(new GridLayoutManager(this, 2));

        db = new Database(getApplicationContext());
        AddDataToDB();

        /*btn = findViewById(R.id.viewAllButton);
        viewAll();
        */
    }

    public void AddDataToDB(){
        for (Organization org : organizations){


            boolean isInserted = db.insertData(
                    org.getName(),
                    org.getAddress(),
                    org.getEmail(),
                    org.getRating(),
                    convertArrayToString(org.getTreatments()));

            if (isInserted == true){
                System.out.println("Data inserted");
            } else{
                System.out.println("Failure in inserting data");
            }

        }
    }

    public static String stringSeparator = ", ";
    public static String convertArrayToString(String[] treatments){
        String str = "";
        for (int i = 0; i < treatments.length; i++){
            str = str+treatments[i];
            // Do not append comma for last element
            if(i<treatments.length-1){
                str = str+stringSeparator;
            }
        }
        return str;
    }

    /*
    public static String[] convertStringToArray(String str){
        String[] treatments = str.split(stringSeparator);
        return treatments;
    }
    */

    /*
    public void viewAll(){

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Cursor data = db.getAllData();
                        if(data.getCount() == 0){
                            // Message shown if db is empty
                            showMessage("Error", "No data found");
                            return ;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(data.moveToNext()){
                            buffer.append("Name :"+ data.getString(0)+ "\n") ;
                            buffer.append("Address :"+ data.getString(0)+ "\n") ;
                            buffer.append("Email :"+ data.getString(0)+ "\n") ;
                            buffer.append("Rating :"+ data.getString(0)+ "\n") ;
                            buffer.append("Treatments :"+ data.getString(0)+ "\n") ;

                        }

                        showMessage("Data", buffer.toString());

                    }
                }
        );
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

     */
}
