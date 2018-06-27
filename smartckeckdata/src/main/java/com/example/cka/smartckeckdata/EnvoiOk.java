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
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Request;

//public class EnvoiOk  implements  Callback{
public class EnvoiOk{
    /*public static final MediaType MEDIA_TYPE =
            MediaType.parse("application/json");*/
//////c'est ici nous allons tout faire////////////////
    ;
    public static void EnvoiO(){

        OkHttpClient client = new OkHttpClient();



        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://app-1530030008.000webhostapp.com/API/get_data.php").newBuilder();

        String url = urlBuilder.build().toString();
        urlBuilder.addQueryParameter("name", "kouassi");
        urlBuilder.addQueryParameter("email", "kouass@mail.com");


        Request request = new Request.Builder().url(url).build();
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
               // Log.i("la requete est ", String.valueOf(response+" et "+ request));
            }
        });

}
}


