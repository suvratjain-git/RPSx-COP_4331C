package com.example.suvratjain.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ExistingSession extends AppCompatActivity {

    TextView firstNum, secondNum, thirdNum, fourthNum;
    String firstN, secondN, thirdN, fourthN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_session);
    }


    public void enterRoom(View view) {

        firstNum = findViewById(R.id.editText1);
        secondNum = findViewById(R.id.editText2);
        thirdNum = findViewById(R.id.editText3);
        fourthNum = findViewById(R.id.editText4);

        firstN = firstNum.getText().toString();
        secondN = secondNum.getText().toString();
        thirdN = thirdNum.getText().toString();
        fourthN = fourthNum.getText().toString();

        int num1 = Integer.parseInt(firstN);
        int num2 = Integer.parseInt(secondN);
        int num3 = Integer.parseInt(thirdN);
        int num4 = Integer.parseInt(fourthN);

        int result = num1*1000 + num2*100 + num3*10 + num4;
        String roomNumber = "Entering Room #" + Integer.toString(result);

        //go into the database to check if the session exists
        Toast.makeText(ExistingSession.this, roomNumber, Toast.LENGTH_LONG).show();


    }
}
