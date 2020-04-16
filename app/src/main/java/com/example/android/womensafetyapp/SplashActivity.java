package com.example.android.womensafetyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );
        setupSharedPreferences();
        checkLoginStatus();
    }

    private void checkLoginStatus() {
        boolean isLoggedIn = sharedPreferences.getBoolean( PrefConstant.IS_LOGGED_IN, false);

        if(isLoggedIn){
            Intent intent = new Intent( SplashActivity.this,MainActivity.class );
            startActivity(intent);
        }else {
            Intent intent = new Intent( SplashActivity.this,LoginActivity.class );
            startActivity(intent);
        }
    }

    private void setupSharedPreferences() {
        sharedPreferences = getSharedPreferences( PrefConstant.SHARED_PREFERENCE_NAME, MODE_PRIVATE );
    }
}
