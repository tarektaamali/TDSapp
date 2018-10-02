package com.mpdam.info.tdsapp.Activity.Demandeur;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.mpdam.info.tdsapp.Model.Projetdetails;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class Voir_projet_Activity extends AppCompatActivity {
    public static final String PREF_NAME = "prefs";
    public static final String KEY_REMEMBER = "remember";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASS = "password";
    public static final String KEY_TOKEN= "token";
    private static final String KEY_USER= "user";
    private static final String KAY_EMAIL= "email";
    public static String token;
    SharedPreferences sharedPreferences;
    static APIService mAPIService;
    SharedPreferences.Editor editor;
    boolean x;
    TextView txtobjectif,txtdescription,txtservice,txtregion,txtstatut;
    int id;
   static int realisateur;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getActionBar().setTitle("Projet Détails");
        setContentView(R.layout.activity_voir_projet_);
        txtobjectif=(TextView)findViewById(R.id.txtobjectif);
        txtdescription=(TextView)findViewById(R.id.txtdescriptionp);
        txtservice=(TextView)findViewById(R.id.txtservice);
        txtregion=(TextView)findViewById(R.id.txtregion);
        txtstatut=(TextView)findViewById(R.id.txtstatus1);
        Voir_projet_Activity.this.getSupportActionBar().setTitle("Détails de Projet  ");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle b =getIntent().getExtras();
        id=b.getInt("id");
        sharedPreferences = getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        token =sharedPreferences.getString(KEY_TOKEN,"");
        Toast.makeText(getApplicationContext(),b.getString("titre")+"iddd",Toast.LENGTH_LONG).show();
        mAPIService = ApiUtils.getAPIService();
       floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Voir_projet_Activity.this.getSupportActionBar().setTitle("Modifier projet");
                Toast.makeText(getApplicationContext(),"modifier",Toast.LENGTH_LONG).show();
                Fragment myFragment = new EditerprojetFragment();
                Bundle bundle=new Bundle();
                myFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.linear, myFragment).addToBackStack(null).commit();


            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                removeClient(1);
                            }
                        });
                alertDialogBuilder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }

            private void removeClient(int i) {
                Toast.makeText(getApplicationContext(),"Supprimer",Toast.LENGTH_LONG).show();

            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(realisateur==0){
                    Toast.makeText(getApplicationContext(), "Aucun fournisseur selectionnez pour cette projet", Toast.LENGTH_LONG).show();
                }
                else {
                    Voir_projet_Activity.this.getSupportActionBar().setTitle("Envoyez Feedback");
                    Toast.makeText(getApplicationContext(), "Feedback", Toast.LENGTH_LONG).show();
                    Fragment myFragment = new FeedbackFragment();
                    Bundle bundle = new Bundle();
                    myFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.linear, myFragment).addToBackStack(null).commit();
                }

            }
        });

    //////////


        //////////////////
        txtobjectif.setText(b.getString("titre"));
        txtdescription.setText(b.getString("desciption"));
        txtservice.setText(b.getString("service"));
        txtregion.setText(b.getString("zone"));
        txtstatut.setText(b.getString("statut"));

                 txtobjectif.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Fragment myFragment = new FeedbackFragment();
                         Bundle bundle=new Bundle();
                         myFragment.setArguments(bundle);
                         getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, myFragment).addToBackStack(null).commit();

                     }
                 });
         loadJson(id,token);



    }

    private void loadJson(int id, String token) {
        mAPIService.getprojetdetails(id,token).enqueue(new Callback<Projetdetails>() {
            @Override
            public void onResponse(Call<Projetdetails> call, Response<Projetdetails> response) {
                    if(response.isSuccessful()){
                        if(response.body().getProjet().getRealisateurId()==null){

                        }else
                        realisateur=response.body().getProjet().getRealisateurId();
                    }
            }

            @Override
            public void onFailure(Call<Projetdetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.error),Toast.LENGTH_LONG).show();


            }
        });
    }

    public void btnEditer(View view) {

        loadFragment();
    }

    private void loadFragment() {
     /*   FragmentTransaction fragmentTransaction=null;
        EditerprojetFragment fragment = new EditerprojetFragment();
        fragmentTransaction.add(R.id.frameLayout, fragment,"");
        fragmentTransaction.commit();*/

    }
}
