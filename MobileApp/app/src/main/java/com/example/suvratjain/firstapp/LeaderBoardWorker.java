package com.example.suvratjain.firstapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LeaderBoardWorker extends AsyncTask<Void, Void, Void>
{
    Context context;
    final private String loss_url = "http://ameade.us/API/getLossBoardrpsx.php";
    final private String win_url = "http://ameade.us/API/getWinBoardrpsx.php";
    private String jsonWinBoardString = null;
    private String jsonLossBoardString = null;


    LeaderBoardWorker (Context ctx)
    {
        context = ctx;
    }


    @Override
    protected Void doInBackground(Void... voids)
    {

        try {
            getWinBoardData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            getLossBoardData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        String winResults = jsonWinBoardString;
        String lossResults = jsonLossBoardString;

        Intent startLeaderBoardActivity = new Intent(context, LeaderBoard.class);
        startLeaderBoardActivity.putExtra("Win Data", winResults);
        startLeaderBoardActivity.putExtra("Loss Data", lossResults);
        context.startActivity(startLeaderBoardActivity);
    }


    public void getWinBoardData() throws IOException
    {

        URL winURL = new URL(win_url);

        HttpURLConnection winURLConnection = (HttpURLConnection) winURL.openConnection();
        winURLConnection.setDoInput(true);

        InputStream winInputStream = winURLConnection.getInputStream();

        BufferedReader winBufferedReader = new BufferedReader(new InputStreamReader(winInputStream, "iso-8859-1"));

        String line;
        while((line = winBufferedReader.readLine()) != null)
        {
            jsonWinBoardString += line;
        }

        winBufferedReader.close();
        winInputStream.close();
        winURLConnection.disconnect();

    }

    public void getLossBoardData() throws IOException {

        URL lossURL = new URL(loss_url);

        HttpURLConnection lossURLConnection = (HttpURLConnection) lossURL.openConnection();
        lossURLConnection.setDoInput(true);

        InputStream lossInputStream = lossURLConnection.getInputStream();

        BufferedReader lossBufferedReader = new BufferedReader(new InputStreamReader(lossInputStream, "iso-8859-1"));

        String line;
        while((line = lossBufferedReader.readLine()) != null)
        {
            jsonLossBoardString += line;
        }

        lossBufferedReader.close();
        lossInputStream.close();
        lossURLConnection.disconnect();

    }



}
