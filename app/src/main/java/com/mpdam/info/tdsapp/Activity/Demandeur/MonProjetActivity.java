package com.mpdam.info.tdsapp.Activity.Demandeur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Adapter.DemandetraveauxAdapter;
import com.mpdam.info.tdsapp.Model.ProjetResp;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mpdam.info.tdsapp.Activity.Demandeur.Main4Activity.token;


public class MonProjetActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public static APIService mAPIService;

    private DemandetraveauxAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projet);
        mAPIService = ApiUtils.getAPIService();
        this.getSupportActionBar().setTitle("Projets ");
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON(token);
    }
    private void loadJSON(String token) {
        mAPIService.getmyprojects(token).enqueue(new Callback<ProjetResp>() {
            @Override
            public void onResponse(Call<ProjetResp> call, Response<ProjetResp> response) {
                if(response.isSuccessful()){
                    ProjetResp projetResp=response.body();

                    adapter = new DemandetraveauxAdapter(getBaseContext(),projetResp.getProjets());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();}
                else
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.error),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ProjetResp> call, Throwable t) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.error),Toast.LENGTH_LONG).show();

            }
        });
    }
}
