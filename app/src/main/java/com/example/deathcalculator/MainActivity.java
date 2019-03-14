package com.example.deathcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String EXTRA_BIRTH = "com.example.myfirstapp.BIRTH";
    public static final String EXTRA_DEATH = "com.example.myfirstapp.DEATH";
    public static final String EXTRA_YEARS = "com.example.myfirstapp.YEARS";
    public static final String EXTRA_DAYS = "com.example.myfirstapp.DAYS";
    String[] keyStrings = {EXTRA_BIRTH,EXTRA_DEATH,EXTRA_YEARS,EXTRA_DAYS};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        EditText birthDate = (EditText) findViewById(R.id.birthDate);
        EditText deathDate  = (EditText) findViewById(R.id.deathDate);
        EditText yearsLived  = (EditText) findViewById(R.id.yearsLived);
        EditText daysLived  = (EditText) findViewById(R.id.daysLived);

        String stringBirthDate = birthDate.getText().toString();
        String stringDeathDate = deathDate.getText().toString();
        String stringYearsLived = yearsLived.getText().toString();
        String stringDaysLived = daysLived.getText().toString();

        String[] dateStrings = {stringBirthDate,stringDeathDate,stringYearsLived,stringDaysLived};

        for(int i = 0; i < dateStrings.length; i++) {
            if (!dateStrings[i].isEmpty()) {
                intent.putExtra(keyStrings[i], dateStrings[i]);
            }
        }

        startActivity(intent);
    }
}
