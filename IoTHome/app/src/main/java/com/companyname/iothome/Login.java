package com.companyname.iothome;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login  extends AppCompatActivity {
    EditText emailtxt,passtxt;
    Button login_btn,register_btn;
    ProgressDialog progressDialog;

    FirebaseAuth firebaseauth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseauth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        emailtxt =  findViewById(R.id.email_txt);
        passtxt =  findViewById(R.id.password_txt);
        login_btn =  findViewById(R.id.login_btn);
        register_btn =  findViewById(R.id.register_btn);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });



        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signin();
            }
        });

    }

    private void Signin() {
        final String email,pass;
        email = emailtxt.getText().toString();
        pass = passtxt.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(Login.this,"Enter Email",Toast.LENGTH_SHORT).show();
            return;
        }if(TextUtils.isEmpty(pass)){
            Toast.makeText(Login.this,"Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Login");
        progressDialog.show();

        firebaseauth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(Login.this,MainActivity.class));
                    Toast.makeText(Login.this,"Welcome",Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(Login.this,"Login error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
