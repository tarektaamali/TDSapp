package com.mpdam.info.tdsapp.Adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Activity.Main2Activity;
import com.mpdam.info.tdsapp.Model.Projet;
import com.mpdam.info.tdsapp.R;

import java.util.List;

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
        String r= mArrayList.get(i).getTitre().toString();
         viewHolder.tv_name.setText(projet.getService().getTitre().toString());
        viewHolder.tv_version.setText(projet.getTitre().toString());
          viewHolder.tv_api_level.setText(projet.getClient().getName().toString());
        viewHolder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,projet.getId().toString(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(v.getContext(), Main2Activity.class);
                v.getContext().startActivity(intent);
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
  /*  @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayList;
                } else {

                    List<Projet> filteredList = new List<>();

                    for (Projet androidVersion : mArrayList) {

                        if (androidVersion.getTitre().toLowerCase().contains(charString) || androidVersion.getClient().getName().toLowerCase().contains(charString) || androidVersion.getDescription().toLowerCase().contains(charString)) {

                            filteredList.add(androidVersion);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Projet>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }*/

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
