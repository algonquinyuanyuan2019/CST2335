package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
import android.graphics.Bitmap;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {
    public static final String ACTIVITY_NAME = "PROFILE_ACTIVITY";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageButton imageButton1;
    Button button2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageButton1.setImageBitmap(imageBitmap);
        }
        Log.e(ACTIVITY_NAME, "In function:" + "onActivityResult");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab3_2);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("lab3_android", Context.MODE_PRIVATE);

        EditText input_text2 = (EditText) findViewById(R.id.inputtext22);
        input_text2.setText(prefs.getString("email", ""));




        imageButton1 = findViewById(R.id.imageButton);
        if (imageButton1 != null)
            imageButton1.setOnClickListener(v -> {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            });


        Button page2Button = findViewById(R.id.button2);
        if(page2Button != null)
            page2Button.setOnClickListener(v -> {
                Intent goToPage2 = new Intent(ProfileActivity.this, ChatRoomActivity.class);

                startActivity(goToPage2);
            });

        Log.e(ACTIVITY_NAME, "In function:" + "onCreate()");

        Button lauchtoWeather = findViewById(R.id.button3);
                if(lauchtoWeather!= null)
                    lauchtoWeather.setOnClickListener(v ->{
                        Intent goToPage2 = new Intent(ProfileActivity.this, WeatherForecast.class);
                        startActivity(goToPage2);




                    });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(ACTIVITY_NAME, "In function:" + "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(ACTIVITY_NAME, "In function:" + "onResume()");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e(ACTIVITY_NAME, "In function:" + "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(ACTIVITY_NAME, "In function:" + "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(ACTIVITY_NAME, "In function:" + "onDestroy()");
    }






}