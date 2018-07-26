
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

public class ForgotPasswordWorker extends AsyncTask<String, Void, String> {

    Context context;



    ForgotPasswordWorker(Context cxt)
    {
        context = cxt;
    }

    @Override
    protected String doInBackground(String... params){

        String email = params[0];
        String forgot_password_url = "http://ameade.us/sendPasswordEmailrpsx.php";

        try {

            JSONObject jsonObject = new JSONObject();
            URL url = new URL(forgot_password_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "";
            String line;


            jsonObject.put("email", email);

            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            bufferedWriter.write(String.valueOf(jsonObject));
            bufferedWriter.flush();

            bufferedWriter.close();
            outputStream.close();

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
//
    }

    @Override
    protected void onPostExecute(String result) {

        if(result.equals("\"0\""))
        {
            Toast.makeText(context, "Sorry, but this e-mail does not exist...", Toast.LENGTH_LONG).show();
        }
        else if (result.equals("\"1\""))
        {
            Toast.makeText(context, "Email sent!", Toast.LENGTH_LONG).show();
            ((Activity)context).finish();
        }


    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }


}
