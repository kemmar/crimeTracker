package com.gov.fiirb.crimemapper;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.VisibleRegion;
import com.gov.fiirb.crimemapper.domain.Crime;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.json.JSONArray;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CrimeFinder {

    private static Context me;

    public static GoogleMap.OnCameraChangeListener cameraChangeListener(final GoogleMap map, Context applicationContext) {
        me = applicationContext;
        return new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                if (map != null) {
                    updateMap(map);
                }
            }
        };
    }

    private static void updateMap(final GoogleMap map) {
        String url = buildBoundary("https://data.police.uk/api/crimes-street/all-crime?poly=", getPoly(map));
            JsonArrayRequest jsObjRequest = new JsonArrayRequest
                    (Request.Method.GET, url, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                            Set<Crime> myCrimes = getCrimes(response);

                            map.clear();

                            setMarkers(myCrimes, map);

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.err.println(error.getMessage());
                        }
                    });
            RequestQueueManager.getInstance(me).addToRequestQueue(jsObjRequest);
        }


    private static void setMarkers(Set<Crime> myCrimes, GoogleMap map) {
        for (Crime crime : myCrimes) {
            Double lat = crime.getLocation().getLatitude();
            Double lng = crime.getLocation().getLongitude();
            String category = crime.getCategory();

            map.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lng))
                    .title(category));
        }
    }

    private static Set<Crime> getCrimes(JSONArray response) {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType crimes = TypeFactory.defaultInstance().constructCollectionType(Set.class, Crime.class);
        try {
            return mapper.readValue(response.toString(), crimes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashSet<>();
    }

    private static String buildBoundary(String url, PolygonOptions points) {
        for (LatLng lo : points.getPoints()) {
            if (url.charAt(url.length() - 1) != '=') {
                url += ":";
            }
            url += lo.latitude + "," + lo.longitude;
        }
        return url;
    }

    private static PolygonOptions getPoly(GoogleMap map) {
        VisibleRegion bounds = map.getProjection().getVisibleRegion();
        return new PolygonOptions()
                .add(bounds.farLeft)
                .add(bounds.farRight)
                .add(bounds.nearRight)
                .add(bounds.nearLeft);
    }
}
