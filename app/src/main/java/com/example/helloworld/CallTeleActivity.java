package com.example.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CallTeleActivity extends AppCompatActivity {

    private TextView phoneNumberView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println(9);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        System.out.println(7);

        initView();
    }

    private void initView() {
        phoneNumberView = (TextView) findViewById(R.id.telephone_number_view);

        phoneNumberView.setText(getNumberToCall());
    }

    private String getNumberToCall() {
        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        return bundle.getString("number_to_call");
    }

    public void callButtonClick(View view) {
        callButtonClick();
    }

    private void callButtonClick() {
        Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + phoneNumberView.getText().toString()));
        startActivity(intent);
    }

}
