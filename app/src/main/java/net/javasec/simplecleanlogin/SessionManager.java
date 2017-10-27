package net.javasec.simplecleanlogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Wiguna R on 13/06/2017.
 */

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context _context;

    int SESSION_MODE = 0; //Private = 0;Public = 1;

    private static final String PREF_NAME = "LoginSession"; //Session Name
    private static final String IS_LOGIN  = "IsLoggedIn"; //For validate log in.

    public static final String KEY_USERNAME = "Username"; //Key to put in Session
    //public static final String KEY_EMAIL  = "Email"; //Key to put in Session
    //public static final String KEY_NAME   = "Name"; //Key to put in Session

    //Construction
    public SessionManager(Context context){
        this._context = context;
        sharedPreferences = _context.getSharedPreferences(PREF_NAME, SESSION_MODE);
        editor = sharedPreferences.edit();
    }

    /*
        Create Login Session
        In this case, I put username as Session.
        You can add values for new Session Key
     */
    public void createLoginSession(String username){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USERNAME, username);
        //editor.putString(KEY_EMAIL, email);
        //editor.putString(KEY_NAME, name);
        editor.commit();
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent intent = new Intent(_context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(intent);
            ((Activity)_context).finish();
        }
    }

    /*
        Get User Details from Session
     */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_USERNAME, sharedPreferences.getString(KEY_USERNAME, null));
        //user.put(KEY_EMAIL, sharedPreferences.getString(KEY_EMAIL, null));
        //user.put(KEY_NAME, sharedPreferences.getString(KEY_NAME, null));

        return user;
    }

    public Boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void logoutSession(){
        editor.clear();
        editor.commit();

        Intent intent = new Intent(_context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(intent);
    }
}
