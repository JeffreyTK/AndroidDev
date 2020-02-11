package com.example.newsapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import android.util.Log;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.ArrayList;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    String News_Url;
    ArrayList data_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void search_option(View view){
        TextView user_input = findViewById(R.id.Query_News);
        CalendarView date = findViewById(R.id.calendarView);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dates = sdf.format(new Date(date.getDate()));
        String user_text = user_input.getText().toString();
        Intent intent = new Intent(this, Titles.class);
        intent.setType("Text/Plain");
        intent.putExtra("user_text", user_text);
        intent.putExtra("date", dates);
        startActivity(intent);
    }

}
