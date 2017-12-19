package com.example.adila.songringguitarchordtabvideo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor spEdit;

    TabLayout myTab;
    ViewPager myPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTab = (TabLayout)findViewById(R.id.myTab);
        myPage = (ViewPager)findViewById(R.id.myPager);

        sp = getSharedPreferences(TabDownloaded.PREF_MHS,MODE_PRIVATE);
        spEdit = sp.edit();

        //swap tab fragment
        myPage.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
        myTab.setupWithViewPager(myPage);

        myTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                myPage.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //class pager adapter
    class MyPageAdapter extends FragmentPagerAdapter {

        String tab[] = {"Song List","Downloaded"};

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0){
                return new TabList();
            }
            if (position == 1){
                return new TabDownloaded();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tab.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tab[position];
        }
    }

    //class View Holder from database
    public static class SongListViewHolder extends RecyclerView.ViewHolder {
        Context ct;
        TextView title,musician,version;
        public SongListViewHolder(final View itemView) {
            super(itemView);
            ct=itemView.getContext();

            title = (TextView) itemView.findViewById(R.id.title_list);
            musician = (TextView) itemView.findViewById(R.id.musician_list);
            version = (TextView) itemView.findViewById(R.id.version_list);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ct, DetailList.class);
                    ct.startActivity(i);
                }
            });
        }
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.req:
                Intent i = new Intent(this, InputList.class);
                startActivity(i);
                break;
            case R.id.clearAll:
                spEdit.clear();
                spEdit.apply();
                onRestart();
                break;
            case R.id.about:
                Intent in = new Intent(this,About.class);
                startActivity(in);
        }

        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
