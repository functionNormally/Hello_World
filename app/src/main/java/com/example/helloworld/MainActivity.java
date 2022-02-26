package com.example.helloworld;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private EditText inputName;
    private EditText inputFirstName;
    private EditText inputBirthday;
    private EditText inputBirthDepartment;
    private EditText inputTelephoneNumber;
    private String textName;
    private String textFirstName;
    private String textBirthday;
    private String textBirthDepartment;
    private String textTelephoneNumber;
    private Spinner spinnerDepartments;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        MenuItem item = (MenuItem) findViewById(R.id.resetAction);
        
        sharedPreferences= getSharedPreferences("input_text", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        initView();

    }
    protected void initView(){
        inputName = (EditText) findViewById(R.id.name_edit_text);
        inputFirstName = (EditText) findViewById(R.id.firstname_edit_text);
        inputBirthday = (EditText) findViewById(R.id.birthday_edit_text);
        inputBirthDepartment = (EditText) findViewById(R.id.birth_city_edit_view);
        inputTelephoneNumber = (EditText) findViewById(R.id.telephone_edit_view);
        spinnerDepartments = (Spinner) findViewById(R.id.Spinner);
        textName = sharedPreferences.getString("textName",textName);
        textFirstName = sharedPreferences.getString("textFirstName",textFirstName);
        textBirthday = sharedPreferences.getString("textBirthday",textBirthday);
        textBirthDepartment = sharedPreferences.getString("textBirthDepartment",textBirthDepartment);
        textTelephoneNumber = sharedPreferences.getString("textTelephoneNumber",textTelephoneNumber);
        inputName.setText(textName);
        inputFirstName.setText(textFirstName);
        inputBirthday.setText(textBirthday);
        inputBirthDepartment.setText(textBirthDepartment);
        inputTelephoneNumber.setText(textTelephoneNumber);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        textName = inputName.getText().toString();
        textFirstName = inputFirstName.getText().toString();
        textBirthday = inputBirthday.getText().toString();
        textBirthDepartment = inputBirthDepartment.getText().toString();
        textTelephoneNumber = inputTelephoneNumber.getText().toString();
        outState.putString("input",textName);
        outState.putString("inputFirstName",textFirstName);
        outState.putString("inputBirthday",textBirthday);
        outState.putString("inputBirthDepartment",textBirthDepartment);
        outState.putString("inputTelephoneNumber",textTelephoneNumber);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textName = savedInstanceState.getString("input");
        inputName.setText(textName);
        textFirstName = savedInstanceState.getString("inputFirstName");
        inputFirstName.setText(textFirstName);
        textBirthday = savedInstanceState.getString("inputBirthday");
        inputBirthday.setText(textBirthday);
        textBirthDepartment = savedInstanceState.getString("inputBirthDepartment");
        inputBirthDepartment.setText(textBirthDepartment);
        textTelephoneNumber = savedInstanceState.getString("inputTelephoneNumber");
        inputTelephoneNumber.setText(textTelephoneNumber);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void resetAction(MenuItem item) {
        textName = "";
        textFirstName = "";
        textBirthday = "";
        textBirthDepartment = "";
        inputName.setText(textName);
        inputFirstName.setText(textFirstName);
        inputBirthday.setText(textBirthday);
        inputBirthDepartment.setText(textBirthDepartment);
        spinnerDepartments.setSelection(0);
        deleteAllNumbers();
    }

    public void searchAction(MenuItem item) {
        Intent intent = new Intent();
        intent.setData(Uri.parse( "http://fr.wikipedia.org/?search="+inputBirthDepartment.getText().toString()));
        intent.setAction(Intent.ACTION_VIEW);
        this.startActivity(intent);
    }

    public void shareAction(MenuItem item) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, inputBirthDepartment.getText().toString());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public void Validation(View v) {
        Validation();
    }

    private void Validation() {
        Snackbar.make(findViewById(R.id.snackbar_container), inputName.getText().toString()+ inputFirstName.getText().toString() + inputBirthday.getText().toString() + inputBirthDepartment.getText().toString(), Snackbar.LENGTH_LONG).show();

        Stocker stocker = new Stocker();
        stocker.setName(inputName.getText().toString());
        stocker.setFirstname(inputFirstName.getText().toString());
        stocker.setBirthday(inputBirthday.getText().toString());
        stocker.setBirthCity(inputBirthDepartment.getText().toString());
        Bundle b = new Bundle();
        b.putParcelable("stocker", stocker);
        Intent i = new Intent(MainActivity.this,DisplayActivity.class);
        i.putExtras(b);
        startActivity(i);


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
        LinearLayout layout = (LinearLayout) findViewById(R.id.number_area);
        LinearLayout AddLayout = new LinearLayout(this);
        AddLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        AddLayout.setOrientation(LinearLayout.HORIZONTAL);
        TextView shownumber = new TextView(this);
        shownumber.setText(inputTelephoneNumber.getText());
        textTelephoneNumber = inputTelephoneNumber.getText().toString();
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
        textTelephoneNumber = "";
        inputTelephoneNumber.setText(textTelephoneNumber);
    }

    private void deleteAllNumbers() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.number_area);
        layout.removeAllViews();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        textBirthday = dayOfMonth+"/"+(month+1)+"/"+year;
        inputBirthday.setText(textBirthday);
    }
    @Override
    protected void onDestroy() {
        textName = inputName.getText().toString();
        textFirstName = inputFirstName.getText().toString();
        textBirthday = inputBirthday.getText().toString();
        textBirthDepartment = inputBirthDepartment.getText().toString();
        textTelephoneNumber = inputTelephoneNumber.getText().toString();
        editor.putString("textName", textName);
        editor.putString("textFirstName", textFirstName);
        editor.putString("textBirthday", textBirthday);
        editor.putString("textBirthDepartment", textBirthDepartment);
        editor.putString("textTelephoneNumber", textTelephoneNumber);
        editor.apply();
        super.onDestroy();
    }
}

