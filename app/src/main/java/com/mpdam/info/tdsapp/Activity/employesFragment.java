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
import com.mpdam.info.tdsapp.Adapter.RapportAdapter;
import com.mpdam.info.tdsapp.Model.Employe;
import com.mpdam.info.tdsapp.Model.EmployeResp;
import com.mpdam.info.tdsapp.Model.RapportAll;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class employesFragment extends Fragment {
    Button btn ;
    private RecyclerView recyclerView;
    private EmployeAdapter adapter;
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
        //Toast.makeText(getContext(),token,Toast.LENGTH_SHORT).show();

        //btn = (Button)view.findViewById(R.id.button);
        //you can set the title for your toolbar here for different fragments different titles

        getActivity().setTitle("Employes");




       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"hello",Toast.LENGTH_SHORT).show();
            }
        });*/
        recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        mAPIService = ApiUtils.getAPIService();
        loadJSON();

    }

    private void loadJSON() {
        mAPIService.getallemployes().enqueue(new Callback<EmployeResp>() {
            @Override
            public void onResponse(Call<EmployeResp> call, Response<EmployeResp> response) {
                EmployeResp employes=response.body();
                adapter = new EmployeAdapter(getActivity().getBaseContext(),employes.getEmployes());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<EmployeResp> call, Throwable t) {

            }
        });
    }



}