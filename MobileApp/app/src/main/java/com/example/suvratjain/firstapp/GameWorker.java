package com.example.suvratjain.firstapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GameWorker extends AsyncTask<Void, Void, Void>
{
    public final String game_url = "http://ameade.us/API/Loginrpsx.php";
    Context context;
    String user_1;
    String user_2;

    public GameWorker (Context ctx)
    {
        context = ctx;
    }

    @Override
    protected Void doInBackground(Void... voids)
    {
        JSONObject jsonObject = new JSONObject();
        URL url = new URL(game_url);

        return null;
    }


}
