package com.mpdam.info.tdsapp.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Model.Token;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Info on 4/20/2018.
 */

public class SplashActivity extends AppCompatActivity{
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private APIService mAPIService;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    private static final String KEY_TOKEN= "token";
    String token;
    String user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        token =sharedPreferences.getString(KEY_TOKEN,"");
        user =sharedPreferences.getString(KEY_USERNAME,"");

        Toast.makeText(getApplicationContext(),token,Toast.LENGTH_SHORT).show();
        mAPIService = ApiUtils.getAPIService();
        token=null;
      if(token == null){
          Intent i =new Intent(SplashActivity.this,loginActivity.class);
          startActivity(i);
      }
      else {
          verifyToken(token);
      }

    }

    private void verifyToken( String token) {
        final String  token1=token;
   mAPIService.verify(token).enqueue(new Callback<Token>() {
       @Override
       public void onResponse(Call<Token> call, Response<Token> response) {

           if(response.isSuccessful()){
               Toast.makeText(SplashActivity.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
               if(response.body().getSuccess()==true){
                   Intent i =new Intent(SplashActivity.this,MainActivity.class);
                   i.putExtra("token",token1);
                   i.putExtra("user",user);
                   startActivity(i);

               }

           }
           else {
               Intent i =new Intent(SplashActivity.this,loginActivity.class);
               startActivity(i);
           }
       }

       @Override
       public void onFailure(Call<Token> call, Throwable t) {
           Intent i =new Intent(SplashActivity.this,loginActivity.class);
           startActivity(i);
       }
   });
    }
}
