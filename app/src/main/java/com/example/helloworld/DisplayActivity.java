package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
    private TextView theName;
    private TextView theFirstName;
    private TextView theBirthday;
    private TextView theBirthCity;
    private TextView theBirthDepartment;
    private ArrayList<String> Numbers;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        theName = (TextView) findViewById(R.id.name);
        theFirstName = (TextView) findViewById(R.id.firstname);
        theBirthday = (TextView) findViewById(R.id.birthday);
        theBirthCity = (TextView) findViewById(R.id.birthcity);
        theBirthDepartment = (TextView) findViewById(R.id.birth_department);
        Intent i = getIntent();
        Bundle bun = i.getExtras();
        Stocker stocker = bun.getParcelable("stocker");
        theName.setText("Name: " + stocker.getName());
        theFirstName.setText("First name: " + stocker.getFirstname());
        theBirthday.setText("Birthday: " + stocker.getBirthday());
        theBirthCity.setText("Birth city: " + stocker.getBirthCity());
        theBirthDepartment.setText("Birth department: " + stocker.getBirthDepartment());
        Numbers = stocker.getTeleNumbers();
        for(String number: Numbers){
            LinearLayout layout = (LinearLayout) findViewById(R.id.displaylayout);
            LinearLayout NumberLayout = new LinearLayout(this);
            NumberLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            NumberLayout.setOrientation(LinearLayout.VERTICAL);
            TextView shownumber = new TextView(this);
            shownumber.setText(number);
            NumberLayout.addView(shownumber);
            layout.addView(NumberLayout);
        }






    }

}