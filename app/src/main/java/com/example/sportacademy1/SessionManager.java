package com.example.sportacademy1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.sportacademy1.model.login.LoginData;

import java.util.HashMap;

public class SessionManager {
    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String NAMA = "nama";
    public static final String NO_TELP = "no_telp";
   // public static final String ROLE = "id_role";

    public SessionManager (Context context) {
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(LoginData user){
        editor.putBoolean(IS_LOGGED_IN,true);
        editor.putString(USER_ID,user.getUserId());
        editor.putString(USERNAME, user.getUsername());
        editor.putString(EMAIL, user.getEmail());
        editor.commit();
    }
    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(USER_ID, sharedPreferences.getString(USER_ID,null));
        user.put(USERNAME,sharedPreferences.getString(USERNAME, null));
        user.put(EMAIL,sharedPreferences.getString(EMAIL,null));
        user.put(NAMA,sharedPreferences.getString(NAMA,null));
        user.put(NO_TELP,sharedPreferences.getString(NO_TELP,null));
        return user;
    }

    public void logOutSession() {
        editor.clear();
        editor.commit();
    }

    public boolean isLogged(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }
}
