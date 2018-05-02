package com.example.jeffnyangoya.alkibapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class register extends ActionBarActivity implements View.OnClickListener {
    Button bRegister;
    EditText etName,etregNo,etUsername,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etName = (EditText) findViewById(R.id.etName);
        etregNo = (EditText) findViewById(R.id.etregNo);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bRegister:
                String name=etName.getText().toString();
                String username=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                String regNo=etregNo.getText().toString();

                User user= new User(name,regNo,username,password);

                registerUser(user);
                break;
        }
    }
    private void registerUser(User user){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(register.this,login.class));
            }
        });
    }
}
