package com.example.androidassignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidassignment.Activities.UpdateAddressActivity;
import com.example.androidassignment.Model.AddressModel;
import com.example.androidassignment.R;

import java.util.ArrayList;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.myViewHolder> {
    private ArrayList<AddressModel> addressList;
    private Context context;

    public AddressAdapter(ArrayList<AddressModel> addressList, Context context) {
        this.addressList = addressList;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_address_view, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        AddressModel model = addressList.get(position);
        holder.address.setText(model.getAddress());
        holder.description.setText(model.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, UpdateAddressActivity.class)
                .putExtra("address", model.getAddress())
                .putExtra("description", model.getDescription()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView address, description;
        CardView view;
        ImageView image;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view);
            address = itemView.findViewById(R.id.idTVAddressName);
            description = itemView.findViewById(R.id.idTVAddressDescription);
            image = itemView.findViewById(R.id.address_photo);
        }
    }
}
