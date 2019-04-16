package com.example.androidcsdcourse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.androidcsdcourse.adapters.RecyclerviewCustomAdapter;
import com.example.androidcsdcourse.adapters.SocialAdapter;
import com.example.androidcsdcourse.models.SocialItem;


import java.util.ArrayList;


public class NextActivity extends AppCompatActivity {

    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    ArrayList<SocialItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        items = new ArrayList<>();

        SocialItem twitter = new SocialItem("Twitter", "Social App", "Yolo description", R.drawable.twitter);
        SocialItem facebook = new SocialItem("Facebook", "Social App", "Some description", R.drawable.facebook);

//        SocialItem item =
        items.add(twitter);
        items.add(facebook);


        RecyclerviewCustomAdapter adapter = new RecyclerviewCustomAdapter(this, items);
        recyclerView.setAdapter(adapter);
    }
}
