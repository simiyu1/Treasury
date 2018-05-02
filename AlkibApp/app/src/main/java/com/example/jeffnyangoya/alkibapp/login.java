package com.example.jeffnyangoya.alkibapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class login extends ActionBarActivity implements View.OnClickListener{
    Button bLogin;
    EditText etUsername, etPassword;
    TextView tvRegisterLink;
   UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);

    }

   @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bLogin:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                User user= new User(null, null);

               authenticate(user);
                break;
            case R.id.tvRegisterLink:
                startActivity(new Intent(this, register.class));
                break;
        }
    }
    private void authenticate(User user){
       ServerRequests serverRequests = new ServerRequests(this);
      serverRequests.fetchUserDataInBackground(user, new GetUserCallback() {
           @Override
          public void done(User returnedUser) {
                if (returnedUser == null) {
                    showErrorMessage();
                } else {
                   logUserIn(returnedUser);
               }

           }
       });
   }
    private void showErrorMessage(){


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(login.this);
        dialogBuilder.setMessage("Incorrect User Details");
        dialogBuilder.setPositiveButton("OK", null);
        dialogBuilder.show();
    }


    private void logUserIn(User returnedUser){
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);

        startActivity(new Intent(this, MainActivity.class));
    }
}

