package com.example.cka.smartckeckdata;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class LogsManager implements ISaveUserCallHistory {

    public static final int INCOMING = CallLog.Calls.INCOMING_TYPE;
    public static final int OUTGOING = CallLog.Calls.OUTGOING_TYPE;
    public static final int MISSED = CallLog.Calls.MISSED_TYPE;
    public static final int TOTAL = 579;
    //public static final int NUMERO_USER = TelephonyManager.

    public static final int INCOMING_CALLS = 672;
    public static final int OUTGOING_CALLS = 609;
    public static final int MISSED_CALLS = 874;
    public static final int ALL_CALLS = 814;
    private static final int READ_CALL_LOG = 47;

    private Context context;
    private String url= "http://smart-data-tech.com/dev/API/v1/saveUserCallHistory/";
    //TelephonyManager tfr = SubscriptionManager();
    /*TelephonyManager tm = (TelephonyManager) getContexteApplication().getSystemService(getContexteApplication().TELEPHONY_SERVICE);
    TelephonyManager tx = g*/

    public LogsManager(Context context) {
        this.context = context;
    }



    public int getOutgoingDuration() {
        int sum = 0;
        @SuppressLint("MissingPermission")
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                CallLog.Calls.TYPE + " = " + CallLog.Calls.OUTGOING_TYPE, null, null);
        try {

            int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);

            while (cursor.moveToNext()) {
                String callDuration = cursor.getString(duration);
                sum += Integer.parseInt(callDuration);
            }

            cursor.close();
        } finally {
            cursor.close();
        }

        return sum;
    }

    public int getIncomingDuration() {
        int sum = 0;
        @SuppressLint("MissingPermission")
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                CallLog.Calls.TYPE + " = " + CallLog.Calls.INCOMING_TYPE, null, null);

        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);

        while (cursor.moveToNext()) {
            String callDuration = cursor.getString(duration);
            sum += Integer.parseInt(callDuration);
        }

        cursor.close();

        return sum;
    }

    public int getTotalDuration() {
        int sum = 0;
        @SuppressLint("MissingPermission")
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);

        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);

        while (cursor.moveToNext()) {
            String callDuration = cursor.getString(duration);
            sum += Integer.parseInt(callDuration);
        }

        cursor.close();

        return sum;
    }

    public String getCoolDuration(int type) {
        float sum;

        switch (type) {
            case INCOMING:
                sum = getIncomingDuration();
                break;
            case OUTGOING:
                sum = getOutgoingDuration();
                break;
            case TOTAL:
                sum = getTotalDuration();
                break;
            default:
                sum = 0;
        }

        String duration = "";
        String result;

        if (sum >= 0 && sum < 3600) {

            result = String.valueOf(sum / 60);
            String decimal = result.substring(0, result.lastIndexOf("."));
            String point = "0" + result.substring(result.lastIndexOf("."));

            int minutes = Integer.parseInt(decimal);
            float seconds = Float.parseFloat(point) * 60;

            DecimalFormat formatter = new DecimalFormat("#");
            duration = minutes + " min " + formatter.format(seconds) + " secs";

        } else if (sum >= 3600) {

            result = String.valueOf(sum / 3600);
            String decimal = result.substring(0, result.lastIndexOf("."));
            String point = "0" + result.substring(result.lastIndexOf("."));

            int hours = Integer.parseInt(decimal);
            float minutes = Float.parseFloat(point) * 60;

            DecimalFormat formatter = new DecimalFormat("#");
            duration = hours + " hrs " + formatter.format(minutes) + " min";

        }

        return duration;
    }

    public void SaveUserCallHistory(int callType) {
        List<LogObject> logs = new ArrayList<>();

        String selection;

        switch (callType) {
            case INCOMING_CALLS:
                selection = CallLog.Calls.TYPE + " = " + CallLog.Calls.INCOMING_TYPE;
                break;
            case OUTGOING_CALLS:
                selection = CallLog.Calls.TYPE + " = " + CallLog.Calls.OUTGOING_TYPE;
                break;
            case MISSED_CALLS:
                selection = CallLog.Calls.TYPE + " = " + CallLog.Calls.MISSED_TYPE;
                break;
            case ALL_CALLS:
                selection = null;
            default:
                selection = null;
        }

        @SuppressLint("MissingPermission")
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, selection, null, null);
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int dat = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);

        while (cursor.moveToNext()) {
            EnvoiPost(url,number,number,Integer.toString(duration),Integer.toString(number),Integer.toString(number),Integer.toString(type),
                    Integer.toString(type),Integer.toString(dat),"8h","actif");
            LogObject log = new LogObject(context);

            log.setNumber(cursor.getString(number));
            log.setType(cursor.getInt(type));
            log.setDuration(cursor.getInt(duration));
            log.setDate(cursor.getLong(dat));

            logs.add(log);

        }

        cursor.close();


       // return logs;
    }
    public void EnvoiPost(String url, int id_uer, int id_call,String call_duration,String correspondant_number, String coorespondant_name, String type_call, String call_date, String call_heure,
                          String dat_ins_call_history, String etat) {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(11);
            nameValuePairs.add(new BasicNameValuePair("id_uer",Integer.toString(id_uer)));
            nameValuePairs.add(new BasicNameValuePair("id_call",Integer.toString(id_call)));
            nameValuePairs.add(new BasicNameValuePair("call_duration",call_duration));
            nameValuePairs.add(new BasicNameValuePair("correspondant_number",correspondant_number));
            nameValuePairs.add(new BasicNameValuePair("coorespondant_name",coorespondant_name));
            nameValuePairs.add(new BasicNameValuePair("type_call",type_call));
            nameValuePairs.add(new BasicNameValuePair("call_date",call_date));
            nameValuePairs.add(new BasicNameValuePair("call_heure",call_heure));
            nameValuePairs.add(new BasicNameValuePair("dat_ins_call_history",dat_ins_call_history));
            nameValuePairs.add(new BasicNameValuePair("etat",etat));

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

/*
    @Override
    public void SaveUserCallHistory() {

    }*/

    @Override
    public String getNumber() {
        return null;
    }

    @Override
    public void setNumber(String number) {

    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public void setType(int type) {

    }

    @Override
    public long getDate() {
        return 0;
    }

    @Override
    public void setDate(long date) {

    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public void setDuration(int duration) {

    }

    @Override
    public String getCoolDuration() {
        return null;
    }

    @Override
    public String getContactName() {
        return null;
    }

    @Override
    public String getUserNumber() {
        return null;
    }
}
