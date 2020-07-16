package com.example.lab2demo;

import com.google.gson.annotations.SerializedName;

public class Get {
    public  Routes routes;
    class Routes{

        @SerializedName("/oembed/1.0")
        public Oem  oem;

    }
    class Oem {

    String namespace;
    }

}
