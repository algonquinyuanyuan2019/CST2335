package com.example.luo1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Profile_activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logintosystem);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        EditText input_email = (EditText) findViewById(R.id.editText);
        input_email.setText(prefs.getString("email", ""));

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("login", Context.MODE_PRIVATE );//sharedPreferences save the user's email address
        SharedPreferences.Editor edit = prefs.edit();
        EditText input_email = findViewById(R.id.editText);
        String email_address = "email";
        edit.putString(email_address, input_email.getText().toString());
        edit.commit();
    }
}
