package com.example.sagar.sicklab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Sagar on 11/12/2017.
 */

public class LoginChecker extends AppCompatActivity {
    private static final String PREF_NAME = "LOGIN_PREF";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = null;
        SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
//Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
        boolean isfirst = settings.getBoolean("FirstLogin", true);
        if (isfirst) {

            //changing the preference in first login then starting the activity
            SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("FirstLogin", false);
            editor.apply();


            intent = new Intent(this, IntroActivity.class);
            startActivity(intent);
        } else {


            intent = new Intent(this, LoginPage.class);

            startActivity(intent);
        }


    }
}
