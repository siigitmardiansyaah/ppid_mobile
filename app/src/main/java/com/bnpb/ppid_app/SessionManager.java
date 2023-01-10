package com.bnpb.ppid_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import com.bnpb.ppid_app.model.LoginData;

import java.util.HashMap;

public class SessionManager {
    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String IS_LOGGED_IN = "isLoggedIn";

    //DATA SESSION LOGIN
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_EMAIL = "user_email";
    public static final String NO_HP = "no_hp";
    public static final String USER_LEVEL = "user_level";
    public static final String USER_STATUS = "user_status";

    public SessionManager(Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(LoginData user){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(USER_ID, user.getUser_id());
        editor.putString(USER_NAME, user.getUser_name());
        editor.putString(USER_EMAIL, user.getUser_email());
        editor.putString(NO_HP, user.getNo_hp());
        editor.putString(USER_LEVEL, user.getUser_level());
        editor.putString(USER_STATUS, user.getUser_status());
        editor.commit();
    }

    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<>();
        user.put(USER_ID, sharedPreferences.getString(USER_ID,null));
        user.put(USER_NAME, sharedPreferences.getString(USER_NAME,null));
        user.put(USER_EMAIL, sharedPreferences.getString(USER_EMAIL,null));
        user.put(NO_HP, sharedPreferences.getString(NO_HP,null));
        user.put(USER_LEVEL, sharedPreferences.getString(USER_LEVEL,null));
        user.put(USER_STATUS, sharedPreferences.getString(USER_STATUS,null));
        return user;
    }


    public void logoutSession(){
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

}
