package com.example.testandoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      Button button_perfilmenu2 = findViewById(R.id.button_perfilmenu);

        button_perfilmenu2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent telacadastro = new Intent(MainActivity.this, Tela_cadastro.class);
                finish();
                startActivity(telacadastro);
            }
        });

    }
}