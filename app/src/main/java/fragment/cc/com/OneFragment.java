package fragment.cc.com;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

import activity.cc.com.demo009.R;
import systemstatusbar.cc.com.StatusBarCompat;
import utils.cc.com.App;
import utils.cc.com.CustomScrollView;
import utils.cc.com.MarqueeView;
import utils.cc.com.PublicUtils;

/**
 * 第一个显示的Fragment(轮播图+导航栏+全局滑动+layout)
 * @author 寇财玮
 * @version 2017年11月30日15:18:56
 */
public class OneFragment extends Fragment implements MarqueeView.onItemClickListener, XBanner.XBannerAdapter, XBanner.OnItemClickListener  {

    /** 状态栏颜色 */
    private boolean isHide = false;

    //全部xml的
    View view;

    //轮播图控件
    /** 轮播图 */
    XBanner xBanner;

    /** ScrollView */
    CustomScrollView mCustomScrollView;

    /** 轮播图图片资源加载器 */
    List<String> imgesUrl = new ArrayList<>();

    /** 轮播图文字资源加载器 */
    List<String> textUrl = new ArrayList<>();

    MarqueeView one_fragment_txt_marquee;

    String[] contentArray = new String[] {
            "游戏打造虚拟故宫&东京持刀砍人事件",
            "何洁离婚案开庭&男子掐死临盆妻子",
            "军机零件掉幼儿园&赵丽颖悼念粉丝",
            "欧豪深夜删微博&饭店倒闭当菜窖",
            "周杰伦现身学校&赵薇澄清传闻"};

    Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.one_fragment, container, false);
        initView();
        initData();
        setAdapter();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StatusBarCompat.translucentStatusBar(getActivity(), isHide);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            StatusBarCompat.translucentStatusBar(getActivity(), isHide);
            isHide = !isHide;
        }
    }

    private void setAdapter() {
        xBanner.setmAdapter(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        one_fragment_txt_marquee.setTextArray(contentArray);
        imgesUrl.add("http://bpic.588ku.com/back_pic/03/70/72/5257b6c12d89875.jpg");
        imgesUrl.add("http://bpic.588ku.com/back_pic/00/13/15/08564453d0190aa.jpg");
        imgesUrl.add("http://bpic.588ku.com/back_pic/00/14/65/3256657136926fa.jpg");
        imgesUrl.add("http://bpic.588ku.com/back_pic/03/72/92/6657b9a240d3d1f.jpg");

        textUrl.add("1");
        textUrl.add("2");
        textUrl.add("3");
        textUrl.add("4");

        xBanner.setData(imgesUrl, textUrl);
    }

    @Override
    public void loadBanner(XBanner banner, Object model, View view, int position) {
        Glide.with(getActivity()).load(imgesUrl.get(position)).into((ImageView) view);
    }

    @Override
    public void onItemClick(XBanner banner, int position) {
        Log.i(PublicUtils.AppName, "position=" + position);
    }

    /**
     * 初始化视图
     */
    private void initView() {

        mCustomScrollView = (CustomScrollView) view.findViewById(R.id.scrollView);
        mCustomScrollView.setOnScrollChangeListener(new CustomScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChanged(CustomScrollView scrollView, int l, int t, int oldl, int oldt) {
                Log.i(PublicUtils.AppName, "滑动监听-->");
                int i = App.dip2px(getActivity(), t);
                int dp = App.px2dp(getActivity(), i);
                if(dp > 400){
                    StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getContext(), R.color.blue));
                } else {
                    StatusBarCompat.translucentStatusBar(getActivity(), isHide);
                }
            }
        });


        /** 轮播图 */
        xBanner = view.findViewById(R.id.xbanner);
        /** 设置轮播图的样式 */
        xBanner.setPageTransformer(Transformer.Accordion);
        /** 开始跑起来 */
        xBanner.startAutoPlay();

        /** 跑马灯 */
        one_fragment_txt_marquee = view.findViewById(R.id.one_fragment_txt_marquee);
        one_fragment_txt_marquee.setOnItemClickListener(this);
        
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                one_fragment_txt_marquee.resume();
            }
        }, 1000);
        xBanner.startAutoPlay();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        one_fragment_txt_marquee.destory();
        xBanner.stopAutoPlay();
    }

    /**
     * 跑马灯的item内容
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        Log.i(PublicUtils.AppName, "one_fragment_txt_marquee跑马灯" + position);
    }

}