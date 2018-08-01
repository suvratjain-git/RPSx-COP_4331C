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

public class RefreshWorker extends AsyncTask<String, Void, String>
{
    private String verifyRoomfull = "http://ameade.us/API/verifyFullRoomrpsx.php";
    private String getUserNames_url = "http://ameade.us/API/getRoomUsersrpsx.php";
    private String verifyBothUsersEntered = "http://ameade.us/API/verifyChoicerpsx.php";
    private String getChoices = "http://ameade.us/API/requestChoicerpsx.php";

    Context context;

    public RefreshWorker(Context ctx)
    {
        context = ctx;
    }

    protected String doInBackground(String... params)
    {

        if(params[0].equals("host"))
        {
            int room_number = Integer.parseInt(params[1]);

            try {
                if(isRoomFull(room_number))
                {
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


                        return result.toString();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                else
                {
                    return "Room not full";
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(params[0].equals("Get Results"))
        {
            int roomNum = Integer.parseInt(params[1]);

            try {
                if(isFull(roomNum))
                {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("room", roomNum);

                    URL url = new URL(getChoices);
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

                    return result.toString();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    public boolean isFull(int room) throws JSONException, IOException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("room", room);

        URL url = new URL(verifyBothUsersEntered);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
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

        if(result.equals("0"))
        {
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return false;
        }


        bufferedReader.close();
        inputStream.close();
        httpURLConnection.disconnect();

        return true;

    }

    public boolean isRoomFull(int room) throws IOException, JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("room", room);

        URL url = new URL(verifyRoomfull);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
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

        if(result.equals("0"))
        {
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return false;
        }


        bufferedReader.close();
        inputStream.close();
        httpURLConnection.disconnect();

        return true;
    }


    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);

    }
}
