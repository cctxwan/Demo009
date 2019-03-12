package adapter.cc.com;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/11/30.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments = new ArrayList<Fragment>();

    public FragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fragments = list;
    }



    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
