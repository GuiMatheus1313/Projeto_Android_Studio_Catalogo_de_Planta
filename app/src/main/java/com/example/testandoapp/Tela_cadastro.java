package com.example.testandoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;

public class Tela_cadastro extends AppCompatActivity {
    EditText edittext_email;
    EditText edittext_senha;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        //criando os métodos para cada Button e EditText nesta Activity
        Button button_perfilmenu1 = findViewById(R.id.buttonCadastroVoltar);
        edittext_email = findViewById(R.id.editTextEmailCadastro);
        edittext_senha = findViewById(R.id.editTextSenhaCadastro);
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
                /*
                Context pegarcontexto = getApplicationContext();
                String aviso = "Você já está cadastrado";
                int tempo = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(pegarcontexto, aviso, tempo);
                toast.show();
                */
                try{

                        GravarInterno();

                }catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext() , e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    public void GravarInterno() throws IOException {
        boolean ArmazenamentoExternoLiberado = false;
        boolean ArmazenamentoExternoWriteable = false;

        String estado = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(estado)){
            ArmazenamentoExternoLiberado = ArmazenamentoExternoWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(estado)){
            ArmazenamentoExternoLiberado = true;
            ArmazenamentoExternoWriteable = false;
        } else {
            ArmazenamentoExternoLiberado = ArmazenamentoExternoWriteable = false;
        }

        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);

        File arquivo = new File(dir, edittext_email.getText().toString());

        FileOutputStream fos = new FileOutputStream(arquivo);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(arquivo);
        oos.close();
        fos.close();



    };
    /*
    private boolean isExternalStorageWritable(){
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            Log.i("Estado", "Sim, é possível ser escrito!");
            return true;
        } else{
            return false;
        }

    }
    */

    /*
    public void GravarExterno(){
        if(isExternalStorageWritable() && checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            File arquivoemailsenha = new File(Environment.getExternalStorageDirectory(), edittext_email.getText().toString());
            try {
                FileOutputStream fos  = new FileOutputStream(arquivoemailsenha);
                fos.write(edittext_senha.getText().toString().getBytes());
                fos.close();

                Toast.makeText(this, "Arquivo salvo com sucesso no armazenamento externo", Toast.LENGTH_SHORT).show();
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Falha de salvar no armazenamento externo", Toast.LENGTH_SHORT).show();

        }
    }
    */



}


