package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
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
    private EditText input;
    private EditText input1;
    private EditText input2;
    private EditText input3;
    private EditText input4;
    private String inputtext;
    private String inputtext1;
    private String inputtext2;
    private String inputtext3;
    private String inputtext4;
    private Spinner spinnerDepartments;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MenuItem it = (MenuItem) findViewById(R.id.resetAction);
        sharedPreferences= getSharedPreferences("input_text", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        inputtext=sharedPreferences.getString("input",inputtext);
        inputtext=sharedPreferences.getString("input1",inputtext1);
        inputtext=sharedPreferences.getString("input2",inputtext2);
        inputtext=sharedPreferences.getString("input3",inputtext3);
        inputtext=sharedPreferences.getString("input4",inputtext4);
        initView();

        resetAction(it);

    }
    protected void initView(){
        input = (EditText) findViewById(R.id.edit_text);
        input1 = (EditText) findViewById(R.id.edit_text_1);
        input2 = (EditText) findViewById(R.id.edit_text_2);
        input3 = (EditText) findViewById(R.id.edit_text_3);
        input4 = (EditText) findViewById(R.id.tele);
        spinnerDepartments = (Spinner) findViewById(R.id.Spinner);
        inputtext=sharedPreferences.getString("input",inputtext);
        inputtext=sharedPreferences.getString("input1",inputtext1);
        inputtext=sharedPreferences.getString("input2",inputtext2);
        inputtext=sharedPreferences.getString("input3",inputtext3);
        inputtext=sharedPreferences.getString("input4",inputtext4);
        input.setText(inputtext);
        input1.setText(inputtext1);
        input2.setText(inputtext2);
        input3.setText(inputtext3);
        input4.setText(inputtext4);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        inputtext = input.getText().toString();
        inputtext1 = input1.getText().toString();
        inputtext2 = input2.getText().toString();
        inputtext3 = input3.getText().toString();
        inputtext4 = input4.getText().toString();
        outState.putString("input",inputtext);
        outState.putString("input1",inputtext1);
        outState.putString("input2",inputtext2);
        outState.putString("input3",inputtext3);
        outState.putString("input4",inputtext4);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        inputtext = savedInstanceState.getString("input");
        input.setText(inputtext);
        inputtext1 = savedInstanceState.getString("input1");
        input1.setText(inputtext1);
        inputtext2 = savedInstanceState.getString("input2");
        input2.setText(inputtext2);
        inputtext3 = savedInstanceState.getString("input3");
        input3.setText(inputtext3);
        inputtext4 = savedInstanceState.getString("input4");
        input4.setText(inputtext4);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void resetAction(MenuItem item) {
        inputtext = "";
        inputtext1 = "";
        inputtext2 = "";
        inputtext3 = "";
        input.setText(inputtext);
        input1.setText(inputtext1);
        input2.setText(inputtext2);
        input3.setText(inputtext3);
        spinnerDepartments.setSelection(0);
        deleteAllNumbers();
    }

    public void Validation(View v) {
        Validation();
    }

    private void Validation() {
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

    public void Add() {
//        LinearLayout layout = (LinearLayout) findViewById(R.id.main);
        LinearLayout layout = (LinearLayout) findViewById(R.id.number_area);
        LinearLayout AddLayout = new LinearLayout(this);
        AddLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        AddLayout.setOrientation(LinearLayout.HORIZONTAL);
        TextView shownumber = new TextView(this);
        shownumber.setText(input4.getText());
        inputtext4 = input4.getText().toString();
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
        inputtext4 = "";
        input4.setText(inputtext4);
    }

    private void deleteAllNumbers() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.number_area);
        layout.removeAllViews();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        inputtext2 = dayOfMonth+"/"+(month+1)+"/"+year;
        input2.setText(inputtext2);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        inputtext = input.getText().toString();
        inputtext1 = input1.getText().toString();
        inputtext2 = input2.getText().toString();
        inputtext3 = input3.getText().toString();
        inputtext4 = input4.getText().toString();
        editor.putString("input", inputtext);
        editor.putString("input1", inputtext1);
        editor.putString("input2", inputtext2);
        editor.putString("input3", inputtext3);
        editor.putString("input4", inputtext4);
        editor.commit();
    }
}

