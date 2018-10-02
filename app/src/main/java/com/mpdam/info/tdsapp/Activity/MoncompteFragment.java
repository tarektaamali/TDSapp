package com.mpdam.info.tdsapp.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Adapter.DevisAdapter;
import com.mpdam.info.tdsapp.Model.Feedback;
import com.mpdam.info.tdsapp.Model.Region;
import com.mpdam.info.tdsapp.Model.RegionResp;
import com.mpdam.info.tdsapp.Model.RegistreResp;
import com.mpdam.info.tdsapp.Model.User;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoncompteFragment extends Fragment  {
    Button btn ;
    private RecyclerView recyclerView;
    private ArrayList<Feedback> data;
    private DevisAdapter  adapter;

    private APIService mAPIService;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    private static final String KEY_TOKEN= "token";
    String token;
    String user;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    RktArrayAdapter rktArrayAdapter;
    public ArrayList<String> kaminey_dost_array_list;
    private  int etat;
    ListView rkt_ListView;
    EditText mEmailEdit,mPasswordEdit,mPrenomEdit,mNomEdit,mSocieteEdit,mAdresseEdit,mtelephoneEdit;


    public static MoncompteFragment newInstance()
    {
        return new MoncompteFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_moncompte, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn_show_me;
        mEmailEdit=(EditText)view.findViewById(R.id.memail);
        mPasswordEdit=(EditText)view.findViewById(R.id.mpassword);
        mPrenomEdit=(EditText)view.findViewById(R.id.mprenom);
        mNomEdit=(EditText)view.findViewById(R.id.mnom);
        mSocieteEdit=(EditText)view.findViewById(R.id.msociete);
        mAdresseEdit=(EditText)view.findViewById(R.id.madresse);
        mtelephoneEdit=(EditText)view.findViewById(R.id.mtele);
        token =sharedPreferences.getString(KEY_TOKEN,"");
        loadData(token);
        btn_show_me = (Button) view.findViewById(R.id.btn_show_me);

        btn_show_me.setText("Modifier Votre zone");
        mAPIService = ApiUtils.getAPIService();
        //  loadJson();
        //kaminey_dost();
       // rktArrayAdapter = new RktArrayAdapter(getActivity().getApplicationContext(), R.layout.list_row, android.R.id.text1, kaminey_dost_array_list);
        //rkt_ListView.setAdapter(rktArrayAdapter);
        //rkt_ListView.setOnItemClickListener(this);
        sharedPreferences = this.getActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        token =sharedPreferences.getString(KEY_TOKEN,"");

        btn_show_me.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            }

            private void loadregistre(String token, String[] myList) {

            }
        });

    }

    private void loadData(String token) {
        mAPIService.getUser(token).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    mEmailEdit.setText(response.body().getEmail());
                    mAdresseEdit.setText(response.body().getAdresse());
                    mtelephoneEdit.setText(response.body().getTelephone());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }

    private void loadJson() {
    }




}