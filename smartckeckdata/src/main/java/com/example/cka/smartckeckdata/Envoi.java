package com.example.cka.smartckeckdata;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Envoi extends AsyncTask<String, String, String> {
        private HashMap<String, String> mData = null;// post data

    public Envoi(HashMap<String, String> data) {
        mData = data;
    }
    @Override
    protected String doInBackground(String... strings) {

        byte[] result = null;
        String str = "";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://smart-data-tech.com/dev/API/v1/saveuser/");// in this case, params[0] is URL
        try {
            // set up post data
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

            post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse response = client.execute(post);
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpURLConnection.HTTP_OK){
                result = EntityUtils.toByteArray(response.getEntity());
                str = new String(result, "UTF-8");
            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
        }
        return str;

    }
}
