package com.mpdam.info.tdsapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mpdam.info.tdsapp.Model.Materiel;
import com.mpdam.info.tdsapp.R;
import java.util.List;

public class MaterielAdapter extends RecyclerView.Adapter<com.mpdam.info.tdsapp.Adapter.MaterielAdapter.ViewHolder> {
    private List<Materiel> android;
    Context c;

    public MaterielAdapter(Context ctx,List<Materiel> android)
    {
        this.c=ctx;
        this.android = android;
    }

    @Override
    public com.mpdam.info.tdsapp.Adapter.MaterielAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_materiel, viewGroup, false);
        return new com.mpdam.info.tdsapp.Adapter.MaterielAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(com.mpdam.info.tdsapp.Adapter.MaterielAdapter.ViewHolder viewHolder, int i) {
        final Materiel materiel = android.get(i);
        viewHolder.tv_name.setText(materiel.getLibelle());
        viewHolder.tv_version.setText(materiel.getNombre()+"");
       viewHolder.tv_api_level.setText("");
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_version,tv_api_level;
        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView)view.findViewById(R.id.tv_name2);
            tv_version = (TextView)view.findViewById(R.id.tv_version2);
            tv_api_level = (TextView)view.findViewById(R.id.tv_api_level2);

        }
    }

}