package com.example.jeffnyangoya.alkibapp;

/**
 * Created by Jeff Nyangoya on 2/9/2016.
 */

import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalStore {

    public static final String SP_NAME= "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore (Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);

    }
    public void storeUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", user.name);
        spEditor.putString("regNo", user.regNo);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);
        spEditor.commit();
    }

    public User getLoggedInUser(){
        String name=userLocalDatabase.getString("name", "");
        String regNo=userLocalDatabase.getString("regNo", "");
        String username=userLocalDatabase.getString("username", "");
        String password=userLocalDatabase.getString("password","");

        User storedUser= new User(name,regNo,username,password);
        return storedUser;
    }
    public void setUserLoggedIn (boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }
    public boolean getUserLoggedIn(){
        if (userLocalDatabase.getBoolean("loggedIn",false)==true){
            return true;
        }
        else{
            return false;
        }
    }
    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

}
