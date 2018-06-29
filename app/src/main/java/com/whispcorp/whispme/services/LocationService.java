package com.whispcorp.whispme.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

import com.whispcorp.whispme.util.Constants;
import com.whispcorp.whispme.view.fragments.MapMenuFragment;

public class LocationService extends Service {

    private LocationListener mListener;
    private LocationManager mLocationManager;

    private final IBinder binder = new LocalBinder();
    private MapMenuFragment.MapCallback mMapCallback;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("GGx", "LocationService: onCreate");
        if (mListener == null) {
            initListener();
            initManager();
        }
    }

    @Override
    public void onDestroy() {
        Log.d("GGx", "LocationService: onCreate");

        super.onDestroy();
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(mListener);
        }
    }


    public void setMapCallback(MapMenuFragment.MapCallback mapCallback) {
        this.mMapCallback = mapCallback;
    }

    public class LocalBinder extends Binder {
        public LocationService getService() {
            return LocationService.this;
        }
    }

    private void initListener() {
        Log.d("GGx", "LocationService: init");

        mListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                sendPosition(location.getLatitude(), location.getLongitude());
                Log.d("GGx", "LocationService: onLocationChanged");
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                Log.d("GGx", "onStatusChanged");
            }

            @Override
            public void onProviderEnabled(String s) {
                Log.d("GGx", "onProviderEnabled");
            }

            @Override
            public void onProviderDisabled(String s) {
                Log.d("GGx", "onProviderDisabled");
                boolean gotPosition = false;

                try {
                    if (mLocationManager == null) {
                        initManager();
                    }

                    if (mLocationManager != null) {
                        Location lastKnownLocation = ((lastKnownLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)) != null) ?
                                lastKnownLocation : mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        if (lastKnownLocation != null) {
                            sendPosition(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                            gotPosition = true;
                            Log.d("GGx", "lastKnownLocation");
                        }
                    }

                    if (!gotPosition && !MapMenuFragment.hasRequestedSettingLocation) {
                        MapMenuFragment.hasRequestedSettingLocation = true;
                        // TODO: callback showing world map
                        // Prompt Location Settings
                        //startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

                        // Default position
                        sendPosition(Constants.Default.LATITUDE, Constants.Default.LONGITUDE);

                        // Execute onFailed in MapMenuFragment
                        if (mMapCallback != null) {
                            mMapCallback.onRequestLocationPermissionFailed();
                        }
                        Log.d("GGx", "onRequestLocationPermissionFailed");
                    }

                } catch (SecurityException | NullPointerException e) {
                    Log.d("GGx", "E: LocationService: initListener: " + e.toString());
                }
            }
        };
    }

    private void initManager() {
        Log.d("GGx", "LocationService: initManager");
        try {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
            if (mLocationManager != null) {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, Constants.UpdateTime.LOCATION, 0, mListener);
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, Constants.UpdateTime.LOCATION, 0, mListener);
            }
        } catch (SecurityException | NullPointerException e) {
            Log.d("GGx", "E: LocationService: initManager: " + e.toString());
        }
    }

    private void sendPosition(double latitude, double longitude) {
        Intent intent = new Intent(Constants.Action.LOCATION_UPDATE);
        intent.putExtra(Constants.Extra.LATITUDE, latitude);
        intent.putExtra(Constants.Extra.LONGITUDE, longitude);
        sendBroadcast(intent);
    }

}