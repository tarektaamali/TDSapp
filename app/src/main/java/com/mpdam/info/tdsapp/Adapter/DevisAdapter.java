package com.mpdam.info.tdsapp.Adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Activity.MainActivity;
import com.mpdam.info.tdsapp.Activity.planningFragment;
import com.mpdam.info.tdsapp.Model.Feedback;
import com.mpdam.info.tdsapp.Model.Planning;
import com.mpdam.info.tdsapp.R;

import java.util.List;

public class DevisAdapter extends RecyclerView.Adapter<DevisAdapter.ViewHolder> {
    private List<Planning> mArrayList;
    //private List<Projet> mFilteredList;
    Context c;

    public DevisAdapter(Context ctx, List<Planning> arrayList) {
        this.c=ctx;
        this.mArrayList = arrayList;
        //this.mFilteredList = arrayList;
    }

    @Override
    public DevisAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
       // i++;
        final Planning projet = mArrayList.get(i);
         viewHolder.tv_name.setText(projet.getEchenance());
       ///   viewHolder.tv_api_level.setText(projet.getClient().getName().toString());
        viewHolder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*    Toast.makeText(c,projet.getId().toString(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(v.getContext(), ProjetDetailsActivity.class);
                v.getContext().startActivity(intent);*/
            //    MainActivity main=new MainActivity();
              //  main.displaySelectedScreen(R.id.nav_camera);
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new planningFragment();
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
