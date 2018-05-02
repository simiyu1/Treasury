package com.example.jeffnyangoya.alkibapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class
        MainActivity extends ActionBarActivity implements View.OnClickListener {
    Button bForum, bFaculty, bMessage, bProfile;
    Button bLogout;
    // EditText etName,etAge,etUsername;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bForum = (Button) findViewById(R.id.bForum);
        bFaculty = (Button) findViewById(R.id.bFaculity);
        bProfile = (Button) findViewById(R.id.bProfile);
        bMessage = (Button) findViewById(R.id.bMessages);
        bLogout = (Button) findViewById(R.id.bLogout);

        bFaculty.setOnClickListener(this);
        bProfile.setOnClickListener(this);
        bMessage.setOnClickListener(this);
        bLogout.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
    }
/*
    @Override
    protected void onStart() {
        super.onStart();
       /* if (authenticate()==true){
            displayUserDetails();
        }else{
        startActivity(new Intent(MainActivity.this, login.class));

    }

//}

    private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();
    }

    private void displayUserDetails(){
        User user= userLocalStore.getLoggedInUser();

       /* etUsername.setText(user.username);
        etName.setText(user.name);
        etAge.setText(user.age + "");*/
   // }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bLogout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);

                startActivity(new Intent(this, login.class));
                break;
            case R.id.bForum:
                startActivity(new Intent(this, Forum.class));
                break;
            case R.id.bProfile:
                startActivity(new Intent(this, Profile.class));
                break;
            case R.id.bFaculity:
                startActivity(new Intent(this,Faculty.class));
                break;
            case R.id.bMessages:
                startActivity(new Intent(this, Messages.class));
                break;
        }
    }
}

