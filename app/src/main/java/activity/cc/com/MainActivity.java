package activity.cc.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;

import activity.cc.com.demo009.R;
import fragment.cc.com.FourFragment;
import fragment.cc.com.OneFragment;
import fragment.cc.com.ThreeFragment;
import fragment.cc.com.TwoFragment;
import utils.cc.com.PublicUtils;

/**
 * 主界面
 * @author 寇财玮
 * @version 2017年11月29日16:00:09
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /** 底部导航栏 */
    LinearLayout Lin_one, Lin_two, Lin_three, Lin_four;

    /** 第一个fragment */
    public static final int PAGE_COMMON = 0;
    /** 第二个fragment */
    public static final int PAGE_TRANSLUCENT = 1;
    /** 第三个fragment */
    public static final int PAGE_COORDINATOR = 2;
    /** 第四个fragment */
    public static final int PAGE_COLLAPSING_TOOLBAR = 3;

    /** 管理fragment */
    private HashMap<Integer,Fragment> fragments = new HashMap<>();

    //当前activity的fragment控件
    private int fragmentContentId = R.id.fragment_content;

    /** 设置默认的fragment */
    private int currentTab;

    //获取底部导航栏的ImageView
    ImageView img_one_bottom, img_two_bottom, img_three_bottom, img_four_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //判断当前系统版本是否>=Andoird4.4
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //设置状态栏背景状态
            //true：表明当前Android系统版本>=4.4
        }

        //判断是否有网
        if(!PublicUtils.checkNetworkConnection(this)){
//            Toast.makeText(MainActivity.this, "请先连接网络", Toast.LENGTH_SHORT).show();
            Log.i(PublicUtils.AppName, "没有连接网络");
            //提示没有网络，显示Toast框
            PublicUtils.showToast(this);
            return;
        }
        //判断是否第一次进入app，如果是，弹出隐藏的透明activity进行新人礼包放送
        try{
            if(isFirstInAPPMain()){
                //界面弹出
                initFrag();
                initView();
                defaultFragment();
                initData();
                //新人共享页面
                ToGuidanceActivity();
            }else{
                //界面弹出
                initFrag();
                initView();
                defaultFragment();
                initData();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initFrag() {
        fragments.put(PAGE_COMMON, new OneFragment());
        fragments.put(PAGE_TRANSLUCENT, new TwoFragment());
        fragments.put(PAGE_COORDINATOR, new ThreeFragment());
        fragments.put(PAGE_COLLAPSING_TOOLBAR, new FourFragment());
    }

    private void defaultFragment() {
        //设置当前currentTab底部的状态
        SelectColor(PAGE_COMMON);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(fragmentContentId,fragments.get(PAGE_COMMON));
        currentTab = PAGE_COMMON;
        ft.commit();
    }

    /**
     * 初始化数据
     */
    private void initData() {

    }

    /**
     * 点击切换下部按钮
     * @param page
     */
    private void changeTab(int page) {
        //默认的currentTab == 当前的页码，不做任何处理
        if (currentTab == page) {
            return;
        }

        //获取fragment的页码
        Fragment fragment = fragments.get(page);
        //fragment事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //如果该Fragment对象被添加到了它的Activity中，那么它返回true，否则返回false。
        //当前activity中添加的不是这个fragment
        if(!fragment.isAdded()){
            //所以将他加进去
            ft.add(fragmentContentId,fragment);
        }
        //隐藏当前currentTab的
        ft.hide(fragments.get(currentTab));
        //显示现在page的
        ft.show(fragments.get(page));
        //设置当前currentTab底部的状态
        SelectColor(currentTab);
        //当前显示的赋值给currentTab
        currentTab = page;
        //设置当前currentTab底部的状态
        SelectColor(currentTab);
        //activity被销毁？  ！否
        if (!this.isFinishing()) {
            //允许状态丢失
            ft.commitAllowingStateLoss();
        }
    }

    /**
     * 当页面选中时改变当前的导航栏蓝色和图片的状态
     * @param position 当前页面
     */
    private void SelectColor(int position) {
        if(position == 0){
            //给底部到导航栏的image更换图片
            img_one_bottom.setImageResource(R.mipmap.xiao);
            img_two_bottom.setImageResource(R.mipmap.ws);
            img_three_bottom.setImageResource(R.mipmap.ws);
            img_four_bottom.setImageResource(R.mipmap.nu);

            //给底部导航栏更换背景色
            Lin_one.setBackgroundResource(R.color.blue);
            Lin_two.setBackgroundResource(R.color.white);
            Lin_three.setBackgroundResource(R.color.white);
            Lin_four.setBackgroundResource(R.color.white);
        } else if (position == 1){
            //给底部到导航栏的image更换图片
            img_one_bottom.setImageResource(R.mipmap.ws);
            img_two_bottom.setImageResource(R.mipmap.xiao);
            img_three_bottom.setImageResource(R.mipmap.ws);
            img_four_bottom.setImageResource(R.mipmap.nu);

            //给底部导航栏更换背景色
            Lin_one.setBackgroundResource(R.color.white);
            Lin_two.setBackgroundResource(R.color.blue);
            Lin_three.setBackgroundResource(R.color.white);
            Lin_four.setBackgroundResource(R.color.white);
        } else if (position == 2){
            //给底部到导航栏的image更换图片
            img_one_bottom.setImageResource(R.mipmap.nu);
            img_two_bottom.setImageResource(R.mipmap.ws);
            img_three_bottom.setImageResource(R.mipmap.xiao);
            img_four_bottom.setImageResource(R.mipmap.ws);

            //给底部导航栏更换背景色
            Lin_one.setBackgroundResource(R.color.white);
            Lin_two.setBackgroundResource(R.color.white);
            Lin_three.setBackgroundResource(R.color.blue);
            Lin_four.setBackgroundResource(R.color.white);
        } else if (position == 3){
            //给底部到导航栏的image更换图片
            img_one_bottom.setImageResource(R.mipmap.nu);
            img_two_bottom.setImageResource(R.mipmap.ws);
            img_three_bottom.setImageResource(R.mipmap.ws);
            img_four_bottom.setImageResource(R.mipmap.xiao);

            //给底部导航栏更换背景色
            Lin_one.setBackgroundResource(R.color.white);
            Lin_two.setBackgroundResource(R.color.white);
            Lin_three.setBackgroundResource(R.color.white);
            Lin_four.setBackgroundResource(R.color.blue);
        }
    }

    /**
     * 初始化视图
     */
    private void initView() {
        //底部导航栏的父控件
        Lin_one = (LinearLayout) findViewById(R.id.Lin_one);
        Lin_two = (LinearLayout) findViewById(R.id.Lin_two);
        Lin_three = (LinearLayout) findViewById(R.id.Lin_three);
        Lin_four = (LinearLayout) findViewById(R.id.Lin_four);

        img_one_bottom = (ImageView) findViewById(R.id.img_one_bottom);
        img_two_bottom = (ImageView) findViewById(R.id.img_two_bottom);
        img_three_bottom = (ImageView) findViewById(R.id.img_three_bottom);
        img_four_bottom = (ImageView) findViewById(R.id.img_four_bottom);


        Lin_one.setOnClickListener(this);
        Lin_two.setOnClickListener(this);
        Lin_three.setOnClickListener(this);
        Lin_four.setOnClickListener(this);
    }

    /**
     * 没网的情况下进入刷新页面
     */
//    private void ToRefreshActivity() {
//        Intent intent = new Intent(MainActivity.this, RefreshActivity.class);
//        startActivity(intent);
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//    }

    /**
     * 第一次进入引导透明页
     */
    private void ToGuidanceActivity() {
        Intent intent = new Intent(MainActivity.this, GuidanceActivity.class);
        startActivity(intent);
    }

    /**
     * 判断是否第一次进入app的Main界面
     * @return
     */
    public boolean isFirstInAPPMain() throws PackageManager.NameNotFoundException {
        //获取包信息
        PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
        //当前app包的版本号（XML配置中的version）
        int currentVersion = info.versionCode;
        //本地存储文件
        SharedPreferences sf = PreferenceManager.getDefaultSharedPreferences(this);
        //将当前获取到的版本号存储起来，因为第一次运行，所以存入0
        int lastVersion = sf.getInt("/data/xml/firstinappmain.xml", 0);
        //当前版本号大于之前版本号说明该版本号第一次进入，故加载welcome页面启动动画效果
        if(currentVersion > lastVersion){
            //因为第一次进入，所以肯定会执行这段代码，执行之后，下次进入就应该将将0改为当前版本存储
            sf.edit().putInt("/data/xml/firstinappmain.xml", currentVersion).commit();
            return true;
        }
        return false;
    }

    /**
     * 所有的控件在这里进行点击（单击）事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        int temdId = v.getId();
        if(temdId == R.id.Lin_one){
            Log.i(PublicUtils.AppName, "Lin_one");
            changeTab(PAGE_COMMON);
        } else if (temdId == R.id.Lin_two){
            Log.i(PublicUtils.AppName, "Lin_two");
            changeTab(PAGE_TRANSLUCENT);
        } else if (temdId == R.id.Lin_three){
            Log.i(PublicUtils.AppName, "Lin_three");
            changeTab(PAGE_COORDINATOR);
        } else if (temdId == R.id.Lin_four){
            Log.i(PublicUtils.AppName, "Lin_four");
            changeTab(PAGE_COLLAPSING_TOOLBAR);
        }
    }

    long temptime;

    /**
     * 点击两次返回才退出
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if((keyCode == KeyEvent.KEYCODE_BACK)&&(event.getAction() == KeyEvent.ACTION_DOWN)) {
            if(System.currentTimeMillis() - temptime >2000){
                System.out.println(Toast.LENGTH_LONG);
                Toast.makeText(this, "请在按一次返回退出", Toast.LENGTH_LONG).show();
                temptime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0); //凡是非零都表示异常退出!0表示正常退出!
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
