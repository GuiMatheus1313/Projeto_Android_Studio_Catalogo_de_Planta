package com.example.testandoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Criação de métodos para ImageButtons e Buttons

      Button button_perfilmenu2 = findViewById(R.id.button_perfilmenu);

      ImageButton button_catalogoConsumiveis = findViewById(R.id.PrimeiroBotaoCatalogo);

      ImageButton imagebutton_promo1 = findViewById(R.id.imageButton_Promo1);

      Button button_pedidoecontato = findViewById(R.id.button_ListaDesejo);
        //Leva o usuário à Activity Tela Cadastro, sendo uma intent explicita
        button_perfilmenu2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent telacadastro = new Intent(MainActivity.this, Tela_cadastro.class);
                finish();
                startActivity(telacadastro);
            }
        });
        //Leva o usuário à Activity Catálogo Consumíveis, sendo uma intent explicita
        button_catalogoConsumiveis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaconsumiveis = new Intent(MainActivity.this, Activity_Catalogo_Consumiveis.class);
                finish();
                startActivity(telaconsumiveis);
            }
        });
        //Leva o usuário para um site externo, sendo uma intent implícita
        imagebutton_promo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.plantei.com.br/loja/busca.php?categoria=10&variacao=ideal-para-frutiferas")));
            }
        });
        //Leva o usuário para à Activity Lista Pedido e Contato, sendo uma intent explicita
        button_pedidoecontato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telapedido = new Intent(MainActivity.this, Activity_Lista_Pedido_e_Contato.class);
                finish();
                startActivity(telapedido);
            }
        });


    }
}