package com.mpdam.info.tdsapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mpdam.info.tdsapp.Model.RapportMsg;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RapportScrollingActivity extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private APIService mAPIService;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    private static final String KEY_TOKEN= "token";
    private TextView rapportdesc,projetdesc,clientname;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String token =sharedPreferences.getString(KEY_TOKEN,"");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        rapportdesc=(TextView)findViewById(R.id.rapportcontent);
        img=(ImageView)findViewById(R.id.imageView2);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
        Bundle b =intent.getExtras();
        int id=b.getInt("id");
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getResources().getString(R.string.user_name));
   dynamicToolbarColor();
        toolbarTextAppernce();
        mAPIService = ApiUtils.getAPIService();

          LaodRapport(token,id);
    }

    private void LaodRapport(String token, int id) {
        mAPIService.getRapportDetails(id,token).enqueue(new Callback<RapportMsg>() {
            @Override
            public void onResponse(Call<RapportMsg> call, Response<RapportMsg> response) {
                if(response.isSuccessful()){
                    rapportdesc.setText(response.body().getRapport().getNote().toString());

                  Glide.with(getApplicationContext())
                          .load("http://192.168.42.98:8000/storage/blogimage/"+response.body().getRapport().getImg().trim().toString())
                            .into(img);
                }
            }

            @Override
            public void onFailure(Call<RapportMsg> call, Throwable t) {

            }
        });
    }

    private void dynamicToolbarColor() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.profile_pic);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                collapsingToolbarLayout.setContentScrimColor(getResources().getColor( R.color.colorPrimary));
                collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor( R.color.colorPrimaryDark));
            }
        });
    }


    private void toolbarTextAppernce() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
