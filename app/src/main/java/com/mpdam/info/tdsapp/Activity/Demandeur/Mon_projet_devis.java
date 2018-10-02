package com.mpdam.info.tdsapp.Activity.Demandeur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Adapter.DemandetraveauxAdapter;
import com.mpdam.info.tdsapp.Adapter.DevisAdapterDemandeur;
import com.mpdam.info.tdsapp.Model.DevisResp;
import com.mpdam.info.tdsapp.Model.PlanningResp;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mpdam.info.tdsapp.Activity.Demandeur.Main4Activity.token;

public class Mon_projet_devis extends AppCompatActivity {
    private RecyclerView recyclerView;
    public static APIService mAPIService;
    private DevisAdapterDemandeur adapter;
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projet);
        mAPIService = ApiUtils.getAPIService();
        this.getSupportActionBar().setTitle("Détails de Devis");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
       Bundle b=getIntent().getExtras();
        int id=b.getInt("id");
        loadJSON(id,token);

    }

    private void loadJSON(int id,String token) {
        mAPIService.getmyprojetdevis(id,token).enqueue(new Callback<DevisResp>() {
            @Override
            public void onResponse(Call<DevisResp> call, Response<DevisResp> response) {
                DevisResp planningResp=response.body();
                adapter = new DevisAdapterDemandeur(getApplicationContext(),planningResp.getPropositions());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<DevisResp> call, Throwable t) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.error),Toast.LENGTH_LONG).show();

            }
        });
    }
}
