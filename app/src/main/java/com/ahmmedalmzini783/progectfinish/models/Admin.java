package com.ahmmedalmzini783.progectfinish.models;

import java.io.Serializable;

public class Admin implements Serializable {
    private int id;
    private String username;
    private String email;
    private String password;

    public static final String TABLE_NAME="Admin";
    public static final String COL_ID="id";
    public static final String COL_USERNAME="username";
    public static final String COL_EMAIL="email";
    public static final String COL_PASSWORD="password";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_USERNAME + " TEXT, "
            + COL_EMAIL + " TEXT, "
            + COL_PASSWORD + " TEXT)";




    public Admin(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
