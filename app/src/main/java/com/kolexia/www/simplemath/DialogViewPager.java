package com.kolexia.www.simplemath;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by mike on 11/28/15.
 */
public class DialogViewPager extends FragmentActivity {
    ViewPager vp;
    ArrayList<Pecahan> numList;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        vp = new ViewPager(this);
        vp.setId(R.id.viewpager);
        setContentView(vp);
        FragmentManager fm = getSupportFragmentManager();
        numList = SingletonAngka.get(this).getAllAngka();
        vp.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Pecahan p = numList.get(position);
                return FragmentDialogViewPager.newInstance(p.getId(),position,position);
            }

            @Override
            public int getCount() {
                return numList.size();
            }
        });
        UUID id = (UUID)getIntent().getSerializableExtra(FragmentDialogViewPager.KEY);
        for(int i = 0; i < numList.size(); i++){
            if(numList.get(i).getId().equals(id)){
                vp.setCurrentItem(i);
                break;
            }
        }
    }
}
