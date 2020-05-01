package com.example.intercareapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> organizationNamesList;


    public MyAdapter(Context context, ArrayList<String> organizationNamesList) {
        this.context = context;
        this.organizationNamesList = organizationNamesList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.organization_name_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.organizationNameText.setText(organizationNamesList.get(position));
    }

    @Override
    public int getItemCount() {
        return organizationNamesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView organizationNameText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            organizationNameText = itemView.findViewById(R.id.organizationName);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            System.out.println("You have clicked position: " + getAdapterPosition());
            organizationNamesList.remove(getAdapterPosition());
            notifyDataSetChanged();
        }
    }
}