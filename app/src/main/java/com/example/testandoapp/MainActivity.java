package com.example.testandoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class MainActivity extends AppCompatActivity {
private int[] imgs = new int[] {
  R.drawable.loja1, R.drawable.loja1
};

private String[] imgstitle = new String[] {
        "Loja1", "Loja2"
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CarouselView carouselView = findViewById(R.id.Caroussel1Main);
        carouselView.setPageCount(imgs.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(imgs[position]);
            }
        });
        /**
         * Teste para troca de fonte
         *
        TextView txtv = (TextView) findViewById(R.id.TextView_tituloCarrosel);
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Lobster.ttf");
        txtv.setTypeface(fonte);
         */

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