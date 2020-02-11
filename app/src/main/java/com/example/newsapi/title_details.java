package com.example.newsapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class title_details extends AppCompatActivity {
    String date;
    String user_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_details);
        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        user_input = intent.getStringExtra("searched_input");
        TextView url = findViewById(R.id.url);
        TextView title = findViewById(R.id.title);
        TextView published = findViewById(R.id.published);
        TextView description = findViewById(R.id.descrption);
        TextView details = findViewById(R.id.details);
        TextView source = findViewById(R.id.source);
        ImageView imageurl = findViewById(R.id.Imageurl);

        try{
            JSONObject article = new JSONObject(intent.getStringExtra("data"));
            details.setText(article.getString("content"));
            description.setText(article.getString("description"));
            url.setText(article.getString("url"));
            title.setText(article.getString("title"));
            published.setText(article.getString("publishedAt"));
            source.setText(article.getJSONObject("source").getString("name"));
            String imageurlstring = article.getString(("urlToImage"));
            Picasso.get().load(imageurlstring).into(imageurl);
        }catch(JSONException e){
            Log.d("not good", "bad things are happening");
        }
    }
}
