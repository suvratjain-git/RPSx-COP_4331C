package com.example.suvratjain.firstapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class CreateRoomWorker extends AsyncTask<String, Void, String> {

    Context context;
    String roomNum;
    String displayName;

    CreateRoomWorker(Context ctx)
    {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        //API URL string
        String createRoom_URL = "http://ameade.us/API/createRoomrpsx.php";

        //get the room number and display of the user
        roomNum = params[0];
        displayName = params[1];

        int roomNumber = Integer.parseInt(roomNum);
        try {

            //create a json object
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("room", roomNumber);
            jsonObject.put("displayName",displayName);

            //create  a connection between android and API
            //allow input and output operations
            URL url = new URL(createRoom_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            bufferedWriter.write(String.valueOf(jsonObject));
            bufferedWriter.flush();

            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String result = "";
            String line;

            while((line = bufferedReader.readLine()) != null)
            {
                result += line;
            }

            bufferedReader.close();
            inputStream.close();

            httpURLConnection.disconnect();

            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }



        return null;
    }



    @Override
    protected void onPostExecute(String result) {

        if(result.equals("\"0\""))
        {
            Toast.makeText(context, "Connection Error!", Toast.LENGTH_LONG).show();
        }
        else if(result.equals("\"1\""))
        {
            String str = "Welcome to Game Room #" + roomNum;
            Toast.makeText(context, str, Toast.LENGTH_LONG).show();

            Intent i = new Intent(context, Game.class);
            i.putExtra("gamer_category", "host");
            i.putExtra("display name", displayName);
            i.putExtra("room number", roomNum);
            context.startActivity(i);
            ((Activity)context).finish();
        }
        else if(result.equals("\"2\""))
        {
            Toast.makeText(context, "This Room is already exists! Please try another set of digits.", Toast.LENGTH_LONG).show();
        }

    }


}
