package com.example.helloworld;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
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
    private Integer indexSpinnerDepartments = 0;
    private ArrayList<String> teleNumberList;
    private Set<String> numberSet = new HashSet<String>();

    private Spinner spinnerDepartments;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    static final int REQUEST_DATE_PICKER = 0;
    static final int REQUEST_EDIT_FIRST_NAME = 1;


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

        teleNumberList = new ArrayList<>(); // ArrayList to manage the list of telephone numbers

        /* Get resources stored in persistent file */
        textName = sharedPreferences.getString("textName",textName);
        textFirstName = sharedPreferences.getString("textFirstName",textFirstName);
        textBirthday = sharedPreferences.getString("textBirthday",textBirthday);
        textBirthDepartment = sharedPreferences.getString("textBirthDepartment",textBirthDepartment);
        textTelephoneNumber = sharedPreferences.getString("textTelephoneNumber",textTelephoneNumber);
        indexSpinnerDepartments = sharedPreferences.getInt("spinnerDepartments",indexSpinnerDepartments);

        /* Set text for TextView widgets in MainLayout */
        inputName.setText(textName);
        inputFirstName.setText(textFirstName);
        inputBirthday.setText(textBirthday);
        inputBirthDepartment.setText(textBirthDepartment);
        inputTelephoneNumber.setText(textTelephoneNumber);
        spinnerDepartments.setSelection(indexSpinnerDepartments);

        //Add Telephone Number
        numberSet = sharedPreferences.getStringSet("NumberSet",numberSet);
        for(String number: numberSet){
            teleNumberList.add(number);
        }
        ReAdd();
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
        outState.putInt("spinnerDepartmentsIndex", spinnerDepartments.getSelectedItemPosition());
        outState.putStringArrayList("inputTelephoneNumberList",teleNumberList);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        textName = savedInstanceState.getString("input");
        textFirstName = savedInstanceState.getString("inputFirstName");
        textBirthday = savedInstanceState.getString("inputBirthday");
        textBirthDepartment = savedInstanceState.getString("inputBirthDepartment");
        textTelephoneNumber = savedInstanceState.getString("inputTelephoneNumber");

        inputName.setText(textName);
        inputFirstName.setText(textFirstName);
        inputBirthday.setText(textBirthday);
        inputBirthDepartment.setText(textBirthDepartment);
        inputTelephoneNumber.setText(textTelephoneNumber);
        spinnerDepartments.setSelection(savedInstanceState.getInt("spinnerDepartmentsIndex"));
        teleNumberList = savedInstanceState.getStringArrayList("inputTelephoneNumberList");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Reset all the variables and also reset tha value showed in the widgets of MainLayout
     * @param item item of main menu
     */
    public void resetAction(MenuItem item) {
        /* Reset the variables used in MainActivity */
        textName = "";
        textFirstName = "";
        textBirthday = "";
        textBirthDepartment = "";
        textTelephoneNumber = "";

        /* Reset the content of widgets */
        inputName.setText(textName);
        inputFirstName.setText(textFirstName);
        inputBirthday.setText(textBirthday);
        inputBirthDepartment.setText(textBirthDepartment);
        inputTelephoneNumber.setText(textTelephoneNumber);
        spinnerDepartments.setSelection(0);

        deleteAllNumbers();
        teleNumberList.clear();
        numberSet.clear();

    }

    /**
     * Call the outside application of navigator to search an entered text in widget of MainLayout
     * @param item item of main menu
     */
    public void searchAction(MenuItem item) {
        Intent intent = new Intent();
        intent.setData(Uri.parse( "http://fr.wikipedia.org/?search="+inputBirthDepartment.getText().toString()));
        intent.setAction(Intent.ACTION_VIEW);
        this.startActivity(intent);
    }

    /**
     * Call the outside applications which belong to the ACTION_SEND to share an entered text in widget of MainLayout
     * @param item item of main menu
     */
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

    /**
     * Send all the entered information to a new activity
     */
    private void Validation() {
        /* Display a snack bar to show entered information */
        Snackbar.make(findViewById(R.id.snackbar_container), inputName.getText().toString()+ inputFirstName.getText().toString() + inputBirthday.getText().toString() + inputBirthDepartment.getText().toString(), Snackbar.LENGTH_LONG).show();

        /* Stock entered information in a Parcelable class and start a new activity */
        Stocker stocker = new Stocker();
        stocker.setName(inputName.getText().toString());
        stocker.setFirstname(inputFirstName.getText().toString());
        stocker.setBirthday(inputBirthday.getText().toString());
        stocker.setBirthCity(inputBirthDepartment.getText().toString());
        stocker.setTeleNumbers(teleNumberList);
        stocker.setBirthDepartment(spinnerDepartments.getSelectedItem().toString());

        Bundle bundle = new Bundle();
        bundle.putParcelable("stocker", stocker);
        Intent intent = new Intent(MainActivity.this,DisplayActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void showName(View v) {
        showName();
    }

    private void showName() {
        Intent intent = new Intent("android.intent.action.VIEW");
        Stocker stocker = new Stocker();
        stocker.setName(inputName.getText().toString());
        Bundle bundle = new Bundle();
        bundle.putParcelable("stocker", stocker);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void editFirstName(View v) {
        editFirstName();
    }

    private void editFirstName() {
        Intent i = new Intent("android.intent.action.EDIT");
        Stocker stocker = new Stocker();
        stocker.setFirstname(inputFirstName.getText().toString());
        Bundle bundle = new Bundle();
        bundle.putParcelable("stocker", stocker);
        i.putExtras(bundle);
        startActivityForResult(i, REQUEST_EDIT_FIRST_NAME);
    }

    public void showDate(View v) {
        showDate();
    }


    private void showDate() {
        Intent i = new Intent("android.intent.action.PICK");
        Stocker stocker = new Stocker();
        stocker.setBirthday(inputBirthday.getText().toString());
        Bundle bundle = new Bundle();
        bundle.putParcelable("stocker", stocker);
        i.putExtras(bundle);
        startActivityForResult(i, REQUEST_DATE_PICKER);
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
        System.out.println(inputTelephoneNumber.getText().toString());
        System.out.println(shownumber.getText().toString());
        AddLayout.addView(shownumber);
        teleNumberList.add(shownumber.getText().toString());
        Button call = new Button(this);
        call.setText("Call");
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.DIAL");
                String numberToCall = shownumber.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("number_to_call", numberToCall);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        AddLayout.addView(call);
        Button delete = new Button(this);
        delete.setText("Delete");
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(AddLayout);
                teleNumberList.remove(shownumber.getText().toString());
                numberSet.remove(shownumber.getText().toString());
            }
        });
        AddLayout.addView(delete);
        layout.addView(AddLayout);
        textTelephoneNumber = "";
        inputTelephoneNumber.setText(textTelephoneNumber);
    }

    public void ReAdd() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.number_area);
        for(String number: teleNumberList){
            LinearLayout AddLayout = new LinearLayout(this);
            AddLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            AddLayout.setOrientation(LinearLayout.HORIZONTAL);
            TextView shownumber = new TextView(this);
            shownumber.setText(number);
            AddLayout.addView(shownumber);
            Button call = new Button(this);
            call.setText("Call");
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent("android.intent.action.DIAL");
                    String numberToCall = shownumber.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("number_to_call", numberToCall);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            AddLayout.addView(call);
            Button delete = new Button(this);
            delete.setText("Delete");
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout.removeView(AddLayout);
                    teleNumberList.remove(shownumber.getText().toString());
                    numberSet.remove(shownumber.getText().toString());
                }
            });
            AddLayout.addView(delete);
            layout.addView(AddLayout);
        }
    }

    private void deleteAllNumbers() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.number_area);
        layout.removeAllViews();
    }


    @Override
    protected void onDestroy() {
        textName = inputName.getText().toString();
        textFirstName = inputFirstName.getText().toString();
        textBirthday = inputBirthday.getText().toString();
        textBirthDepartment = inputBirthDepartment.getText().toString();
        textTelephoneNumber = inputTelephoneNumber.getText().toString();
        indexSpinnerDepartments = spinnerDepartments.getSelectedItemPosition();
        editor.putString("textName", textName);
        editor.putString("textFirstName", textFirstName);
        editor.putString("textBirthday", textBirthday);
        editor.putString("textBirthDepartment", textBirthDepartment);
        editor.putString("textTelephoneNumber", textTelephoneNumber);
        editor.putInt("spinnerDepartments", indexSpinnerDepartments);


        for(String number: teleNumberList){
            numberSet.add(number);
        }
        editor.putStringSet("NumberSet",numberSet);
        editor.apply();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_DATE_PICKER & resultCode == RESULT_OK && (data != null)) {
            String dateSelected = data.getStringExtra("date_selected");
            inputBirthday.setText(dateSelected);
        }
        else if (requestCode == REQUEST_EDIT_FIRST_NAME & resultCode == RESULT_OK && (data != null)) {
            String FirstNme = data.getStringExtra("first_name_edited");
            inputFirstName.setText(FirstNme);
        }
    }
}

