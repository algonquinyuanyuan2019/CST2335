package com.example.androidlabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Arrays;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "MessagesDB";
    private static final String DB_TABLE = "Messages_Table";
    public static final int VERSION_NUM = 1;

    //columns
    private static final String COL_MESSAGE = "Message";
    private static final String COL_ISSEND = "IsSend";
    private static final String COL_MESSAGEID = "MessageID";
    SQLiteDatabase db = this.getReadableDatabase();
    //queries
    private static final String CREATE_TABLE = "CREATE TABLE "+DB_TABLE+" ("+COL_MESSAGEID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_MESSAGE+" TEXT, "+COL_ISSEND+" BIT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(db);
    }
    //insert data
    public boolean insertData(String message, boolean isSend) {
        SQLiteDatabase db = this.getWritableDatabase();//open a database as write only
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_MESSAGE, message);
        if (isSend)
            contentValues.put(COL_ISSEND, 0);
        else
            contentValues.put(COL_ISSEND, 1);

        long result = db.insert(DB_TABLE, null, contentValues);

        return result != -1; //if result = -1 data doesn't insert
    }

    //view data
    public Cursor viewAllData(){

        String query = "Select * from "+DB_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        //this.PrintCursor(cursor);
        Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(cursor));
        return cursor;
    }

    //view data
    public void PrintCursor(){
        SQLiteDatabase dbLite =  this.getReadableDatabase();

        Cursor cursor = dbLite.rawQuery("SELECT * FROM "+DB_TABLE,null);
        //int colIndex = cursor.getColumnIndex("message");
        //for(int i = 0; i < cursor.getCount(); i++){
        //    String m = cursor.getString(colIndex);
        //    System.out.println("Message: " + m);
        //    cursor.moveToNext();
        //}

        if (cursor.getCount() != 0){
            //while (c.moveToNext()){
            //Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(c));

            //}

            Log.i("DB ver:", String.valueOf(db.getVersion()));
            Log.v("Nuber of Columns:", String.valueOf(cursor.getColumnCount()));
            Log.v("Col Name:", Arrays.toString(cursor.getColumnNames()));
            Log.v("number of results:", String.valueOf(cursor.getCount()));
            Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(cursor));
            System.out.println("Col Name:"+ Arrays.toString(cursor.getColumnNames()));
            //int colIndex = cursor.getColumnIndex("message");
            for(int i = 0; i < cursor.getCount(); i++){
                int j = cursor.getColumnIndex(COL_MESSAGE);
                //String x1 = cursor.getString(j);
                //String x2 = cursor.getString(cursor.getColumnIndex(COL_ISSEND) );
                //String x3 = cursor.getString(cursor.getColumnIndex(COL_MESSAGEID) );

                //System.out.println("Message: " + x1);
                //System.out.println("IsSend: " + x2);
                //System.out.println("MessageID: " + x1);

                //Log.v("Message:", x1);
                //Log.v("IsSend:", x2);
                //Log.v("MessageID:", x3);

                cursor.moveToNext();
            }
            Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(cursor));
        }
    }

}