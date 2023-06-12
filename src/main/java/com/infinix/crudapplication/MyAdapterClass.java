package com.infinix.crudapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterClass extends RecyclerView.Adapter<MyAdapterClass.MyViewHolder> {
   
    ArrayList<DataModel> dataHolder;
    Context context;

    DBHelper db;

    public MyAdapterClass(ArrayList<DataModel> dataHolder, Context context) {
        this.dataHolder = dataHolder;
        this.context = context;
    }

 
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      
        DataModel currentData = dataHolder.get(position);

    
        holder.txtName.setText(currentData.getName());
        holder.txtAddress.setText(currentData.getAddress());
        holder.txtContact.setText(currentData.getContact());
        holder.txtDob.setText(currentData.getDob());

        });
    }

   
    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

 
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        
        TextView txtName, txtAddress, txtContact, txtDob;

      
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

         
            txtName = itemView.findViewById(R.id.txtName);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtContact = itemView.findViewById(R.id.txtContact);
            txtDob = itemView.findViewById(R.id.txtDob);
        }
    }
}