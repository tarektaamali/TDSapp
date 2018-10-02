package com.mpdam.info.tdsapp.Activity.Demandeur;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Info on 6/28/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch(position)
        {

            case 0:
                DevisFragment tab1 = new DevisFragment();
                return tab1;
            case 1:
                Mes_demandes_Fragment tab2 = new Mes_demandes_Fragment();
                return  tab2;
            case 2:
                Traveaux_valideFragment tab3 = new Traveaux_valideFragment();
                return  tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}