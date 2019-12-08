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
                                if(x[4] == "Gate3"){
                                    jArrayGate3.put(jArray.getJSONObject(i));
                                }
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
}
