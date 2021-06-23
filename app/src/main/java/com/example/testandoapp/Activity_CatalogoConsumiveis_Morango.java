package com.example.testandoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_CatalogoConsumiveis_Morango extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_consumiveis_morango);

        Button retornar = (Button) findViewById(R.id.button_RetornaCatalogo3);

        retornar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent telaConsumiveis = new Intent(Activity_CatalogoConsumiveis_Morango.this, Activity_Catalogo_Consumiveis.class);
                finish();
                startActivity(telaConsumiveis);
            }
        });
    }
}