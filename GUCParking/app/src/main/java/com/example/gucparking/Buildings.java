package com.example.gucparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Buildings extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private String building;
    private Spinner spinner1;
    private String[] Buildings =  {"B Building","C Building","D Building","Exam Halls"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildings);
        spinner1 = (Spinner)findViewById(R.id.spinner1);
        Button done1 = findViewById(R.id.button3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Buildings.this,
                android.R.layout.simple_spinner_item,Buildings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);
        done1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Buildings.this,Updates.class);
                i.putExtra("RequiredPlace",building);
                startActivity(i);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        building = Buildings[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void viewMyProfile(View view){
        Intent i = new Intent(Buildings.this,Profile.class);
        startActivity(i);
    }
}
