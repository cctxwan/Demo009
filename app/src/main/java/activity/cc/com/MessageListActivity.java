package activity.cc.com;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import activity.cc.com.demo009.R;
import adapter.cc.com.MessageAdapter;
import data.cc.com.MessageData;
import db.cc.com.SqliteDB;
import info.cc.com.MessageInfo;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import systemstatusbar.cc.com.StatusBarCompat;
import utils.cc.com.PublicUtils;
import utils.cc.com.SystemBarTintManager;

public class MessageListActivity extends SwipeBackActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    ImageView message_back_img, message_edit_img;

    ListView message_listView;

    List<MessageInfo> messages = new ArrayList<>();

    List<MessageData> datas = new ArrayList<>();

    MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        //判断当前系统版本是否>=Andoird4.4
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //设置状态栏背景状态
            //true：表明当前Android系统版本>=4.4
//            setTranslucentStatus(true);
        }
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.blue));


        initView();
        initData();

    }

    /**
     * 查询并初始化一些数据
     */
    private void initData() {
        messages = SqliteDB.getInstance(this).getMessages();
        if(messages.size() == 0){
            Log.i(PublicUtils.AppName, "Message暂无消息");
        }else{
            Log.i(PublicUtils.AppName, "有" + messages.size() + "条Message数据");
        }

        messageAdapter = new MessageAdapter(this, messages, message_listView);
        message_listView.setAdapter(messageAdapter);
        message_listView.deferNotifyDataSetChanged();
    }

    private void initView() {
        message_back_img = (ImageView) findViewById(R.id.message_back_img);
        message_edit_img = (ImageView) findViewById(R.id.message_edit_img);
        message_listView = (ListView) findViewById(R.id.message_listView);

        message_back_img.setOnClickListener(this);
        message_edit_img.setOnClickListener(this);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public void onClick(View v) {
        int temdId = v.getId();
        if(temdId == R.id.message_back_img){
            Log.i(PublicUtils.AppName, "message_back_img");
        }else if(temdId == R.id.message_edit_img){
            Log.i(PublicUtils.AppName, "message_edit_img");
            Intent intent = new Intent(MessageListActivity.this, MessageEditActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * 图片
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        messageAdapter.setOnImageItemClick(new MessageAdapter.OnImageItemClick() {
            @Override
            public void ImageItemClicksucc(View view, int position) {
                Log.i(PublicUtils.AppName, "点击的是第" + position + "个图片");
            }
        });
    }
}
