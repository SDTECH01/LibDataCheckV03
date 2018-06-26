package com.example.cka.smartckeckdata;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Request;

//public class EnvoiOk  implements  Callback{
public class EnvoiOk{
    public static final MediaType MEDIA_TYPE =
            MediaType.parse("application/json");
//////c'est ici nous allons tout faire////////////////
    public static void EnvoiO(){

        OkHttpClient client = new OkHttpClient();
        JSONObject postdata = new JSONObject();
        try {
            postdata.put("tel1", "747965842");
            postdata.put("tel2", "49599779");
            postdata.put("tel3", "49599778");
            postdata.put("tel4", "42157862");
            postdata.put("imei", "251k");
            postdata.put("version", "android 5");
            postdata.put("model", "A5");
            postdata.put("duree_activite", "20");
            postdata.put("gmail", "mail.com");
            postdata.put("twitter", "twitter.com");

            postdata.put("fb", "fb.com");
            postdata.put("dat_ins", "20/03/2018");
            postdata.put("heure_ins", "20:30");
            postdata.put("last_update", "18/09/2018");
            postdata.put("etat", "actif");
            postdata.put("statut", "actif");
        } catch(JSONException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://smart-data-tech.com/dev/API/v1/saveuser/").newBuilder();
        //HttpUrl.Builder urlBuilder = HttpUrl.parse("http://smart-data-tech.com/dev/fr/crud.php").newBuilder();
        //HttpUrl.Builder urlBuilder = HttpUrl.parse("http://smart-data-tech.com/dev/model/usercka.php").newBuilder();
        String url = urlBuilder.build().toString();
       /* urlBuilder.addQueryParameter("nom", "nom");
        urlBuilder.addQueryParameter("numero", "021548");*/
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


        final Request request = new Request.Builder().url(url).post(body).addHeader("Content-Type", "application/json").build();
        //client.newCall(request).execute();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                System.out.println("votre erreur"+mMessage);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String mMessage = response.body().string();
                if (response.isSuccessful()){
                    try {
                        JSONObject json = new JSONObject(mMessage);
                        final String serverResponse = json.getString("c'est OK");

                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                Log.i("la requete est ", String.valueOf(response+" et "+ request));
            }
        });
    }

    public static void EnvoiFinal()
            throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("nom", "nom")
                .add("numero", "2548754")
                .build();

        Request request = new Request.Builder()
                .url("http://smart-data-tech.com/dev/fr/crud.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();


        Log.i("est votre code", String.valueOf(response.code()));
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
