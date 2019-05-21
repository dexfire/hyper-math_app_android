package work.mathwiki.base.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import work.mathwiki.core.logger.Logger;

public class FragmentPagesAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public FragmentPagesAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
        Logger.si("finished build FragmentPagesAdapter with "+ (list==null ? 0 : list.size()) + "child");
    }

    @Override
    public Fragment getItem(int i) {
        return list==null?null:list.get(i);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }
}
