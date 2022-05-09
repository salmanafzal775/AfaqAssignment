package com.example.androidassignment.Helper;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.androidassignment.Model.UserModel;

public class AppSharedPreferences {
    public static final String APP_PREFERENCES = "APPPREF";
    private static SharedPreferences appSharedPreferences;
    static String KEY_USER = "user";


    public static void setUser(Context context, UserModel u) {
        try {
            appSharedPreferences = context.getSharedPreferences(KEY_USER, MODE_PRIVATE);
            SharedPreferences.Editor editor = appSharedPreferences.edit();

            editor.putString("name", u.getUserName());
            editor.putString("address", u.getUserAddress());
            editor.putString("password", u.getUserPassword());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserModel getUser(Context context) {
        try {
            appSharedPreferences = context.getSharedPreferences(KEY_USER, MODE_PRIVATE);
            if (appSharedPreferences.contains("name")) {

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
