package com.example.basic_banking_system;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class My_account extends AppCompatActivity implements View.OnClickListener {


    EditText ed1,ed2,ed3,ed4,amount_added;
    TextView txt_name,txt_acno,txt_bank,id,bal,txt_balance;
    Button add,add2,check,add_money,add_money2;
    private String nam;
    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);


        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        ed4 = findViewById(R.id.ed4);
        add = findViewById(R.id.add);
        id = findViewById(R.id.id);
        bal = findViewById(R.id.bal);
        txt_balance = findViewById(R.id.txt_balance);
        amount_added = findViewById(R.id.amount_added);
        add_money = findViewById(R.id.add_money);
        add_money2 = findViewById(R.id.add_money2);
        txt_acno = findViewById(R.id.txt_acno);
        txt_bank = findViewById(R.id.txt_bank);
        txt_name = findViewById(R.id.txt_name);
        add2 = findViewById(R.id.add2);
        check = findViewById(R.id.check);
        check.setOnClickListener(this);
        add.setOnClickListener(this);
        add2.setOnClickListener(this);
        add_money.setOnClickListener(this);
        add_money2.setOnClickListener(this);
        nam = " ra";
        nam = ed1.getText().toString();
        setData();
    }

    @Override
    public void onClick(View v) {
        final LoadingDialog loadingDialog = new LoadingDialog(My_account.this);
        switch (v.getId()){
            case R.id.add:
                ed1.setVisibility(v.VISIBLE);
                ed2.setVisibility(v.VISIBLE);
                ed3.setVisibility(v.VISIBLE);
                ed4.setVisibility(v.VISIBLE);
                add2.setVisibility(v.VISIBLE);
                add.setVisibility(v.INVISIBLE);
                break;

            case R.id.add2:
                if(ed1.length() == 0){
                    ed1.setError("Required");
                }else if(ed2.length() == 0){
                    ed2.setError("Required");
                }else if(ed3.length() == 0){
                    ed3.setError("Required");
                }else if(ed4.length() == 0){
                    ed4.setError("Required");
                }else{
                    MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(My_account.this);
                    myDatabaseHelper.addAccount(ed1.getText().toString().trim(),Long.parseLong(ed2.getText().toString().trim()),ed3.getText().toString().trim(),Integer.parseInt(ed4.getText().toString().trim()));
                    setData();
                    loadingDialog.satrtDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.disdialog();
                        }
                    },2000);

                    ed1.setVisibility(v.GONE);
                    ed2.setVisibility(v.GONE);
                    ed3.setVisibility(v.GONE);
                    ed4.setVisibility(v.GONE);
                    add2.setVisibility(v.GONE);
                    add.setVisibility(v.VISIBLE);
                }
                break;


            case R.id.check:
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(My_account.this);
                Cursor curs = myDatabaseHelper.readBalance();
                String CurrentBalance = "";
                if(curs.getCount() == 0){
                    Toast.makeText(this, "Add Account to Add money to Wallet ", Toast.LENGTH_SHORT).show();
                }else{
                    while(curs.moveToNext()){
                        CurrentBalance = curs.getString(4);
                        String n = curs.getString(1);
                        String i = curs.getString(0);
                        String b = curs.getString(3);
                        String a = curs.getString(2);
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(My_account.this);
                    builder.setTitle("Your Current Account Balance is : ");
                    builder.setMessage(CurrentBalance);

                    builder.setCancelable(true);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                }
                //txt_balance.setText(CurrentBalance);

                break;

            case R.id.add_money:
                amount_added.setVisibility(v.VISIBLE);
                add_money2.setVisibility(v.VISIBLE);
                add_money.setVisibility(v.GONE);
                ed1.setVisibility(v.GONE);
                ed2.setVisibility(v.GONE);
                ed3.setVisibility(v.GONE);
                ed4.setVisibility(v.GONE);
                add2.setVisibility(v.GONE);
                add.setVisibility(v.VISIBLE);
                break;

            case R.id.add_money2:
                if(amount_added.length() == 0){
                    amount_added.setError("Not Empty");
                }else{
                    MyDatabaseHelper myDatabaseHelper1 = new MyDatabaseHelper(My_account.this);
                    nam = "";
                    int previousAmount = 0;
                    Cursor curs1 = myDatabaseHelper1.readBalance();
                    if(curs1.getCount() == 0){
                        Toast.makeText(this, "Add Account Details to add Money ", Toast.LENGTH_SHORT).show();
                    }else{
                        while(curs1.moveToNext()){
                            String t = curs1.getString(4);
                            String n = curs1.getString(1);
                            String i = curs1.getString(0);
                            String b = curs1.getString(3);
                            String a = curs1.getString(2);
                            nam = n;
                            previousAmount = Integer.parseInt(t);
                        }
                        myDatabaseHelper1.updateMyDataBalance(nam,previousAmount+Integer.parseInt(amount_added.getText().toString().trim()));
                        Toast.makeText(this, amount_added.getText().toString()+"Rs Credited to your account!", Toast.LENGTH_SHORT).show();
                        loadingDialog.satrtDialog();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.disdialog();
                            }
                        },2000);
                    }

                    amount_added.setVisibility(v.GONE);
                    add_money2.setVisibility(v.GONE);
                    add_money.setVisibility(v.VISIBLE);
                }
                break;
        }
    }

    void setData(){
        myDatabaseHelper = new MyDatabaseHelper(My_account.this);
        Cursor cursor = myDatabaseHelper.readaccountData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Add Account Details to do Payments.. ", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                String namet = cursor.getString(1);
                if(namet != null)
                  txt_name.setText(namet);
                String acnot = cursor.getString(2);
                if(acnot != null)
                    txt_acno.setText(acnot);
                String bankt = cursor.getString(3);
                if(bankt != null)
                    txt_bank.setText(bankt);
                String balt = cursor.getString(4);
                if(namet != null)
                    bal.setText(balt);
                String idt = cursor.getString(0);
                if(namet != null)
                    id.setText(idt);
            }
        }
    }
}