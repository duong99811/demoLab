package com.example.lab2demo;

import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GetLoader extends android.os.AsyncTask<String,Integer,String> {
    TextView tvText;

    public GetLoader(TextView tvText) {
        this.tvText = tvText;
    }



    @Override
    protected String doInBackground(String... strings) {
        String link = strings[0];
        String data = "";
       try {
           URL url = new URL(link);
           HttpURLConnection connection= (HttpURLConnection) url.openConnection();
           InputStream inputStream= connection.getInputStream();
           Scanner scanner = new Scanner(inputStream);
           while (scanner.hasNext()){
               data+= scanner.nextLine();
           }
           scanner.close();
           inputStream.close();
           connection.disconnect();
       }catch (Exception e){

       }
        return data;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
//        try {
//            JSONObject root = new JSONObject(s);
//            JSONObject routes = root.getJSONObject("routes");
//            JSONObject oembed = routes.getJSONObject("/oembed/1.0");
//            JSONArray endpoints =oembed.getJSONArray("endpoints");
//            JSONObject first = endpoints.getJSONObject(0);
//            JSONObject args = first.getJSONObject("args");
//            JSONObject namespace = args.getJSONObject("namespace");
//            String default_ = namespace.getString("default");
//            tvText.setText(default_);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        Gson gson = new Gson();
        Get get = gson.fromJson(s,Get.class);
        String namespace = get.routes.oem.namespace;
        tvText.setText(namespace);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
}
