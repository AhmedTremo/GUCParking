package com.example.gucparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Thankyou extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);
        Button back = findViewById(R.id.buttonb);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(Status.this,Updates.class);
//                i.putExtra("user",userinfo);
//                startActivity(i); need to navigate to updates page

            }
        });

    }
}
