package com.gps.customview1_1;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gps.customview1_1.frags.DrawArcFrag;
import com.gps.customview1_1.frags.DrawCircleFrag;
import com.gps.customview1_1.frags.DrawColorFrag;
import com.gps.customview1_1.frags.DrawHistogramFrag;
import com.gps.customview1_1.frags.DrawPathFrag;
import com.gps.customview1_1.frags.DrawPieFrag;
import com.gps.customview1_1.frags.DrawRectFrag;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    TabLayout tab;
    ViewPager vp;
    FragmentPagerAdapter adapter;
    List<Fragment> fgs;
    String[] title = {"drawColor",
                      "drawCircle",
                      "drawRect",
                      "drawArc",
                      "drawPath",
                      "直方图",
                      "饼状图"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab = (TabLayout) findViewById(R.id.tab);
        initData();
        vp  = (ViewPager) findViewById(R.id.vp);
        tab.setupWithViewPager(vp);
        adapter = new MyFragmentAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
    }

    private void initData() {

//        tab.addTab(tab.newTab().setText("drawColor"));
//        tab.addTab(tab.newTab().setText("drawCircle"));
//        tab.addTab(tab.newTab().setText("drawRect"));
//        tab.addTab(tab.newTab().setText("drawArc"));
//        tab.addTab(tab.newTab().setText("drawPath"));
//        tab.addTab(tab.newTab().setText("直方图"));
//        tab.addTab(tab.newTab().setText("饼状图"));

        fgs = new ArrayList<>();
        fgs.add(DrawColorFrag.newInstance());
        fgs.add(DrawCircleFrag.newInstance());
        fgs.add(DrawRectFrag.newInstance());
        fgs.add(DrawArcFrag.newInstance());
        fgs.add(DrawPathFrag.newInstance());
        fgs.add(DrawHistogramFrag.newInstance());
        fgs.add(DrawPieFrag.newInstance());

    }

    class MyFragmentAdapter extends FragmentPagerAdapter{

        // name fragment

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fgs.get(position);
        }

        @Override
        public int getCount() {
            return fgs.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
}
