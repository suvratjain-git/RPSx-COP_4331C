package com.example.suvratjain.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }


    public void getCredentials(View view)
    {
        EditText ue = findViewById(R.id.emailEntry);
        String user_email = ue.getText().toString();

        if(isValidEmail(user_email))
        {
            ForgotPasswordWorker forgotPasswordWorker = new ForgotPasswordWorker(this);
            forgotPasswordWorker.execute(user_email);
        }
        else
        {
            Toast.makeText(this, "Invalid E-Mail!", Toast.LENGTH_LONG).show();
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
