package com.example.androidlabs;

import android.content.Intent;
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

        boolean isTablet = findViewById(R.id.fragmentLocation) != null; //check if the FrameLayout is loaded

        listView = (ListView)findViewById(R.id.ListView);
        editText = (EditText)findViewById(R.id.ChatEditText);
        sendBtn = (Button)findViewById(R.id.SendBtn);
        //receiveBtn = (Button)findViewById(R.id.ReceiveBtn);
        db = new DatabaseHelper(this);
        boolean isTable = findViewById(R.id.fragmentLocation) != null;
        db.PrintCursor();
        viewData();

        listView.setOnItemClickListener((list, item, position, id) -> {
            Bundle dataToPass = new Bundle();
            dataToPass.putString("item", listMessage.get(position).getMessage());
            dataToPass.putInt("id", position);
            dataToPass.putLong("db_id", listMessage.get(position).getMessageID());


            if (isTable){
                DetailFragment dFragment = new DetailFragment(); //add a DetailFragment
                dFragment.setArguments( dataToPass ); //pass it a bundle for information
                dFragment.setTablet(true);  //tell the fragment if it's running on a tablet or not
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragmentLocation, dFragment) //Add the fragment in FrameLayout
                        .addToBackStack("AnyName") //make the back button undo the transaction
                        .commit(); //actually load the fragment.
            }else {
                Intent emptyActivity = new Intent(ChatRoomActivity.this, EmptyActivity.class);
                emptyActivity.putExtras(dataToPass);
                startActivityForResult(emptyActivity, 345);
            }

        });

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

/*
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
*/

        Log.d("ChatRoomActivity","onCreate");
    }

    private void viewData(){

       Cursor cursor = db.viewAllData();             //get all data from table and assign the resultset to Curso
        if (cursor.getCount() != 0){              //
            while (cursor.moveToNext()){
                //MessageModel model = new MessageModel(cursor.getString(1), cursor.getInt(2)==0?true:false);//get one message,
                MessageModel model = new MessageModel(cursor.getString(1), cursor.getInt(2)==0?true:false,cursor.getLong(0));//get one messag
                listMessage.add(model);   //Arraylist List<MessageModel> listMessage = new ArrayList<>();
                ChatAdapter adt = new ChatAdapter(listMessage, getApplicationContext());
                listView.setAdapter(adt);
            }
        }
    }

    //This function only gets called on the phone. The tablet never goes to a new activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 345)
        {
            if(resultCode == RESULT_OK) //if you hit the delete button instead of back button
            {
                long id = data.getLongExtra("db_id", 0);
                deleteMessageId((int)id);
            }
        }
    }

    public void deleteMessageId(int id)
    {

        db.deleteEntry(id);
        listMessage.clear();
        viewData();
    }
}
