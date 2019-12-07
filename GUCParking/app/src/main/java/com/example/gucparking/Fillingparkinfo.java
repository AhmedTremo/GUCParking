package com.example.gucparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class Fillingparkinfo extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private String zone;
    private Spinner spinner;
    private String username;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    private static final String[] paths = {"Gate1", "Gate2", "Gate3","Gate4","Gate5","OnCampusParking"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String URL = "http://localhost:8080/addingUpdate";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillingparkinfo);
        Button done = findViewById(R.id.button2);


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
        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String userinfo = username +"has parked at "+ zone;
                OkHttpClient client = new OkHttpClient();

                RequestBody formbody = new FormEncodingBuilder()
                        .add("update",userinfo)
                        .build();
                Request request = new Request.Builder()
                        .url(URL)
                        .post(formbody)
                        .build();
                try{
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) System.out.println("cannot insert");
                    else {
                        System.out.println("Successful");
                        Intent i = new Intent(Fillingparkinfo.this,Updates.class);
                        startActivity(i);
                    }

                    try{
                        System.out.println(response.body().string());
                    }
                    catch(Exception e){}
                }
                catch(Exception e){
                    e.printStackTrace();
                }



            }
        });

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(Fillingparkinfo.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        zone = paths[position];
        //Attribute to fill later on in the database
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

}
