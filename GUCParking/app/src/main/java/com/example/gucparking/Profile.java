package com.example.gucparking;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    private String pmail;
    private String id;
    private String status; // As the default when a user logs in to be not parked in the begenning
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    TextView v1;
    TextView v2;
    TextView v3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        status = "notParked";
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        v1 = (TextView) findViewById(R.id.textView);
        v2 = (TextView) findViewById(R.id.textView2);
        v3 = (TextView) findViewById(R.id.textView4);
        Button p = findViewById(R.id.proceed1);
        p.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null) {
                    pmail = user.getEmail();
                    id = user.getUid();



                }
                else {
                    pmail = "Not Signed In";
                    id = "Nothing To Show";
                }


            }
        };
        mAuthListener.onAuthStateChanged(mAuth);
        v1.setText("USERNAME : "+pmail);

        v2.setText("USERID : "+id);

        v3.setText("STATUS : "+status);






       /* pmail = getIntent().getExtras().getString("STRING_I_NEED","NO NAME");
        v1 = (TextView) findViewById(R.id.textView);
        v1.setText(v1.getText()+pmail);
        id = getIntent().getExtras().getString("ID","NO ID");
        v2 = (TextView) findViewById(R.id.textView2);
        v2.setText(v2.getText()+id);
        v3 = (TextView) findViewById(R.id.textView4);
        v3.setText(v3.getText()+status);*/


    }
    public void setStatus(String s){
        this.status = s;
    }
    public String getStatus(){
        return this.status;
    }
    public void changeStatus(){
        if(status.equals("Not Parked"))
            status = "Parked";
        else
            status = "Not Parked";
    }


    @Override
    public void onClick(View v) {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null) {
                    if(user.getDisplayName()=="Parked"){
                        Intent i = new Intent(Profile.this,Fillingparkinfo.class);
                        startActivity(i);
                    }
                    else{
                        if(user.getDisplayName().equals("Not Parked")){
                            Intent i = new Intent(Profile.this,Buildings.class);
                            startActivity(i);
                        }
                        else{
                            Intent i = new Intent(Profile.this,Status.class);
                            startActivity(i);
                        }
                    }



                }
                else {
                    pmail = "Not Signed In";
                    id = "Nothing To Show";
                }


            }
        };
        mAuthListener.onAuthStateChanged(mAuth);
    }
}