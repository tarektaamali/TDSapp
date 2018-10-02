package com.mpdam.info.tdsapp.Activity.Demandeur;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Activity.SplashActivity;
import com.mpdam.info.tdsapp.Activity.loginActivity;
import com.mpdam.info.tdsapp.Model.Token;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main4Activity extends AppCompatActivity {
    Button btn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String PREF_NAME = "prefs";
    public static final String KEY_REMEMBER = "remember";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASS = "password";
    public static final String KEY_TOKEN= "token";
    private static final String KEY_USER= "user";
    private static final String KAY_EMAIL= "email";
    public static String token;
    public static APIService mAPIService;
    boolean x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        btn=(Button)findViewById(R.id.button6);
        sharedPreferences = getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        token =sharedPreferences.getString(KEY_TOKEN,"");
        mAPIService = ApiUtils.getAPIService();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main4Activity.this,TrouverActivity.class);
                startActivity(i);



            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

        }
        return super.onKeyDown(keyCode, event);
    }

    public void Voir_mon_projet(View view) {
        Intent i=new Intent(Main4Activity.this,MonProjetActivity.class);
        startActivity(i);
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
                Intent i=new Intent(Main4Activity.this,loginActivity.class);
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

}
