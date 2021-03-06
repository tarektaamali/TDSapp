package com.mpdam.info.tdsapp.Adapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mpdam.info.tdsapp.Activity.MainProjetFragment;
import com.mpdam.info.tdsapp.Activity.ProjetDetailsFragment;
import com.mpdam.info.tdsapp.Model.Projet;
import com.mpdam.info.tdsapp.R;

import java.util.List;

import static com.mpdam.info.tdsapp.Activity.ProjetCasFragment.fragmentetat;

public class ProjetAdapter extends RecyclerView.Adapter<ProjetAdapter.ViewHolder> {
    private List<Projet> mArrayList;
    //private List<Projet> mFilteredList;
    Context c;

    public ProjetAdapter(Context ctx,List<Projet> arrayList) {
        this.c=ctx;
        this.mArrayList = arrayList;
        //this.mFilteredList = arrayList;
    }

    @Override
    public ProjetAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
       // i++;
        final Projet projet = mArrayList.get(i);
         viewHolder.tv_name.setText(projet.getService().getLibelle().toString());
        viewHolder.tv_version.setText(projet.getObjet().toString());
       ///   viewHolder.tv_api_level.setText(projet.getClient().getName().toString());
        viewHolder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int cas= fragmentetat;
               /* AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new ProjetDetailsFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("id",projet.getId());
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, myFragment).addToBackStack(null).commit();
                */
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new MainProjetFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("id",projet.getId());
                bundle.putInt("cas",cas);
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, myFragment).addToBackStack(null).commit();


            }
        });
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

        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_version = (TextView) view.findViewById(R.id.tv_version);
            tv_api_level = (TextView) view.findViewById(R.id.tv_api_level);
            lin=(LinearLayout)view.findViewById(R.id.lin);


        }
    }
}
