package work.mathwiki.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MainFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList = null;

    public MainFragmentAdapter(FragmentManager fm, List<Fragment> list){
        super(fm);
        mFragmentList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return (mFragmentList==null)?null:mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return (mFragmentList==null)?0:mFragmentList.size();
    }



}
