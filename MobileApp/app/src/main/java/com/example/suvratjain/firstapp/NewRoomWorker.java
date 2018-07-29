package com.example.suvratjain.firstapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewSessionWorker extends AsyncTask<String, Void, String> {

    Context context;

    NewSessionWorker(Context ctx)
    {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
