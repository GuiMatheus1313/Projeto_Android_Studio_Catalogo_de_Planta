package com.example.testandoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_CatalogoConsumiveis_limao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_consumiveis_limao);
        //Criação de métodos para os Buttons
        Button retornar = findViewById(R.id.button_RetornaCatalogo2);

        Button site = findViewById(R.id.button_ExtraCatalogoLimao);
        //Retornando à Activity Catálogo Consumíveis, sendo uma intent explicita
        retornar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent telaConsumiveis = new Intent(Activity_CatalogoConsumiveis_limao.this, Activity_Catalogo_Consumiveis.class);
                finish();
                startActivity(telaConsumiveis);
            }
        });
        //Leva o usuário ao site com conteúdo extra, sendo uma intent implícita
        site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ruralidades.pt/index.php/fruteiras/aprender-de-a-z/aprender-kl/limoeiro-ficha-tecnica")));
            }
        });
    }
}