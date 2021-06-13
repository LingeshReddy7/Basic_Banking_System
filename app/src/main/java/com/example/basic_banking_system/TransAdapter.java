package com.example.basic_banking_system;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.awt.font.TextAttribute;
import java.util.ArrayList;

public class TransAdapter extends RecyclerView.Adapter<TransAdapter.ViewHolder> {

    private Context context;
    private ArrayList user_id,user_name,user_amount,user_satus;
    TransAdapter(Context context,ArrayList user_id,ArrayList user_name,ArrayList user_amount,ArrayList user_satus){
        this.context = context;
        this.user_amount = user_amount;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_satus = user_satus;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.transa_layout,parent,false);
        return new TransAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.user_name_txt.setText(String.valueOf(user_name.get(position)));
        holder.user_status_txt.setText(String.valueOf(user_satus.get(position)));
        holder.user_amount_txt.setText(String.valueOf(user_amount.get(position)));
        holder.blank1.setText(String.valueOf(user_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return user_name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView user_name_txt,user_status_txt,user_amount_txt,blank1;
        public ViewHolder(@NonNull View view) {
            super(view);
            user_amount_txt = view.findViewById(R.id.user_amount_txt);
            user_status_txt = view.findViewById(R.id.user_status_txt);
            user_name_txt = view.findViewById(R.id.user_name_txt);
            blank1 = view.findViewById(R.id.blank1);
        }
    }
}
