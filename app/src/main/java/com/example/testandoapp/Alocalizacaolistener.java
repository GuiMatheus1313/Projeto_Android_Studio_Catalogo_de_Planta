package com.example.testandoapp;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Alocalizacaolistener extends AsyncTask<Location, Void, String[]> {

    private Context contexto;
    private OnTaskCompleted ouvinte;



    Alocalizacaolistener(Context contextoapp, OnTaskCompleted ouvinte2){
        contexto = contextoapp;
        ouvinte = ouvinte2;
    }

    private final String TAG = Alocalizacaolistener.class.getSimpleName();

    @Override
    protected String[] doInBackground(Location... params) {
        //Inicialização do geocoder

        Geocoder geocoder = new Geocoder(contexto, Locale.getDefault());

        //Preparando as variáveis para os sensores
        Location localizar = params[0];
        List<Address> endereco = null;
        String[] mensagem_resultado = new String[3];
        mensagem_resultado[0] = "";
        mensagem_resultado[1] = "";
        mensagem_resultado[2] = "";

        try{
            endereco = geocoder.getFromLocation(localizar.getLatitude(), localizar.getLongitude(), 1);
            mensagem_resultado[1] = String.valueOf(localizar.getLatitude());
            mensagem_resultado[2] = String.valueOf(localizar.getLongitude());
        } catch (IOException ioException){
            //Irá informar uma mensagem de erro caso ocorra um problema no processo
            mensagem_resultado[0] = contexto.getString(R.string.serviço_interrompido);
            Log.e(TAG, mensagem_resultado[0], ioException);
        } catch (IllegalArgumentException illegalArgumentException) {
            mensagem_resultado[0] = contexto.getString(R.string.erro_coordenadas);
            Log.e(TAG, mensagem_resultado[0] + ". " + "Latitude = " + localizar.getLatitude() + ", Longitude = " + localizar.getLongitude(), illegalArgumentException);
        }

        if(endereco == null || endereco.size() == 0) {
            if (mensagem_resultado[0].isEmpty()) {
                mensagem_resultado[0] = contexto.getString(R.string.endereco_indisponivel);
                Log.e(TAG, mensagem_resultado[0]);
            } else {
                Address enderecos = endereco.get(0);
                ArrayList<String> partes_endereco = new ArrayList<>();

                for (int i = 0; i <= enderecos.getMaxAddressLineIndex(); i++){
                    partes_endereco.add(enderecos.getAddressLine(i));
                }

                mensagem_resultado[0] = TextUtils.join("\n", partes_endereco);
            }
        }
        return mensagem_resultado;


    }

    @Override
    protected void onPostExecute(String[] endereco){
        ouvinte.onTaskCompleted(endereco);
        super.onPostExecute(endereco);
    }


    interface OnTaskCompleted {
        void onTaskCompleted(String[] result);
    }
}
