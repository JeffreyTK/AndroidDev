package com.example.newsapi;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Titles extends AppCompatActivity {
    ArrayList<String> titles_List = new ArrayList();
    ArrayList<JSONObject> articles_List = new ArrayList();
    String searched_input;
    String dates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titles);
        Intent intent = getIntent();
        final ListView titles_menu = findViewById(R.id.titles_layout);
        searched_input = intent.getStringExtra("user_text");
        dates = intent.getStringExtra("date");
        Search(titles_menu);
        final Intent title_details = new Intent(this, title_details.class);
        titles_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Json data", articles_List.get(position).toString());
                title_details.putExtra("date", dates);
                title_details.putExtra("data", articles_List.get(position).toString());
                title_details.putExtra("searched_input", searched_input);
                startActivity(title_details);
            }
        });

    }

    public void Search(final ListView titles){
        String url = "https://newsapi.org/v2/everything?q=" + searched_input +"&from=" + dates +
                "&sortBy=publishedAt&apiKey=ec155475d2044037bd335ab679105aee";
        RequestQueue request = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray titles_result = response.optJSONArray("articles");
                        for (int i = 0; i < titles_result.length(); i++) {
                            try {
                                titles_List.add(titles_result.getJSONObject(i).getString("title"));
                                articles_List.add(titles_result.getJSONObject(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Titles.super.getApplicationContext(),
                                android.R.layout.simple_list_item_1, titles_List);
                        titles.setAdapter(arrayAdapter);
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.e("error occured", "errors have been detected");
                    }
        });
        request.add(jsonObjectRequest);
    }

}
