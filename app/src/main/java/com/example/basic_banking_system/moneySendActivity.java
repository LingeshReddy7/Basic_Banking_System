package com.example.basic_banking_system;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class moneySendActivity extends AppCompatActivity {

    TextView ssname,acno1;
    EditText sentamount;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_send);
        ssname = findViewById(R.id.ssname);
        acno1 = findViewById(R.id.acno1);
        sentamount = findViewById(R.id.sentamount);
        send = findViewById(R.id.send);
        String namer = "no name";
        String com = "11******";
        String acno = " ";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            namer = extras.getString("name");
            acno = extras.getString("acno");
        }
        String n = "";
        n = acno.substring(acno.length()-2);
        ssname.setText(namer);
        acno1.setText(com+n);

        final String finalNamer = namer;
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int enterAmount = Integer.parseInt(sentamount.getText().toString());
                int availableAmount = 0;
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(moneySendActivity.this);
                Cursor curs = myDatabaseHelper.readBalance();
                String nameCur = "";
                if(curs.getCount() == 0){
                    Toast.makeText(moneySendActivity.this, "Create account first ", Toast.LENGTH_SHORT).show();
                }else{
                    while(curs.moveToNext()){
                        String t = curs.getString(4);
                        String n = curs.getString(1);
                        String i = curs.getString(0);
                        String b = curs.getString(3);
                        String a = curs.getString(2);
                        availableAmount = Integer.parseInt(t);
                        nameCur = n;
                    }
                }

                if (sentamount.length() == 0) {
                    sentamount.setError("Money should not be Empty");
                }else if(enterAmount > availableAmount){
                    sentamount.setError("Not Enough money to send");
                    Toast.makeText(moneySendActivity.this, "Add Money to your account to make Transactions..", Toast.LENGTH_SHORT).show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(moneySendActivity.this);
                    builder.setTitle("Send");
                    builder.setMessage("Confirm to send..");

                    builder.setCancelable(false);

                    final String finalNameCur = nameCur;
                    final int finalAvailableAmount = availableAmount;
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyDatabaseHelper mydb = new MyDatabaseHelper(moneySendActivity.this);
                            long res = mydb.updateData(finalNamer, Integer.parseInt(sentamount.getText().toString().trim()));
                            String stat = "";
                            if(res != -1) {
                                Toast.makeText(moneySendActivity.this, sentamount.getText().toString()+" Rs Debited from your Account", Toast.LENGTH_SHORT).show();
                                stat = "Successful!";
                            }else{
                                stat = "Failed";
                            }
                            mydb.addTransation(finalNamer,stat,Integer.parseInt(sentamount.getText().toString().trim()));
                            mydb.updateMyDataBalance(finalNameCur, finalAvailableAmount -Integer.parseInt(sentamount.getText().toString().trim()));
                            dialog.dismiss();
                            redirect();
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyDatabaseHelper mydb = new MyDatabaseHelper(moneySendActivity.this);
                            mydb.addTransation(finalNamer,"Failed!",Integer.parseInt(sentamount.getText().toString().trim()));
                            dialog.dismiss();
                        }
                    });

                    builder.create().show();
                }
            }
        });
    }
    void redirect(){
        final LoadingDialog loadingDialog = new LoadingDialog(moneySendActivity.this);
        Intent i  = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        loadingDialog.satrtDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.disdialog();
            }
        },4000);
    }
}