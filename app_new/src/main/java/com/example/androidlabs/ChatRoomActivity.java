package com.example.androidlabs;

import android.database.sqlite.SQLiteDatabase;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;


public class ChatRoomActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    List<MessageModel> listMessage = new ArrayList<>();
    Button sendBtn;
    Button receiveBtn;
    DatabaseHelper db;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chatroom);//display chatroom layout to the screen

        listView = (ListView)findViewById(R.id.ListView);
        editText = (EditText)findViewById(R.id.ChatEditText);
        sendBtn = (Button)findViewById(R.id.SendBtn);
        receiveBtn = (Button)findViewById(R.id.ReceiveBtn);
        db = new DatabaseHelper(this);
        db.PrintCursor();
        viewData();

        sendBtn.setOnClickListener(c -> {
            String message = editText.getText().toString();             //get messege value
            //MessageModel model = new MessageModel(message, true);
            //listMessage.add(model);
            db.insertData(message, true);                        //insert into database
            editText.setText("");                                       //clear the editText
            viewData();                                                 //display all messages in listView getting from table
            db.PrintCursor();
            //ChatAdapter adt = new ChatAdapter(listMessage, getApplicationContext());
            //listView.setAdapter(adt);
        });

        receiveBtn.setOnClickListener(c -> {
            String message = editText.getText().toString();
            //MessageModel model = new MessageModel(message, false);
            //listMessage.add(model);
            db.insertData(message, false);
            editText.setText("");
            viewData();
            db.PrintCursor();
            //ChatAdapter adt = new ChatAdapter(listMessage, getApplicationContext());
            //listView.setAdapter(adt);
            //this.PrintCursor();
        });
        Log.d("ChatRoomActivity","onCreate");
    }

    private void viewData(){

       Cursor cursor = db.viewAllData();             //get all data from table and assign the resultset to Curso
        if (cursor.getCount() != 0){              //
            while (cursor.moveToNext()){
                MessageModel model = new MessageModel(cursor.getString(1), cursor.getInt(2)==0?true:false);//get one message,
                listMessage.add(model);   //Arraylist List<MessageModel> listMessage = new ArrayList<>();
                ChatAdapter adt = new ChatAdapter(listMessage, getApplicationContext());
                listView.setAdapter(adt);
            }
        }
    }
}
