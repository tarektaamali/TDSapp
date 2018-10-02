package com.mpdam.info.tdsapp.Activity.Demandeur;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Model.devisUpdate;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mpdam.info.tdsapp.Activity.Demandeur.Voir_projet_Activity.mAPIService;
import static com.mpdam.info.tdsapp.Activity.Demandeur.Voir_projet_Activity.realisateur;
import static com.mpdam.info.tdsapp.Activity.Demandeur.Voir_projet_Activity.token;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackFragment extends Fragment {
    RatingBar mRatingBar;
    EditText mFeedback ;
    Button mSendFeedback;
    float x=0;

    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         mRatingBar = (RatingBar) view.findViewById(R.id.ratingBar);
         mFeedback = (EditText) view.findViewById(R.id.etFeedback);
         mSendFeedback = (Button) view.findViewById(R.id.btnSubmit);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                 x=(float) ratingBar.getRating();

            }
        });

        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFeedback.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity().getBaseContext(), "Please fill in feedback text box"+x+""+realisateur, Toast.LENGTH_LONG).show();
                } else {
                    String note=mFeedback.getText().toString();
                    Toast.makeText(getActivity().getBaseContext(), note+""+x+""+(int)realisateur, Toast.LENGTH_LONG).show();

                    mAPIService.feedback(token,note,x,(int)realisateur).enqueue(new Callback<devisUpdate>() {
                         @Override
                         public void onResponse(Call<devisUpdate> call, Response<devisUpdate> response) {
                             if(response.isSuccessful()){
                                 Toast.makeText(getActivity().getBaseContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();
                             }
                         }

                         @Override
                         public void onFailure(Call<devisUpdate> call, Throwable t) {

                         }
                     });
                }
            }
        });
    }
}
