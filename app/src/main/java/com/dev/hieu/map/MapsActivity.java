package com.dev.hieu.map;

import android.app.Dialog;
import android.nfc.Tag;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.dev.hieu.map.DAO.MapsDAO;
import com.dev.hieu.map.database.DatabaseManager;
import com.dev.hieu.map.model.Maps;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private MapsDAO mapsDAO;
    private GoogleMap mMap;
    private DatabaseManager databaseManager;
    private List<Maps> maps;
    private Dialog dialog;
    private String latitude, longitude;
    private EditText editText, editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        dialog =new Dialog(MapsActivity.this);
        maps = new ArrayList<>();
        databaseManager = new DatabaseManager(this);
        mapsDAO = new MapsDAO(databaseManager);
        Maps map1 = new Maps(-34,151);
        mapsDAO.insertMaps(map1);
        maps.add(map1);
        Log.e("insertMaps1", String.valueOf(map1));
//        Maps map2 = new Maps("1111",Double.parseDouble(latitude),Double.parseDouble(longitude));
//        mapsDAO.insertMaps(map2);
//        maps.add(map2);
        maps = mapsDAO.getAllMaps();


//        for(int i = 0;i< maps.size();i++) {
//            LatLng sydney = new LatLng(maps.get(i).getLat(),maps.get(i).getLng());
//            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//
//        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for(int i = 0;i< maps.size();i++) {
            LatLng sydney = new LatLng(maps.get(i).getLat(),maps.get(i).getLng());
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        }

        LatLng sydney = new LatLng(-34,151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                dialog.setContentView(R.layout.dialog);
                dialog.show();

                dialog.findViewById(R.id.them).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editText= dialog.findViewById(R.id.latitude);
                        editText1= dialog.findViewById(R.id.longitude);
                        dialog.findViewById(R.id.layout).setVisibility(View.VISIBLE);
                        dialog.findViewById(R.id.layout1).setVisibility(View.GONE);
                        dialog.findViewById(R.id.thaydoi).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                latitude=editText.getText().toString();
                                longitude=editText1.getText().toString();
                                if (!latitude.isEmpty() && !longitude.isEmpty()){
                                    final LatLng sydney1 = new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));
                                    mMap.addMarker(new MarkerOptions().position(sydney1).title("Haha"));
                                    dialog.dismiss();
                                }

                            }
                        });
                    }
                });

                dialog.findViewById(R.id.sua).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editText= dialog.findViewById(R.id.latitude);
                        editText1= dialog.findViewById(R.id.longitude);
                        dialog.findViewById(R.id.layout).setVisibility(View.VISIBLE);
                        dialog.findViewById(R.id.layout1).setVisibility(View.GONE);
                        final LatLng latLng= marker.getPosition();
                        editText.setText(latLng.latitude+"");
                        editText1.setText(latLng.longitude+"");
                        dialog.findViewById(R.id.thaydoi).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String latitude=editText.getText().toString();
                                String longitude=editText1.getText().toString();
                                if (!latitude.isEmpty() && !longitude.isEmpty()){
                                    final LatLng sydney1 = new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));
                                    if (!latLng.toString().equals(sydney1.toString())){
                                        Log.e("OKI","OKI");
                                        marker.setPosition(sydney1);
                                        dialog.dismiss();
                                    }else {
                                        Log.e("TAG","MỜI BẠN NHẬP VỊ TRÍ KHÁC");
                                    }

                                }

                            }
                        });

                    }
                });


                dialog.findViewById(R.id.xoa).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        marker.remove();
                        dialog.dismiss();
                    }
                });



                return true;
            }
        });
}

}

