package com.example.androidlabs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.support.design.widget.Snackbar;


public class TestToolbar_bkp extends AppCompatActivity {

    String overflowToast = "You clicked on the overflow menu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toolbar toolbar;//when import toolBar, if use new appCompatActivity, import the new version
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);

        toolbar = (Toolbar)findViewById(R.id.toolbar);//get tool bar
        setSupportActionBar(toolbar);//display the tool bar
Log.d("xxx1","xxx");
int i=1;
        //  original:   getSupportActionBar().setDisplayShowTitleEnabled(true);//disappear the title

        //add back navigation button
        //if (getSupportActionBar() !=null){
        //    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //    getSupportActionBar().setDisplayShowHomeEnabled(true);
        //}

        //turn off action bar in styles.xml???????

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//inflate the menu items for use in the action bar

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            //what to do when the menu item is selected:
            case R.id.overflow:
                //Show the toast immediately:
                Toast.makeText(this, "You clicked on the overflou menu", Toast.LENGTH_LONG).show();
                //Toast.makeText(this, overflowToast, Toast.LENGTH_LONG).show();
                break;
            case R.id.toast://when click ont he toast, look for the id,
                //Show the toast immediately:
                Toast.makeText(this, "This is the initial message", Toast.LENGTH_LONG).show();
                break;
            case R.id.dialog:
                alertExample();
                break;
            case R.id.snackbar:
                //Snackbar code:
                //Snackbar sb = Snackbar.make(toolbar, "This is the Snackbar", Snackbar.LENGTH_LONG)
                 //       .setAction("Go Back?", e -> finish());
                //sb.show();
                break;

        }
        return true;
    }

    public void alertExample()
    {
        View middle = getLayoutInflater().inflate(R.layout.dialog, null);
        EditText et = (EditText)middle.findViewById(R.id.view_edit_text);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("The Message")//????what is it?
                .setPositiveButton("Positive", new DialogInterface.OnClickListener() {//the sring Positive will show on the screen
                    public void onClick(DialogInterface dialog, int id) {
                        overflowToast = et.getText().toString();
                    }
                })
                .setNegativeButton("Negative", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // What to do on Cancel
                    }
                }).setView(middle);

        builder.create().show();
    }

}
