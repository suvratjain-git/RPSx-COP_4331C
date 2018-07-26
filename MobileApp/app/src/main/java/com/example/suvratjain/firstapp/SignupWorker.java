package com.example.suvratjain.firstapp;

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

public class SignupWorker extends AsyncTask<String, Void, String> {

    Context context;
    AlertDialog alertDialog;
    private boolean authorization = false;
    private String user_name;
    private String password;


    SignupWorker(Context cxt) {
        context = cxt;
    }

    @Override
    protected String doInBackground(String... params){


        String firstName = params[0];
        String lastName = params[1];
        String username = params[2];
        String displayName = params[3];
        String email = params[4];
        String password1 = params[5];

        String signup_url = "http://ameade.us/addUserrpsx.php";

            try {

                JSONObject signup = new JSONObject();
                signup.put("firstName", firstName);
                signup.put("lastName", lastName);
                signup.put("Password", password1);
                signup.put("Username", username);
                signup.put("email", email);
                signup.put("displayName", displayName);

                //create a URL pointer that points to the file specified by the URL path
                URL url = new URL(signup_url);
                //open port to allow http connection
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                //create an output stream to connect to the open port and write to the stream using buffered writer
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

//                String post_data = URLEncoder.encode("Username", "UTF-8")+"="+URLEncoder.encode(user_name, "UTF-8")+"&"
//                        +URLEncoder.encode("Password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");

                bufferedWriter.write(String.valueOf(signup));
                bufferedWriter.flush();


                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String result = "";
//                StringBuilder result = new StringBuilder();
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
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Signup Status");
        alertDialog.hide();
    }

    @Override
    protected void onPostExecute(String result) {

            if(result.equals(""))
            {

            }
            alertDialog.setMessage(result);
            alertDialog.show();


//
//        //start menu if the credentials are correct
//        if(result.equals("\"0\""))
//        {
//            alertDialog.setMessage("Login Unsuccessful. Try Again!");
//            alertDialog.show();
//        }
//
//        else
//        {
//            alertDialog.setMessage("Login Successful!");
//            alertDialog.show();
//
//            Intent menu = new Intent(context, Menu.class);
//            menu.putExtra("user name", user_name);
//            context.startActivity(menu);
//        }
//
//
//        System.out.println("JSON equals: " + result);
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }


}
