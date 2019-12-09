package com.example.gucparking;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static android.provider.Telephony.Mms.Part.CHARSET;

public class Updates extends AppCompatActivity {

    JSONArray jArray;
    JSONArray jArrayGate1;
    JSONArray jArrayGate2;
    JSONArray jArrayGate3;
    JSONArray jArrayGate4;
    JSONArray jArrayGate5;
    JSONArray jArrayParking;
    JSONArray sortedarray;
    TextView v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);
        v = findViewById(R.id.t1);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        new GetDataTask().execute("http://10.0.2.2:8080/updates");//Getting the data task 10.0.2.2 access local host
    }

    class GetDataTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder b = new StringBuilder();
            try{
                URL url = new URL(strings[0]);
                HttpURLConnection urlconnection;
                urlconnection = (HttpURLConnection) url.openConnection();
                urlconnection.setRequestMethod("GET");
                urlconnection.setDoInput(true);
                urlconnection.setRequestProperty("Accept-Charset", CHARSET);
                //error???
                urlconnection.connect();

                InputStream i = urlconnection.getInputStream();
                BufferedReader r = new BufferedReader(new InputStreamReader(i));
                String line;
                while((line = r.readLine())!=null){

                    b.append(line).append("\n");
                }
                return b.toString();

            }
            catch (MalformedURLException e){
                return "MalformedURL";
            }
            catch( ProtocolException e){
                return "Protocol exception";
            }
            catch (IOException e){
                return "open connection";
            }
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            v.setText(s);
        }
    }
}
