package com.whispcorp.whispme.view.fragments;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.whispcorp.whispme.R;
import com.whispcorp.whispme.services.LocationService;
import com.whispcorp.whispme.util.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapMenuFragment extends Fragment implements OnMapReadyCallback {

    private Context mContext;
    private FragmentManager mFragmentManager;

    private GoogleMap mMap;
    private Marker mMarker;
    private BroadcastReceiver mBroadcastReceiver;

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

        try {
            mFragmentManager = getActivity().getSupportFragmentManager();

            SupportMapFragment mapFragment = (SupportMapFragment) mFragmentManager
                    .findFragmentById(R.id  .map);
            mapFragment.getMapAsync(MapMenuFragment.this);

            if (runtimePermissions()) {
                startLocationService();
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            e.printStackTrace();
        }

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
        mMap = googleMap;
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                showWhispsDialog();
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBroadcastReceiver == null) {
            mBroadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Bundle extras = intent.getExtras();
                    if (extras != null) {
                        double latitude = intent.getExtras().getDouble(Constants.Extra.LATITUDE);
                        double longitude = intent.getExtras().getDouble(Constants.Extra.LONGITUDE);
                        LatLng position = new LatLng(latitude, longitude);

                        if (mMarker != null)
                            mMarker.remove();
                        mMarker = mMap.addMarker(new MarkerOptions().position(position).title(null));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 15.0f));

                        Toast.makeText(context, "(" + latitude + ", " + longitude + ")", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "(SIN GPS)", Toast.LENGTH_SHORT).show();
                    }
                }
            };
        }

        mContext.registerReceiver(mBroadcastReceiver, new IntentFilter(Constants.Action.LOCATION_UPDATE));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBroadcastReceiver != null) {
            mContext.unregisterReceiver(mBroadcastReceiver);
        }
        stopLocationService();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.RequestCode.LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                startLocationService();
            } else {
                runtimePermissions();
            }
        }
    }


    private void startLocationService() {
        mContext.startService(new Intent(mContext, LocationService.class));
    }

    private void stopLocationService() {
        mContext.stopService(new Intent(mContext, LocationService.class));
    }

    private boolean runtimePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        Constants.RequestCode.LOCATION);
                return false;
            }
        }

        return true;
    }

    public void showWhispsDialog() {
        WhispListDialogFragment whispListDialogFragment = new WhispListDialogFragment();
        whispListDialogFragment.show(mFragmentManager, "dialog_whisps_detail");
    }
}
