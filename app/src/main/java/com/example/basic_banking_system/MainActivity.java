package com.example.basic_banking_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton floating1;
    MyDatabaseHelper myDB;
    customAdapter.RecyclerViewListner listner;
    ArrayList<String> user_id,user_name,user_acno,user_branch,user_amount;
    customAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        floating1 = findViewById(R.id.floating1);

        floating1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Add_User.class);
                startActivity(i);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        user_id = new ArrayList<>();
        user_acno = new ArrayList<>();
        user_name = new ArrayList<>();
        user_branch = new ArrayList<>();
        user_amount = new ArrayList<>();

        storeData();
        setOnCliclListner();
        customAdapter = new customAdapter(MainActivity.this,user_id,user_name,user_acno,user_branch,user_amount,listner);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ex_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "Intem 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Intent i1 = new Intent(MainActivity.this,My_account.class);
                startActivity(i1);
                return true;

            case R.id.item3:
                Intent i = new Intent(MainActivity.this,transaction_activity.class);
                startActivity(i);
                return true;

            case R.id.item4:
                Intent i2 = new Intent(MainActivity.this,aboutMe.class);
                startActivity(i2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void setOnCliclListner() {
        listner = new customAdapter.RecyclerViewListner() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(getApplicationContext(),userData.class);
                i.putExtra("name",user_name.get(position));
                i.putExtra("acno",user_acno.get(position));
                i.putExtra("branch",user_branch.get(position));
                i.putExtra("amount",user_amount.get(position));
                startActivity(i);
            }
        };
    }

    void storeData(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "0 User List ", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                user_id.add(cursor.getString(0));
                user_name.add(cursor.getString(1));
                user_acno.add(cursor.getString(2));
                user_branch.add(cursor.getString(3));
                user_amount.add(cursor.getString(4));
            }
        }
    }
}