package com.mpdam.info.tdsapp.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Model.User;
import com.mpdam.info.tdsapp.Model.UserResponse;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.firebase.Constants;
import com.mpdam.info.tdsapp.internet.InternetConnection;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity implements TextWatcher, CompoundButton.OnCheckedChangeListener {
    private EditText mPasswordEdit;
    private View mProgressView;
    private View mLoginFormView;
    private EditText mEmailEdit;
    private CheckBox rem_userpass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private APIService mAPIService;
    private static final String PREF_NAME = "prefs";
    SharedPreferences sharedPreferences1;
    SharedPreferences.Editor editor1;
    private static final String PREF_NAME1 = "prefs_token";
    private static final String KEY_FCM = "devices_token";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    private static final String KEY_TOKEN= "token";
    private static final String TAG ="" ;
    String device_token;
    private   String token="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailEdit=(EditText)findViewById(R.id.email);
        mPasswordEdit=(EditText)findViewById(R.id.password);
        sharedPreferences1 = getSharedPreferences(PREF_NAME1, Context.MODE_PRIVATE);
        sharedPreferences = getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor1 = sharedPreferences1.edit();
        editor = sharedPreferences.edit();
        fire();
        rem_userpass = (CheckBox)findViewById(R.id.checkBox);
        if(sharedPreferences.getBoolean(KEY_REMEMBER, false))
            rem_userpass.setChecked(true);
        else
            rem_userpass.setChecked(false);
        mEmailEdit.setText(sharedPreferences.getString(KEY_USERNAME,""));
        mPasswordEdit.setText(sharedPreferences.getString(KEY_PASS,""));
       Toast.makeText(getApplicationContext(),sharedPreferences.getString(KEY_TOKEN,""),Toast.LENGTH_SHORT).show();
        mEmailEdit.addTextChangedListener(this);
        mPasswordEdit.addTextChangedListener(this);
        rem_userpass.setOnCheckedChangeListener(this);
        mAPIService = ApiUtils.getAPIService();
    }

    private void fire() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }
    }

    public void login(View view) {

        String email = mEmailEdit.getText().toString();
        String password = mPasswordEdit.getText().toString();
      /* if (!InternetConnection.checkConnection(getApplicationContext())) {
            Toast.makeText(getApplicationContext(),"string_internet_connection_not_available", Toast.LENGTH_LONG).show();

        };*/
            if(TextUtils.isEmpty(email)){
                mEmailEdit.setError(getString(R.string.error_field_required));
            }
            else if(!isEmailValid(email)){
            mEmailEdit.setError(getString(R.string.error_invalid_email));
            }
            else if(TextUtils.isEmpty(password)&&(!isPasswordValid(password))){
                mPasswordEdit.setError(getString(R.string.error_invalid_password));
            }
            else {
                connexion(email,password);



            }
    }

    private void connexion(String email, String password) {
        sharedPreferences1 = getSharedPreferences(PREF_NAME1, Context.MODE_PRIVATE);
        editor1 = sharedPreferences1.edit();
        device_token=sharedPreferences1.getString(KEY_FCM,"");
        if(device_token == null){
            device_token="";
        }
        mAPIService.login(email, password,device_token).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if(response.isSuccessful()) {

                    if(response.body().getMessage().equals("User signin"))
                    Toast.makeText(getApplicationContext(),response.body().getToken().toString(),Toast.LENGTH_SHORT).show();
                     token=response.body().getToken().toString();
                    managePrefs();
                    Intent intent = new Intent(loginActivity.this, MainActivity.class);
                    managePrefs();
                    intent.putExtra("token",response.body().getToken().toString());
                    intent.putExtra("user",response.body().getUser().getEmail().toString());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                    finish();
                  /*  if(response.body().getUserId()==0){
                        Toast.makeText(getApplicationContext(),"succes        s",Toast.LENGTH_SHORT).show();
						/*
						  Intent i =new Intent(MainActivity.this,Main2Activity.class);
                        i.putExtra("id",response.body().getResults().toString());
                        startActivity(i);


                    }
                    else{
                        Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                    }*/
                }
                else
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e(TAG, "failed.");
            }
        });


    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 6;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

   @Override
   public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      //  managePrefs();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
       // managePrefs();
    }

    private void managePrefs() {
        if(rem_userpass.isChecked()){
            editor.putString(KEY_USERNAME, mEmailEdit.getText().toString().trim());
            editor.putString(KEY_PASS, mPasswordEdit.getText().toString().trim());
            editor.putString(KEY_TOKEN,token);
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
        }else{
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);//editor.putString(KEY_PASS,"");
            editor.remove(KEY_USERNAME);//editor.putString(KEY_USERNAME, "");
            editor.putString(KEY_TOKEN,token);
            editor.apply();
        }
    }
}
