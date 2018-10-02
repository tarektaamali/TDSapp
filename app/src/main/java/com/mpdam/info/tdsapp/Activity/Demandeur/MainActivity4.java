package com.mpdam.info.tdsapp.Activity.Demandeur;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Activity.DevisFragment;
import com.mpdam.info.tdsapp.Activity.FeedbackFragment;
import com.mpdam.info.tdsapp.Activity.MainFragment;
import com.mpdam.info.tdsapp.Activity.ProjetCasFragment;
import com.mpdam.info.tdsapp.Activity.ProjetFragment;
import com.mpdam.info.tdsapp.Activity.loginActivity;
import com.mpdam.info.tdsapp.Activity.planningFragment;
import com.mpdam.info.tdsapp.Model.Token;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity4 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView txt,txtName;
    private Handler mHandler;
    private View navHeader;
    private NavigationView navigationView;
    static String choix="";
    public static final String PREF_NAME = "prefs";
    public static final String KEY_REMEMBER = "remember";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASS = "password";
    public static final String KEY_TOKEN= "token";
    private static final String KEY_USER= "user";
    private static final String KAY_EMAIL= "email";
    public static String usermailo;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static APIService mAPIService;
    String token;
    String user;
    boolean x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAPIService = ApiUtils.getAPIService();
        mHandler = new Handler();
      /*  Intent intent=getIntent();
        Bundle b =intent.getExtras();
       String username=b.getString("user");
       token=b.getString("token");*/
        sharedPreferences = getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        token =sharedPreferences.getString(KEY_TOKEN,"");
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.txtn);
        txt=(TextView)navHeader.findViewById(R.id.textuser);
        txtName.setText(sharedPreferences.getString(KAY_EMAIL,""));
         txt.setText(sharedPreferences.getString(KEY_USER,""));

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displaySelectedScreen(R.id.nav_camera);

        if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
             planningFragment details = new planningFragment();
            details.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.content_frame, details).commit();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
          super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i=new Intent(getBaseContext(), Main3Activity.class);
            startActivity(i);        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
      int id = item.getItemId();
        if (id == R.id.nav_logout) {
            Toast.makeText(getApplicationContext(),token,Toast.LENGTH_SHORT).show();
            logout(token);


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        displaySelectedScreen(item.getItemId());
        return true;
    }


    public void  displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;
        //initializing the fragment object which is selected4
        switch (itemId) {
            case R.id.nav_camera:
                fragment = new planningFragment();
                break;
            case R.id.nav_projet:
                fragment = new ProjetFragment();
                choix="Demandes";
                break;
            case R.id.nav_projet_v:
                fragment = new ProjetCasFragment();
                choix="Projet Encours";
                break;
            case R.id.nav_projet_en:
                fragment = new ProjetCasFragment();
                choix="Projet Valide";
                break;
            case R.id.nav_gallery:
                fragment = new ProjetCasFragment();
                choix="Projet Termine";
                break;
            case R.id.nav_manage:
                fragment = new FeedbackFragment();
                break;
            case R.id.nav_slideshow:
                fragment = new DevisFragment();
                break;
            case R.id.nav_share:
                fragment = new MainFragment();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction().addToBackStack(null);
            ft.replace(R.id.content_frame  , fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
    public  void logout(String token) {

        mAPIService.logout(token).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if(response.isSuccessful()){
                    Intent i=new Intent(MainActivity4.this,loginActivity.class);
                    startActivity(i);
                }

            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                  x=false;
            }
        });
    }

}
