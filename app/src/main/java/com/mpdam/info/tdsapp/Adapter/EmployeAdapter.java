package com.mpdam.info.tdsapp.Adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.mpdam.info.tdsapp.Activity.*;
import com.mpdam.info.tdsapp.Model.Employe;
import com.mpdam.info.tdsapp.Model.Projet;
import com.mpdam.info.tdsapp.Model.Rapport;
import com.mpdam.info.tdsapp.R;

import java.util.ArrayList;
import java.util.List;

public class EmployeAdapter extends RecyclerView.Adapter<EmployeAdapter.ViewHolder> {
    private List<Employe> android;
    Context c;

    public EmployeAdapter(Context ctx,List<Employe> android)
    {
        this.c=ctx;
        this.android = android;
    }

    @Override
    public EmployeAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_employe, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeAdapter.ViewHolder viewHolder, int i) {
        final Employe employe = android.get(i);

        viewHolder.tv_name.setText(employe.getFirstname());
        viewHolder.tv_version.setText(employe.getPhone());
        viewHolder.tv_api_level.setText(employe.getEmail());
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_version,tv_api_level;
        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView)view.findViewById(R.id.tv_name1);
            tv_version = (TextView)view.findViewById(R.id.tv_version1);
            tv_api_level = (TextView)view.findViewById(R.id.tv_api_level1);

        }
    }

}