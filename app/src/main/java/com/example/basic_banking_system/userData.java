package com.example.basic_banking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class userData extends AppCompatActivity {

    TextView name,gacno,gbranch,gamount;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        name = findViewById(R.id.gname);
        gacno = findViewById(R.id.gacno);
        gbranch = findViewById(R.id.gbranch);
        gamount = findViewById(R.id.gamount);
        button = findViewById(R.id.button);

        String namer = "no name";
        String acno = " ";
        String branch = " ";
        String amount = "0.0";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            namer = extras.getString("name");
            acno = extras.getString("acno");
            branch = extras.getString("branch");
            amount = extras.getString("amount");
        }
        name.setText(namer);
        gacno.setText(acno);
        gbranch.setText(branch);
        gamount.setText(amount);

        final String finalAcno = acno;
        final String finalNamer = namer;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),moneySendActivity.class);
                i.putExtra("name", finalNamer);
                i.putExtra("acno", finalAcno);
                startActivity(i);
            }
        });

    }
}