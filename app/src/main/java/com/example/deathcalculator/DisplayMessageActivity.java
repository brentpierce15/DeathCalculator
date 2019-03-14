package com.example.deathcalculator;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class DisplayMessageActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        String birthDate = intent.getStringExtra(MainActivity.EXTRA_BIRTH);
        String deathDate = intent.getStringExtra(MainActivity.EXTRA_DEATH);
        String yearsLived = intent.getStringExtra(MainActivity.EXTRA_YEARS);
        String daysLived = intent.getStringExtra(MainActivity.EXTRA_DAYS);

        TextView lifeLivedView = findViewById(R.id.lifeLivedView);

        if(!(birthDate == null || deathDate == null )) {
                int byear = Integer.parseInt(birthDate.substring(0,4));
                int bmonth = Integer.parseInt(birthDate.substring(4,6));
                int bday = Integer.parseInt(birthDate.substring(6,8));
                int dyear = Integer.parseInt(deathDate.substring(0,4));
                int dmonth = Integer.parseInt(deathDate.substring(4,6));
                int dday = Integer.parseInt(deathDate.substring(6,8));

                LocalDate dateBirth = LocalDate.of(byear,bmonth,bday);
                LocalDate dateDeath = LocalDate.of(dyear,dmonth,dday);

                Period period = Period.between(dateBirth, dateDeath);

                lifeLivedView.setText("They lived " + period.getYears() + " Years, " + period.getMonths() + " Months, and " + period.getDays() + " Days");

        }else if(!(birthDate == null || yearsLived == null || daysLived == null )) {

                int year = Integer.parseInt(birthDate.substring(0,4));
                int month = Integer.parseInt(birthDate.substring(4,6));
                int day = Integer.parseInt(birthDate.substring(6,8));
                LocalDate dateBirth = LocalDate.of(year,month,day);
                dateBirth = dateBirth.plusYears(Long.parseLong(yearsLived));
                dateBirth = dateBirth.plusDays(Long.parseLong(daysLived));

                lifeLivedView.setText("They died on " + dateBirth);

        }else if(!(deathDate == null || yearsLived == null || daysLived == null )) {

                int year = Integer.parseInt(deathDate.substring(0,4));
                int month = Integer.parseInt(deathDate.substring(4,6));
                int day = Integer.parseInt(deathDate.substring(6,8));
                LocalDate dateDeath = LocalDate.of(year,month,day);
                dateDeath = dateDeath.minusYears(Long.parseLong(yearsLived));
                dateDeath = dateDeath.minusDays(Long.parseLong(daysLived));

                lifeLivedView.setText("They were born on " + dateDeath);

        }

    }
}
