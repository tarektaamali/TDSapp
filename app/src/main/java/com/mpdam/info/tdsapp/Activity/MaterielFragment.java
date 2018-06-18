package com.mpdam.info.tdsapp.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Adapter.EmployeAdapter;
import com.mpdam.info.tdsapp.Adapter.MaterielAdapter;
import com.mpdam.info.tdsapp.Model.EmployeResp;
import com.mpdam.info.tdsapp.Model.MaterielResp;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MaterielFragment extends Fragment {
    Button btn ;
    private RecyclerView recyclerView;
    private MaterielAdapter adapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private APIService mAPIService;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    private static final String KEY_TOKEN= "token";
    String token;
    String user;
    public static employesFragment newInstance()
    {
        return new employesFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file

        return inflater.inflate(R.layout.activity_employes, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = this.getActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        token =sharedPreferences.getString(KEY_TOKEN,"");
        getActivity().setTitle("Materiels");


        recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        mAPIService = ApiUtils.getAPIService();
        loadJSON();

    }

    private void loadJSON() {
        mAPIService.getallmateriel().enqueue(new Callback<MaterielResp>() {
            @Override
            public void onResponse(Call<MaterielResp> call, Response<MaterielResp> response) {
                MaterielResp materiel=response.body();
                adapter = new MaterielAdapter(getActivity().getBaseContext(),materiel.getMateriels());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MaterielResp> call, Throwable t) {

            }
        });
    }

}