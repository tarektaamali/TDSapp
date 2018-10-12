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

import com.mpdam.info.tdsapp.Adapter.DevisAdapter;
import com.mpdam.info.tdsapp.Adapter.FeedbackAdapter;
import com.mpdam.info.tdsapp.Model.DevisResp;
import com.mpdam.info.tdsapp.Model.Feedback;
import com.mpdam.info.tdsapp.Model.FeedbackResp;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mpdam.info.tdsapp.Activity.MainActivity.mAPIService;

public class DevisFragment extends Fragment {
    Button btn ;
    private RecyclerView recyclerView;
    private ArrayList<Feedback> data;
    private DevisAdapter  adapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    private static final String KEY_TOKEN= "token";
    String token;
    String user;
    private  int etat;
    public static DevisFragment newInstance()
    {
        return new DevisFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file

        return inflater.inflate(R.layout.activity_projet, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      sharedPreferences = this.getActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
       token =sharedPreferences.getString(KEY_TOKEN,"");
        getActivity().setTitle("Devis proposez");

        recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON(token);
/*


 */
    }

    private void loadJSON(String token) {
        mAPIService.getalldevis(token).enqueue(new Callback<DevisResp>() {
            @Override
            public void onResponse(Call<DevisResp> call, Response<DevisResp> response) {
                DevisResp projetResp=response.body();
                adapter = new DevisAdapter(getActivity().getBaseContext(),projetResp.getPropositions());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<DevisResp> call, Throwable t) {

            }
        });    }


}