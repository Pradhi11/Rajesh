package com.example.pradeep.rajtrack;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.pradeep.rajtrack.tabfragment.TabFragmentFirst;
import com.example.pradeep.rajtrack.tabfragment.TabFragmentSecond;
import com.example.pradeep.rajtrack.tabfragment.TabFragmentThree;

import java.util.ArrayList;
import java.util.List;



public class CopyTabActivity extends AppCompatActivity implements TabFragmentFirst.OnFragmentInteractionListener,TabFragmentSecond.OnFragmentInteractionListener,TabFragmentThree.OnFragmentInteractionListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_tab);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        String usn=getIntent().getStringExtra("usn");
        String fee=getIntent().getStringExtra("fee");
        String branch=getIntent().getStringExtra("branch");

        bundle.putString("usn",usn);
        bundle.putString("fee",fee);
        bundle.putString("branch",branch);


        Log.d("copyTabActivity", usn + " : " + fee + " : " + branch + " : ");

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TabFragmentFirst(), "FEE");
        adapter.addFragment(new TabFragmentSecond(), "TIMETABLE");
        adapter.addFragment(new TabFragmentThree(), "RESULT");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
            fragment.setArguments(bundle);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}      //1200 1500 10000 7000 1000     33500  16500  1200  4000 3000  1000