package com.mpdam.info.tdsapp.Activity.Demandeur;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import static com.mpdam.info.tdsapp.Activity.Demandeur.DemandeurActivity.token;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DevisFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DevisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DevisFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Spinner spinner,spinner1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private APIService mAPIService;
    int s=0,r=0;


    private OnFragmentInteractionListener mListener;

    public DevisFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DevisFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DevisFragment newInstance(String param1, String param2) {
        DevisFragment fragment = new DevisFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

                View v= inflater.inflate(R.layout.fragment_demande,
                        container, false);
        //return inflater.inflate(R.layout.fragment_devis, container, false);
         spinner = (Spinner) v.findViewById(R.id.spinner);
        spinner1 = (Spinner) v.findViewById(R.id.spinner1);

        mAPIService = ApiUtils.getAPIService();
        intisaliser_spinner();
        intisaliser_spinner1();
         spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedName = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(getContext(), "Kamu memilih dosen " + i, Toast.LENGTH_SHORT).show();
                   s=i;
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedName = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), "Kamu memilih dosen " + i, Toast.LENGTH_SHORT).show();
                    r=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        final EditText desciption = (EditText) v.findViewById(R.id.description_projet);
        final EditText objet = (EditText) v.findViewById(R.id.objet_projet);
        final EditText adr = (EditText) v.findViewById(R.id.adr_projet);

        desciption.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View view, MotionEvent motionEvent) {
                 view.getParent().requestDisallowInterceptTouchEvent(true);
                 switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
                     case MotionEvent.ACTION_UP:
                         view.getParent().requestDisallowInterceptTouchEvent(false);
                         break;
                 }
                 return false;
             }
         });

        final Button button = (Button)v.findViewById(R.id.btnsave);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(s==0) {
                    Toast.makeText(v.getContext(), "choisir le service", Toast.LENGTH_SHORT).show();
                }else if(r==0){
                    Toast.makeText(v.getContext(), "choisir la region", Toast.LENGTH_SHORT).show();
                }
                else {
               String objetStr=objet.getText().toString();
               String  desciptionStr=desciption.getText().toString();
               String   adrStr=adr.getText().toString();
                   loadJson(token,objetStr,desciptionStr,adrStr,s,r);
                }

            }
        });


        return  v;
    }

    private void loadJson(String token, String objetStr, String desciptionStr, String adrStr, int s, int r) {
        mAPIService.save(token,objetStr,desciptionStr,adrStr,s,r).enqueue(new Callback<Projetpostresp>() {
            @Override
            public void onResponse(Call<Projetpostresp> call, Response<Projetpostresp> response) {
                 if(response.isSuccessful()){
                     Toast.makeText(getContext(),"engistrer",Toast.LENGTH_SHORT).show();
                 }
            }

            @Override
            public void onFailure(Call<Projetpostresp> call, Throwable t) {

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
                    listSpinner.add("choisir service");
                    for (int i = 0; i < services.size(); i++){
                        listSpinner.add(services.get(i).getLibelle());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ServiceResp> call, Throwable t) {

            }
        });
    }
    private void intisaliser_spinner1() {
        mAPIService.view_allregion().enqueue(new Callback<RegionResp>() {
            @Override
            public void onResponse(Call<RegionResp> call, Response<RegionResp> response) {
                if (response.isSuccessful()){
                    List<Region> regions= response.body().getRegions();
                    List<String> listSpinner1 = new ArrayList<String>();
                    listSpinner1.add("choisir region");
                    for (int i = 0; i < regions.size(); i++){
                        listSpinner1.add(regions.get(i).getRegion());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, listSpinner1);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner1.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<RegionResp> call, Throwable t) {

            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
