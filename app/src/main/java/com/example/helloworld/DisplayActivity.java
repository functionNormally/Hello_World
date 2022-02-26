package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {
    private TextView theName;
    private TextView theFirstName;
    private TextView theBirthday;
    private TextView theBirthCity;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        theName = (TextView) findViewById(R.id.name);
        theFirstName = (TextView) findViewById(R.id.firstname);
        theBirthday = (TextView) findViewById(R.id.birthday);
        theBirthCity = (TextView) findViewById(R.id.birthday);
        Intent i = getIntent();
        Bundle bun = i.getExtras();
        Stocker stocker = bun.getParcelable("stocker");
        theName.setText(stocker.getName());
        theFirstName.setText(stocker.getFirstname());
        theBirthday.setText(stocker.getBirthday());
        theBirthCity.setText(stocker.getBirthCity());





    }

}