package com.example.buttonpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private static final int RESULT_CODE = 9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        Button button = findViewById(R.id.button);
        //if (button != null)
            button.setOnClickListener(v -> {
                Intent goToPage2 = new Intent (MainActivity.this, SubmitActivity.class);
                startActivity(goToPage2);
              //startActivityForResult(goToPage2, 1);
            });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



    }
}
