package com.example.haibeey.puzzle15;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by haibeey on 8/27/2017.
 */

public class swipeAdapter extends FragmentStatePagerAdapter {
    int[] arr=new int[5];
    public swipeAdapter(FragmentManager fm) {
        super(fm);
        arr[0]=R.drawable.main;
        arr[1]=R.drawable.dd;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment f=new BlankFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("data",arr[position]);
        bundle.putInt("pos",position);
        f.setArguments(bundle);
        return f;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
