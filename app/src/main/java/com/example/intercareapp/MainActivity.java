package com.example.intercareapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView organizationsRecycleView;
    private ArrayList<Organization> organizations;
    private ArrayList<Organization> dbOrganizationsList;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Hardcoded data used as dummy data to put into the database. We do this to allow for proof of concept of the database requirement.
        organizations = new ArrayList<>();
        organizations.add(new Organization("H.C. Andersen Klinikken", "Langelinie 29, 5230 Odense", "Andersen@klinik.dk", 10, new String[]{"Brystløft", "Botox", "Ny hofte", "Pandeløft"}));
        organizations.add(new Organization("Capio CFR Odense", "Pantheonsgade 25, 5000 Odense", "Capio@odense.dk", 0, new String[]{"Volvo", "BMW", "Ford", "Mazda"}));
        organizations.add(new Organization("Privathospitalet Mølholm", "Brummersvej 1, 7100 Vejle", "Mølholm@klinik.dk", 0, new String[]{"Volvo", "BMW", "Ford", "Mazda"}));

        // Creating database
        db = new Database(getApplicationContext());

        // Ensures that the same data hardcoded into organizations list is not duplicated,
        // for demonstration only.
        if (db.getOrganizationsCount() != organizations.size()) {
            AddDataToDB();
        }
        dbOrganizationsList = new ArrayList<>();
        dbOrganizationsList = getDbOrganizationsList();

        this.organizationsRecycleView = findViewById(R.id.organizationsRecycleView);
        MyAdapter myAdapter = new MyAdapter(this, this.dbOrganizationsList);
        this.organizationsRecycleView.setAdapter(myAdapter);
        this.organizationsRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportFragmentManager().beginTransaction().replace(R.id.organizationDetailsLayoutLand, new OrganizationFragment(), "replacedOrganization").addToBackStack("").commit();
        }
    }

    // Adding data to database from hardcoded arraylist "organizations" of type Organization
    public void AddDataToDB() {
        for (Organization org : organizations) {

            boolean isInserted = db.insertData(
                    org.getName(),
                    org.getAddress(),
                    org.getEmail(),
                    org.getRating(),
                    convertArrayToString(org.getTreatments()));

            if (isInserted == true) {
                System.out.println("Data inserted");
            } else {
                System.out.println("Failure in inserting data");
            }

        }
    }

    // Method taking each treatment string from the treatment array, forming one string to be put
    // into the treatments column in the database.
    public static String stringSeparator = ", ";

    public static String convertArrayToString(String[] treatments) {
        String str = "";
        for (int i = 0; i < treatments.length; i++) {
            str = str + treatments[i];
            // Do not append comma for last element
            if (i < treatments.length - 1) {
                str = str + stringSeparator;
            }
        }
        return str;
    }

    // Retrieves all organizations from the database, and inserts them into the dbOrganizationList
    // used by MyAdapter
    public ArrayList<Organization> getDbOrganizationsList() {
        for (int i = 1; i <= db.getOrganizationsCount(); i++) {
            dbOrganizationsList.add(db.getOrganizationDetailsById(i));
        }
        return dbOrganizationsList;
    }


}
