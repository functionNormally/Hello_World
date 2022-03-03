package com.example.helloworld;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class DateActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView dateSelectedText;
    private Button buttonConfirm;
    private Button buttonCancel;

    static final int RESULT_OK = 1;
    static final int RESULT_CANCELED = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datedisplay);
        initView();
    }

    private void initView() {
        dateSelectedText = (TextView) findViewById(R.id.date_view);
        buttonConfirm = (Button) findViewById(R.id.button_confirm);
        buttonCancel = (Button) findViewById(R.id.button_cancel);

        dateSelectedText.setText(getStocker().getBirthday());

        showDate(findViewById(R.id.display_date_layout));
    }

    private void showDate(View v) {
        DatePickerDialog datePickerDialog;
        if (dateSelectedText.getText().toString().equals("")) {
            // If date not selected in MainActivity
            datePickerDialog = new DatePickerDialog(
                    this,
                    this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );
        } else {
            // If there is already a date selected in MainActivity
            int year, month, day;
            String[] date = dateSelectedText.getText().toString().split("/");
            year = Integer.parseInt(date[2]);
            month = Integer.parseInt(date[1]);
            day = Integer.parseInt(date[0]);
            datePickerDialog = new DatePickerDialog(
                    this,
                    this,
                    year,
                    month - 1,
                    day
                    );
        }

        datePickerDialog.show();
    }

    private Stocker getStocker() {
        Intent i = getIntent();
        Bundle bun = i.getExtras();

        return bun.getParcelable("stocker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String textBirthday = dayOfMonth+"/"+(month+1)+"/"+year;
        dateSelectedText.setText(textBirthday);
    }

    private void btnClick(boolean confirm) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        if (confirm) {
            String dateSelected = dateSelectedText.getText().toString();
            intent.putExtra("date_selected", dateSelected);
            setResult(RESULT_OK, intent);
        } else {
            setResult(RESULT_CANCELED, intent);
        }
        finish();
    }

    public void confirmBtnClick(View v) {
        btnClick(true);
    }

    public void cancelBtnClick(View v) {
        btnClick(false);
    }
}






