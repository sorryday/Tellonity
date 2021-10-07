package com.example.fragmentuiprac2;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter
{
    //탭의 갯수
    int mNumOfTabs;

    public FragmentAdapter(FragmentManager fm, int numTabs)
    {
        super(fm);
        this.mNumOfTabs = numTabs;
    }

    @Override
    public Fragment getItem(int position)
    {
        // TabView 프레그먼트 설정
        switch (position)
        {
            case 0:
                Drone1Fragment tab1 = new Drone1Fragment();
                return tab1;
            case 1:
                Drone2Fragment tab2 = new Drone2Fragment();
                return tab2;
            case 2:
                CommunityFragment tab3 = new CommunityFragment();
                return tab3;
            case 3:
                CommunityFragment tab4 = new CommunityFragment();
                return tab4;
            default:
                return null;
        }
    }
    @Override
    public int getCount()
    {
        return mNumOfTabs;
    }
}
