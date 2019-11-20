package com.companyname.iothome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Fan extends AppCompatActivity {

    Switch power,num1,num2,num3;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference powerOnOff,lowpower,midpower,highpower;
    Button changeColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan);


        power = findViewById(R.id.power_sw);
        num1 = findViewById(R.id.number1_sw);
        num2 = findViewById(R.id.number2_sw);
        num3 = findViewById(R.id.number3_sw);
        changeColor = findViewById(R.id.changeColor_btn);

        firebaseDatabase = FirebaseDatabase.getInstance();
        powerOnOff=firebaseDatabase.getReference("IoT/Fan/power");
        lowpower=firebaseDatabase.getReference("IoT/Fan/lowpower");
        midpower=firebaseDatabase.getReference("IoT/Fan/midpower");
        highpower=firebaseDatabase.getReference("IoT/Fan/highpower");

        changeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pow, low, mid, high;
                if(power.isChecked()){

                    powerOnOff.setValue(1);
                    pow = power.getTextOn().toString();
                }else{
                    powerOnOff.setValue(0);
                    pow = power.getTextOff().toString();
                }
                if(num1.isChecked()){
                    lowpower.setValue(1);
                    low = num1.getTextOn().toString();
                }else{
                    lowpower.setValue(0);
                    low = num1.getTextOff().toString();
                }
                if(num2.isChecked()){
                    midpower.setValue(1);
                    mid = num2.getTextOn().toString();
                }else{
                    midpower.setValue(0);
                    mid = num2.getTextOff().toString();
                }
                if(num3.isChecked()){
                    highpower.setValue(1);
                    high = num3.getTextOn().toString();

                }else{
                    highpower.setValue(0);
                    high = num3.getTextOff().toString();
                }
                Toast.makeText(Fan.this,"Power - "+pow + " \n" + "Low Power - " + low + " \n" +"Mid Power - "+ mid + " \n" +"High Power - "+ high,Toast.LENGTH_SHORT).show();
            }
        });


    }
}
