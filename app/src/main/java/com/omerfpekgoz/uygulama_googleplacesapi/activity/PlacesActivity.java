package com.omerfpekgoz.uygulama_googleplacesapi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.omerfpekgoz.uygulama_googleplacesapi.R;
import com.omerfpekgoz.uygulama_googleplacesapi.adapter.PlaceAdapter;
import com.omerfpekgoz.uygulama_googleplacesapi.model.Places;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlacesActivity extends AppCompatActivity {

    private RecyclerView recPlaces;
    private ArrayList<Places> placesArrayList;
    private PlaceAdapter placeAdapter;

    private  String placeLatitude;  //Enlem
    private String placeLongitude;  //Boylam



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        recPlaces=findViewById(R.id.recPlaces);

        placeLatitude=getIntent().getStringExtra("latitude");  //Enlem
        placeLongitude=getIntent().getStringExtra("longitude"); //Boylam

        recPlaces.setHasFixedSize(true);
        recPlaces.setLayoutManager(new LinearLayoutManager(this));



        findPlace();
    }

    public void findPlace(){  //Mekan Bul metodu

        String key="AIzaSyDc3MPEEFwXwvNVEFFnVI3YGBS49Vvu6BQ";
        String radius="5000";
        String location=placeLatitude+","+placeLongitude;

        String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+location+"&radius="+radius+"&key="+key;


        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    placesArrayList=new ArrayList<>();

                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray places=jsonObject.getJSONArray("results");
                    for (int i=0;i<places.length();i++){

                        JSONObject p=places.getJSONObject(i);

                        String placesName=p.getString("name");
                        String placeVicinity=p.getString("vicinity");
                        String icon=p.getString("icon");

                        JSONObject geometry=p.getJSONObject("geometry");
                        JSONObject location=geometry.getJSONObject("location");

                        Double placeLatitude=location.getDouble("lat");  //Enlem
                        Double placeLongitude=location.getDouble("lng");  //Boylam

                        Places place=new Places(placesName,placeLatitude,placeLongitude,placeVicinity,icon);

                        placesArrayList.add(place);

                    }

                    placeAdapter=new PlaceAdapter(PlacesActivity.this,placesArrayList);
                    recPlaces.setAdapter(placeAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(PlacesActivity.this).add(stringRequest);
    }
}
