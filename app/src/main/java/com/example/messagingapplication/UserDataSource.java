package com.example.messagingapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UserDataSource {
    private SQLiteDatabase database;
    private DBUserHelper dbUser;

    public UserDataSource(Context context){
        dbUser = new DBUserHelper(context);
    }

    public void open() throws SQLException {
        database = dbUser.getWritableDatabase();
    }

    public void close(){
        dbUser.close();
    }

    public boolean insertUser(User u) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();
            initialValues.put("username", u.getUserName());
            initialValues.put("password", u.getPassword());
            initialValues.put("firstName", u.getFirstName());
            initialValues.put("lastName", u.getLastName());
            initialValues.put("emailAddress", u.getEmailAddress());
            initialValues.put("phoneNumber", u.getPhoneNumber());
            initialValues.put("getBirthDay", u.getBirthDay());
            initialValues.put("getBirthMonth", u.getBirthMonth());
            initialValues.put("getBirthYear", u.getBirthYear());

            didSucceed = database.insert("user", null, initialValues) >0;
        }
        catch (Exception e){

        }
        return  didSucceed;
    }

    public boolean updateUser(User u) {
        boolean didSucceed = false;
        try{
            long rowID = (long) u.getUserID();
            ContentValues updateValues = new ContentValues();

            updateValues.put("userName", u.getUserID());
            updateValues.put("password", u.getPassword());
            updateValues.put("firstName", u.getFirstName());
            updateValues.put("lastName", u.getLastName());
            updateValues.put("emailAddress", u.getEmailAddress());
            updateValues.put("phoneNumber", u.getPhoneNumber());
            updateValues.put("brithDay", u.getBirthDay());
            updateValues.put("birthMonth", u.getBirthMonth());
            updateValues.put("birthYear", u.getBirthYear());

            didSucceed = database.update("user", updateValues,"_id=" + rowID, null) > 0;
        }
        catch (Exception e) {

        }
        return didSucceed;
    }

    public ArrayList<User> getUser() {
        ArrayList<User> users = new ArrayList<>();
        try {
            String query = "Select * FROM users";
            Cursor cursor = database.rawQuery(query, null);

            User newUser;
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                newUser = new User();
                newUser.setUserID(cursor.getInt(0));
                newUser.setUserName(cursor.getString(1));
                newUser.setPassword(cursor.getString(2));
                newUser.setFirstName(cursor.getString(3));
                newUser.setLastName(cursor.getString(4));
                newUser.setPhoneNumber(cursor.getString(5));
                newUser.setEmailAddress(cursor.getString(6));
                newUser.setBirthDay(cursor.getString(7));
                newUser.setBirthYear(cursor.getString(8));
                newUser.setBirthYear(cursor.getColumnName(9));
                users.add(newUser);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            users = new ArrayList<>();
        }
        return users;
    }

    public User getUser(String id) {
        User newUser = new User();
        try {
            String query = "SELECT  * FROM hotspot WHERE _id = " + id;
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                newUser.setUserID(cursor.getInt(0));
                newUser.setUserName(cursor.getString(1));
                newUser.setPassword(cursor.getString(2));
                newUser.setFirstName(cursor.getString(3));
                newUser.setLastName(cursor.getString(4));
                newUser.setPhoneNumber(cursor.getString(5));
                newUser.setEmailAddress(cursor.getString(6));
                newUser.setBirthDay(cursor.getString(7));
                newUser.setBirthMonth(cursor.getString(8));
                newUser.setBirthYear(cursor.getString(9));
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            newUser = new User();
        }
        return newUser;
    }

    public boolean deleteUser(int userID) {
        boolean didDelete = false;
        try {
            didDelete = database.delete("user", "_id=" + userID, null) > 0;
        }
        catch (Exception e) {

        }
        return didDelete;
    }
}
