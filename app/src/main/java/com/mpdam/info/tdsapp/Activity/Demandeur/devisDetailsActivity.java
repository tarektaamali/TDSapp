package com.mpdam.info.tdsapp.Activity.Demandeur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.mpdam.info.tdsapp.Activity.Demandeur.fournisseur.Main5Activity;
import com.mpdam.info.tdsapp.Model.Planning;
import com.mpdam.info.tdsapp.Model.devisUpdate;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import java.nio.file.Files;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mpdam.info.tdsapp.Activity.Demandeur.Main3Activity.token;
import static com.mpdam.info.tdsapp.Adapter.DevisAdapterDemandeur.mArrayList;

public class devisDetailsActivity extends AppCompatActivity {
    public static APIService mAPIService;
    TextView txtsociete,txtdesc,txtech,txtdevis,txtSdt,txtStatut;
    int id,projet_id,count;
    FloatingActionButton btn,btnrefuse,btnview;
    public static String token1;
    public static int iduser;
    int idf ;


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devis_details);
        this.getSupportActionBar().setTitle("Détails de Devis");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAPIService = ApiUtils.getAPIService();
        btn=(FloatingActionButton)findViewById(R.id.btnaccepter);
        btnrefuse=(FloatingActionButton)findViewById(R.id.btnrefuse);
        btnview=(FloatingActionButton)findViewById(R.id.btnview);
        txtsociete=(TextView)findViewById(R.id.txtsociete);
        txtdesc=(TextView)findViewById(R.id.txtdesc);
        txtech=(TextView)findViewById(R.id.txtech);
        txtdevis=(TextView)findViewById(R.id.txtdevis);
        txtSdt=(TextView)findViewById(R.id.txtSdt);
        txtStatut=(TextView)findViewById(R.id.txtStatut);
        Bundle b =getIntent().getExtras();
        id=b.getInt("id");
        projet_id=b.getInt("projet_id");
        loadJson(id,projet_id,token);
        loadDevis(id,token);
        token1=token;
        btnrefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadJson(id,token,2);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count==0){
                    loadJson(id,token,1);
                }
                else
                    Toast.makeText(getApplicationContext(),"long click",Toast.LENGTH_SHORT).show();

            }
        });
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                loadJson1( projet_id,token);

                return false;
            }


        });
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),iduser+""+token1,Toast.LENGTH_LONG).show();

                Intent i=new Intent(devisDetailsActivity.this,Main5Activity.class);
                i.putExtra("iduser",iduser);
                i.putExtra("token",token1);

                startActivity(i);

            }
        });
    }

    private void loadDevis(int id, String token) {
        mAPIService.showdevis(id,token).enqueue(new Callback<Planning>() {
            @Override
            public void onResponse(Call<Planning> call, Response<Planning> response) {
                if(response.isSuccessful()){
                    txtsociete.setText(response.body().getUser().getSociete());
                    txtdesc.setText(response.body().getDescription());
                    txtech.setText(response.body().getEchenance());
                    txtSdt.setText(response.body().getStartDate());
                    txtdevis.setText(response.body().getDevis());
                    iduser=response.body().getUser().getId();
                    if(response.body().getStatus()==1){
                        txtStatut.setText("devis deja accepter");
                    }
                    else if(response.body().getStatus()==2) {
                        txtStatut.setText("devis refuser");

                    }
                }


            }


            @Override
            public void onFailure(Call<Planning> call, Throwable t) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.error),Toast.LENGTH_LONG).show();

            }
        });
    }

    private void loadJson1(int projet_id,String token){
        mAPIService.propositiondstatus(projet_id,token).enqueue(new Callback<devisUpdate>() {
            @Override
            public void onResponse(Call<devisUpdate> call, Response<devisUpdate> response) {
                Toast.makeText(getApplicationContext(),response.body().getMessage().toString(),Toast.LENGTH_SHORT).show();
                loadJson(id,token1,1);

            }

            @Override
            public void onFailure(Call<devisUpdate> call, Throwable t) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.error),Toast.LENGTH_LONG).show();

            }
        });

    }
    private void loadJson(int id,int projet_id, String token) {
        mAPIService.getcountdevis(id,projet_id,token).enqueue(new Callback<devisUpdate>() {
            @Override
            public void onResponse(Call<devisUpdate> call, Response<devisUpdate> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),response.body().getMessage()+"",Toast.LENGTH_SHORT).show();
                    count=Integer.parseInt(response.body().getMessage().toString());
                }
            }

            @Override
            public void onFailure(Call<devisUpdate> call, Throwable t) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.error),Toast.LENGTH_LONG).show();

            }
        });
    }
    private void loadJson(int id, String token,int status) {
        mAPIService.accepterdevis(id,token,status).enqueue(new Callback<devisUpdate>() {
            @Override
            public void onResponse(Call<devisUpdate> call, Response<devisUpdate> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"acepter",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<devisUpdate> call, Throwable t) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.error),Toast.LENGTH_LONG).show();

            }
        });
    }

}
