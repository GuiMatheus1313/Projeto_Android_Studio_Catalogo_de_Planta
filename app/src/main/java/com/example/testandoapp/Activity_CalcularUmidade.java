package com.example.testandoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import java.util.List;

public class Activity_CalcularUmidade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_umidade);

        Button btn_umidade = findViewById(R.id.button_CalcularUmidade);

        btn_umidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaumidade = new Intent(Activity_CalcularUmidade.this, Activity_CalcUmidade.class);
                finish();
                startActivity(telaumidade);

            }
        });


    }


}