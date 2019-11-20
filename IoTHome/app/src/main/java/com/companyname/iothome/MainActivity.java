package com.companyname.iothome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView fan_btn,light_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void toFan(View view) {

        startActivity(new Intent(MainActivity.this,Fan.class));
    }
    public void toLightRGB(View view) {
        startActivity(new Intent(MainActivity.this,LightRGBActivity.class));
    }
}
