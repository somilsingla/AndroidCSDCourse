package com.example.androidcsdcourse;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActivityChooserView;
import android.text.TextPaint;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;

public class menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout layout;
    NavigationView navigationView;
    Activity context;
    String number = "9466886600";
    static int REQUEST_COARSE_LOCATION = 500;
    static int REQUEST_CALL = 100;
    static int REQUEST_FINE_LOCATION = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_menu);
        ImageView icon = findViewById(R.id.ham);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.openDrawer(GravityCompat.START);
            }
        });
        layout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        final EditText email = findViewById(R.id.email);
        final EditText subject = findViewById(R.id.subject);
        final EditText body = findViewById(R.id.body);
        Button emailbut = findViewById(R.id.emailbut);

        Button call = findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(context,CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                    //Call Another Phone
                    Intent call = new Intent(Intent.ACTION_CALL);
                    Uri number = Uri.parse("tel:9466886600");
                    call.setData(number);
                    startActivity(call);

                }
                else{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                       requestPermissions(new String[]{CALL_PHONE},REQUEST_CALL);
                    }

                }

            }
        });

        emailbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "mailto:" + Uri.encode(email.getText().toString()) +
                        "?subject=" + Uri.encode(subject.getText().toString()) +
                        "&body=" + Uri.encode(body.getText().toString());
                Uri send_data = Uri.parse(s);
                Intent send = new Intent(Intent.ACTION_SENDTO);
                send.setData(send_data);
                startActivity(Intent.createChooser(send,"Send email using:"));

            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        layout.closeDrawer(GravityCompat.START);
        switch(menuItem.getItemId()){
            case R.id.inbox:
                Toast.makeText(context,"Inbox",Toast.LENGTH_SHORT).show();
                break;
            case R.id.sent:
                Toast.makeText(context,"Sent",Toast.LENGTH_SHORT).show();
                break;
            case R.id.search:
                Toast.makeText(context,"Search",Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                // Call Another Phone
                Intent call = new Intent(Intent.ACTION_CALL);
                Uri number = Uri.parse("tel:9466886600");
                call.setData(number);

                if(ActivityCompat.checkSelfPermission(context,CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                    //Call Another Phone
                    startActivity(call);
                }

            }
            else {
                Toast.makeText(context,"Permission Denied",Toast.LENGTH_SHORT).show();
            }

        }
    }
}
