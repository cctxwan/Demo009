package fragment.cc.com;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import activity.cc.com.CollectActivity;
import activity.cc.com.FeedBackActivity;
import activity.cc.com.InstallActivity;
import activity.cc.com.LoginActivity;
import activity.cc.com.MessageListActivity;
import activity.cc.com.demo009.R;
import activity.cc.com.SafetyActivity;
import adapter.cc.com.FourFragmentListAdapter;
import dialog.cc.com.CommomDialog;
import info.cc.com.FourFragmentListViewDataInfo;
import systemstatusbar.cc.com.StatusBarCompat;
import utils.cc.com.CleanMessageUtil;
import utils.cc.com.PublicUtils;

/**
 * 第四个显示fragment
 * @author 寇财玮
 * @version 2017年11月30日16:23:40
 */
public class FourFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    /** 状态栏颜色 */
    private boolean isHide = false;

    //view为全局使用
    View view;

    //背景图
    RelativeLayout four_rel_img_bg;

    //圆头像
    ImageView img_RoundPickect, four_fragment_img_left, four_fragment_img_right;

    //svip
    ImageView four_rel_img_svip;

    //ListView
    ListView four_lin_listview;

    //给listview加数据（对象类型）
    List<FourFragmentListViewDataInfo> datas = new ArrayList<FourFragmentListViewDataInfo>();

    //listview添加数据适配器
    FourFragmentListAdapter fourFragmentListAdapter;

    DrawerLayout drawerLayout;

    NavigationView navigationView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.four_fragment, container, false);
        initView();
        initData();
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

    /**
     * 初始化数据
     */
    private void initData() {
        datas.add(
                new FourFragmentListViewDataInfo(
                        "http://bpic.588ku.com/back_pic/03/57/59/8357a1517def4a3.jpg!ww800",
                        "反馈",
                        "")
                );
        datas.add(
                new FourFragmentListViewDataInfo(
                        "http://bpic.588ku.com/back_pic/03/57/59/8357a1517def4a3.jpg!ww800",
                        "设置",
                        "")
        );
        datas.add(
                new FourFragmentListViewDataInfo(
                        "http://bpic.588ku.com/back_pic/03/57/59/8357a1517def4a3.jpg!ww800",
                        "收藏",
                        "")
        );
        datas.add(
                new FourFragmentListViewDataInfo(
                        "http://bpic.588ku.com/back_pic/03/57/59/8357a1517def4a3.jpg!ww800",
                        "安全",
                        "")
        );
        datas.add(
                new FourFragmentListViewDataInfo(
                        "http://bpic.588ku.com/back_pic/03/57/59/8357a1517def4a3.jpg!ww800",
                        "缓存",
                        "缓存大小：")
        );
        datas.add(
                new FourFragmentListViewDataInfo(
                        "http://bpic.588ku.com/back_pic/03/57/59/8357a1517def4a3.jpg!ww800",
                        "注销",
                        "")
        );
        fourFragmentListAdapter = new FourFragmentListAdapter(getActivity(), datas, four_lin_listview);
        four_lin_listview.setAdapter(fourFragmentListAdapter);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //item.setChecked(true);
                Toast.makeText(getActivity(),item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });

        //关闭手势滑动控制侧滑页面
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        //允许手势滑动控制侧滑页面
//        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    /**
     * 初始化视图
     */
    private void initView() {
        four_rel_img_bg = view.findViewById(R.id.four_rel_img_bg);
        img_RoundPickect = view.findViewById(R.id.img_RoundPickect);
        four_fragment_img_left = view.findViewById(R.id.four_fragment_img_left);
        four_fragment_img_right = view.findViewById(R.id.four_fragment_img_right);
        four_rel_img_svip = view.findViewById(R.id.four_rel_img_svip);
        four_lin_listview = view.findViewById(R.id.four_lin_listview);

        drawerLayout = view.findViewById(R.id.activity_na);
        navigationView = view.findViewById(R.id.nav);

        four_lin_listview.setOnItemClickListener(this);
        four_fragment_img_left.setOnClickListener(this);
        four_fragment_img_right.setOnClickListener(this);
    }

    /**
     * 本页面的控件单击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        int temdId = v.getId();
        if(temdId == R.id.four_lin_listview){
            Log.i(PublicUtils.AppName, "four_lin_listview");
        } else if(temdId == R.id.four_fragment_img_left) {
            Log.i(PublicUtils.AppName, "four_fragment_img_left");
            if (drawerLayout.isDrawerOpen(navigationView)){
                drawerLayout.closeDrawer(navigationView);
            }else{
                drawerLayout.openDrawer(navigationView);
            }
        } else if(temdId == R.id.four_fragment_img_right) {
            Log.i(PublicUtils.AppName, "four_fragment_img_right");
            Intent intent = new Intent(getActivity(), MessageListActivity.class);
            startActivity(intent);
        }
    }

    /**
     * listview的item点击事件
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0){
            //反馈
            Log.i(PublicUtils.AppName, datas.get(position).getTitle());
            Intent intent = new Intent(getActivity(), FeedBackActivity.class);
            intent.putExtra("title", datas.get(position).getTitle());
            startActivity(intent);
        }else if(position == 1){
            //设置
            Log.i(PublicUtils.AppName, datas.get(position).getTitle());
            Intent intent = new Intent(getActivity(), InstallActivity.class);
            intent.putExtra("title", datas.get(position).getTitle());
            startActivity(intent);
        }else if(position == 2){
            //收藏
            Log.i(PublicUtils.AppName, datas.get(position).getTitle());
            Intent intent = new Intent(getActivity(), CollectActivity.class);
            intent.putExtra("title", datas.get(position).getTitle());
            startActivity(intent);
        }else if(position == 3){
            //安全
            Log.i(PublicUtils.AppName, datas.get(position).getTitle());
            Intent intent = new Intent(getActivity(), SafetyActivity.class);
            intent.putExtra("title", datas.get(position).getTitle());
            startActivity(intent);
        }else if(position == 4){
            //缓存
            Log.i(PublicUtils.AppName, datas.get(position).getTitle());
            //弹出友好提示框，提示用户确认清除缓存？
            new CommomDialog(getActivity(), R.style.dialog, "是否清除缓存？", new CommomDialog.OnCloseListener() {

                @Override
                public void onClick(Dialog dialog, boolean confirm) {
                    if(confirm){
                        Log.i(PublicUtils.AppName, "清除缓存");
                        CleanMessageUtil.clearAllCache(getContext());
                        fourFragmentListAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                }

            }).setTitle("提示").show();
        }else if(position == 5){
            //注销
            Log.i(PublicUtils.AppName, datas.get(position).getTitle());
            //弹出友好提示框，提示用户确认注销？
            new CommomDialog(getActivity(), R.style.dialog, "是否注销登录？", new CommomDialog.OnCloseListener() {

                @Override
                public void onClick(Dialog dialog, boolean confirm) {
                    if(confirm){
                        Log.i(PublicUtils.AppName, "注销登录");
                        ToLoginActivity();
                        dialog.dismiss();
                    }
                }

            }).setTitle("提示").show();
        }
    }

    /**
     * 进入登录界面
     */
    protected void ToLoginActivity() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}
