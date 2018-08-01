package com.example.suvratjain.firstapp;

import android.content.Context;
import android.os.AsyncTask;

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

public class GameLogic extends AsyncTask<String, Void, String>
{
    Context context;

    private String submitChoice = "http://ameade.us/API/submitChoicerpsx.php";

    private String verifyRoomfull = "http://ameade.us/API/verifyFullRoomrpsx.php";

    public GameLogic(Context ctx)
    {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params)
    {
        int room = Integer.parseInt(params[0]);
        String displayName = params[1];
        int choice = Integer.parseInt(params[2]);


        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("room", room);
            jsonObject.put("displayName", displayName);
            jsonObject.put("choice", choice);

            URL url = new URL(submitChoice);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            bufferedWriter.write(String.valueOf(jsonObject));
            bufferedWriter.flush();

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


            return result.toString();



        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
