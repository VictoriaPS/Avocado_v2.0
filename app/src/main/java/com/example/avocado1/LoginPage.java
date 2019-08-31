package com.example.avocado1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;

 public class LoginPage extends AppCompatActivity {


    TextView signUp;
    Button home;
     private FirebaseAuth mAuth;
     EditText email;
     EditText password;
     Button loginBtn;
     ProgressBar progressBar;

     private static final String TAG= LoginPage.class.getName();






     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        signUp= findViewById(R.id.signUpId);
       // home=findViewById(R.id.homepageId);
        mAuth= FirebaseAuth.getInstance();
         email = findViewById(R.id.emailId);
         password = findViewById(R.id.passId);
         loginBtn= findViewById(R.id.loginId);
         progressBar= findViewById(R.id.progressBarId);
         final Intent HomeIntent = new Intent(LoginPage.this, HomePage.class);





         signUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent myIntent = new Intent(view.getContext(), RegisterPage.class);
               startActivityForResult(myIntent, 0);
           }
       });

         loginBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 progressBar.setVisibility(View.VISIBLE);


                 final String Email = email.getText().toString().trim();
                 String Password= password.getText().toString().trim();
                 mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {                @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()){
                             Log.d(TAG, "success");
                             FirebaseUser user = mAuth.getCurrentUser();
                             String userEmail,userId, userName;

                                 userEmail=user.getEmail();
                                 userName= user.getDisplayName();
                                 userId= user.getUid();







                             HomeIntent.putExtra("Current user", userEmail);
                            // Toast.makeText(LoginPage.this,"hello"+userEmail,Toast.LENGTH_LONG).show();
                             Log.d(TAG, "createUserWithEmail:"+userEmail);

                             startActivityForResult(HomeIntent, 0);
                             progressBar.setVisibility(View.GONE);



                         }
                         else {
                             Log.w(TAG, "signInWithEmail:failure", task.getException());
                             Toast.makeText(LoginPage.this, "Authentication failed."+ task.getException().getMessage(),
                                     Toast.LENGTH_SHORT).show();

                         }

                     }
                 });







             }
         });





            }
        }



