package com.example.jeffnyangoya.alkibapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Faculty extends ActionBarActivity implements View.OnClickListener {

    Button bFs,bFess,bScai,bSobe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        bFs=(Button)findViewById(R.id.bFs);
       /* bFs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),FES.class);
            }


        });*/
        bFess=(Button)findViewById(R.id.bFess);
        bScai=(Button)findViewById(R.id.bScai);
        bSobe=(Button)findViewById(R.id.bSobe);

       bFs.setOnClickListener(this);
       bFess.setOnClickListener(this);
       bScai.setOnClickListener(this);
        bSobe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    Intent intent= null;
        switch (v.getId()) {
            case R.id.bFs:
                startActivity(new Intent(this, facultyfs.class));
                break;
            case R.id.bFess:
                startActivity(new Intent(this, facultyfess.class));
                break;
            case R.id.bScai:
                startActivity(new Intent(this, facultyscai.class));
                break;
            case R.id.bSobe:
                startActivity(new Intent(this, facultysobe.class));
                break;
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_faculty, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
