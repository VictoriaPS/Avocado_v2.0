package com.example.avocado1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "ViewDatabase";
    private Toolbar toolbar;
    private TextView helloUser;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private TextView emailNav;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();


        toolbar = findViewById(R.id.toolbarId);
        emailNav= findViewById(R.id.emailNavBarId);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = findViewById(R.id.drawerLayoutId);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navViewId);
        navigationView.setNavigationItemSelectedListener(this);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();




    }
    @Override
    public void onStart() {
        super.onStart();
        checkAuth();
//        mAuth.addAuthStateListener(mAuthListener);
    }


    private void checkAuth() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(HomePage.this, "Successfully signed in with: " + user.getEmail(),Toast.LENGTH_LONG).show();

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Toast.makeText(HomePage.this, "Successfully signed out " + user.getEmail(),Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    private void logout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent= new Intent(getApplicationContext(),LoginPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_homeId:
                Intent HomeIntent = new Intent(this, HomePage.class);
                startActivity(HomeIntent);
                return true;



            case R.id.action_accountId:
                Intent AccountIntent = new Intent(this, AccountPage.class);
                startActivity(AccountIntent);
                return true;

            case R.id.action_notificationId:
                Toast.makeText(this, "notifications selected", Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_genresId:
                Toast.makeText(this, "genres selected", Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_moviesId:
                Toast.makeText(this, "movies selected", Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_tvShowsId:
                Toast.makeText(this, "tvShows selected", Toast.LENGTH_LONG).show();
                return true;


            case R.id.action_signOutId:
                Intent signOutIntent = new Intent(this, LoginPage.class);
                startActivity(signOutIntent);
                return true;


            default:
                return super.onOptionsItemSelected(item);

        }


    }



    @Override
    public void onBackPressed() {
     /*   DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
       */ }

    public boolean onNavigationItemSelected(MenuItem item) {

     return true;

    }
}
