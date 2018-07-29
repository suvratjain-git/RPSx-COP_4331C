package com.example.suvratjain.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebBrowser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);

        WebView webview = new WebView(this);
        setContentView(webview);


        Intent browser = getIntent();
        Bundle web = browser.getExtras();

        String key = (String) web.get("Key");

        if(key.equals("Change Info"))
        {
            webview.loadUrl("http://www.google.com");
        }
        else if (key.equals("Report Problem"))
        {
            webview.loadUrl("http://www.facebook.com");
        }

        finish();
    }
}
