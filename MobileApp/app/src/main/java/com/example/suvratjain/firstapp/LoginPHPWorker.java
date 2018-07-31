package com.example.suvratjain.firstapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.webkit.URLUtil;
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
import java.net.URLEncoder;

import static java.lang.Thread.sleep;

public class LoginPHPWorker extends AsyncTask<String, Void, String> {

    Context context;
    private String user_name = null;
    private String password = null;


    LoginPHPWorker (Context cxt) {
        context = cxt;
    }

    @Override
    protected String doInBackground(String... params){

        String type = params[0];
//      String login_url = "http://192.168.1.2/login.php";
        String login_url = "http://ameade.us/API/Loginrpsx.php";


        if(type.equals("login"))
        {
            try {

                //get the user entered username and password
                user_name = params[1];
                password = params[2];

                JSONObject loginVerification = new JSONObject();
                loginVerification.put("Username", user_name);
                loginVerification.put("Password", password);

                //create a URL pointer that points to the file specified by the URL path
                URL url = new URL(login_url);
                //open port to allow http connection
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                //create an output stream to connect to the open port and write to the stream using buffered writer
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                bufferedWriter.write(String.valueOf(loginVerification));
                bufferedWriter.flush();


                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                StringBuilder result = new StringBuilder();
//                StringBuilder result = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null)
                {
//                    result += line;
                    result.append(line);
                }


                bufferedReader.close();
                inputStream.close();

                httpURLConnection.disconnect();

                return result.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        return null;
    }

    @Override
    protected void onPreExecute() {

    }


    @Override
    protected void onPostExecute(String result) {

        //start menu if the credentials are correct
        if (result == null) {
            Toast.makeText(context, "Connection error...", Toast.LENGTH_LONG).show();
        } else if(result.equals("\"0\"")) {
            Toast.makeText(context, "Invalid Login. Please try again...", Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(context, "Login Successful. Welcome!", Toast.LENGTH_LONG).show();

            Intent menu = new Intent(context, Menu.class);
            menu.putExtra("user name", user_name);
            menu.putExtra("json object", result);
            context.startActivity(menu);


        }

    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }


}
