package com.example.luo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    public static final String Activity_name = "Main_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        EditText input_email = (EditText) findViewById(R.id.editText);
        input_email.setText(prefs.getString("email", ""));

        Button page1Button = findViewById(R.id.button);

        if(page1Button != null){

            page1Button.setOnClickListener(v -> {
                Intent goToPage2 = new Intent(MainActivity.this, Profile_activity.class);
                startActivity(goToPage2);
            });
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

    }}

