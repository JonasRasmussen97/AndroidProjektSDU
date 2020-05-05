package com.example.intercareapp;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> organizationNamesList;
    ArrayList<String> organizationNamesListOrdered;


    public MyAdapter(Context context, ArrayList<String> organizationNamesList) {
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
    holder.organizationNameText.setText(organizationNamesList.get(position));
    }

    @Override
    public int getItemCount() {
        return organizationNamesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView organizationNameText;
        EditText searchField;
        Context context;

        public MyViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            organizationNameText = itemView.findViewById(R.id.organizationName);
            searchField = (EditText) ((Activity) context).findViewById(R.id.searchField);
            searchField.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (organizationNamesList.contains(charSequence.toString())) {
                        organizationNamesListOrdered = (ArrayList<String>) organizationNamesList.clone();
                        organizationNamesList.clear();
                        organizationNamesList.add(charSequence.toString());
                        notifyDataSetChanged();
                    }
                    if((organizationNamesList.contains(charSequence.toString()) == false) && organizationNamesListOrdered != null) {
                            organizationNamesList = (ArrayList<String>) organizationNamesListOrdered.clone();
                            notifyDataSetChanged();
                        }
                    }



                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
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