package com.example.testandoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * Teste para troca de fonte
         *
        TextView txtv = (TextView) findViewById(R.id.TextView_tituloCarrosel);
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Lobster.ttf");
        txtv.setTypeface(fonte);
         */

      Button button_perfilmenu2 = findViewById(R.id.button_perfilmenu);

      ImageButton button_catalogoConsumiveis = findViewById(R.id.PrimeiroBotaoCatalogo);

        button_perfilmenu2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent telacadastro = new Intent(MainActivity.this, Tela_cadastro.class);
                finish();
                startActivity(telacadastro);
            }
        });
        button_catalogoConsumiveis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaconsumiveis = new Intent(MainActivity.this, Activity_Catalogo_Consumiveis.class);
                finish();
                startActivity(telaconsumiveis);
            }
        });

    }
}