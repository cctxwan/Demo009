package activity.cc.com;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import activity.cc.com.demo009.R;
import adapter.cc.com.NewDetailItemAdapter;
import db.cc.com.SqliteDB;
import info.cc.com.NewsData;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import utils.cc.com.PublicUtils;

public class TwoFragDetailActivity extends SwipeBackActivity implements View.OnClickListener {

    LinearLayout lin_new_detail;

    ImageView img_about;

    ListView new_detail_listview;

    NewDetailItemAdapter newDetailItemAdapter;

    int newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_two_frag_detail);
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        Bundle bundle = getIntent().getExtras();
        newId = bundle.getInt("newId");
        List<NewsData> data = SqliteDB.getInstance(this).selectByIdNew(String.valueOf(newId));

        Log.i(PublicUtils.AppName, data.get(0).getCollectnew());
        newDetailItemAdapter = new NewDetailItemAdapter(this, data, new_detail_listview);
        new_detail_listview.setAdapter(newDetailItemAdapter);
    }

    /**
     * 初始化视图
     */
    private void initView() {
        lin_new_detail = (LinearLayout) findViewById(R.id.lin_new_detail);
        img_about = (ImageView) findViewById(R.id.img_about);

        new_detail_listview = (ListView) findViewById(R.id.new_detail_listview);

        img_about.setOnClickListener(this);
    }

    /**
     * 页面的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        int temdId = v.getId();
        if(temdId == R.id.img_about){
            Log.i(PublicUtils.AppName, "img_about");
            showAboutPopupWindows();
        }
    }

    /**
     * 出现about弹框
     */
    private void showAboutPopupWindows() {
        View view = LayoutInflater.from(this).inflate(R.layout.new_detail_about, null, false);

        TextView txt_about = view.findViewById(R.id.txt_collect);

        List<NewsData> data = SqliteDB.getInstance(this).selectByIdNew(String.valueOf(newId));
        Log.i(PublicUtils.AppName, "收藏属性值" + data.get(0).getCollectnew());
        if(data.get(0).getCollectnew().equals("0")){

        }else{
            txt_about.setText("已收藏");
            txt_about.setTextColor(Color.GRAY);
        }
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popWindow.setAnimationStyle(R.anim.anim_pop);  //设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);

        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效


        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(view, lin_new_detail.getWidth(), lin_new_detail.getHeight());

        //设置popupWindow里的按钮的事件
        txt_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
                List<NewsData> data = SqliteDB.getInstance(TwoFragDetailActivity.this).selectByIdNew(String.valueOf(newId));
                Log.i(PublicUtils.AppName, "收藏属性值" + data.get(0).getCollectnew());
                if(data.get(0).getCollectnew().equals("0")){
                    SqliteDB.getInstance(TwoFragDetailActivity.this).updateNew("1", String.valueOf(newId));
                    PublicUtils.collectToast(TwoFragDetailActivity.this, "已收藏", R.mipmap.hoy);
                    Log.i(PublicUtils.AppName, "修改之后收藏属性值" + data.get(0).getCollectnew());
                }else{
//                    PublicUtils.collectToast(TwoFragDetailActivity.this, "收藏已存在", R.mipmap.hpb);
                }

            }
        });
    }
}
