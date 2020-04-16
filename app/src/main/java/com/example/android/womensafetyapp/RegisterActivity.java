package com.example.android.womensafetyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText nameEdt, emailEdt, passwordEdt, rePasswordEdt, mobileUserEdt, person1NameEdt, contact1Edt, person2NameEdt, contact2Edt, person3NameEdt, contact3Edt, person4NameEdt, contact4Edt, person5NameEdt, contact5Edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );
    }

    public void storeInDB(View view) {

        String name, email, password, mobile, person1Name, contact1, person2Name, contact2, person3Name, contact3, person4Name, contact4, person5Name, contact5;
        SQLiteDatabase db;

        nameEdt = findViewById( R.id.nameEdt );
        emailEdt = findViewById( R.id.emailEdt );
        passwordEdt = findViewById( R.id.passwordEdt );
        mobileUserEdt = findViewById( R.id.mobileUserEdt );
        person1NameEdt = findViewById( R.id.person1NameEdt );
        person2NameEdt = findViewById( R.id.person2NameEdt );
        person3NameEdt = findViewById( R.id.person3NameEdt );
        person4NameEdt = findViewById( R.id.person4NameEdt );
        person5NameEdt = findViewById( R.id.person5NameEdt );
        contact1Edt = findViewById( R.id.contact1Edt );
        contact2Edt = findViewById( R.id.contact2Edt );
        contact3Edt = findViewById( R.id.contact3Edt );
        contact4Edt = findViewById( R.id.contact4Edt );
        contact5Edt = findViewById( R.id.contact5Edt );

        name = nameEdt.getText().toString();
        email = emailEdt.getText().toString();
        password = passwordEdt.getText().toString();
        mobile = mobileUserEdt.getText().toString();
        person1Name = person1NameEdt.getText().toString();
        person2Name = person2NameEdt.getText().toString();
        person3Name = person3NameEdt.getText().toString();
        person4Name = person4NameEdt.getText().toString();
        person5Name = person5NameEdt.getText().toString();
        contact1 = contact1Edt.getText().toString();
        contact2 = contact2Edt.getText().toString();
        contact3 = contact3Edt.getText().toString();
        contact4 = contact4Edt.getText().toString();
        contact5 = contact5Edt.getText().toString();

        db = openOrCreateDatabase( "User Details", Context.MODE_PRIVATE,null );
        db.execSQL( "CREATE TABLE IF NOT EXISTS details(name VARCHR, email VARCHAR, password VARCHAR, mobile VARCHAR, person1Name VARCHAR, contact1 VARCHAR, person2Name VARCHAR, contact2 VARCHAR, person3Name VARCHAR, contact3 VARCHAR, person4Name VARCHAR, contact4 VARCHAR, person5Name VARCHAR, contact5 VARCHAR);" );

        Cursor cursor = db.rawQuery( "SELECT * FROM details", null );
        db.execSQL( "INSERT INTO details VALUES ('"+name+"','"+email+"','"+password+"','"+mobile+"','"+person1Name+"','"+contact1+"','"+person2Name+"','"+contact2+"','"+person3Name+"','"+contact3+"','"+person4Name+"','"+contact4+"','"+person5Name+"','"+contact5+"');" );
        Toast.makeText(getApplicationContext(), "Successfully Saved", Toast.LENGTH_SHORT).show();
        db.close();

        Intent i_login = new Intent( this, LoginActivity.class );
        startActivity( i_login );
    }
//
//    public void storeInDB(View view) {
//        String str_name, str_number;
//        SQLiteDatabase db;
//
//        name = findViewById( R.id.personEdt );
//        number = findViewById( R.id.mobileEdt );
//        str_name = name.getText().toString();
//        str_number = number.getText().toString();
//        db = openOrCreateDatabase( "NumDB", Context.MODE_PRIVATE, null );
//        db.execSQL( "CREATE TABLE IF NOT EXISTS details(name VARCHAR, number VARCHAR);" );
//
//        Cursor c = db.rawQuery( "SELECT * FROM details",null );
//        if(c.getCount()<2){
//            db.execSQL( "INSERT INTO details VALUES('"+str_name+"','"+str_number+"');" );
//            Toast.makeText(getApplicationContext(), "Successfully Saved", Toast.LENGTH_SHORT).show();
//        }else {
//            db.execSQL("INSERT INTO details VALUES('"+str_name+"','"+str_number+"');");
//            Toast.makeText(getApplicationContext(), "Maximun Numbers limited reached. Previous numbers are replaced.",Toast.LENGTH_SHORT).show();
//        }
//        db.close();
//    }

}
