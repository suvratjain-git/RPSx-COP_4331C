package com.example.suvratjain.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Paint;

public class MainActivity extends AppCompatActivity {

    public static final String dummyUsername = "test";
    public static final String dummypassword = "test";

    private EditText usernameValue;
    private EditText passwordValue;
    private String login_username;
    private String login_password;
    private String type;
    private TextView signUpButton;
    private TextView forgotPasswordTextView;
    LoginPHPWorker loginPHPWorker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //configure the sign up and forgotbutton
        signUpButton = findViewById(R.id.SignUpLink);
        forgotPasswordTextView = findViewById(R.id.ForgotPasswordLink);

        signUpButton.setPaintFlags(signUpButton.getPaintFlags() & ~Paint.UNDERLINE_TEXT_FLAG);
        forgotPasswordTextView.setPaintFlags(forgotPasswordTextView.getPaintFlags() & ~Paint.UNDERLINE_TEXT_FLAG);

        //connect to database
        loginPHPWorker = new LoginPHPWorker(this);
    }

    /* Called when the user taps the Sign-in button */
    public void login(View view) {


        //get the fields by their ID mentioned in XML
        usernameValue = findViewById(R.id.usernameField);
        passwordValue = findViewById(R.id.passwordField);

        //retrieve the strings typed in the fields
        login_username = usernameValue.getText().toString();
        login_password = passwordValue.getText().toString();
        type = "login";

        //connects to database to check if the user exists
        LoginPHPWorker loginPHPWorker = new LoginPHPWorker(this);
        loginPHPWorker.execute(type, login_username,login_password);

//        if(login_username.equals("test") && login_password.equals("test"))
//        {
//            Intent menu = new Intent(this, Menu.class);
//            //send the username to the next activity
//            menu.putExtra("user name", login_username);
//            startActivity(menu);
//        }

    }


    public void openSignUpPage(View view) {


        signUpButton.setPaintFlags(signUpButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Intent menu = new Intent(this, SignUp.class);
        startActivity(menu);
    }

    public void openForGotPasswordPage(View view) {


        forgotPasswordTextView.setPaintFlags(forgotPasswordTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Intent i = new Intent(this, ForgotPassword.class);
        startActivity(i);
    }
}
