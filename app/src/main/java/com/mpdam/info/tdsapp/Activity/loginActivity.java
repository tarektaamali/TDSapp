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
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Activity.Demandeur.Main3Activity;
import com.mpdam.info.tdsapp.Activity.Demandeur.Main4Activity;
import com.mpdam.info.tdsapp.Model.UserResponse;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.firebase.Constants;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity implements TextWatcher, CompoundButton.OnCheckedChangeListener {
    private EditText mPasswordEdit;
    private EditText mEmailEdit;
    private CheckBox rem_userpass;
    private Button btn ;
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
    private static final String KEY_USER= "user";
    private static final String KEY_ROLE= "role";
    private static final String KAY_EMAIL= "email";
    private static final String TAG ="" ;
    String device_token;
    private   String token="",user="",email1="",role="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailEdit=(EditText)findViewById(R.id.email);
        mPasswordEdit=(EditText)findViewById(R.id.password);
        btn=(Button)findViewById(R.id.btn_login);
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
                btn.setEnabled(false);
              //  mProgressView.setVisibility(View.VISIBLE);

                //mLoginFormView.setVisibility(View.INVISIBLE);



            }
    }

    private void connexion(final String email, String password) {
        sharedPreferences1 = getSharedPreferences(PREF_NAME1, Context.MODE_PRIVATE);
        editor1 = sharedPreferences1.edit();
        device_token=sharedPreferences1.getString(KEY_FCM,"");

        mAPIService.login(email, password).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
               String role ;
                if(response.isSuccessful()) {

                    if(response.body().getMessage().equals("User signin")){
                      //  mProgressView.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),response.body().getToken(),Toast.LENGTH_SHORT).show();
                     token=response.body().getToken();
                     user=response.body().getUser().getPrenom();
                        role=response.body().getUser().getRole();
                     email1=response.body().getUser().getEmail();
                        role=response.body().getUser().getRole().trim();

                    managePrefs();
                   //Intent intent = new Intent(loginActivity.this, DemandeurActivity.class);
                        Intent intent;
                        if(role.equals("fournisseur")) {
                            intent = new Intent(loginActivity.this, MainActivity.class);
                        }else {
                             intent = new Intent(loginActivity.this, Main3Activity.class);
                        }
                        managePrefs();
                    intent.putExtra("token",response.body().getToken());
                    intent.putExtra("user",response.body().getUser().getEmail());
                   // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                        finish();
                }
                    else
                        Toast.makeText(getApplicationContext(),"An error occurred",Toast.LENGTH_SHORT).show();

                }else
                    Toast.makeText(getApplicationContext(),"An error occurred",Toast.LENGTH_SHORT).show();


                btn.setEnabled(true);

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e(TAG, "failed.");
                Toast.makeText(getApplicationContext(),R.string.internet,Toast.LENGTH_SHORT).show();
                btn.setEnabled(true);

            }
        });


    }

    static boolean isEmailValid(String email) {
        return email.contains("@");
    }

    static boolean isPasswordValid(String password) {
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
            editor.putBoolean(KEY_REMEMBER, true);

        }else{
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);//editor.putString(KEY_PASS,"");
            editor.remove(KEY_USERNAME);//editor.putString(KEY_USERNAME, "");

        }
        editor.putString(KEY_TOKEN,token);
        editor.putString(KEY_USER,user);
        editor.putString(KEY_ROLE,role);
        editor.putString(KAY_EMAIL,email1);
        editor.apply();
    }

    public void inscrit(View view) {
        Intent i=new Intent(loginActivity.this,InscriptionActivity.class);
        startActivity(i);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(loginActivity.this,
                    SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
