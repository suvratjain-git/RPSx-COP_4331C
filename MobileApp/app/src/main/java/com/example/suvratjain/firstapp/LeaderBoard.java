package com.example.suvratjain.firstapp;

import android.app.ActionBar.*;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.Gravity;
import android.view.ViewGroup;
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

        TextView t1 = findViewById(R.id.winEntry_1_displayName);
        TextView t2 = findViewById(R.id.winEntry_1_winCount);
        TextView t3 = findViewById(R.id.winEntry_2_displayName);
        TextView t4 = findViewById(R.id.winEntry_2_winCount);
        TextView t5 = findViewById(R.id.winEntry_3_displayName);
        TextView t6 = findViewById(R.id.winEntry_3_winCount);
        TextView t7 = findViewById(R.id.winEntry_4_displayName);
        TextView t8 = findViewById(R.id.winEntry_4_winCount);
        TextView t9 = findViewById(R.id.winEntry_5_displayName);
        TextView t10 = findViewById(R.id.winEntry_5_winCount);

        TextView [] rowEntries = {t1, t2, t3, t4, t5, t6, t7,t8, t9, t10};

        int i = 0;
        for(String k: keys)
        {
            System.out.println("Win Key: " + k + ", Value " + data.get(k));

            rowEntries[i].setText(k);
            i+=2;
        }

        i = 1;
        for(String k: keys)
        {
//            System.out.println("Key: " + k + ", Value " + data.get(k));

            rowEntries[i].setText(Integer.toString(data.get(k)));
            i+=2;
        }


    }


    public void dispalyLossBoardData(String lossList) throws JSONException {

        HashMap<String, Integer> data = convertJSONtoLinkedHashMap(lossList);
        Set<String> keys = data.keySet();

//        System.out.println("Losers Linked Hash Map: " + data);

        TextView t1 = findViewById(R.id.lossEntry_1_displayName);
        TextView t2 = findViewById(R.id.lossEntry_1_lossCount);
        TextView t3 = findViewById(R.id.lossEntry_2_displayName);
        TextView t4 = findViewById(R.id.lossEntry_2_lossCount);
        TextView t5 = findViewById(R.id.lossEntry_3_displayName);
        TextView t6 = findViewById(R.id.lossEntry_3_lossCount);
        TextView t7 = findViewById(R.id.lossEntry_4_displayName);
        TextView t8 = findViewById(R.id.lossEntry_4_lossCount);
        TextView t9 = findViewById(R.id.lossEntry_5_displayName);
        TextView t10 = findViewById(R.id.lossEntry_5_lossCount);

        TextView [] rowEntries = {t1, t2, t3, t4, t5, t6, t7,t8, t9, t10};

        int i = 0;
        for(String k: keys)
        {
            System.out.println("Loss Key: " + k + ", Value " + data.get(k));

            rowEntries[i].setText(k);
            i+=2;
        }

        i = 1;
        for(String k: keys)
        {
//            System.out.println("Key: " + k + ", Value " + data.get(k));

            rowEntries[i].setText(Integer.toString(data.get(k)));
            i+=2;
        }

    }

}
