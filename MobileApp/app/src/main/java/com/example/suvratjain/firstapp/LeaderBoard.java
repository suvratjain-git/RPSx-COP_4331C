package com.example.suvratjain.firstapp;

import android.app.ActionBar.*;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.graphics.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LeaderBoard extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        dispalyWinBoardData((String)b.get("Win Data"));
        dispalyLossBoardData((String)b.get("Loss Data"));

    }


    public void dispalyWinBoardData(String winList)
    {
        System.out.println("Win List: " + winList);
    }


    public void dispalyLossBoardData(String lossList)
    {
        System.out.println("Win List: " + lossList);
    }

}
