package com.example.basic_banking_system;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customAdapter extends RecyclerView.Adapter<customAdapter.MyViewHolder> {

    private Context context;
    private RecyclerViewListner listner;
    private ArrayList user_id,user_name,user_acno,user_branch,user_amount;
    customAdapter(Context context,ArrayList user_id,ArrayList user_name,ArrayList user_acno,ArrayList user_branch,ArrayList user_amount,RecyclerViewListner listner){
        this.context = context;
        this.user_acno = user_acno;
        this.user_amount = user_amount;
        this.user_branch = user_branch;
        this.user_id = user_id;
        this.user_name = user_name;
        this.listner = listner;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.user_name_txt.setText(String.valueOf(user_name.get(position)));
        holder.user_branch_txt.setText(String.valueOf(user_branch.get(position)));
        holder.user_amount_txt.setText(String.valueOf(user_amount.get(position)));
        holder.blank1.setText(String.valueOf(user_id.get(position)));
        holder.blank2.setText(String.valueOf(user_acno.get(position)));
    }

    @Override
    public int getItemCount() {
        return user_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView user_name_txt,user_branch_txt,user_amount_txt,blank1,blank2;

        public MyViewHolder(@NonNull View view) {
            super(view);
            user_amount_txt = view.findViewById(R.id.user_amount_txt);
            user_branch_txt = view.findViewById(R.id.user_branch_txt);
            user_name_txt = view.findViewById(R.id.user_name_txt);
            blank1 = view.findViewById(R.id.blank1);
            blank2 = view.findViewById(R.id.blank2);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listner.onClick(view,getAdapterPosition());
        }
    }
    public interface RecyclerViewListner{
        void onClick(View view,int position);
    }
}
