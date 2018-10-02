package com.mpdam.info.tdsapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mpdam.info.tdsapp.Activity.ProjetDetailsFragment;
import com.mpdam.info.tdsapp.Model.Feedback;
import com.mpdam.info.tdsapp.R;

import java.util.List;

public class DfeedbackAdapter extends RecyclerView.Adapter<DfeedbackAdapter.ViewHolder> {
    private List<Feedback> mArrayList;
    //private List<Projet> mFilteredList;
    Context c;

    public DfeedbackAdapter(Context ctx, List<Feedback> arrayList) {
        this.c=ctx;
        this.mArrayList = arrayList;
        //this.mFilteredList = arrayList;
    }

    @Override
    public DfeedbackAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_feed, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
       // i++;
        final Feedback projet = mArrayList.get(i);
         viewHolder.tv_name.setText(projet.getNote().toString());
         viewHolder.mRatingBar.setRating(projet.getAvis());

        // viewHolder.mRatingBar.setEnabled(false);
        viewHolder.mRatingBar.setIsIndicator(true);
       ///   viewHolder.tv_api_level.setText(projet.getClient().getName().toString());
    /*    viewHolder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new ProjetDetailsFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("id",projet.getId());
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, myFragment).addToBackStack(null).commit();


            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_version, tv_api_level;
        private LinearLayout lin;
        private RatingBar mRatingBar;
        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_version = (TextView) view.findViewById(R.id.tv_version);
            lin=(LinearLayout)view.findViewById(R.id.lin);
            mRatingBar = (RatingBar) view.findViewById(R.id.ratingBar);



        }
    }
}
