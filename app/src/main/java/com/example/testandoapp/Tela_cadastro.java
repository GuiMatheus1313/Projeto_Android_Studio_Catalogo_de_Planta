package com.example.testandoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Tela_cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        //criando os métodos para cada Button nesta Activity
        Button button_perfilmenu1 = findViewById(R.id.buttonCadastroVoltar);

        Button button_cadastro = findViewById(R.id.buttonCadastroEnviar);
        //Retornando à Main Activity, sendo uma intent explicita
        button_perfilmenu1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent telamenu = new Intent(Tela_cadastro.this, MainActivity.class);
                finish();
                startActivity(telamenu);
            }


        });
        //Mostra o usuário uma caixa de informação na tela
        button_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     Context pegarcontexto = getApplicationContext();
                     String aviso = "Você já está cadastrado";
                     int tempo = Toast.LENGTH_SHORT;

                     Toast toast = Toast.makeText(pegarcontexto, aviso, tempo);
                     toast.show();

            }
        });

    }
}

