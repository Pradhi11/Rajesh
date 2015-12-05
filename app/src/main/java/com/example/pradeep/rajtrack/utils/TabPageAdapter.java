package com.example.pradeep.rajtrack.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.pradeep.rajtrack.tabfragment.TabFragmentFirst;
import com.example.pradeep.rajtrack.tabfragment.TabFragmentSecond;
import com.example.pradeep.rajtrack.tabfragment.TabFragmentThree;

/**
 * Created by PRADEEP on 12/4/2015.
 */
public class TabPageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public TabPageAdapter(FragmentManager fm,int numTab) {
        super(fm);
        this.mNumOfTabs=numTab;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TabFragmentFirst tab1 = new TabFragmentFirst();
                return tab1;
            case 1:
                TabFragmentSecond tab2 = new TabFragmentSecond();
                return tab2;
            case 2:
                TabFragmentThree  tab3 = new TabFragmentThree();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
