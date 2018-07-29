package com.example.suvratjain.firstapp;

import android.app.ActionBar.*;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.widget.*;
import android.graphics.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class LeaderBoard extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        try {
            dispalyWinBoardData((String)b.get("Win Data"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            dispalyLossBoardData((String)b.get("Loss Data"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //get a LinkedHashMap of JSON object array
    public LinkedHashMap<String, Integer> convertJSONtoLinkedHashMap (String jsonString) throws JSONException
    {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        JSONObject jsonObject = new JSONObject(jsonString);
        Iterator<?> keys = jsonObject.keys();
        while(keys.hasNext())
        {
            String key = (String)keys.next();
            int value = Integer.parseInt(jsonObject.getString(key));
            map.put(key, value);
        }

        return map;
    }



    public void dispalyWinBoardData(String winList) throws JSONException {

        HashMap<String, Integer> data = convertJSONtoLinkedHashMap(winList);

        Set<String> keys = data.keySet();

        for(String k: keys)
        {
            System.out.println("Key: " + k + ", Value " + data.get(k));
        }



    }


    public void dispalyLossBoardData(String lossList) throws JSONException {

        HashMap<String, Integer> data = convertJSONtoLinkedHashMap(lossList);

        System.out.println("Losers Linked Hash Map: " + data);


    }

}
