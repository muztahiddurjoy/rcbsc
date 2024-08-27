package com.scientia.science.rcbscassignmentupload;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> list ;
    public PagerAdapter(@NonNull FragmentManager fm, List<Fragment> frag) {
        super(fm);
        this.list = frag;
    }

    @NonNull

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
