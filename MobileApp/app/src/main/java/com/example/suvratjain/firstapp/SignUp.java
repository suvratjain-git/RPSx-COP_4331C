package com.example.suvratjain.firstapp;


import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {


    private AlertDialog.Builder alertDialog;
    private boolean allFieldsArefilled = false;
    private int count = 0;

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

        String [] entries = {firstName, lastName, username, displayName, email, password1, password2};


//
//        for(int i = 0; i < 7; i++)
//        {
//            if(entries[i] == null)
//            {
//                String output = entries[i] + " is incomplete!";
//                Toast.makeText(this, output, Toast.LENGTH_LONG).show();
//            }
//
//        }
//
//        for(int i = 0; i < 7; i++)
//        {
//            if(entries[i] != null)
//            {
//                count++;
//            }
//
//        }
//
//        //all the entries are full in the sign up page.
//        if(count == 7) {
//            allFieldsArefilled = true;
//        }
//

            if(password1.equals(password2) && isValidEmail(email)) {
                SignupWorker signup = new SignupWorker(this);
                signup.execute(firstName, lastName, username, displayName, email, password1);
            } else if (!password1.equals(password2) && !isValidEmail(email)) {
                Toast.makeText(this, "Invalid e-mail and Passwords don't match!", Toast.LENGTH_LONG).show();
            } else if (!password1.equals(password2) && isValidEmail(email)) {
                Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_LONG).show();
            } else if (password1.equals(password2) && !isValidEmail(email)) {
                Toast.makeText(this, "Invalid e-mail!", Toast.LENGTH_LONG).show();
            }



    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

//
//    //go back to main screen
//    public void cancel(View view) {
//
//        finish();
//    }
}
