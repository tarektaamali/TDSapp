package com.mpdam.info.tdsapp.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

import com.mpdam.info.tdsapp.R;

public class ProjetDetailsActivity extends AppCompatActivity  {
     CardView cv_employes,cv_materiels,cv_rapport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projet_details);
       LinearLayout cv_details=(LinearLayout) findViewById(R.id.cardprojetdetails);
        LinearLayout lin_mat=(LinearLayout)findViewById(R.id.lin_materiel) ;
        LinearLayout lin_empt=(LinearLayout)findViewById(R.id.lin_emp) ;
        LinearLayout linrapport=(LinearLayout)findViewById(R.id.lin_materiel) ;
        cv_employes=(CardView)findViewById(R.id.card_employes);
        cv_materiels=(CardView)findViewById(R.id.card_materiel);
        cv_rapport=(CardView)findViewById(R.id.card_rapport);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Details");
        cv_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(ProjetDetailsActivity.this,DetailsActivity.class);
                startActivity(i);
            }
        });
        lin_mat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(ProjetDetailsActivity.this,P_EmployesActivity.class);
                startActivity(i);
            }
        });
        lin_empt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(ProjetDetailsActivity.this,P_EmployesActivity.class);
                startActivity(i);
            }
        });
        linrapport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(ProjetDetailsActivity.this,P_EmployesActivity.class);
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
