package com.example.lab2demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GetActivity extends AppCompatActivity {
    TextView txtKq;
    Button btnSendGet;
    String link = "http://dotplays.com/wp-json/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);
        txtKq= findViewById(R.id.txtKq);
        btnSendGet= findViewById(R.id.btnSendGet);
        btnSendGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetLoader getLoader = new GetLoader(txtKq);
                getLoader.execute(link);
            }
        });
    }
}