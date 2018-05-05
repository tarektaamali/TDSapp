package com.mpdam.info.tdsapp.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Model.Projet;
import com.mpdam.info.tdsapp.Model.ProjetResp;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class planningFragment extends Fragment {
//    private CalendarView mCalendarView;
  //  private List<EventDay> mEventDays = new ArrayList<>();
    private APIService mAPIService;
    String token;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    private static final String KEY_TOKEN= "token";


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
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Planning");

      /*  mCalendarView = (CalendarView) view.findViewById(R.id.calendarView);
        List<EventDay> events = new ArrayList<>();*/
        mAPIService = ApiUtils.getAPIService();
    //    compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        mAPIService.getall(token,3).enqueue(new Callback<ProjetResp>() {
            @Override
            public void onResponse(Call<ProjetResp> call, Response<ProjetResp> response) {
                ProjetResp projetResp=response.body();
                      int x=response.body().getProjets().size();
                List<Projet> projet=response.body().getProjets();
                for (int i=0;i<x;i++){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");//dd-M-yyyy 2018-04-07
                    String dateInString = projet.get(i).getStartDate().toString();
                    Date date = null;
                    try {

                            date =sdf.parse(dateInString);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String a=projet.get(i).getTitre();
      //              Event event  = new Event(Color.GREEN, 1433704251000L);
        //            compactCalendarView.addEvent(event);
                 /*   Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    MyEventDay myEventDay = new MyEventDay(cal,R.drawable.ic_person,projet.get(i).getTitre().toString());
                    mEventDays.add(myEventDay);*/

                 //   mCalendarView.setEvents(mEventDays);
                }
            }

            @Override
            public void onFailure(Call<ProjetResp> call, Throwable t) {

            }
        });
    }

}