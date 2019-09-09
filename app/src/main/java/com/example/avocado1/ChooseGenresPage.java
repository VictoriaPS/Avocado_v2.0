package com.example.avocado1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChooseGenresPage extends AppCompatActivity {

    private List<String> selectedGenres;
    private CheckBox check_action;
    private CheckBox check_drama;
    private CheckBox check_comedy;
    private CheckBox check_horror;
    private CheckBox check_scifi;
    private Button chooseBtn;
    private User user;
    private TextView goToHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_genres_page);

        chooseBtn= findViewById(R.id.chooseBtnId);
        goToHome= findViewById(R.id.goToHomeId);

        String currentUser = getIntent().getStringExtra("Current user");
        String currentEmail=getIntent().getStringExtra("current email");
        final Intent HomeIntent = new Intent(ChooseGenresPage.this, HomePage.class);


        Toast.makeText(ChooseGenresPage.this,"Uid:"+currentUser,Toast.LENGTH_LONG).show();
        Toast.makeText(ChooseGenresPage.this,"User Email:"+currentEmail,Toast.LENGTH_LONG).show();






        chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedGenres= new ArrayList<>();
                check_action = findViewById(R.id.check_action);
                check_comedy = findViewById(R.id.check_comedy);
                check_drama = findViewById(R.id.check_drama);
                check_horror = findViewById(R.id.check_horror);
                check_scifi = findViewById(R.id.check_scifi);

                if (check_action.isChecked()) {
                    selectedGenres.add("Action");

                }
                if (check_comedy.isChecked()){
                    selectedGenres.add("Comedy");
                }
                if (check_drama.isChecked()){
                    selectedGenres.add("Drama");
                }
                if (check_horror.isChecked()){
                    selectedGenres.add("Horror");
                }
                if (check_scifi.isChecked()){
                    selectedGenres.add("Sci-Fi");
                }



                Toast.makeText(ChooseGenresPage.this, selectedGenres.toString(),Toast.LENGTH_LONG).show();

            }
        });

        goToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(HomeIntent, 0);


            }
        });
    }



}