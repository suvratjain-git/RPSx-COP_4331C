package com.example.suvratjain.firstapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    //add entry to database
    public void signUp(View view) {

        EditText fn = findViewById(R.id.firstName);
        EditText ln = findViewById(R.id.lastName);
        EditText un = findViewById(R.id.userName);
        EditText gi = findViewById(R.id.gamerID);
        EditText em = findViewById(R.id.e_mail);
        EditText ps1 = findViewById(R.id.password1);
        EditText ps2 = findViewById(R.id.password2);

        String firstName = fn.getText().toString();
        String lastName = ln.getText().toString();
        String username = un.getText().toString();
        String displayName = gi.getText().toString();
        String email = em.getText().toString();
        String password1 = ps1.getText().toString();
        String password2 = ps2.getText().toString();


        SignupWorker signup = new SignupWorker(this);
        signup.execute(firstName, lastName, username, displayName, email, password1);

        boolean everythingIsOkay = true;


        if(everythingIsOkay)
        {
            Toast.makeText(SignUp.this, "User Added", Toast.LENGTH_LONG).show();
        }



    }

    //go back to main screen
    public void cancel(View view) {

        finish();
    }
}
