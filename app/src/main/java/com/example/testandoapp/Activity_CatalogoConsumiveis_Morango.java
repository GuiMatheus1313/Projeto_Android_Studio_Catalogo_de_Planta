package com.example.testandoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_CatalogoConsumiveis_Morango extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_consumiveis_morango);
        //Criação dos métodos para os Buttons
        Button retornar = (Button) findViewById(R.id.button_RetornaCatalogo3);

        Button extra = (Button) findViewById((R.id.button_ExtraCatalogoMorango));
        //Retornando à Activity Catálogo Consumíveis, sendo uma intent explicita
        retornar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent telaConsumiveis = new Intent(Activity_CatalogoConsumiveis_Morango.this, Activity_Catalogo_Consumiveis.class);
                finish();
                startActivity(telaConsumiveis);
            }
        });
        //Leva o usuário ao site com conteúdo extra, sendo uma intent implícita
        extra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ruralidades.pt/index.php/fruteiras/aprender-de-a-z/aprender-mn/morango-ficha-tecnica")));
            }
        });
    }
}