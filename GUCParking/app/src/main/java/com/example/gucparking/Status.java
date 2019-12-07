package com.example.gucparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Status extends AppCompatActivity {

    private String username;
    private String status;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    username = user.getDisplayName();
                } else {
                    username = "guest";
                }

            }
        };

        Button parked = findViewById(R.id.parked);
        Button notparked = findViewById(R.id.looking);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        Intent i = new Intent(Status.this,Updates.class);
        i.putExtra("user",username);
        startActivity(i);
        parked.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                status = "parked";
                String userinfo = username + " " + status;
                Intent i = new Intent(Status.this,Fillingparkinfo.class);
                i.putExtra("user",userinfo);
                startActivity(i);
            }
        });
        notparked.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                status = "not parked";
                String userinfo = username + " " + status;
                Intent i = new Intent(Status.this,Updates.class);
                i.putExtra("user",userinfo);
                startActivity(i);

            }
        });
    }
}
