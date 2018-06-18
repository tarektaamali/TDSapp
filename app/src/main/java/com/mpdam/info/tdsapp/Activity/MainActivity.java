package com.mpdam.info.tdsapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
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

import com.mpdam.info.tdsapp.Model.Token;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView txt;
    private Handler mHandler;
    private View navHeader;
    private NavigationView navigationView;
    private String token;
    public static APIService mAPIService;
    static String choix="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAPIService = ApiUtils.getAPIService();
        mHandler = new Handler();
        Intent intent=getIntent();
        Bundle b =intent.getExtras();
       String username=b.getString("user");
       token=b.getString("token");
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navHeader = navigationView.getHeaderView(0);
        //txtName = (TextView) navHeader.findViewById(R.id.name);
        txt=(TextView)navHeader.findViewById(R.id.textuser);
         txt.setText(username);

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
        // Inflate the menu; this adds items to the action bar if it is present.
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
            return true;
        }

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


    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;
        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_camera:
                fragment = new planningFragment();
                break;
            case R.id.nav_projet:
                fragment = new ProjetFragment();
                choix="projet";
                break;
            case R.id.nav_projet_v:
                fragment = new ProjetFragment();
                choix="non_valide";
                break;
            case R.id.nav_projet_en:
                fragment = new ProjetFragment();
                choix="en_realisation";
                break;
            case R.id.nav_gallery:
                fragment = new employesFragment();
                break;
            case R.id.nav_manage:
                fragment = new MaterielFragment();
                break;
            case R.id.nav_slideshow:
                fragment = new RapportFragment();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame  , fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
    private void logout(String token) {
        mAPIService.logout(token).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),response.body().getMessage().toString(),Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(MainActivity.this,loginActivity.class);
                    startActivity(i);
                }

            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });

    }

}
