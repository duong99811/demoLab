package com.example.lab2demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PostActivity extends AppCompatActivity {
    EditText edPost1,edPost2,edPost3;
    Button btnSendPost;
    TextView tvPost;
    String link = "https://jsonplaceholder.typicode.com/posts";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        edPost1 = findViewById(R.id.edPost1);
        edPost2 = findViewById(R.id.edPost2);
        edPost3 = findViewById(R.id.edPost3);
        tvPost = findViewById(R.id.tvPost);
        btnSendPost = findViewById(R.id.btnSendPost);
        btnSendPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id= edPost1.getText().toString().trim();
                String body= edPost2.getText().toString().trim();
                String title= edPost3.getText().toString().trim();

                PostLoader postLoader = new PostLoader(id,body,title,tvPost);
                postLoader.execute(link);
            }
        });
    }

}