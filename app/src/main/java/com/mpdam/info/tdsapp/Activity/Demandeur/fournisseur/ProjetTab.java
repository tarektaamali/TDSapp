package com.mpdam.info.tdsapp.Activity.Demandeur.fournisseur;

/**
 * Created by Info on 7/18/2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Adapter.RapportAdapter;
import com.mpdam.info.tdsapp.Model.RapportAll;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mpdam.info.tdsapp.Activity.Demandeur.fournisseur.Main5Activity.iduser;
import static com.mpdam.info.tdsapp.Activity.Demandeur.fournisseur.Main5Activity.mAPIService;

public class ProjetTab extends Fragment{
    private RecyclerView recyclerView;
    private RapportAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_projet, container, false);
        loadJson(iduser);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        mAPIService = ApiUtils.getAPIService();

        return rootView;
    }

    private void loadJson(int iduser) {
        mAPIService.get_f_rapport(iduser).enqueue(new Callback<RapportAll>() {
            @Override
            public void onResponse(Call<RapportAll> call, Response<RapportAll> response) {
                if(response.isSuccessful()){
                    RapportAll rapportAll=response.body();
                    adapter = new RapportAdapter(getActivity().getBaseContext(),rapportAll.getRapports());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<RapportAll> call, Throwable t) {
                Toast.makeText(getActivity().getBaseContext(),"not ok",Toast.LENGTH_LONG).show();

            }
        });
    }
}