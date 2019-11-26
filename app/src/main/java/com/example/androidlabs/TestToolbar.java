package com.example.androidlabs;


import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;


public class TestToolbar extends AppCompatActivity {

    String overflowToast = "You clicked on the overflow menu";
    Toolbar tBar;//when import toolBar, if use new appCompatActivity, import the new version
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        tBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(tBar);



        //getSupportActionBar().setDisplayShowTitleEnabled(true);//disappear the title

        //add back navigation button
        if (getSupportActionBar() !=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

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
                Snackbar sb = Snackbar.make(tBar, "This is the Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Go Back?", e -> finish());
                sb.show();
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
                        Toast.makeText(getBaseContext(),overflowToast, Toast.LENGTH_LONG).show();
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
