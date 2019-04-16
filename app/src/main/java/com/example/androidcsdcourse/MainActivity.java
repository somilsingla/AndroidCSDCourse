package com.example.androidcsdcourse;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.androidcsdcourse.Services.NotificationServices;
import com.example.androidcsdcourse.fragments.BlankFragment;
import com.example.androidcsdcourse.listeners.FragmentButtonClickListener;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FragmentButtonClickListener {
    Activity activity;
    Button button2;
    Button button3;
    String SERVER_URL = "https://jsonplaceholder.typicode.com/posts/1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;

        setContentView(R.layout.activity_main);
        BlankFragment fragment = new BlankFragment();

        //        Get the references
        ImageView facebook1, twitter, facebook2;
        facebook1 = findViewById(R.id.facebook1);
        facebook2 = findViewById(R.id.facebook2);
        twitter = findViewById(R.id.twitter);

//        Set onclick listeners
        facebook1.setOnClickListener(this);
        twitter.setOnClickListener(this);
        facebook2.setOnClickListener(this);

//        Fragment replacement
        getSupportFragmentManager().beginTransaction().add(R.id.fragment, fragment, "Tag").commit();

//        Buttons
        Button start = findViewById(R.id.start_service);
        final Button stop = findViewById(R.id.stop_service);

//        On click listeners
        start.setOnClickListener(this);
        stop.setOnClickListener(this);

        start.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(activity,"Long Clicked",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        OkHttpClient okHttpClient = new OkHttpClient() .newBuilder()
                .addNetworkInterceptor(new StethoInterceptor())
                .connectTimeout(2000, TimeUnit.MICROSECONDS).build();

        AndroidNetworking.initialize(getApplicationContext(),okHttpClient);


        fetchData();



//        Different Gestures
        stop.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                stop.setText("Long Clicked");
                return true;
            }
        });

    }

    @Override
    public void onClick(View v) {
     //   Toast.makeText(activity, "Button Clicked", Toast.LENGTH_LONG).show();

        //        Menu Item Switch
        switch (v.getId()){
            case R.id.facebook1:
                Toast.makeText(activity, "Facebook1 called", Toast.LENGTH_SHORT).show();
                break;
            case R.id.facebook2:
                Toast.makeText(activity, "Facebook2 called", Toast.LENGTH_SHORT).show();
                break;
            case R.id.twitter:
                Toast.makeText(activity, "Twitter called", Toast.LENGTH_SHORT).show();
                break;
        }

        switch (v.getId()){
            case R.id.start_service:
                startService(new Intent(this, NotificationServices.class));
                break;
            case R.id.stop_service:
                stopService(new Intent(this, NotificationServices.class));
                break;
        }
    }

    @Override
    public void onButtonClicked(String message) {
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show();
    }

    void fetchData(){
        JSONObject post_body = new JSONObject();
        try {
            post_body.put("id",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.get(SERVER_URL).build().getAsJSONObject(new JSONObjectRequestListener() {

            @Override
            public void onResponse(JSONObject response  /*JSONArray resonse*/) {
                JSONArray array = null;
                for (int i =0 ; i<array.length();i++){
                    try {
                        JSONObject object = array.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    String title = response.getString("title");
                    Toast.makeText(activity,title,Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("Response", response.toString());
            }

            @Override
            public void onError(ANError anError) {
                Log.e("Error in Networking",anError.toString());
                Toast.makeText(activity,"Something Went Wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
