package com.example.basic_banking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_User extends AppCompatActivity {

    EditText user_name,user_acno,user_branch;
    Button submit_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__user);

        user_acno = findViewById(R.id.user_acno);
        user_branch = findViewById(R.id.user_branch);
        user_name = findViewById(R.id.user_name);
        submit_button = findViewById(R.id.submit_button);

        final LoadingDialog loadingDialog = new LoadingDialog(Add_User.this);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(user_name.length() == 0 || user_branch.length() == 0 || user_acno.length() < 10){
                    if(user_name.length() == 0) {
                        user_name.setError("You Missed it");
                    }else if(user_branch.length() == 0){
                        user_branch.setError("You Missed it");
                    }else {
                        user_acno.setError("Invalid AcNo");
                    }
                }
                else {
                    String s;
                    s =  "0";
                    MyDatabaseHelper myDB = new MyDatabaseHelper(Add_User.this);
                    myDB.addBook(user_name.getText().toString().trim(),
                            Long.parseLong(user_acno.getText().toString().trim()),
                            user_branch.getText().toString().trim(),
                            Integer.parseInt(s));
                    Intent i  = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    loadingDialog.satrtDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.disdialog();
                        }
                    },3000);
                }
            }
        });

    }
}