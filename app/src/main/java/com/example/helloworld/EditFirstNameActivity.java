package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditFirstNameActivity extends AppCompatActivity {

    private EditText firstNameEdit;
    private Button buttonConfirm, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_first_name);

        initView();
    }

    private void initView() {
        firstNameEdit = (EditText) findViewById(R.id.edit_first_name);
        buttonConfirm = (Button) findViewById(R.id.button_confirm);
        buttonCancel = (Button) findViewById(R.id.button_cancel);

        Stocker stocker = getStocker();
        firstNameEdit.setText(stocker.getFirstname());
    }

    private Stocker getStocker() {
        Intent i = getIntent();
        Bundle bun = i.getExtras();

        return bun.getParcelable("stocker");
    }

    private void btnClick(boolean confirm) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        if (confirm) {
            String firstNameEdited = firstNameEdit.getText().toString();
            intent.putExtra("first_name_edited", firstNameEdited);
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
