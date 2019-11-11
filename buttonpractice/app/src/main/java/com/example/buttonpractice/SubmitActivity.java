package com.example.buttonpractice;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SubmitActivity extends AppCompatActivity {

    public static final String ACTIVITY_NAME = "PROFILE_ACTIVITY";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int RESULT_CODE = 9;

    //


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.submit);
            EditText email = findViewById(R.id.editText2);
            SharedPreferences prefs = getApplicationContext().getSharedPreferences("Login_Submit", Context.MODE_PRIVATE);

            EditText input_text2 = (EditText) findViewById(R.id.editText2);
            input_text2.setText(prefs.getString("email", ""));


            Button backToPage1 = findViewById(R.id.button2);
            backToPage1.setOnClickListener(v ->{
                Intent backemail = new Intent(SubmitActivity.this, MainActivity.class);
                backemail.putExtra("email", "yuanyuan");
                setResult(RESULT_CODE, backemail);

            });

           // startActivity();


        }
    }