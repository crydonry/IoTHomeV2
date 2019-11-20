package com.companyname.iothome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText emailtxt,passtxt;
    Button register_btn;
    ProgressDialog progressDialog;

    FirebaseAuth firebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseauth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        emailtxt = findViewById(R.id.email_txt);
        passtxt =  findViewById(R.id.password_txt);
        register_btn = findViewById(R.id.register_btn);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRegister();
            }
        });
    }

    private void UserRegister() {
        String email,pass;
        email = emailtxt.getText().toString();
        pass = emailtxt.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(Register.this,"Enter Email",Toast.LENGTH_SHORT).show();
            return;
        }if(TextUtils.isEmpty(pass)){
            Toast.makeText(Register.this,"Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registion .....");
        progressDialog.show();

        firebaseauth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register.this,"Registion Successful",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Register.this,"Registion error",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void toLogin(View view) {
        startActivity(new Intent(Register.this,Login.class));
    }
}
