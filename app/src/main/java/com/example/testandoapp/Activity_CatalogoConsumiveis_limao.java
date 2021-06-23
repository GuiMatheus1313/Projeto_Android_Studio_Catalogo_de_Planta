package com.example.testandoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_CatalogoConsumiveis_limao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_consumiveis_limao);

        Button retornar = (Button) findViewById(R.id.button_RetornaCatalogo2);

        retornar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent telaConsumiveis = new Intent(Activity_CatalogoConsumiveis_limao.this, Activity_Catalogo_Consumiveis.class);
                finish();
                startActivity(telaConsumiveis);
            }
        });
    }
}