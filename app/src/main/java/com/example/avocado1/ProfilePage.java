package com.example.avocado1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilePage extends AppCompatActivity {

    TextView User;
    Button Btn;
    TextView Email;
    DatabaseReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);



        User= findViewById(R.id.enteruserId);
        Email= findViewById(R.id.showemailId);
        Btn= findViewById(R.id.seeemailId);





        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String user= User.getText().toString().trim();
                reff= FirebaseDatabase.getInstance().getReference("Users").child(user);



                 reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        User user= dataSnapshot.getValue(User.class);
                        Email.setText(user.getEmail());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(ProfilePage.this, "failed to read"+ databaseError.toException(),Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });

    }
}
