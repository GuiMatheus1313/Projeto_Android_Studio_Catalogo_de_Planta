package com.example.testandoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tela_cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        Button button_perfilmenu1 = findViewById(R.id.buttonCadastroVoltar);

        button_perfilmenu1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent telamenu = new Intent(Tela_cadastro.this, MainActivity.class);
                finish();
                startActivity(telamenu);
            }
        });

    }
}

