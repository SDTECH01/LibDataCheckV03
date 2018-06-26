package com.example.cka.smartckeckdata;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.Request;

//public class EnvoiOk  implements  Callback{
public class EnvoiOk{

//////c'est ici nous allons tout faire////////////////
    public static void EnvoiO(){
        OkHttpClient client = new OkHttpClient();
        //HttpUrl.Builder urlBuilder = HttpUrl.parse("http://smart-data-tech.com/dev/API/v1/saveuser/").newBuilder();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://smart-data-tech.com/dev/fr/crud.php").newBuilder();
        //HttpUrl.Builder urlBuilder = HttpUrl.parse("http://smart-data-tech.com/dev/model/usercka.php").newBuilder();
        String url = urlBuilder.build().toString();
        urlBuilder.addQueryParameter("nom", "nom");
        urlBuilder.addQueryParameter("numero", "021548");
        /*urlBuilder.addQueryParameter("tel3", "tel3");
        urlBuilder.addQueryParameter("tel4", "tel4");
        urlBuilder.addQueryParameter("imei", "imei");
        urlBuilder.addQueryParameter("version", "version");
        urlBuilder.addQueryParameter("model", "model");
        urlBuilder.addQueryParameter("duree_activite", "duree_activite");
        urlBuilder.addQueryParameter("gmail", "gmail");
        urlBuilder.addQueryParameter("twitter", "twitter");
        urlBuilder.addQueryParameter("fb", "fb");
        urlBuilder.addQueryParameter("dat_ins", "dat_ins");
        urlBuilder.addQueryParameter("etat", "etat");
        urlBuilder.addQueryParameter("statut", "statut");*/

////////cest ici////////
        /*Request request = new Request.Builder()
                .url(url)
                .build();*/


        final Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.w("l'erreur est ici", e);
                System.out.println("votre erreur"+e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            Log.i("la requete est ", String.valueOf(response+" et "+ request));
            }
        });
    }

    /*@Override
    public void onFailure(Call call, IOException e) {*/
        /*void runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //resultsTextView.setText("Erreur");
                snackbar.dismiss();
            }
        });*/

    /*}

    @Override
    public  void onResponse(Call call, Response response) throws IOException {
        if (!response.isSuccessful()) {
            throw new IOException(response.toString());
        }

        final String body = response.body().string();*/

       /* void runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //resultsTextView.setText(body);
                snackbar.dismiss();
                //Log.i(body,"votre message");
            }
        });*/
   // }

}
