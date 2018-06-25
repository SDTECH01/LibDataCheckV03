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

import java.text.DateFormat;
import java.util.ArrayList;

public class Localisation  implements ILocalisation {
Jsjava jsjava;
    @Override
    public void SaveUserCommonProperty() {
        jsjava.getLocalisation();
    }

}
