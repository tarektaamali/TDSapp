package com.mpdam.info.tdsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Activity.Demandeur.Mon_projet_devis;
import com.mpdam.info.tdsapp.Activity.Demandeur.Tab3;
import com.mpdam.info.tdsapp.Activity.Demandeur.TrouverActivity;
import com.mpdam.info.tdsapp.Activity.Demandeur.Voir_projet_Activity;
import com.mpdam.info.tdsapp.Activity.MainProjetFragment;
import com.mpdam.info.tdsapp.Model.Projet;
import com.mpdam.info.tdsapp.R;

import java.util.List;

import static com.mpdam.info.tdsapp.R.color.green;

public class DemandetraveauxAdapter extends RecyclerView.Adapter<DemandetraveauxAdapter.ViewHolder> {
    private List<Projet> mArrayList;
    //private List<Projet> mFilteredList;
    Context c;

    public DemandetraveauxAdapter(Context ctx, List<Projet> arrayList) {
        this.c=ctx;
        this.mArrayList = arrayList;
        //this.mFilteredList = arrayList;
    }

    @Override
    public DemandetraveauxAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_demande_tra, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Projet projet = mArrayList.get(i);
        viewHolder.tv_name.setText(projet.getObjet());
        viewHolder.tv_version.setText(projet.getService().getLibelle());
        viewHolder.tv_api_level.setText(projet.getLieu());
        viewHolder.tv_cas.setText(projet.getEtat().getLibelle());
        viewHolder.btn_devis.setText("Voir les Devis ("+projet.getPropositions().size()+")");
        viewHolder.btn_devis.setVisibility(View.VISIBLE);
        viewHolder.btn_devis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(projet.getPropositions().isEmpty()){
                    Toast.makeText(view.getContext(),"Aucun devis",Toast.LENGTH_SHORT).show();
                }else{
                    Intent i =new Intent(view.getContext(),Mon_projet_devis.class);
                    i.putExtra("id",projet.getId());
                    view.getContext().startActivity(i);}
            }
        });
        viewHolder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(v.getContext(),Voir_projet_Activity.class);
                i.putExtra("id",projet.getId());
                i.putExtra("titre",projet.getObjet());
                i.putExtra("desciption",projet.getDescription());
                i.putExtra("service",projet.getService().getLibelle());
                i.putExtra("zone",projet.getRegion().getRegion());
                i.putExtra("statut",projet.getEtat().getLibelle());
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
        private TextView tv_name,tv_cas,tv_devis,tv_desc;
        private Button tv_version, tv_api_level,btn_devis;

        private LinearLayout lin;

        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_version = (Button) view.findViewById(R.id.tv_version);
            tv_api_level = (Button) view.findViewById(R.id.tv_api_level);
            tv_cas = (TextView) view.findViewById(R.id.tv_cas);
            btn_devis = (Button) view.findViewById(R.id.btn_devis);
            lin=(LinearLayout)view.findViewById(R.id.lin);


        }
    }
}
