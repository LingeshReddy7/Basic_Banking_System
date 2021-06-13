package com.example.basic_banking_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class transaction_activity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDatabaseHelper myDatabaseHelper;
    ArrayList user_id,user_name,user_amount,user_satus;
    TransAdapter transAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_activity);
        recyclerView = findViewById(R.id.recyclerView_trans);
        myDatabaseHelper = new MyDatabaseHelper(transaction_activity.this);
        user_id = new ArrayList();
        user_name = new ArrayList();
        user_amount = new ArrayList();
        user_satus = new ArrayList();

        storeData();
        transAdapter = new TransAdapter(transaction_activity.this,user_id,user_name,user_amount,user_satus);
        recyclerView.setAdapter(transAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(transaction_activity.this));

    }

    private void storeData() {
        Cursor cursor = myDatabaseHelper.readTransAData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "0 Transactions ", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                user_id.add(cursor.getString(0));
                user_name.add(cursor.getString(1));
                user_amount.add(cursor.getString(3));
                user_satus.add(cursor.getString(2));
            }
        }
    }
}