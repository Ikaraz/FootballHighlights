package com.example.footballhighlights;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemsAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private ItemsAdapter itemsAdapter;
    private ArrayList<Items> itemsArrayList;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        itemsArrayList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        parseJson();
    }

    private void parseJson(){
        String url = "https://www.scorebat.com/video-api/v1/";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for(int i=0;i<response.length();i++) {
                                JSONObject object = response.getJSONObject(i);

                                String gameTitle = object.getString("title");
                                String thumbnailUrl = object.getString("thumbnail");
                                String videoUrl = object.getString("url");

                                JSONObject competitionObj = object.getJSONObject("competition");
                                String competitionName = competitionObj.getString("name");

                                itemsArrayList.add(new Items(thumbnailUrl, gameTitle, videoUrl, competitionName));
                            }

                            itemsAdapter = new ItemsAdapter(MainActivity.this,itemsArrayList);
                            recyclerView.setAdapter(itemsAdapter);
                            itemsAdapter.setOnItemClickListener(MainActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this,WebViewActivity.class);
        Items clickedItem = itemsArrayList.get(position);
        intent.putExtra("url",clickedItem.getVideoUrl());
        startActivity(intent);
    }
}
