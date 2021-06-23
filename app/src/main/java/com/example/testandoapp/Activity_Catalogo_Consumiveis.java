package com.example.testandoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Activity_Catalogo_Consumiveis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_consumiveis);

        ImageButton morango = (ImageButton) findViewById(R.id.imageButton_Catalogo_Morango);
        ImageButton limao = (ImageButton) findViewById(R.id.imageButton_Catalogo_Limao);
        Button retorno = (Button) findViewById(R.id.button_RetornaCatalogo);
        retorno.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v){
               Intent telamenu = new Intent(Activity_Catalogo_Consumiveis.this, MainActivity.class);
               finish();
               startActivity(telamenu);
           }
        });

        morango.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaMorango = new Intent(Activity_Catalogo_Consumiveis.this, Activity_CatalogoConsumiveis_Morango.class);
                finish();
                startActivity(telaMorango);
            }
        });

        limao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaLimao = new Intent(Activity_Catalogo_Consumiveis.this, Activity_CatalogoConsumiveis_limao.class);
                finish();
                startActivity(telaLimao);
            }
        });
    }
}