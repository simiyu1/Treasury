package com.example.jeffnyangoya.alkibapp;

/**
 * Created by Jeff Nyangoya on 2/9/2016.
 */

/**
 * Created by Jeff Nyangoya on 10/24/2015.
 */
public class User {

    String name, username,password;
    String regNo;

    public User (String name, String regNo, String username, String password){
        this.name = name;
        this.regNo = regNo;
        this.username = username;
        this.password = password;
    }

    public User (String username, String password){
        this.username = username;
        this.password = password;
        this.regNo = regNo;
        this.name = "";
    }

}
