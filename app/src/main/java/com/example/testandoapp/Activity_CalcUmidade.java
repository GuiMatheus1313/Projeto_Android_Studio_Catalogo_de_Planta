package com.example.testandoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_CalcUmidade extends AppCompatActivity implements SensorEventListener {

    //Criação de variáveis para o funcionamento do sensor de umidade
    private SensorManager srmgr;
    private Sensor Sensor_Umidade;
    private Sensor Sensor_Temperatura;
    private boolean presenca_do_SensorUmidade;
    private boolean presenca_do_SensorTemp;
    private TextView Valor_da_UmidadeRelativa;
    private TextView Valor_da_UmidadeAbsoluta;
    private TextView Valor_de_umedecer;
    private float UltimoPonto_UmidadeRelativa = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_umidade);

        //Adquirindo os valores de ID de cada TextView, além do SensorManager
        Valor_da_UmidadeRelativa = (TextView) findViewById(R.id.textView_Umidade1);
        Valor_da_UmidadeAbsoluta = (TextView) findViewById(R.id.textView_Umidade2);
        Valor_de_umedecer = (TextView) findViewById(R.id.textView_Umidade3);
        srmgr = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);

        //Condição para verificar se o sensor de umidade relativa está ativo ou não, sendo reproduzido uma mensagem de aviso em um caso negativo
        if(srmgr.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null){
            Sensor_Umidade = srmgr.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
            presenca_do_SensorUmidade = true;
        } else{
            Valor_da_UmidadeRelativa.setText("O SENSOR DE UMIDADE NÃO ESTÁ DISPONÍVEL");
            Valor_da_UmidadeAbsoluta.setText("O Sensor de umidade absoluta está comprometido pela falta do sensor de umidade");
            Valor_de_umedecer.setText("O sensor de umedecer está comprometido pela falta do sensor de umidade");
            presenca_do_SensorUmidade=false;
        }

        //Condição para verificar se o sensor de temperatura está ativo ou não, sendo reproduzido uma mensagem de aviso em um caso negativo
        if(srmgr.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null){
            Sensor_Temperatura = srmgr.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            presenca_do_SensorTemp = true;
        } else {
            Valor_da_UmidadeAbsoluta.setText("O sensor de umidade absoluta está comprometido pela falta do sensor de temperatura");
            Valor_de_umedecer.setText("O sensor de umedecer está comprometido pela falta do sensor de temperatura");
            presenca_do_SensorTemp = false;
        }

        //Criação de variável e método para voltar a Activity anterior

        Button btncalcUmi_Voltar = findViewById(R.id.btnCalcUmi_Voltar);

        btncalcUmi_Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaextrasvoltar = new Intent(Activity_CalcUmidade.this, Activity_CalcularUmidade.class);
                finish();
                startActivity(telaextrasvoltar);
            }
        });
    }


    //Todo o método é utilizado para indicar alteração dos valores enquanto há o uso dos sensores
    @Override
    public void onSensorChanged(SensorEvent evento){
        if (evento.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
            Valor_da_UmidadeRelativa.setText("A umidade (em %) é de: " + evento.values[0]);
            UltimoPonto_UmidadeRelativa = evento.values[0];
        } else if(evento.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            if (UltimoPonto_UmidadeRelativa != 0){
                float temperatura = evento.values[0];
                float UmidadeAbsoluta = calcularUmidadeAbsoluta(temperatura, UltimoPonto_UmidadeRelativa);
                Valor_da_UmidadeAbsoluta.setText("A umidade absoluta com a temperatura de " + temperatura + " é: " + UmidadeAbsoluta);
                float Umedecer = calcularUmedecer(temperatura, UltimoPonto_UmidadeRelativa);
                Valor_de_umedecer.setText("O ponto de umedecer na temperatura de "+temperatura+" é: "+ Umedecer );
            }
        }
    }


    //Método que retorna o valor de um calculo de umidade absoluta
    public float calcularUmidadeAbsoluta(float temperatura, float UmidadeRelativa){
        float Dv = 0;
        float m = 17.62f;
        float Tn = 243.12f;
        float Ta = 216.7f;
        float Rh = UmidadeRelativa;
        float Tc = temperatura;
        float A = 6.112f;
        float K = 273.15f;

        Dv =   (float) (Ta * (Rh/100) * A * Math.exp(m*Tc/(Tn+Tc)) / (K + Tc));

        return Dv;
    }

    //Método que retorna o valor de um calculo de umedecer
    public float calcularUmedecer(float temperatura, float Umidaderelativa)
    {
        float Td = 0;
        float m = 17.62f;
        float Tn = 243.12f;
        float Rh = Umidaderelativa;
        float Tc = temperatura;

        Td = (float) (Tn * ((Math.log(Rh/100) + m*Tc/(Tn+Tc))/(m - (Math.log(Rh/100) + m*Tc/(Tn+Tc)))));

        return Td;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    //Método para anular todas as ações dos sensores ao sair desta Activity
    @Override
    protected void onDestroy(){
        super.onDestroy();
        srmgr = null;
        Sensor_Umidade = null;
        Sensor_Temperatura = null;
    }



}