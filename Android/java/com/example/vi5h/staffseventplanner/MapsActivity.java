package com.example.vi5h.staffseventplanner;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String mPost_L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mPost_L = getIntent().getExtras().getString("event_Location");
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
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        float zoomLevel = 16.0f; //This goes up to 21

       switch (mPost_L){

            case "Mellor Building":
                //Mellor Building
                LatLng mellor = new LatLng(53.010042, -2.180419);
                mMap.addMarker(new MarkerOptions()
                        .position(mellor)
                        .title(mPost_L).icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                //Car park A
                LatLng carPark1 = new LatLng(53.010189,  -2.180913);
                mMap.addMarker(new MarkerOptions().position(carPark1).title(mPost_L + " Car park A"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carPark1, zoomLevel));

                //Car park B
                LatLng carPark2 = new LatLng(53.010197,   -2.178928);
                mMap.addMarker(new MarkerOptions().position(carPark2).title(mPost_L +" Car park B"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carPark2, zoomLevel));
                break;


           case "Ember Lounge":
               //Ember Lounge
               LatLng ember = new LatLng(53.009524, -2.179833);
               mMap.addMarker(new MarkerOptions()
                       .position(ember)
                       .title(mPost_L).icon(BitmapDescriptorFactory
                               .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

               //Car park A
               LatLng carPark1a = new LatLng(53.010189,  -2.180913);
               mMap.addMarker(new MarkerOptions().position(carPark1a).title(mPost_L + " Car park A"));
               mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carPark1a, zoomLevel));

               //Car park B
               LatLng carPark2b = new LatLng(53.010197,   -2.178928);
               mMap.addMarker(new MarkerOptions().position(carPark2b).title(mPost_L +" Car park B"));
               mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carPark2b, zoomLevel));
               break;

           case "LRV and Verve":
                //LRV and Verve
                LatLng lrvAndverve = new LatLng(53.007882,  -2.175342);
                mMap.addMarker(new MarkerOptions()
                        .position(lrvAndverve)
                        .title(mPost_L).icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                //Car Park A
                LatLng carPark3 = new LatLng(53.007652,   -2.175546);
                mMap.addMarker(new MarkerOptions().position(carPark3).title(mPost_L + " Car park A"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carPark3, zoomLevel));

                //Car park B
                LatLng carPark4 = new LatLng(53.008826,    -2.175459);
                mMap.addMarker(new MarkerOptions().position(carPark4).title(mPost_L  + " Car park B"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carPark4, zoomLevel));
                break;


           case "Sir Stanley Matthews Sports hall":
               //Stanley Matthews Sports hall
               LatLng SMsportsHall = new LatLng(53.007882,  -2.175342);
               mMap.addMarker(new MarkerOptions()
                       .position(SMsportsHall)
                       .title(mPost_L).icon(BitmapDescriptorFactory
                               .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


               //Car Park A
               LatLng carPark5 = new LatLng(53.008605, -2.174087);
               mMap.addMarker(new MarkerOptions().position(carPark5).title(mPost_L + " Car park A"));
               mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carPark5, zoomLevel));

               //Car park B
               LatLng carPark6 = new LatLng(53.009739, -2.174776);
               mMap.addMarker(new MarkerOptions().position(carPark6).title(mPost_L + " Car park B"));
               mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carPark6, zoomLevel));
               break;


           case "S520 (Mellor Building)":
               //Mellor Building (S520)
               LatLng s520mellor = new LatLng(53.010042, -2.180419);
               mMap.addMarker(new MarkerOptions()
                       .position(s520mellor).snippet("5th Floor of Mellor Building!")
                       .title(mPost_L).icon(BitmapDescriptorFactory
                               .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

               //Car park A
               LatLng carPark7 = new LatLng(53.010189,  -2.180913);
               mMap.addMarker(new MarkerOptions().position(carPark7).title("Mellor Building Car park A"));
               mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carPark7, zoomLevel));

               //Car park B
               LatLng carPark8 = new LatLng(53.010197,   -2.178928);
               mMap.addMarker(new MarkerOptions().position(carPark8).title("Mellor Building Car park B"));
               mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carPark8, zoomLevel));
               break;

        }
    }
}
