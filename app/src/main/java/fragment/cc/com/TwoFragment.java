package fragment.cc.com;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.widget.MsgView;

import java.util.ArrayList;

import activity.cc.com.demo009.R;
import systemstatusbar.cc.com.StatusBarCompat;

/**
 * 第三个显示fragment
 * @author 寇财玮
 * @version 2017年11月30日16:22:57
 */
public class TwoFragment extends Fragment implements OnTabSelectListener {

    //view为全局使用
    View view;

    ArrayList<Fragment> fragments = new ArrayList<>();

    final String[] titles = {
            "热门", "iOS", "Android"
            , "前端", "后端", "设计", "工具资源",
            "朋友圈", "QQ部落", "辅助功能"
    };

    MyPagerAdapter mAdapter;

    ViewPager two_fragment_viewPaper;

    SlidingTabLayout two_fragment_slidingTabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.two_fragment, container, false);

        initView();
        initFragment();
        initData();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getContext(), R.color.blue));
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getContext(), R.color.blue));
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {

//        for (String title : titles) {
//            fragments.add(SimpleCardFragment.getInstance(title));
//        }

        mAdapter = new MyPagerAdapter(getChildFragmentManager());
        two_fragment_viewPaper.setAdapter(mAdapter);

        two_fragment_slidingTabLayout.setViewPager(two_fragment_viewPaper);
        two_fragment_viewPaper.setCurrentItem(4);
        two_fragment_slidingTabLayout.showDot(4);

        two_fragment_slidingTabLayout.showMsg(3, 5);
        two_fragment_slidingTabLayout.setMsgMargin(3, 0, 10);
        MsgView rtv_2_3 = two_fragment_slidingTabLayout.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }

        two_fragment_slidingTabLayout.showMsg(5, 5);
        two_fragment_slidingTabLayout.setMsgMargin(5, 0, 10);
    }

    /**
     * 专门添加fragment
     */
    private void initFragment() {
        TwoTabOneFragment twoTabOneFragment = new TwoTabOneFragment();
        TwoTabTwoFragment twoTabTwoFragment = new TwoTabTwoFragment();
        TwoTabThreeFragment twoTabThreeFragment = new TwoTabThreeFragment();
        TwoTabFourFragment twoTabFourFragment = new TwoTabFourFragment();
        TwoTabfiveFragment twoTabfiveFragment = new TwoTabfiveFragment();
        TwoTabSixFragment twoTabSixFragment = new TwoTabSixFragment();
        TwoTabSevenFragment twoTabSevenFragment = new TwoTabSevenFragment();
        TwoTabEightFragment twoTabEightFragment = new TwoTabEightFragment();
        TwoTabNineFragment twoTabNineFragment = new TwoTabNineFragment();
        TwoTabTenFragment twoTabTenFragment = new TwoTabTenFragment();

        fragments.add(twoTabOneFragment);
        fragments.add(twoTabTwoFragment);
        fragments.add(twoTabThreeFragment);
        fragments.add(twoTabFourFragment);
        fragments.add(twoTabfiveFragment);
        fragments.add(twoTabSixFragment);
        fragments.add(twoTabSevenFragment);
        fragments.add(twoTabEightFragment);
        fragments.add(twoTabNineFragment);
        fragments.add(twoTabTenFragment);
    }

    /**
     * 初始化视图
     */
    private void initView() {
        two_fragment_viewPaper = view.findViewById(R.id.two_fragment_viewPaper);
        two_fragment_slidingTabLayout = view.findViewById(R.id.two_fragment_slidingTabLayout);
    }

    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
    }

}
