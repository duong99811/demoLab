package com.example.lab2demo;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostLoader extends AsyncTask<String,Integer,String > {
    String id,body,title;
    TextView textView;

    public PostLoader(String id, String body, String title, TextView textView) {
        this.id = id;
        this.body = body;
        this.title = title;
        this.textView = textView;
    }

    @Override
    protected String doInBackground(String... strings) {
        String data= "";
        String link = strings[0];

        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            //Content-Type: application/x-www-form-urlencoded
            httpURLConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            // khoi tao param
            StringBuilder params = new StringBuilder();

            params.append("title");
            params.append("=");
            params.append(title);

            params.append("&");
            params.append("body");
            params.append("=");
            params.append(body);

            params.append("&");
            params.append("userId");
            params.append("=");
            params.append(id);


            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter
                    (new OutputStreamWriter(os, "UTF-8"));

            // dua param vao body cua request
            writer.append(params);

            // giai phong bo nho
            writer.flush();
            // ket thuc truyen du lieu vao output
            writer.close();
            os.close();


            // lay du lieu tra ve
            StringBuilder response = new StringBuilder();

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            }
            else if(responseCode == 201){
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            }
            else {
                return  httpURLConnection.getResponseCode()+"";
            }

            return response.toString();
        }catch (Exception e){

        }
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e("JSON",s);
//        try {
//            JSONObject jsonObject = new JSONObject(s);
//            int id = jsonObject.getInt("id");
//            String title = jsonObject.getString("title");
//            String body = jsonObject.getString("body");
//            String usedId = jsonObject.getString("userId");
//            if (s!=null){
//                textView.setText(id +" : "+ title+" : " +body +" : "+ usedId);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        Gson gson = new Gson();
        Post post = gson.fromJson(s,Post.class);
        textView.setText(post.body);

    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
