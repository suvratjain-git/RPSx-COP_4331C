package com.example.suvratjain.firstapp;

import android.app.ActionBar.*;
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

//    final private String loss_url = "http://ameade.us/API/getLossBoardrpsx.php";
//    final private String win_url = "http://ameade.us/API/getWinBoardrpsx.php";
//    private String jsonWinBoardString;
//    private String jsonLossBoardString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

//        LeaderBoardWorker leaderBoardWorker = new LeaderBoardWorker(this);
//        leaderBoardWorker.execute();

    }


    public void dispalyWinBoardData()
    {

    }


    public void dispalyLossBoardData()
    {

    }

}
