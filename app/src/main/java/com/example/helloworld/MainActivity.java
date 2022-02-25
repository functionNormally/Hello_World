package com.example.helloworld;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MenuItem it = (MenuItem) findViewById(R.id.resetAction);
        resetAction(it);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void resetAction(MenuItem item) {
        EditText input = (EditText) findViewById(R.id.edit_text);
        EditText input1 = (EditText) findViewById(R.id.edit_text_1);
        EditText input2 = (EditText) findViewById(R.id.edit_text_2);
        EditText input3 = (EditText) findViewById(R.id.edit_text_3);
        input.setText("");
        input1.setText("");
        input2.setText("");
        input3.setText("");

    }

    public void Validation(View v) {
        Validation();
    }

    private void Validation() {
        EditText input = (EditText) findViewById(R.id.edit_text);
        EditText input1 = (EditText) findViewById(R.id.edit_text_1);
        EditText input2 = (EditText) findViewById(R.id.edit_text_2);
        EditText input3 = (EditText) findViewById(R.id.edit_text_3);
        Snackbar.make(findViewById(R.id.snackbar_container), input.getText().toString()+ input1.getText().toString() + input2.getText().toString() + input3.getText().toString(), Snackbar.LENGTH_LONG).show();
    }

    public void ShowDate(View v) {
        ShowDate();
    }


    private void ShowDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    public void Add(View v) {
        Add();
    }

    private void Add() {
        EditText input4 = (EditText) findViewById(R.id.tele);
        LinearLayout layout = (LinearLayout) findViewById(R.id.main);
        LinearLayout AddLayout = new LinearLayout(this);
        AddLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        AddLayout.setOrientation(LinearLayout.HORIZONTAL);
        TextView shownumber = new TextView(this);
        shownumber.setText(input4.getText());
        AddLayout.addView(shownumber);
        Button delete = new Button(this);
        delete.setText("Delete");
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(AddLayout);
            }
        });
        AddLayout.addView(delete);
        layout.addView(AddLayout);
        input4.setText("");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText input2 = (EditText) findViewById(R.id.edit_text_2);
        input2.setText(dayOfMonth+"/"+(month+1)+"/"+year);
    }
}