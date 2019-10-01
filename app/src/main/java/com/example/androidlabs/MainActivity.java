package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

   // @Override
   // protected void onCreate(Bundle savedInstanceState) {
       // super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_main_linear);
        // setContentView(R.layout.activity_main_grid);
        //setContentView(R.layout.activity_main_relative);
       // setContentView(R.layout.activity_main_lab3);
    //}


    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("lab3_android", Context.MODE_PRIVATE );//sharedPreferences save the user's email address
        SharedPreferences.Editor edit = prefs.edit();
        EditText input_email = findViewById(R.id.inputtext11);
        String email_address = "email";
        edit.putString(email_address, input_email.getText().toString());
        edit.commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab3);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("lab3_android", Context.MODE_PRIVATE );

        EditText input_email = (EditText)findViewById(R.id.inputtext11);
        input_email.setText(prefs.getString("email", ""));
        Button page1Button = findViewById(R.id.button);
        if(page1Button != null)
            page1Button.setOnClickListener(v -> {
                Intent goToPage2 = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(goToPage2);
            });

    }
}
