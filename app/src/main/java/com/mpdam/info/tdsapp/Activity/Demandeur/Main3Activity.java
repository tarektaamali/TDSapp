package com.mpdam.info.tdsapp.Activity.Demandeur;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Activity.loginActivity;
import com.mpdam.info.tdsapp.Model.Token;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Main3Activity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    public ViewPager mViewPager;
    public static final String PREF_NAME = "prefs";
    public static final String KEY_REMEMBER = "remember";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASS = "password";
    public static final String KEY_TOKEN= "token";
    private static final String KEY_USER= "user";
    private static final String KAY_EMAIL= "email";
    public static String token;
    SharedPreferences sharedPreferences;
    static APIService mAPIService;
    SharedPreferences.Editor editor;
    boolean x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mAPIService = ApiUtils.getAPIService();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        sharedPreferences = getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        token =sharedPreferences.getString(KEY_TOKEN,"");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent i=new Intent(Main3Activity.this,TrouverActivity.class);
                startActivity(i);
              PlaceholderFragment.newInstance(2);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main3, menu);
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
            Toast.makeText(this, "parametre ", Toast.LENGTH_SHORT).show();
          }
          else {
            log(token);

        }
        return super.onOptionsItemSelected(item);
    }

    private void log(String token) {
        Toast.makeText(this,token, Toast.LENGTH_SHORT).show();
       if(!logout(token)){
            Toast.makeText(this, "deconnexion ", Toast.LENGTH_SHORT).show();
        //   editor.remove(KEY_TOKEN);
          //  editor.apply();
           Intent i=new Intent(Main3Activity.this,loginActivity.class);
            startActivity(i);
           finish();
        }else
           Toast.makeText(this, "error ", Toast.LENGTH_SHORT).show();
    }
    public  boolean logout(String token) {

        mAPIService.logout(token).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if(response.isSuccessful()){
                   /**/
                     x = true;
                }

            }

            @Override
            public void onFailure(Call<Token> call, Throwable t)
            {
                x=false;
            }
        });
        return x;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main3, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
           // return PlaceholderFragment.newInstance(position + 1);
           switch (position){
               case 2:
                   Tab1 a=new Tab1();
                   return a;
               case 1:
                   Tab2 b=new Tab2();
                   return b;
               case 0:
                   Tab3 c=new Tab3();
                   return c;
               case 3:
                   Tab2 w=new Tab2();
                   return w;
               default:
                   return null;
           }



        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Acceuil";
                case 1:
                    return "Mes Projet ";
                case 2:
                    return "Compte";
            }
            return null;
        }
    }
}
