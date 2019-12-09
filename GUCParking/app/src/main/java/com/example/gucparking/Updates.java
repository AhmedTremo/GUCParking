package com.example.gucparking;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Switch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Updates extends AppCompatActivity {

    JSONArray jArray;
    JSONArray jArrayGate1;
    JSONArray jArrayGate2;
    JSONArray jArrayGate3;
    JSONArray jArrayGate4;
    JSONArray jArrayGate5;
    JSONArray jArrayParking;
    JSONArray sortedarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);
        OkHttpClient client = new OkHttpClient();


        final String URL = "http://localhost:8080/updates";

        Request request = new Request.Builder()
                .url(URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {

                if (response.isSuccessful()) {
                    final String onResponse = response.body().string();
                    try {
                        JSONObject jObject = new JSONObject(onResponse);
                        jArray = jObject.getJSONArray("data");

                        try {

                            for(int i =0;i<jArray.length();i++){
                                String[] x = jArray.getJSONObject(i).toString().split(" ");
                                switch(x[4]){
                                    case "Gate3" : jArrayGate3.put(jArray.getJSONObject(i));
                                    case "Gate4" : jArrayGate4.put(jArray.getJSONObject(i));
                                    case "Gate5":  jArrayGate5.put(jArray.getJSONObject(i));
                                    case "Gate2":  jArrayGate2.put(jArray.getJSONObject(i));
                                    case "Gate1":  jArrayGate1.put(jArray.getJSONObject(i));
                                    case "Parking":jArrayParking.put(jArray.getJSONObject(i));

                                }
                            }
                            String rq = getIntent().getExtras().getString("RequiredPlace");
                            switch(rq){
                                case "B Building": addGate1();addparking();addGate3();addGate2();addGate4();addGate5();
                                case "C Building": addGate3();addGate2();addGate4();addGate5();addGate1();addparking();
                                case "D Building": ;addparking();addGate3();addGate2();addGate1();addGate4();addGate5();
                                case "Exam Halls": ;addGate4();addGate5();addGate3();addGate2();addGate1();addparking();

                            }
                            for(int j =0;j<sortedarray.length();j++){

                            }


                        } catch (JSONException e) {
                            // Oops
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    Updates.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }
            }
        }
        );
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        we're supposed to get the updates from the updates database???

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void addGate3(){
        for(int i=0;i<jArrayGate3.length();i++) {
            try {
                sortedarray.put(jArrayGate3.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void addGate2(){
        for(int i=0;i<jArrayGate2.length();i++) {
            try {
                sortedarray.put(jArrayGate2.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void addGate4(){
        for(int i=0;i<jArrayGate4.length();i++) {
            try {
                sortedarray.put(jArrayGate4.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void addGate5(){
        for(int i=0;i<jArrayGate5.length();i++) {
            try {
                sortedarray.put(jArrayGate5.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void addGate1(){
        for(int i=0;i<jArrayGate1.length();i++) {
            try {
                sortedarray.put(jArrayGate1.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void addparking(){
        for(int i=0;i<jArrayParking.length();i++) {
            try {
                sortedarray.put(jArrayParking.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
