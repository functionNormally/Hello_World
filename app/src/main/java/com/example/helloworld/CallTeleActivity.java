package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CallTeleActivity extends AppCompatActivity {

    private TextView phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println(9);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_tele);
        System.out.println(7);

        initView();
    }

    private void initView() {
        System.out.println(8);
        nameView = (TextView) findViewById(R.id.name_view);

        nameView.setText(getStocker().getName());
    }

    private Stocker getStocker() {
        Intent i = getIntent();
        Bundle bun = i.getExtras();

        return bun.getParcelable("stocker");
    }

}
