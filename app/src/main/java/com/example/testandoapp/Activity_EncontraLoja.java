package com.example.testandoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.Manifest;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import java.util.List;

public class Activity_EncontraLoja extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnencontraLoja_voltar = findViewById(R.id.btnEncontraLoja_Voltar);

        btnencontraLoja_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaencontralojavoltar = new Intent (Activity_EncontraLoja.this, Activity_CalcularUmidade.class);
                finish();
                startActivity(telaencontralojavoltar);
            }
        });

    }
    public void buscainfoGPS(View v){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity_EncontraLoja.this), new String[]{ Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(Activity_EncontraLoja.this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            ActivityCompat.requestPermissions(Activity_EncontraLoja.this, new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, 1);
            return;
        }

        LocationManager localizamanager = (LocationManager) getSystemService(Activity_EncontraLoja.this.LOCATION_SERVICE);
        LocationListener localizalistener = new Alocalizacaolistener();

        localizamanager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, localizalistener);

        if(localizamanager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            String texto = "Latitude: " + Alocalizacaolistener.latitude + "\n" +
                    "Longitude: " + Alocalizacaolistener.longitude + "\n";
        }
        else{
            Toast.makeText(Activity_EncontraLoja.this, "GPS está desabilitado neste dispositivo", Toast.LENGTH_LONG).show();
        }
        this.mostrarGoogleMaps(Alocalizacaolistener.latitude, Alocalizacaolistener.longitude);


    }

    public void mostrarGoogleMaps(double latitude, double longitude){
        WebView wv = findViewById(R.id.WebView_GPS);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude);
    }
}




























