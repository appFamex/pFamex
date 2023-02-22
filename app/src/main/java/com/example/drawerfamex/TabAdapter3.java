package com.example.drawerfamex;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class TabAdapter3 extends FragmentPagerAdapter {

    private final ArrayList<Fragment> FragmentArrayList = new ArrayList<>();
    private final ArrayList<String> FragmentTitle = new ArrayList<>();


    //Nuevo Constructor 03-09-22
    public TabAdapter3(FragmentManager fm){
        super(fm);
    }
    //----------------------

    public TabAdapter3(@NonNull FragmentManager fm, int behavior) {
        super(fm,behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return FragmentArrayList.get(position);
    }

    @Override
    public int getCount() {

        return FragmentArrayList.size();
    }

    public void addFragments (Fragment fragment, String title){
        FragmentArrayList.add(fragment);
        FragmentTitle.add(title);
    }
    public void addFragments (Fragment fragment){

        FragmentArrayList.add(fragment);
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentTitle.get(position);
    }
}
