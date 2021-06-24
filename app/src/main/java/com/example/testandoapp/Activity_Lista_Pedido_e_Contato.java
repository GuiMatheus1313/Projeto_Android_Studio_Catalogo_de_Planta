package com.example.testandoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Activity_Lista_Pedido_e_Contato extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedido_econtato);


        //Criação para métodos de cada ImageButton e Button
        Button retorno = findViewById(R.id.button_RetornarPedido);

        Button feedback = findViewById(R.id.button_EnviaPedido);

        ImageButton telefonar = findViewById(R.id.imageButton_Telefone);

        ImageButton email = findViewById(R.id.imageButton_Email);
        //Mostrando caixa de informação ao usuário
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context pegarcontexto = getApplicationContext();
                String aviso = "Feedback enviado, agradecemos pelo feedback!";
                int tempo = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(pegarcontexto, aviso, tempo);
                toast.show();
            }
        });

        //Retorno para Activity Main, sendo uma intent explicita
        retorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telamenu = new Intent(Activity_Lista_Pedido_e_Contato.this, MainActivity.class);
                finish();
                startActivity(telamenu);
            }
        });
        //Fazer ligação, sendo uma intent implícita
        telefonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:" + "123456789");

                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                if (ActivityCompat.checkSelfPermission(Activity_Lista_Pedido_e_Contato.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Activity_Lista_Pedido_e_Contato.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
                startActivity(intent);
            }
        });
        //Fazer um email, sendo uma intent implícita
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Intent.ACTION_VIEW);
                intent2.setData(Uri.parse("mailto:" + "local@gmail.com"));
                startActivity(intent2);

            }
        });




    }
}