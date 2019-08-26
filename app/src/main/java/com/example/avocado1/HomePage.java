package com.example.avocado1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;




public class test extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        toolbar= findViewById(R.id.toolBarId);
        String currentUser = getIntent().getStringExtra("Current user");

        setSupportActionBar(toolbar);
    }
}
