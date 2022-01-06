package com.example.fetchtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.fetchtest.model.ListData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
* MainActivity uses Volley to create facilitate the JSON request
* */

public class MainActivity extends AppCompatActivity {

    private List<ListData> mDataList;
    private final String URL = "https://fetch-hiring.s3.amazonaws.com/hiring.json";

    RecyclerView mRecyclerView;
    DataAdapter mDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataList = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.data_list_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        mDataAdapter = new DataAdapter(mDataList);
        mRecyclerView.setAdapter(mDataAdapter);

        startJSONRequest();
    }

    private void startJSONRequest(){
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        // Request a JSON response from the provided URL.
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                parseListData(response);
                sortList();
                mDataAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CharSequence message = "JSON request failed";
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
                toast.show();

            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }

    private void parseListData (JSONArray jsonArray){
        try {
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject jsonDataItem = jsonArray.getJSONObject(i);
                int jsonID = jsonDataItem.getInt("id");
                int jsonListID = jsonDataItem.getInt("listId");
                String jsonName = jsonDataItem.getString("name");
                if(!jsonName.equals("null") && !(jsonName.isEmpty())){
                    mDataList.add(new ListData(jsonID, jsonListID, jsonName));
                }
            }
        }catch (JSONException error){
            error.printStackTrace();
        }
    }

    private void sortList(){
        Collections.sort(mDataList, ListData.listIDComparator.thenComparing(ListData.dataNameComparator));
    }
}

