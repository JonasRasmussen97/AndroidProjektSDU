package com.example.intercareapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.Random;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Organization> organizationNamesList;
    private Random rand = new Random();
    private OrganizationFragment organizationDetailsFragment;


    public MyAdapter(Context context, ArrayList<Organization> organizationNamesList) {
        this.context = context;
        this.organizationNamesList = organizationNamesList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.organization_name_row, parent, false);
        return new MyViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.organizationNameText.setText(organizationNamesList.get(position).getName());
        // Here we inject images from an API into the organizationImage view for each element in the recyclerView.
        // This is done through the Glide library.
        Glide.with(holder.organizationImage.getContext()).load(String.format("https://picsum.photos/id/" + rand.nextInt(100) + "/500/500")).into(holder.organizationImage);
    }

    @Override
    public int getItemCount() {
        return organizationNamesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView organizationNameText;
        ImageView organizationImage;
        Context context;

        public MyViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            organizationNameText = itemView.findViewById(R.id.organizationName);
            organizationImage = itemView.findViewById(R.id.organizationIcon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // We check the orientation here and do things based on if it is landscape or portrait.
            // We switch activity in portrait mode and therefore we use intent to send data to the new activity.
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                Intent intent = new Intent(context, OrganizationDetailsActivity.class);
                Organization organizationClicked = organizationNamesList.get(getAdapterPosition());
                intent.putExtra("organizationName", organizationClicked.getName());
                intent.putExtra("organizationAddress", organizationClicked.getAddress());
                intent.putExtra("organizationEmail", organizationClicked.getEmail());
                intent.putExtra("organizationRating", organizationClicked.getRating());
                intent.putExtra("organizationTreatments", organizationClicked.getTreatments());
                context.startActivity(intent);
                // If it is landscape we have the organization details fragment in same activity and we change the values through supportFragmentManager without intent needed.
            } else if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Organization organizationClicked = organizationNamesList.get(getAdapterPosition());
                // This fragment is added in MainActivity onCreate(), if the orientation is landscape.
                // We find it by the tag and set the details.
                organizationDetailsFragment = (OrganizationFragment) ((AppCompatActivity) context).getSupportFragmentManager().findFragmentByTag("replacedOrganization");
                System.out.println(organizationDetailsFragment);
                // We add a check here that ensures, that the textfields we try to set are actually in the activity.
                // If one of them is not, then all of them are not and vice-versa, therefore it is enough to check only one view.
                if (organizationDetailsFragment.getOrganizationAddress() != null) {
                    organizationDetailsFragment.setOrganizationName(organizationClicked.getName());
                    organizationDetailsFragment.setOrganizationEmail(organizationClicked.getEmail());
                    organizationDetailsFragment.setOrganizationAddress(organizationClicked.getAddress());
                    organizationDetailsFragment.setOrganizationRating(Integer.toString(organizationClicked.getRating()));
                    organizationDetailsFragment.setOrganizationTreatments(organizationClicked.getTreatments());
                }
            }
        }
    }
}
