package com.example.android.womensafetyapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;

public class MainActivity extends AppCompatActivity implements LocationListener {


    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    Double lat, lon;
    String provider;
    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;
    private static final int PERMISSION_REQUEST_CODE = 1;
    public static final int REQUEST_LOCATION_CODE = 99;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE );
        // ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            checkLocationPermission();
//        }
        locationManager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if (checkSelfPermission( Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && checkSelfPermission( Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, this );



    }

    private boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);

            }
            return false;
        } else
            return true;
    }


    public void emergency(View view) {

//        SQLiteDatabase db;
//        db = openOrCreateDatabase( "User Details", Context.MODE_PRIVATE,null );
//
//        Cursor c1 = db.rawQuery( "SELECT contact1 FROM details", null );
//        String num1 = c1.getString( 0 );

        Toast.makeText( this, "Latitude:" + lat + ", Longitude:" + lon, Toast.LENGTH_LONG ).show();
        String msg = "Emergency AT.." + lat + " " + lon;
        String num1 = "9630064263";
        if (checkPermission()) {


            SmsManager smsManager = SmsManager.getDefault();


            smsManager.sendTextMessage( num1, null, msg, null, null );
        } else {
            Toast.makeText( this, "Permission denied", Toast.LENGTH_SHORT ).show();

        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lon = location.getLongitude();
       // Toast.makeText(this, "Latitude:" + lat + ", Longitude:" + lon,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

        Log.d("Latitude","status");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");

    }

    public void instruction(View view) {
        Intent i_instruction = new Intent( this, InstructionsActivity.class );
        startActivity(i_instruction);
    }

}
