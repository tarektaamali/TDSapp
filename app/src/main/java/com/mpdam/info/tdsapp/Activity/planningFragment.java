package com.mpdam.info.tdsapp.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.mpdam.info.tdsapp.Adapter.ProjetAdapter;
import com.mpdam.info.tdsapp.Model.Projet;
import com.mpdam.info.tdsapp.Model.ProjetResp;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import java.util.ArrayList;
import java.util.Calendar;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class planningFragment extends Fragment {
//    private CalendarView mCalendarView;
  //  private List<EventDay> mEventDays = new ArrayList<>();

    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    private static final String KEY_TOKEN= "token";
    private RecyclerView recyclerView;
    private ArrayList<Projet> data;
    private ProjetAdapter adapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private APIService mAPIService;
    String token;
    String user;
    public static planningFragment newInstance()
    {
        return new planningFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.activity_planning, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = this.getActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        token =sharedPreferences.getString(KEY_TOKEN,"");
        //you can set the title for your toolbar
        // here for different fragments different titles
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH,-1);
/* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        getActivity().setTitle("Planning");
        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();
        recyclerView = (RecyclerView) view.findViewById(R.id.card_plannig);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        Calendar date=Calendar.getInstance();
        String date2=date.get(Calendar.YEAR)+"-"+(1+date.get(date.MONTH))+"-"+(date.get(Calendar.DAY_OF_MONTH));
        recyclerView.setHasFixedSize(true);
        mAPIService = ApiUtils.getAPIService();
        loadJson(token,date2);
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                String date2=date.get(Calendar.YEAR)+"-"+(1+date.get(date.MONTH))+"-"+(date.get(Calendar.DAY_OF_MONTH));

              loadJson(token,date2);
            }

        });


    }

    private void loadJson(String token, String s) {
        mAPIService.getplanning(token,s).enqueue(new Callback<ProjetResp>() {
            @Override
            public void onResponse(Call<ProjetResp> call, Response<ProjetResp> response) {
                ProjetResp projetResp=response.body();
                adapter = new ProjetAdapter(getActivity().getBaseContext(),projetResp.getProjets());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ProjetResp> call, Throwable t) {

            }
        });
    }


}