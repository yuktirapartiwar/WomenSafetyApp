package com.example.android.womensafetyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText emailEdt, passwordEdt;
    Button loginBtn, signUpBtn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        emailEdt = findViewById( R.id.emailEdt );
        passwordEdt = findViewById( R.id.passwordEdt );
        loginBtn = findViewById( R.id.loginBtn );
        signUpBtn = findViewById( R.id.signUpBtn );

        setupSharedPreferences();

        loginBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEdt.getText().toString();
                String password = passwordEdt.getText().toString();

                if (!TextUtils.isEmpty( email ) && !TextUtils.isEmpty( password )) {
                    Intent intent = new Intent( LoginActivity.this, MainActivity.class );
                    startActivity( intent );
                    saveLoginStatus();
                }else {
                    Toast.makeText( LoginActivity.this,"Fields can't be empty", Toast.LENGTH_SHORT ).show();
                }

            }

            private void saveLoginStatus() {
                editor = sharedPreferences.edit();
                editor.putBoolean( PrefConstant.IS_LOGGED_IN,true );
                editor.apply();
            }
        } );
        
    }

    private void setupSharedPreferences() {
        sharedPreferences = getSharedPreferences( PrefConstant.SHARED_PREFERENCE_NAME,MODE_PRIVATE );
    }

    public void signUp(View view) {

        Intent i_signUp = new Intent( this, RegisterActivity.class );
        startActivity(i_signUp);
    }
}
