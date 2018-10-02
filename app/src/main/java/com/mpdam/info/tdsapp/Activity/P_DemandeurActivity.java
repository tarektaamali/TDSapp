package com.mpdam.info.tdsapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mpdam.info.tdsapp.Model.Projetdetails;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class P_DemandeurActivity extends AppCompatActivity {
    TextView tv_titre,tv_description,tv_client,tv_service;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private APIService mAPIService;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    private static final String KEY_TOKEN= "token";
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tv_titre=(TextView)findViewById(R.id.tv_titre_p) ;
        tv_description=(TextView)findViewById(R.id.tv_description_p) ;
        tv_client=(TextView)findViewById(R.id.tv_client_p) ;
        tv_service=(TextView)findViewById(R.id.tv_service_p) ;
        Intent intent=getIntent();
        Bundle b =intent.getExtras();
        int id=b.getInt("id_projet");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Details");
        mAPIService = ApiUtils.getAPIService();
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        token =sharedPreferences.getString(KEY_TOKEN,"");
        loadJson(id,token);
    }

    private void loadJson(int i,String token) {
        mAPIService.getprojetdetails(i,token).enqueue(new Callback<Projetdetails>() {
            @Override
            public void onResponse(Call<Projetdetails> call, Response<Projetdetails> response) {
                if(response.isSuccessful()){
                    tv_titre.setText(response.body().getProjet().getUser().getEmail());
                    tv_description.setText(response.body().getProjet().getDescription());
                }
            }

            @Override
            public void onFailure(Call<Projetdetails> call, Throwable t) {

            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
