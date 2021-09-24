package com.example.testandoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class Activity_EncontraLoja extends AppCompatActivity implements Alocalizacaolistener.OnTaskCompleted {
    //Definição de variáveis para o uso de sensores
    private static final String PREFERENCIAS_nome = "com.example.android.localizacao";
    private static final String TRACKING_LOCATION_KEY = "tracking_location";
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final String LATITUDE_KEY = "latitude";
    private static final String LONGITUDE_KEY = "longitude";
    private static final String LASTDATE_KEY = "data";

    private Button botaolocaliza;
    private TextView textlocaliza;
    private static final String LASTADRESS_KEY = "endereco";
    private FusedLocationProviderClient localizacliente;
    private LocationCallback chamadadelocalizacao;
    private boolean tracarlocalizacao;

    private SharedPreferences preferencias;
    private String ultimalatitude = "";
    private String ultimalongitude = "";
    private String ultimoendereco = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_encontra_loja);

        botaolocaliza = findViewById(R.id.btnEncontraLoja_INICIAR);
        textlocaliza = findViewById(R.id.textView_location);
        Button btnVoltar = findViewById(R.id.buttonGPS_Voltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaExtras = new Intent(Activity_EncontraLoja.this, Activity_CalcularUmidade.class);
                finish();
                startActivity(telaExtras);
            }
        });

        localizacliente = LocationServices.getFusedLocationProviderClient(this);
        if (savedInstanceState != null) {
            tracarlocalizacao = savedInstanceState.getBoolean(TRACKING_LOCATION_KEY);
        }

        //Click do botão utilizado para iniciar os métodos de localização
        botaolocaliza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tracarlocalizacao){
                    startTrackingLocation();
                } else {
                    stopTrackingLocation();
                }
            }
        });

        chamadadelocalizacao = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult resultadolocalizacao) {
                if (tracarlocalizacao) {
                    new Alocalizacaolistener(Activity_EncontraLoja.this, Activity_EncontraLoja.this).execute(resultadolocalizacao.getLastLocation());
                }
            }
        };

        preferencias = getSharedPreferences(PREFERENCIAS_nome, MODE_PRIVATE);
        recuperar();

    }
    //Inicia o método para uso dos sensores GPS
    private void startTrackingLocation() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        } else {
            tracarlocalizacao = true;
            localizacliente.requestLocationUpdates(getLocationRequest(), chamadadelocalizacao, null);

            textlocaliza.setText(getString(R.string.endereco,
            getString(R.string.Carregamento), null, null, System.currentTimeMillis()));

            botaolocaliza.setText(R.string.Parar_Busca);


        }
    }
    //Método que determina as configurações de tempo e precisão do GPS
     private LocationRequest getLocationRequest(){
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
     }

     //Método que faz parar o funcionamento do GPS
     private void stopTrackingLocation() {
        if (tracarlocalizacao) {
            tracarlocalizacao = false;
            botaolocaliza.setText(R.string.Inicia_Busca);
            textlocaliza.setText(R.string.hint);
        }
     }

     @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putBoolean(TRACKING_LOCATION_KEY, tracarlocalizacao);
        super.onSaveInstanceState(outState);
     }
     //Faz o APP perguntar para o usuário sobre o acesso do GPS
     @Override
     public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_LOCATION_PERMISSION:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startTrackingLocation();
                } else {
                    Toast.makeText(this, "Permissão negada, autorize a aplicação", Toast.LENGTH_SHORT).show();
                }
                break;
        }
     }

     @Override
    public void onTaskCompleted(String[] result){
        if (tracarlocalizacao) {
            ultimalatitude = result[1];
            ultimalongitude = result[2];
            ultimoendereco = result[0];
            textlocaliza.setText(getString(R.string.endereco, ultimoendereco, ultimalatitude, ultimalongitude, System.currentTimeMillis()));
        }
     }
    //Método que realiza uma pausa no GPS quando o usuário coloca em segundo plano o APP
     @Override
    protected void onPause(){
        super.onPause();
        if (tracarlocalizacao) {
            stopTrackingLocation();
            tracarlocalizacao = true;
            armazenar(ultimalatitude, ultimalongitude, ultimoendereco);
        }
     }

     //Método que faz resumir a função do GPS antes parado
     @Override
    protected void onResume(){
        if (tracarlocalizacao) {
            startTrackingLocation();
        }
        recuperar();
        super.onResume();
     }
    //Faz armazenar os conteúdos de latitude, longitude e data
     private void armazenar(String latitude, String longitude, String lastAdress) {
        SharedPreferences.Editor preferencesEditor = preferencias.edit();
        preferencesEditor.putString(LATITUDE_KEY, latitude);
        preferencesEditor.putString(LONGITUDE_KEY, longitude);
        preferencesEditor.putLong(LASTDATE_KEY, System.currentTimeMillis());
        preferencesEditor.putString(LASTADRESS_KEY, lastAdress);
        preferencesEditor.apply();
     }

    //Método utilizado para recuperar os últimos dados da latitude, longitude e tempo
     private void recuperar() {
        ultimalatitude = preferencias.getString(LATITUDE_KEY, "");
        ultimalongitude = preferencias.getString(LONGITUDE_KEY, "");
        long time = preferencias.getLong(LASTDATE_KEY,0);
        ultimoendereco = preferencias.getString(LASTADRESS_KEY, "");

        Toast.makeText(this,getString(R.string.endereco, ultimoendereco, ultimalatitude ,ultimalongitude, time),Toast.LENGTH_SHORT).show();

     }




}




























