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
import com.mpdam.info.tdsapp.Model.Rapport;
import com.mpdam.info.tdsapp.R;

import java.util.List;

public class RapportAdapter extends RecyclerView.Adapter<RapportAdapter.ViewHolder> {
    private List<Rapport> mArrayList;
    Context c;

    public RapportAdapter(Context ctx,List<Rapport> arrayList) {
        this.c=ctx;
        this.mArrayList = arrayList;
        //this.mFilteredList = arrayList;
    }

    @Override
    public RapportAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_rapport_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        // i++;
        final Rapport rapport = mArrayList.get(i);
        //String r= mArrayList.get(i).getTitre().toString();
        viewHolder.tv_name.setText(rapport.getTitle().toString());
         viewHolder.tv_version.setText(rapport.getProjets().getClient().getName().toString());
         viewHolder.tv_api_level.setText(rapport.getProjets().getAdresse().toString());
        Glide.with(c)
                .load("http://192.168.42.98:8000/storage/blogimage/"+rapport.getImg().trim().toString())
                .thumbnail(0.5f)
                .into(viewHolder.imgview);
        viewHolder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,rapport.getId().toString(),Toast.LENGTH_SHORT).show();

                Intent i =new Intent(v.getContext(), RapportScrollingActivity.class);
                i.putExtra("id",rapport.getId());
                v.getContext().startActivity(i);
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
        private ImageView imgview;
        private LinearLayout lin;

        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_title);
            tv_version = (TextView) view.findViewById(R.id.tv_descr);
            tv_api_level = (TextView) view.findViewById(R.id.tv_addresse);
            imgview=(ImageView)view.findViewById(R.id.img) ;
            lin=(LinearLayout)view.findViewById(R.id.lin1);


        }
    }
}
