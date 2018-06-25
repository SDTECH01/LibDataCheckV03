package com.example.cka.smartckeckdata;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class Jsjava implements LocationListener {
    final String TAG = "GPS";
    private final static int ALL_PERMISSIONS_RESULT = 101;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
    public static Context contextOfApplication;

    private String Ur="http://smart-data-tech.com/dev/API/v1/saveUserCommonProprety/";
    private String id_user;
    private String id_phone_number;
    private String phone_number, number_name, date_ins_number, img_number, type_number, groupe_number;
    private String local_number, dat_user_phone;
    private String etat = "actif";
    public static Context getContextOfApplication() {
        return contextOfApplication;
    }


    LocationManager locationManager;
    Location loc;
    ArrayList<String> permissions = new ArrayList<>();
    ArrayList<String> permissionsToRequest;
    ArrayList<String> permissionsRejected = new ArrayList<>();
    boolean isGPS = false;
    boolean isNetwork = false;
    boolean canGetLocation = true;
    private String latitude;
    private String longitude;


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    ////////////////////////
    public void getLocalisation() {
        //locationManager = getContextOfApplication().getSystemService(Context.ACCESSIBILITY_SERVICE);
        isGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionsToRequest = findUnAskedPermissions(permissions);

        if (!isGPS && !isNetwork) {

            getLastLocation();
        } else {
            Log.d(TAG, "Connection on");
            // check permissions
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(contextOfApplication, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    canGetLocation = false;
                }
            }



           /* if (permissionsToRequest.size() > 0) {
                ActivityCompat.requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
                Log.d(TAG, "Permission requests");
                canGetLocation = false;
            }*/

        }
    }

    private ArrayList findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList result = new ArrayList();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canAskPermission()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (ContextCompat.checkSelfPermission(contextOfApplication,permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }
/////c'est ici qu'il faut envoyé la requete http
    private void updateUI(Location loc) {
        Log.d(TAG, "updateUI");
        latitude= String.valueOf(loc.getLatitude());
        longitude= String.valueOf(loc.getLongitude());
        String dat = DateFormat.getTimeInstance().format(loc.getTime());
        EnvoiPost(Ur,id_user,id_user,longitude,latitude,dat,
                "15","liberty1","liberty2","liberty3","liberty4", "liberty5",
                "liberty6","liberty7",dat,etat);
        //EnvoiPost();
    }

    private void getLastLocation() {
        try {
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, false);
            Location location = locationManager.getLastKnownLocation(provider);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
    private void getLocation() {
        try {
            if (canGetLocation) {
                Log.d(TAG, "Can get location");
                if (isGPS) {
                    // from GPS
                    Log.d(TAG, "GPS on");
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if (locationManager != null) {
                        loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (loc != null)
                            updateUI(loc);
                    }
                } else if (isNetwork) {
                    // from Network Provider
                    Log.d(TAG, "NETWORK_PROVIDER on");
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if (locationManager != null) {
                        loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (loc != null)
                            updateUI(loc);
                    }
                } else {
                    loc.setLatitude(0);
                    loc.setLongitude(0);
                    updateUI(loc);
                }
            } else {
                Log.d(TAG, "Can't get location");
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
    public void EnvoiPost(String url, String id_user, String level_battery, String longitude, String latitude, String dat_ins_proprety,
                          String heure_property,String liberty1, String liberty2, String liberty3, String liberty4, String liberty5,
                          String liberty6,String liberty7, String last_update,String eta) {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("id_user",id_user));
            nameValuePairs.add(new BasicNameValuePair("level_battery",level_battery));
            nameValuePairs.add(new BasicNameValuePair("longitude",longitude));
            nameValuePairs.add(new BasicNameValuePair("latitude",latitude));
            nameValuePairs.add(new BasicNameValuePair("dat_ins_proprety",dat_ins_proprety));
            nameValuePairs.add(new BasicNameValuePair("heure_property",heure_property));
            nameValuePairs.add(new BasicNameValuePair("liberty1",liberty1));
            nameValuePairs.add(new BasicNameValuePair("liberty2",liberty2));
            nameValuePairs.add(new BasicNameValuePair("liberty3",liberty3));
            nameValuePairs.add(new BasicNameValuePair("liberty4",liberty4));
            nameValuePairs.add(new BasicNameValuePair("liberty5",liberty5));
            nameValuePairs.add(new BasicNameValuePair("liberty6",liberty6));
            nameValuePairs.add(new BasicNameValuePair("liberty7",liberty7));
            nameValuePairs.add(new BasicNameValuePair("last_update",last_update));
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
