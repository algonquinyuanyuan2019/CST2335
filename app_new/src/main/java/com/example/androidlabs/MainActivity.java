package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Button;


import android.util.Log;

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
//==============================================================================================================


    public static final String ACTIVITY_NAME1 = "Main_ACTIVITY";
//static public void main( args)
    @Override
    //andorid starts from here:
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //不用关心，每次拷贝过来
        setContentView(R.layout.activity_main_lab3);//display layout显示你画的图activity_main_lab3

        //define a file in order to save information to, so that later the information can be fetched and used by another
        //parameter1: file name,
        //parameter2: permission
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("lab3_android", Context.MODE_PRIVATE );
        EditText input_email = (EditText)findViewById(R.id.inputtext11);  //input_email: 指向你图里的inputtext11
        input_email.setText(     prefs.getString("email", "")     ); //input_email: 外面已近取到了的指向你图里的inputtext11 //如果没有值，为默认值，如果有值会调用之前的值
//String x = prefs.getString("email", "");
        //input_email.setText(x);
        Button page1Button = findViewById(R.id.button);//取到图里的button这个id

        if(page1Button != null)//如果取图里的button这个id，找到了做下面

            page1Button.setOnClickListener(v -> { //把这个button要做什么东西挂上来（挂上来意味着android能call下面的代码）
                Intent goToPage2 = new Intent(MainActivity.this, ProfileActivity.class);//设置好从本身MainActivity转入到另外一个java程序ProfileActivity
                startActivity(goToPage2);//启动转出到另外的程序
            });

    }


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
    protected void onStop() {
        super.onStop();
        Log.e(ACTIVITY_NAME1, "In function:" + "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(ACTIVITY_NAME1, "In function:" + "onDestroy()");
    }

}
