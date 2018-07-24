package com.example.suvratjain.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OneVOne extends AppCompatActivity {

    TextView firstNum, secondNum, thirdNum, fourthNum, fifthNum, sixthNum;
    String firstN, secondN, thirdN, fourthN, fifthN, sixthN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_v_one);
    }

    private int covertToInteger()
    {

        firstNum = findViewById(R.id.firstPlaceDigit);
        secondNum = findViewById(R.id.secondPlaceDigit);
        thirdNum = findViewById(R.id.thirdPlaceDigit);
        fourthNum = findViewById(R.id.fourthPlaceDigit);
        fifthNum = findViewById(R.id.fifthPlaceDigit);
        sixthNum = findViewById(R.id.sixthPlaceDigit);

        firstN = firstNum.getText().toString();
        secondN = secondNum.getText().toString();
        thirdN = thirdNum.getText().toString();
        fourthN = fourthNum.getText().toString();
        fifthN = fifthNum.getText().toString();
        sixthN = sixthNum.getText().toString();

        int num1 = Integer.parseInt(firstN);
        int num2 = Integer.parseInt(secondN);
        int num3 = Integer.parseInt(thirdN);
        int num4 = Integer.parseInt(fourthN);
        int num5 = Integer.parseInt(fifthN);
        int num6 = Integer.parseInt(sixthN);

        return num6*100000 + num5*10000 + num4*1000 + num3*100 + num2*10 + num1;
    }

    public String getUsername(int gamerID)
    {
        return null;
    }

    public void enterRoom(View view) {

        int gamerID = covertToInteger();
        String toastText = "Starting game session with " + getUsername(gamerID);
        Toast.makeText(OneVOne.this, toastText, Toast.LENGTH_LONG).show();


    }


}
