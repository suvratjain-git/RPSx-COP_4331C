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

public class GameWorker extends AsyncTask<String, Void, String>
{
    private String getUserNames_url = "http://ameade.us/API/getRoomUsersrpsx.php";

    Context context;


    public GameWorker (Context ctx)
    {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params)
    {

        if(params[0].equals("displayNames"))
        {

            int room_number = Integer.parseInt(params[1]);

            try {

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("room", room_number);


                URL url = new URL(getUserNames_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                //write data
                bufferedWriter.write(String.valueOf(jsonObject));
                bufferedWriter.flush();

                //close streams
                bufferedWriter.close();
                outputStream.close();

                //start input stream
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                StringBuilder result = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null)
                {
                    result.append(line);
                }


                bufferedReader.close();
                inputStream.close();

                httpURLConnection.disconnect();

    System.out.println("Host result = " + result.toString());

                return result.toString();


            } catch (JSONException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        return null;
    }

    @Override
    protected void onPostExecute(String result)
    {

    }
}
