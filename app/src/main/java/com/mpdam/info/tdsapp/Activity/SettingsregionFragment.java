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
import android.widget.ListView;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Adapter.DevisAdapter;
import com.mpdam.info.tdsapp.Model.Feedback;
import com.mpdam.info.tdsapp.Model.Region;
import com.mpdam.info.tdsapp.Model.RegionResp;
import com.mpdam.info.tdsapp.Model.RegistreResp;
import com.mpdam.info.tdsapp.Model.Service;
import com.mpdam.info.tdsapp.Model.ServiceResp;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsregionFragment extends Fragment implements AdapterView.OnItemClickListener {
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

    public static SettingsregionFragment newInstance()
    {
        return new SettingsregionFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_setting, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn_show_me;
        kaminey_dost_array_list = new ArrayList<String>();

        rkt_ListView = (ListView)view.findViewById(R.id.rkt_listview);
        btn_show_me = (Button) view.findViewById(R.id.btn_show_me);
        btn_show_me.setText(getResources().getString(R.string.mdregion));
        mAPIService = ApiUtils.getAPIService();
          loadJson();

        rkt_ListView.setOnItemClickListener(this);
        sharedPreferences = this.getActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        token =sharedPreferences.getString(KEY_TOKEN,"");

        btn_show_me.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String result = "";

                List<String> resultList = rktArrayAdapter.getCheckedItems();
                ArrayList<String>  myList = new ArrayList<String>();
                String[] s=new String[resultList.size()];
                for (int i = 0; i < resultList.size(); i++) {
                    int y= Integer.parseInt(resultList.get(i))+1;
                    result += String.valueOf(resultList.get(i))+ "\n";
                    s[i]=y+"";

                }
         //       myList.add("1");
                loadregistre(token,s);

                rktArrayAdapter.getCheckedItemPositions().toString();

                if (result.matches("")) {
                    Toast.makeText(
                            getContext(),
                            "Please select some thing from list to show",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(
                            getContext(),result, Toast.LENGTH_LONG).show();
                }


            }

            private void loadregistre(String token, String[] myList) {
                mAPIService.registre_region(token,myList).enqueue(new Callback<RegistreResp>() {
                    @Override
                    public void onResponse(Call<RegistreResp> call, Response<RegistreResp> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getActivity().getBaseContext(),response.body().toString(),Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<RegistreResp> call, Throwable t) {
                        Toast.makeText(getContext(),getResources().getString(R.string.internet),Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

    }

    private void loadJson() {
mAPIService.view_allregion().enqueue(new Callback<RegionResp>() {
    @Override
    public void onResponse(Call<RegionResp> call, Response<RegionResp> response) {
        if (response.isSuccessful()){
            List<Region> services = response.body().getRegions();
            for (int i = 0; i < services.size(); i++){
                kaminey_dost_array_list.add(services.get(i).getRegion());
            }
            rktArrayAdapter = new RktArrayAdapter(getActivity().getBaseContext(), R.layout.list_row, android.R.id.text1, kaminey_dost_array_list);
            rkt_ListView.setAdapter(rktArrayAdapter);

        }
    }

    @Override
    public void onFailure(Call<RegionResp> call, Throwable t) {
        Toast.makeText(getContext(),getResources().getString(R.string.internet),Toast.LENGTH_LONG).show();

    }
});
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        rktArrayAdapter.rkt_toggleChecked(i);

    }
}