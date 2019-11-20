package com.companyname.iothome;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LightRGBActivity extends AppCompatActivity{
    ImageView mImageView;
    TextView mResultTv;
    View mColorView;
    Bitmap bitmap;
    Button changeColor_btn;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference red,blue,green;
    int r,g,b;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_rgb);
        mImageView = findViewById(R.id.colorRGB);
        mResultTv = findViewById(R.id.resultTv);
        mColorView = findViewById(R.id.colorView);


        mImageView.setDrawingCacheEnabled(true);
        mImageView.buildDrawingCache(true);

        firebaseDatabase = FirebaseDatabase.getInstance();
        changeColor_btn = findViewById(R.id.changeColor_btn);


        mImageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    bitmap = mImageView.getDrawingCache();
                    int pixel = bitmap.getPixel((int) event.getX(), (int) event.getY());

                     r = Color.red(pixel);
                     g = Color.green(pixel);
                     b = Color.blue(pixel);


                    mColorView.setBackgroundColor(Color.rgb(r, g, b));
                    mResultTv.setText("RGB: " + r + ", " + g + ", " + b);
                }
                return true;
            }
        });
        changeColor_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeColor();
            }
        });
    }

    private void ChangeColor() {
        red= firebaseDatabase.getReference("IoT/LED/red");
        blue = firebaseDatabase.getReference("IoT/LED/blue");
        green = firebaseDatabase.getReference("IoT/LED/green");

        red.setValue(r);
        blue.setValue(b);
        green.setValue(g);
    }

}
