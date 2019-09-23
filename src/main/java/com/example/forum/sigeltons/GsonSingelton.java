package com.example.forum.sigeltons;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonSingelton {
    private static Gson instance;

    public static Gson getInstance() {
        if (instance == null){
            instance = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        }
        return instance;
    }

}
