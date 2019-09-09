package com.example.avocado1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {


    TextView signUp;
    User user;
    Button home;
     private FirebaseAuth mAuth;
     EditText email;
     EditText password;
     Button loginBtn;
     ProgressBar progressBar;
     private FirebaseDatabase fbdb;
     private DatabaseReference ref;
    DatabaseReference reff;


    private static final String TAG= LoginPage.class.getName();






     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        signUp= findViewById(R.id.signUpId);
       // home=findViewById(R.id.homepageId);
         user= new User();
         email = findViewById(R.id.emailId);
         password = findViewById(R.id.passId);
         loginBtn= findViewById(R.id.loginId);
         progressBar= findViewById(R.id.progressBarId);
         mAuth= FirebaseAuth.getInstance();

         final Intent HomeIntent = new Intent(LoginPage.this, HomePage.class);
         final Intent ChooseGenersIntent = new Intent(LoginPage.this, ChooseGenresPage.class);







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



                 fbdb= FirebaseDatabase.getInstance();
                 ref= fbdb.getReference("Users");






                 mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()){
                             Log.d(TAG, "success");
                             FirebaseUser CurUser = mAuth.getCurrentUser();






                             final String userEmail,userId;


                                 userEmail=CurUser.getEmail();
                                 userId= CurUser.getUid();


                                 reff= FirebaseDatabase.getInstance().getReference("Users");

                                 reff.addValueEventListener(new ValueEventListener() {
                                 @Override
                                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                                    // String name= dataSnapshot.child(userId).child("userName").getValue(String.class);


                                     //Toast.makeText(LoginPage.this,"NewUsername: "+name,Toast.LENGTH_LONG).show();



                                       //  User user = dataSnapshot.getValue(User.class);
                                     // Toast.makeText(LoginPage.this,"username: "+userName,Toast.LENGTH_LONG).show();
                                    // String curId= reff.child(userId).getKey();
                                     //reff.child(curId);
                                    // String userName= reff.child(CurUser()).getKey();



                                     //String userName = dataSnapshot.child("userName").getValue().toString();
                                    // Toast.makeText(LoginPage.this,"username: "+userName,Toast.LENGTH_LONG).show();
                                     //String displayName= CurUser.getDisplayName();



                                     // UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(userName).build();
                                       //  CurUser.updateProfile(profileUpdates);
                                         // Toast.makeText(LoginPage.this,"username: "+displayName,Toast.LENGTH_LONG).show();



                                     }
                                     @Override
                                     public void onCancelled(@NonNull DatabaseError databaseError) {
                                         Toast.makeText(LoginPage.this, "failed to read"+ databaseError.toException(),Toast.LENGTH_SHORT).show();


                                     }
                             });

                            Toast.makeText(LoginPage.this,"hello "+userEmail,Toast.LENGTH_LONG).show();

                             Toast.makeText(LoginPage.this,"id: "+userId,Toast.LENGTH_LONG).show();
                             Toast.makeText(LoginPage.this,"username: "+CurUser.getDisplayName(),Toast.LENGTH_LONG).show();



                             Log.d(TAG, "createUserWithEmail:"+userEmail);

                             HomeIntent.putExtra("CurrentUserName",CurUser.getDisplayName());
                             HomeIntent.putExtra("current email", userEmail);
                            // HomeIntent.putExtra("Current user",userId);



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



