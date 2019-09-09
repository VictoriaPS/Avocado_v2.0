package com.example.avocado1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

import java.util.Arrays;
import java.util.List;
import java.util.prefs.Preferences;

public class RegisterPage extends AppCompatActivity {

    EditText username;
    EditText email;
    EditText password;
    EditText error;
    Button submitBtn;
    User user;
    FirebaseDatabase databaseRef;
    private DatabaseReference userRef;
    ProgressBar progressBar;
    TextView LoginBtn;
    private FirebaseAuth mAuth;
    private static final String TAG = RegisterPage.class.getName();

    private void AddUserToDb(){
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String UserName = username.getText().toString().trim();



                if(!TextUtils.isEmpty(Email)&& !TextUtils.isEmpty(Password)&& !TextUtils.isEmpty(UserName)){

                    if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                        error.setError("Please enter a valid Email Address");
                        error.requestFocus();
                        return;
                    }

                    if(Password.length()<6){
                        error.setError("Minimum length of password should be 6");
                        error.requestFocus();
                        return;
                    }





                    String id=userRef.push().getKey();
                    //example
                    List<String> Preferences= Arrays.asList("action", "");
                    List<String> FavMovies= Arrays.asList("Avengers:Endgame", "Terminator");
                    List<String> FavTVshows= Arrays.asList("Breaking Bad", "Black Mirror", "Games of Thrones");

                    User user= new User(id,Email,Password,UserName, Preferences, FavMovies, FavTVshows);


                    userRef.child(id);
                    userRef.child(id).setValue(user);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RegisterPage.this, "user added to db",Toast.LENGTH_LONG).show();




                }
                else{
                    error.setError("Please enter Email and Password!");
                    error.requestFocus();
                    return;                }




           }
    private void UserProfile(){
        FirebaseUser user= mAuth.getCurrentUser();
        if (user!=null)
        {
            UserProfileChangeRequest ProfileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(username.getText().toString().trim()).build();

            user.updateProfile(ProfileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(), "user profile updated",Toast.LENGTH_LONG).show();
                }
            });
        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);


        email = findViewById(R.id.emailRegId);
        password = findViewById(R.id.passRegId);
        username = findViewById(R.id.usernameId);
        error = findViewById(R.id.errorId);
        progressBar = findViewById(R.id.progressBarId);
        LoginBtn = findViewById(R.id.LoginId);
        user = new User();
        //authBtn= findViewById(R.id.authId);
        submitBtn = findViewById(R.id.submitId);


        databaseRef = FirebaseDatabase.getInstance();
        userRef = databaseRef.getReference("Users");
        mAuth = FirebaseAuth.getInstance();







        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), LoginPage.class);
                startActivityForResult(myIntent, 0);
            }
        });


        //  submitBtn.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View view) {
        //         progressBar.setVisibility(View.VISIBLE);
        //        AddUserToDb();



        submitBtn.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                           AddUserToDb();

                                             String Email2 = email.getText().toString().trim();
                                             String Password2 = password.getText().toString().trim();
                                             String userName = username.getText().toString().trim();
                                             //Toast.makeText(getApplicationContext(), "press is successful",Toast.LENGTH_LONG).show();


                                             mAuth.createUserWithEmailAndPassword(Email2, Password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                 @Override
                                                 public void onComplete(@NonNull Task<AuthResult> task) {
                                                     if (task.isSuccessful()) {
                                                         Log.d(TAG, "createUserWithEmail:success");

                                                         Toast.makeText(RegisterPage.this, "User auth was created!", Toast.LENGTH_LONG).show();
                                                         UserProfile();



                                                     }
                                                     if (!task.isSuccessful()) {
                                                         Toast.makeText(RegisterPage.this, "User Creation Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                         //Toast.makeText(RegisterPage.this, "not successfull",Toast.LENGTH_LONG).show();
                                                         //Log.w(TAG, "failier", task.getException());

                                                         //Toast.makeText(RegisterPage.this, "not working",Toast.LENGTH_LONG).show();
                                                         // FirebaseAuthException e= (FirebaseAuthException)task.getException();
                                                         // Log.d(TAG, "createUserWithEmail:failure", e);
                                                         //  Toast.makeText(getApplicationContext(), "Authentication failed."+e.getMessage(),
                                                         //     Toast.LENGTH_SHORT).show();
                                                     }

                                                 }

                                             });

                                         }
                                     }
        );


    }


}



