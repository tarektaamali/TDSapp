package com.mpdam.info.tdsapp.Activity.Demandeur;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Model.Projetpostresp;
import com.mpdam.info.tdsapp.Model.Region;
import com.mpdam.info.tdsapp.Model.RegionResp;
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

import static com.mpdam.info.tdsapp.Activity.Demandeur.Main4Activity.token;

public class TrouverActivity extends AppCompatActivity {
    Button btn ;
    public static APIService mAPIService;
    LinearLayout ln;
    private EditText edtObjet,edtDescription,edtadr;
    Spinner spinner,spinner1 ;int selectService=0,selectedRegion=0;

    public static String usermailo;

    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_demande);
        spinner=(Spinner)findViewById(R.id.spinner);
        spinner1=(Spinner)findViewById(R.id.spinner1);
        edtObjet=(EditText)findViewById(R.id.objet_projet);
        edtDescription=(EditText)findViewById(R.id.description_projet);
        edtadr=(EditText)findViewById(R.id.adr_projet);

        btn=(Button)findViewById(R.id.btnsave);
        mAPIService = ApiUtils.getAPIService();

        intisaliser_spinner();
        intisaliser_spinner1();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Strobjet,Strdesccription,stradr;
                Strobjet=edtObjet.getText().toString();
                Strdesccription=edtDescription.getText().toString();
                stradr=edtadr.getText().toString();
                if(TextUtils.isEmpty(Strobjet)){
                    edtObjet.setError(getString(R.string.error_field_required));
                }else if(TextUtils.isEmpty(Strdesccription)){
                    edtDescription.setError(getString(R.string.error_field_required));
                }else if(TextUtils.isEmpty(stradr)){
                    edtadr.setError(getString(R.string.error_field_required));
                }
                else if(selectService==0) {
                    Toast.makeText(getApplicationContext(),"selectionner service", Toast.LENGTH_SHORT).show();

                }
                else if(selectedRegion==0) {
                    Toast.makeText(getApplicationContext(),"selectionner region", Toast.LENGTH_SHORT).show();

                }
                //     Toast.makeText(getApplicationContext(),selectService+""+selectedRegion, Toast.LENGTH_SHORT).show();
                mAPIService.save(token,Strobjet,Strdesccription,stradr,selectService,selectedRegion).enqueue(new Callback<Projetpostresp>() {
                    @Override
                    public void onResponse(Call<Projetpostresp> call, Response<Projetpostresp> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"save ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Projetpostresp> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),getResources().getString(R.string.internet),Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectService = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedRegion =i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void intisaliser_spinner1() {
        mAPIService.view_allregion().enqueue(new Callback<RegionResp>() {
            @Override
            public void onResponse(Call<RegionResp> call, Response<RegionResp> response) {
                if(response.isSuccessful()){
                    List<Region> services = response.body().getRegions();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < services.size(); i++){
                        listSpinner.add(services.get(i).getRegion());
                    }


                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner1.setAdapter(adapter);
                }
            }



            @Override
            public void onFailure(Call<RegionResp> call, Throwable t) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.internet),Toast.LENGTH_LONG).show();

            }
        });
    }


    private void intisaliser_spinner() {
        mAPIService.view_all().enqueue(new Callback<ServiceResp>() {
            @Override
            public void onResponse(Call<ServiceResp> call, Response<ServiceResp> response) {
                if (response.isSuccessful()){
                    List<Service> services = response.body().getServices();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < services.size(); i++){
                        listSpinner.add(services.get(i).getLibelle());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ServiceResp> call, Throwable t) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.internet),Toast.LENGTH_LONG).show();
            }
        });


    }
}

