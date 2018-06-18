package com.mpdam.info.tdsapp.Activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.mpdam.info.tdsapp.Adapter.EmployeAdapter;
import com.mpdam.info.tdsapp.Adapter.ProjetAdapter;
import com.mpdam.info.tdsapp.Model.Employe;
import com.mpdam.info.tdsapp.Model.EmployeResp;
import com.mpdam.info.tdsapp.Model.ProjetResp;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class P_EmployesActivity extends AppCompatActivity {
    private APIService mAPIService;
    private EmployeAdapter adapter;
    private RecyclerView recyclerView;
    public static P_EmployesActivity newInstance()
    {
        return new P_EmployesActivity();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employes);
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        mAPIService = ApiUtils.getAPIService();
     loadJSON(5);
    }

    private void loadJSON(int i) {
        mAPIService.getProjet_employes(i).enqueue(new Callback<EmployeResp>() {
            @Override
            public void onResponse(Call<EmployeResp> call, Response<EmployeResp> response) {
                EmployeResp employeResp=response.body();
                adapter = new EmployeAdapter(getBaseContext(),employeResp.getEmployes());
                recyclerView.setAdapter(adapter);
              adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<EmployeResp> call, Throwable t) {

            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

