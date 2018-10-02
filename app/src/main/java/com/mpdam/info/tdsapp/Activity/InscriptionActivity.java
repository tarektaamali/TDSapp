package com.mpdam.info.tdsapp.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Model.UserResponse;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.mpdam.info.tdsapp.Activity.loginActivity.isEmailValid;
import static com.mpdam.info.tdsapp.Activity.loginActivity.isPasswordValid;

public class InscriptionActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton rb0,rb1;
    private TextView txt;
    private APIService mAPIService;
    private EditText mEmailEdit,mPasswordEdit,mPrenomEdit,mNomEdit,mSocieteEdit,mAdresseEdit,mtelephoneEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mEmailEdit=(EditText)findViewById(R.id.memail);
        mPasswordEdit=(EditText)findViewById(R.id.mpassword);
        mPrenomEdit=(EditText)findViewById(R.id.mprenom);
        mNomEdit=(EditText)findViewById(R.id.mnom);
        mSocieteEdit=(EditText)findViewById(R.id.msociete);
        mAdresseEdit=(EditText)findViewById(R.id.madresse);
        mtelephoneEdit=(EditText)findViewById(R.id.mtele);
         rb0 = (RadioButton) findViewById(R.id.rb0);
         rb1 = (RadioButton) findViewById(R.id.rb1);
        mAPIService = ApiUtils.getAPIService();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public void inscrit(View view) {
        int selectedId;
         selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        String email = mEmailEdit.getText().toString();
        String password = mPasswordEdit.getText().toString();
        String nom = mPrenomEdit.getText().toString();
        String prenom = mNomEdit.getText().toString();
        String societe = mSocieteEdit.getText().toString();
        String adresse = mAdresseEdit.getText().toString();
        String telephone = mtelephoneEdit.getText().toString();
        if(TextUtils.isEmpty(email)){
            mEmailEdit.setError(getString(R.string.error_field_required));
        }
        else if(!isEmailValid(email)){
            mEmailEdit.setError(getString(R.string.error_invalid_email));
        } else if(TextUtils.isEmpty(password)&&(!isPasswordValid(password))){
            mPasswordEdit.setError(getString(R.string.error_invalid_password));
        }else if(TextUtils.isEmpty(prenom)){
            mPrenomEdit.setError(getString(R.string.error_field_required));
        }else if(TextUtils.isEmpty(nom)){
          mNomEdit.setError(getString(R.string.error_field_required));
        }else if(TextUtils.isEmpty(societe)){
            mSocieteEdit.setError(getString(R.string.error_field_required));
        }else if(TextUtils.isEmpty(adresse)){
           mAdresseEdit.setError(getString(R.string.error_field_required));

        }else if(!istelephoenValid(telephone)){
            mtelephoneEdit.setError(getString(R.string.error_invalid_phone));

        } else if( !(rb0.isChecked()||rb1.isChecked())){
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.role),Toast.LENGTH_SHORT).show();
        }

        else
           // Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();
        loadJson(email, password, prenom, nom, societe, adresse, telephone, radioButton.getText().toString());

    }

    private void loadJson(String email, String password, String prenom, String nom, String societe, String adresse, String telephone, String t) {
        mAPIService.store(email,password,nom,prenom,societe,telephone,t,adresse).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()) {

                    if(response.body().getMessage().equals("User created"))
                    {  Intent intent = new Intent(InscriptionActivity.this, loginActivity.class);
                        Toast.makeText(getApplicationContext(),getString(R.string.inscription),Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }}

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"An error in server",Toast.LENGTH_SHORT).show();
            }
        });
    }
    static boolean istelephoenValid(String tele) {
        return tele.length() == 8;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
