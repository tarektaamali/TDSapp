package com.mpdam.info.tdsapp.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.mpdam.info.tdsapp.R;

public class loginActivity extends AppCompatActivity implements TextWatcher, CompoundButton.OnCheckedChangeListener {
    private EditText mPasswordEdit;
    private View mProgressView;
    private View mLoginFormView;
    private EditText mEmailEdit;
    private CheckBox rem_userpass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailEdit=(EditText)findViewById(R.id.email);
        mPasswordEdit=(EditText)findViewById(R.id.password);
        sharedPreferences = getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        rem_userpass = (CheckBox)findViewById(R.id.checkBox);
        if(sharedPreferences.getBoolean(KEY_REMEMBER, false))
            rem_userpass.setChecked(true);
        else
            rem_userpass.setChecked(false);

        mEmailEdit.setText(sharedPreferences.getString(KEY_USERNAME,""));
        mPasswordEdit.setText(sharedPreferences.getString(KEY_PASS,""));

        mEmailEdit.addTextChangedListener(this);
        mPasswordEdit.addTextChangedListener(this);
        rem_userpass.setOnCheckedChangeListener(this);

    }
    public void login(View view) {
        String email = mEmailEdit.getText().toString();
        String password = mPasswordEdit.getText().toString();
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
                Intent intent = new Intent(loginActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();

            }
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
        managePrefs();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        managePrefs();
    }

    private void managePrefs() {
        if(rem_userpass.isChecked()){
            editor.putString(KEY_USERNAME, mEmailEdit.getText().toString().trim());
            editor.putString(KEY_PASS, mPasswordEdit.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
        }else{
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);//editor.putString(KEY_PASS,"");
            editor.remove(KEY_USERNAME);//editor.putString(KEY_USERNAME, "");
            editor.apply();
        }
    }
}
