package com.example.gucparking;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
private String pmail;
private String id;
private String status = "Not Parked"; // As the default when a user logs in to be not parked in the begenning
TextView v1;
TextView v2;
TextView v3;

    public void setStatus(String s){
        this.status = s;
    }
    public String getStatus(){
        return this.status;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        pmail = getIntent().getExtras().getString("STRING_I_NEED","NO NAME");
        v1 = (TextView) findViewById(R.id.textView);
        v1.setText(v1.getText()+pmail);
        id = getIntent().getExtras().getString("ID","NO ID");
        v2 = (TextView) findViewById(R.id.textView2);
        v2.setText(v2.getText()+id);
        v3 = (TextView) findViewById(R.id.textView4);
        v3.setText(v3.getText()+status);


    }




}
