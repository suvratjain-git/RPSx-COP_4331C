package com.example.suvratjain.firstapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;

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

public class LoginPHPWorker extends AsyncTask<String, Void, String> {

    Context context;
    AlertDialog alertDialog;
    private boolean authorization = false;
    private String user_name;
    private String password;


    LoginPHPWorker (Context cxt) {
        context = cxt;
    }

    @Override
    protected String doInBackground(String... params){

        String type = params[0];
        String login_url = "http://192.168.1.2/login.php";


        if(type.equals("login"))
        {
            try {

                 user_name = params[1];
                 password = params[2];

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("user_name", "UTF-8")+"="+URLEncoder.encode(user_name, "UTF-8")+"&"
                        +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");

                bufferedWriter.write(post_data);
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
            }


        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {

        alertDialog.setMessage(result);
        alertDialog.show();

        //start menu if the credentials are correct
        if(result.equals("Login Successful!")){
            Intent menu = new Intent(context, Menu.class);
            //send the username to the next activity
            menu.putExtra("user name", user_name);
            context.startActivity(menu);
            alertDialog.hide();
        }





    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
