package com.example.avocado1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

 public class LoginPage extends AppCompatActivity {


    TextView signUp;
    Button home;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        signUp= findViewById(R.id.signUpId);
        home=findViewById(R.id.homepageId);


       signUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent myIntent = new Intent(view.getContext(), RegisterPage.class);
               startActivityForResult(myIntent, 0);
           }
       });
       home.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent myIntent = new Intent(view.getContext(), HomePage.class);
               startActivityForResult(myIntent, 0);
           }
       });






            }
        }



