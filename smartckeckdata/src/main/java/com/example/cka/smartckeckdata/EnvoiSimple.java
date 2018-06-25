package com.example.cka.smartckeckdata;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

class EnvoiSimple {
    public EnvoiSimple() {
        Insert_to_server();
            }


    public static class Inserttask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Insert_to_server();
            return null;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }






}


    public static void Insert_to_server() {

        HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost("http://smart-data-tech.com/dev/API/v1/saveuser/");

        try {

            List<NameValuePair> namevaluepair = new ArrayList<>();

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("tel1", "tel1"));
            nameValuePairs.add(new BasicNameValuePair("tel2", "tel2"));
            nameValuePairs.add(new BasicNameValuePair("tell3", "tel3"));
            nameValuePairs.add(new BasicNameValuePair("tell4", "tel4"));
            nameValuePairs.add(new BasicNameValuePair("imei", "imei"));
            nameValuePairs.add(new BasicNameValuePair("version", "version"));
            nameValuePairs.add(new BasicNameValuePair("model", "model"));
            nameValuePairs.add(new BasicNameValuePair("duree_activite", "duree_activite"));
            nameValuePairs.add(new BasicNameValuePair("gmail", "gmail"));
            nameValuePairs.add(new BasicNameValuePair("twitter", "twitter"));
            nameValuePairs.add(new BasicNameValuePair("fb", "fb"));
            nameValuePairs.add(new BasicNameValuePair("dat_ins", "dat_ins"));
            nameValuePairs.add(new BasicNameValuePair("etat", "etat"));
            nameValuePairs.add(new BasicNameValuePair("statut", "statut"));


            httppost.setEntity(new UrlEncodedFormEntity(namevaluepair));
            HttpResponse httpresponse = httpclient.execute(httppost);
            int status = httpresponse.getStatusLine().getStatusCode();
            Log.d("Voici le status ici:  ", status + "");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
