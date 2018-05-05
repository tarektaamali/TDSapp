package com.mpdam.info.tdsapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mpdam.info.tdsapp.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void suivant(View view) {
        Intent i =new Intent(Main2Activity.this,RapportActivity.class);
        startActivity(i);
    }
}
