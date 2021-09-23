package com.example.testandoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;


import java.util.List;

public class Activity_CalcularUmidade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_umidade);

        Button btn_umidade = findViewById(R.id.button_CalcularUmidade);
        Button btn_voltar = findViewById(R.id.buttonExtras_Voltar);
        Button btn_encontraloja = findViewById(R.id.button_EncontrarLoja);

        btn_umidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaumidade = new Intent(Activity_CalcularUmidade.this, Activity_CalcUmidade.class);
                finish();
                startActivity(telaumidade);

            }
        });

        btn_encontraloja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaencontraloja = new Intent(Activity_CalcularUmidade.this, Activity_EncontraLoja.class);
                finish();
                startActivity(telaencontraloja);
            }
        });

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telamenu = new Intent (Activity_CalcularUmidade.this, MainActivity.class);
                finish();
                startActivity(telamenu);
            }
        });


    }




}