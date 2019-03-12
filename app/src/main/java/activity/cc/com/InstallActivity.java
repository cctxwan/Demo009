package activity.cc.com;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import activity.cc.com.demo009.R;
import adapter.cc.com.InstallListViewAdapter;
import dialog.cc.com.BindPhoneDialog;
import info.cc.com.InstallListViewInfo;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import utils.cc.com.PublicUtils;

public class InstallActivity extends SwipeBackActivity implements AdapterView.OnItemClickListener {

    ListView install_listview;

    InstallListViewAdapter installListViewAdapter;

    List<InstallListViewInfo> datas = new ArrayList<InstallListViewInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView();
        initData();
    }

    private void initData() {
        datas.add(
                new InstallListViewInfo("手机号码", "未绑定")
        );
        datas.add(
                new InstallListViewInfo("登录达人", "3天")
        );
        datas.add(
                new InstallListViewInfo("辅助功能", "")
        );
        datas.add(
                new InstallListViewInfo("VIP特权", "")
        );

        installListViewAdapter = new InstallListViewAdapter(this, datas, install_listview);
        install_listview.setAdapter(installListViewAdapter);
    }

    private void initView() {
        install_listview = (ListView) findViewById(R.id.install_listview);

        install_listview.setOnItemClickListener(this);
    }

    /**
     * listView的item点击事件
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(PublicUtils.AppName, "InstallActivity的listview的item点击事件");
        if(datas.get(position).getTitle().equals("手机号码")){
            if(datas.get(position).getDetail().equals("未绑定")){
                showBindPhoneDialog(position);
            }
        }else if(datas.get(position).getTitle().equals("VIP特权")){
            Intent intent = new Intent(this, VIPActivity.class);
            startActivity(intent);
//            finish();
        }else if(datas.get(position).getTitle().equals("辅助功能")){
            Intent intent = new Intent(this, TimeLineActivity.class);
            startActivity(intent);
//            finish();
        }
    }

    /**
     * 绑定手机号
     */
    private void showBindPhoneDialog(final int position) {
        //注销
        Log.i(PublicUtils.AppName, datas.get(position).getTitle());
        //弹出友好提示框，提示用户确认注销？
        new BindPhoneDialog(this, R.style.dialog, "手机号：", new BindPhoneDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if(confirm){
                    Log.i(PublicUtils.AppName, "提交手机号");
                    datas.get(position).setDetail("已绑定");
                    installListViewAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        }).setTitle("绑定手机号").show();
    }
}