package nikola.bottomnavigationview.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import nikola.bottomnavigationview.R;

/**
 * Created by Nikola on 4/21/2017.
 */

public class ThirdFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;

    public ThirdFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.third_fragment, parent, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                LatLng vilingrad = new LatLng(43.319003, 21.8928873);
                LatLng cineplex  = new LatLng(43.3210004, 21.8936672);
                LatLng kupina    = new LatLng(43.3110867, 21.9363787);
                LatLng nis       = new LatLng(43.316238, 21.8583399);
                googleMap.addMarker(new MarkerOptions().position(vilingrad).title("Vilin Grad").snippet("Vilin Grad, Obrenoviceva 19"));
                googleMap.addMarker(new MarkerOptions().position(cineplex).title("Cineplex").snippet("Cineplex, Bulevar Medijana 21"));
                googleMap.addMarker(new MarkerOptions().position(kupina).title("Kupina").snippet("Kupina, Balkanska 2"));

                CameraPosition cameraPosition = new CameraPosition.Builder().target(nis).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return rootView;
    }
}
