package com.example.gucparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Status extends AppCompatActivity {

    private String userinfo;
    private String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button parked = findViewById(R.id.parked);
        Button notparked = findViewById(R.id.looking);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        Intent i = new Intent(Status.this,Updates.class);
        i.putExtra("user",userinfo);
        startActivity(i);
        parked.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                status = "parked";
                Intent i = new Intent(Status.this,Fillingparkinfo.class);
                i.putExtra("user",userinfo);
                startActivity(i);
            }
        });
        notparked.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                status = "not parked";
                Intent i = new Intent(Status.this,Updates.class);
                i.putExtra("user",userinfo);
                startActivity(i);
            }
        });
    }
}
