package com.example.cka.smartckeckdata;

import android.location.Location;

import java.text.DateFormat;
import java.util.Date;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;

public class SaveUserCommonProprety {
    private Long id_user;
    private int level_battery;
    private  double longitude;
    private double latitude;
    private String dat_ins_proprety;
    private String heure_proprety;
    private String liberty1;
    private String liberty2;
    private String liberty3;
    private String liberty4;
    private String liberty5;
    private String liberty6;
    private String liberty7;
    private String last_update;
    private String etat="actif";

    // bunch of location related apis
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;

    public SaveUserCommonProprety(Long id_user, int level_battery, double longitude, double latitude, String dat_ins_proprety, String heure_proprety, String liberty1, String liberty2, String liberty3, String liberty4, String liberty5, String liberty6, String liberty7, String last_update, String etat) {
        this.id_user = id_user;
        this.level_battery = level_battery;
        this.longitude = longitude;
        this.latitude = latitude;
        this.dat_ins_proprety = dat_ins_proprety;
        this.heure_proprety = heure_proprety;
        this.liberty1 = liberty1;
        this.liberty2 = liberty2;
        this.liberty3 = liberty3;
        this.liberty4 = liberty4;
        this.liberty5 = liberty5;
        this.liberty6 = liberty6;
        this.liberty7 = liberty7;
        this.last_update = last_update;
        this.etat = etat;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public int getLevel_battery() {
        return level_battery;
    }

    public void setLevel_battery(int level_battery) {
        this.level_battery = level_battery;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getDat_ins_proprety() {
        return dat_ins_proprety;
    }

    public void setDat_ins_proprety(String dat_ins_proprety) {
        this.dat_ins_proprety = dat_ins_proprety;
    }

    public String getHeure_proprety() {
        return heure_proprety;
    }

    public void setHeure_proprety(String heure_proprety) {
        this.heure_proprety = heure_proprety;
    }

    public String getLiberty1() {
        return liberty1;
    }

    public void setLiberty1(String liberty1) {
        this.liberty1 = liberty1;
    }

    public String getLiberty2() {
        return liberty2;
    }

    public void setLiberty2(String liberty2) {
        this.liberty2 = liberty2;
    }

    public String getLiberty3() {
        return liberty3;
    }

    public void setLiberty3(String liberty3) {
        this.liberty3 = liberty3;
    }

    public String getLiberty4() {
        return liberty4;
    }

    public void setLiberty4(String liberty4) {
        this.liberty4 = liberty4;
    }

    public String getLiberty5() {
        return liberty5;
    }

    public void setLiberty5(String liberty5) {
        this.liberty5 = liberty5;
    }

    public String getLiberty6() {
        return liberty6;
    }

    public void setLiberty6(String liberty6) {
        this.liberty6 = liberty6;
    }

    public String getLiberty7() {
        return liberty7;
    }

    public void setLiberty7(String liberty7) {
        this.liberty7 = liberty7;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

///////////////////initialisation de l'instance ///////////////////////////////////
/*
    private void init() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mSettingsClient = LocationServices.getSettingsClient(this);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                // location is received
                mCurrentLocation = locationResult.getLastLocation();
                mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());

                updateLocationUI();
            }
        };

        mRequestingLocationUpdates = false;

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }*/

}

