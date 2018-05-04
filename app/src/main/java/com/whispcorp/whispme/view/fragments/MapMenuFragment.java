package com.whispcorp.whispme.view.fragments;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.whispcorp.whispme.R;
import com.whispcorp.whispme.database.entities.Whisp;
import com.whispcorp.whispme.services.LocationService;
import com.whispcorp.whispme.util.Constants;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapMenuFragment extends Fragment implements OnMapReadyCallback {

    private Context mContext;
    private FragmentManager mFragmentManager;

    private MapView mapView;
    private GoogleMap mMap;
    private Marker mCurrentPositionMarker;

    private LocationService mLocationService;
    private ServiceConnection mServiceConnection;
    private BroadcastReceiver mBroadcastReceiver;
    public static Double LATITUDE;
    public static Double LONGITUDE;
    private boolean bound = false;

    private boolean isRequestingLocationPermission = false;
    private static boolean hasShownRequestLocationPermissionRationale = false;
    public static boolean hasRequestedSettingLocation = false;
    private String TAG = "GGx MapMenuFragment";


    public MapMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_menu, container, false);

        /*mapView = view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);*/

        //SupportMapFragment mapFragment = (SupportMapFragment) mContext.getSupportFragmentManager().findFragmentById(R.id.map);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapMenuFragment.this);

        mServiceConnection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName className, IBinder service) {
                Log.d("GGx", "onServiceConnected");
                LocationService.LocalBinder binder = (LocationService.LocalBinder) service;
                mLocationService = binder.getService();
                bound = true;
                mLocationService.setMapCallback(() -> {
                    updateDefaultPosition();
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName arg0) {
                bound = false;
                Log.d("GGx", "onServiceDisconnected");
                updateDefaultPosition();
            }
        };

        return view;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("GGx", "onMapReady");
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setCompassEnabled(false);
        mMap.getUiSettings().setIndoorLevelPickerEnabled(false);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        //mMap.getUiSettings().setZoomGesturesEnabled(false);
        mMap.setMinZoomPreference(16.0f);
        mMap.setMaxZoomPreference(17.0f);
        //mMap.getUiSettings().setScrollGesturesEnabled(false);
        mMap.getUiSettings().setTiltGesturesEnabled(false);
        //mMap.getUiSettings().setRotateGesturesEnabled(false);
        //mMap.getUiSettings().setAllGesturesEnabled(false);

        // Set a style to the map
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(mContext, R.raw.style_json));

        mMap.setOnMarkerClickListener(marker -> {
            //marker.setTitle(marker.getId());
            //marker.showInfoWindow();
            marker.showInfoWindow();
            if (Objects.equals(marker.getId(), mCurrentPositionMarker.getId())) {
                // TODO: Show current position info
                showWhispsDialog();
                return true;
            }

            // TODO: Redirect to selected Whisp in cards
            //Whisp whisp = markerWhisps.get(marker);
            //whispmeApi.getWhispDetail(whisp.getWhispId());

            return true;
        });


        if (!isRequestingLocationPermission && runtimePermissions()) {
            startLocationService();
        } else {
            updateDefaultPosition();
        }
    }

    @Override
    public void onResume() {
        Log.d("GGx", "onResume");
        super.onResume();
    }


    @Override
    public void onStart() {
        Log.d("GGx", "onStart");
        super.onStart();
        if (!isRequestingLocationPermission && mMap != null) {
            if (runtimePermissions()) {
                startLocationService();
            }
        }
    }

    @Override
    public void onStop() {
        Log.d("GGx", "onStop");
        super.onStop();
        stopLocationService();
    }

    @Override
    public void onPause() {
        Log.d("GGx", "onPause");
        super.onPause();
    }

    @Override
    public void onDestroy() {
        Log.d("GGx", "onDestroy");
        super.onDestroy();
        stopLocationService();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.d("GGx", "MapMenu: onRequestPermissionsResult: request: " + requestCode + " result: " + grantResults[0]);

        if (requestCode == Constants.RequestCode.LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                if (!hasRequestedSettingLocation) {
                    Log.d("GGx", "MapMenu: onRequestPermissionsResult: startActivityForResult");

                    hasRequestedSettingLocation = true;
                    startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), Constants.RequestCode.SETTING_LOCATION);

                    // Start GPS Silently
                    /*Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
                    intent.putExtra("enabled", true);
                    mContext.sendBroadcast(intent);*/
                }
            } else {
                //runtimePermissions();
                Toast.makeText(mContext, "Whispme funcionará de forma limitada.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("GGx", "MapMenu: onActivityResult: request: " + requestCode + ", result:" + resultCode);
        switch (requestCode) {
            case Constants.RequestCode.SETTING_LOCATION:
                // TODO: Always returns resultCode = 0
                switch (resultCode) {
                    case 1:
                        startLocationService();
                        isRequestingLocationPermission = false;
                        break;
                    default:
                        updateDefaultPosition();
                        startLocationService();
                        isRequestingLocationPermission = false;
                        break;
                }
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startLocationService() {
        Intent intent = new Intent(mContext, LocationService.class);
        mContext.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);

        //mContext.startService(new Intent(mContext, LocationService.class));

        if (mBroadcastReceiver == null) {
            mBroadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Bundle extras = intent.getExtras();
                    if (extras != null) {
                        LATITUDE = intent.getExtras().getDouble(Constants.Extra.LATITUDE);
                        LONGITUDE = intent.getExtras().getDouble(Constants.Extra.LONGITUDE);
                        LatLng position = new LatLng(LATITUDE, LONGITUDE);
                        updateCurrentPosition(position);

                        Toast.makeText(context, "(" + LATITUDE + ", " + LONGITUDE + ")", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "(SIN GPS)", Toast.LENGTH_SHORT).show();
                    }
                }
            };
        }

        mContext.registerReceiver(mBroadcastReceiver, new IntentFilter(Constants.Action.LOCATION_UPDATE));
        Log.d("GGx", "MapMenu: onRequestPermissionsResult: registrerReceiver");
    }

    private void stopLocationService() {
        if (bound) {
            mLocationService.setMapCallback(null); // unregister
            mContext.unbindService(mServiceConnection);
            bound = false;
        }

        //mContext.stopService(new Intent(mContext, LocationService.class));

        if (mBroadcastReceiver != null) {
            mContext.unregisterReceiver(mBroadcastReceiver);
            mBroadcastReceiver = null;
        }
    }

    private boolean runtimePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            final LocationManager manager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

            if (!Objects.requireNonNull(manager).isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                    (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                            ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
                isRequestingLocationPermission = true;

                if (!hasShownRequestLocationPermissionRationale /*&&
                        (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) ||
                                shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION))*/) {
                    hasShownRequestLocationPermissionRationale = true;
                    Log.d("GGx", "MapMenu: runtimePermissions: AlertDialog");
                    new AlertDialog.Builder(mContext)
                            .setCancelable(true)
                            .setTitle(Constants.Message.PERMISSION_RATIONALE_LOCATION_TITLE)
                            .setMessage(Constants.Message.PERMISSION_RATIONALE_LOCATION_MESSAGE)
                            .setPositiveButton(android.R.string.yes, (dialog, which) -> requestPermissions(
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                                    Constants.RequestCode.LOCATION))
                            .show();
                } else {
                    Log.d("GGx", "MapMenu: runtimePermissions: requestPermissions");
                    requestPermissions(
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                            Constants.RequestCode.LOCATION);
                }
                return false;
            }
        }
        return true;
    }

    private void updateCurrentPosition(LatLng position) {
        if (mCurrentPositionMarker != null)
            mCurrentPositionMarker.remove();
        mCurrentPositionMarker = mMap.addMarker(new MarkerOptions().position(position).title("I'm here"));

        double bounds = Constants.Default.BOUNDS;
        LatLngBounds latLngBounds = new LatLngBounds(
                new LatLng(position.latitude - bounds, position.longitude - bounds),
                new LatLng(position.latitude + bounds, position.longitude + bounds));
        mMap.setLatLngBoundsForCameraTarget(latLngBounds);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, mMap.getCameraPosition().zoom));
    }

    private void updateDefaultPosition() {
        LatLng position = new LatLng(Constants.Default.LATITUDE, Constants.Default.LONGITUDE);
        updateCurrentPosition(position);
    }

    public void showWhispsDialog() {
        WhispListDialogFragment whispListDialogFragment = new WhispListDialogFragment();
        whispListDialogFragment.show(getChildFragmentManager(), "dialog_whisps_detail");
    }

    public interface MapCallback {
        void onRequestLocationPermissionFailed();
    }
}
