package activity.cc.com;

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
import adapter.cc.com.SafetyAdapter;
import data.cc.com.SafetyData;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import utils.cc.com.PublicUtils;

public class SafetyActivity extends SwipeBackActivity implements AdapterView.OnItemClickListener {

    ListView safety_listview;

    SafetyAdapter safetyAdapter;

    List<SafetyData> datas = new ArrayList<SafetyData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        datas.add(
                new SafetyData(
                        "http://bpic.588ku.com/back_pic/03/57/59/8357a1517def4a3.jpg!ww800",
                        "修改密码"
                )
        );

        safetyAdapter = new SafetyAdapter(this, datas, safety_listview);
        safety_listview.setAdapter(safetyAdapter);
    }

    /**
     * 初始化视图
     */
    private void initView() {
        safety_listview = (ListView) findViewById(R.id.safety_listview);

        safety_listview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(PublicUtils.AppName, "safetyActivity的listview的item点击事件");
        if(datas.get(position).getName().equals("修改密码")){
            Log.i(PublicUtils.AppName, "safetyActivity的修改密码");
        }
    }
}
