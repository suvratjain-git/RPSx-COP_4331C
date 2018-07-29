package com.example.suvratjain.firstapp;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Settings extends AppCompatActivity {

    private TextView userNameField, displayNameField, userNameField_value, displayNameField_value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        userNameField = findViewById(R.id.userName);
        userNameField_value = findViewById(R.id.userName_value);
        displayNameField = findViewById(R.id.displayName);
        displayNameField_value = findViewById(R.id.displayName_value);

        final Switch onOffSwitch = findViewById(R.id.sound);
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    onOffSwitch.setText("On");
                }
                else
                {
                    onOffSwitch.setText("Off");
                }
            }

        });

        userNameField.setText("UserName: ");
        userNameField_value.setText(getUsername());
        displayNameField.setText("DisplayName: ");
        displayNameField_value.setText(getDisplayName());
    }

    public String getUsername()
    {

        Intent main = getIntent();
        Bundle b = main.getExtras();
        String username = null;

        if(b!=null){
            username = (String) b.get("user name");
        }

        return username;
    }

    public String getDisplayName()
    {
        Intent main = getIntent();
        Bundle b = main.getExtras();
        String displayName = null;

        if(b!=null){
            displayName = (String) b.get("display name");
        }

        return displayName;
    }


    public void openChangeAccountWebpage(View view)
    {
//        TextView changeAccountInfo = findViewById(R.id.changeAccountInfo);

        Intent i = new Intent(this, WebBrowser.class);
        i.putExtra("Key", "Change Info");
        startActivity(i);

//        changeAccountInfo.setPaintFlags(changeAccountInfo.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

//        WebView webview = new WebView(this);
//        setContentView(webview);
//        webview.loadUrl("http://www.google.com");

    }

    public void openReportAProblemWebpage(View view)
    {
//        TextView reportAProblem = findViewById(R.id.reportAProblem);
//        reportAProblem.setPaintFlags(reportAProblem.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Intent i = new Intent(this, WebBrowser.class);
        i.putExtra("Key", "Report Problem");
        startActivity(i);

//        WebView webview = new WebView(this);
//        setContentView(webview);
//        webview.loadUrl("http://www.facebook.com");
    }
}
