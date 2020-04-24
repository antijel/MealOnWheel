package com.clem.mealonwheels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TruckListActivity extends AppCompatActivity {

    private ArrayList trucks;
    private TruckAdapter adapter;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_list);

        trucks = new ArrayList<>();

        // TODO Source de données

        mQueue = Volley.newRequestQueue(this);

        jsonParse();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*for(int i = 0; i < 25; i++){
            trucks.add(new Truck(R.drawable.burger,"Vela Jean-Marie","52 r Libération, 95440 ECOUEN","10h-14h","06.54.32.19.87","velamarie.com"));
        }*/


        adapter = new TruckAdapter(this,trucks);
        Log.i("RESULT", "Trucks : " + trucks);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void jsonParse() {
        String url = "https://extendsclass.com/api/json-storage/bin/edefdce";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("FoodTruck");
                    Log.i("ITEM", "JSON = " + jsonArray);
                    //trucks = new List<Truck>();
                    for(int i = 0 ; i < jsonArray.length() - 1; i++){
                        JSONObject foodtruck = jsonArray.getJSONObject(i);
                        Log.i("RESULT", "FT : " + foodtruck);
                        Log.i("RESULT", "length : " + jsonArray.length());
                        trucks.add(new Truck(
                                foodtruck.getString("imageUrl"),
                                foodtruck.getString("name"),
                                foodtruck.getString("location"),
                                foodtruck.getString("Time"),
                                foodtruck.getString("phoneNumber"),
                                foodtruck.getString("link")
                                ));
                        Log.i("RESULT", "trucks : " + trucks);
                    }


                } catch (JSONException e) {
                    //Log.i("RESULT", "TEST SA BUG");
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("RESULT", "TEST SA BUG");
                error.printStackTrace();

            }
        });

        //Log.i("RESULT", "request :  " + request);
        mQueue.add(request);

    }
}
