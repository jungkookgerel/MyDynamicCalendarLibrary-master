package com.desai.vatsal.mydynamiccalendarlibrary;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FilterAdapterSeconds extends RecyclerView.Adapter<FilterAdapterSeconds.ViewHolder> {
    private ArrayList<ListData> itemsData;
    private  OnCustomItemClickListenerSeconds listener;
    FilterAdapterSeconds(ArrayList<ListData> itemsData, OnCustomItemClickListenerSeconds listener) {
        this.itemsData = itemsData;
        this.listener=listener;
    }

    @NonNull
    @Override
    public FilterAdapterSeconds.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                              int viewType) {
        // create a new view
        @SuppressLint("InflateParams") View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_single, null);

        // create ViewHolder

        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        viewHolder.txtViewTitle.setText(itemsData.get(position).getTitle());
    /*    viewHolder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "You Clicked at " + itemsData.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });*/
        viewHolder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClickListenerSeconds(viewHolder.getAdapterPosition(), itemsData.get(position).getTitle(),itemsData.get(position).getWFID());

            }
        });
    }

    // inner class to hold a reference to each item of RecyclerView
    class ViewHolder extends RecyclerView.ViewHolder  {

        TextView txtViewTitle;
        private RelativeLayout relative;

        ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = itemLayoutView.findViewById(R.id.item_title);
            relative = itemLayoutView.findViewById(R.id.relative);
        }





    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return itemsData.size();
    }

}
