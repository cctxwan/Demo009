package activity.cc.com;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import activity.cc.com.demo009.R;
import adapter.cc.com.StatusExpandAdapter;
import data.cc.com.TimeLineData;
import db.cc.com.SqliteDB;
import dialog.cc.com.AddTimeLineDialog;
import info.cc.com.ChildStatusEntity;
import info.cc.com.GroupStatusEntity;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import utils.cc.com.FileHelper;
import utils.cc.com.PublicUtils;

public class TimeLineActivity extends SwipeBackActivity implements View.OnClickListener {

    TextView txt_addtime;

    ExpandableListView timeline_listView;

    List<TimeLineData> datas = new ArrayList<TimeLineData>();

    FileHelper file = new FileHelper(TimeLineActivity.this);

    StatusExpandAdapter statusAdapter;

    ArrayList<String> list;

    String[][] childTimeArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        List<TimeLineData> timeLineData = SqliteDB.getInstance(this).getDatas();
        if(timeLineData.size() != 0){
            childTimeArray = new String[64][64];
            Log.i(PublicUtils.AppName, "时光轴日志已逃离时空隧道(读取成功)");
            for (int i = 0; i < timeLineData.size(); i++){
                Log.i(PublicUtils.AppName, timeLineData.get(i).getContent());
                Log.i(PublicUtils.AppName, timeLineData.get(i).getData());
//                String str = timeLineData.get(i).getData();
//                String date = str.substring(0, 10);
//                Log.i(PublicUtils.AppName, "截取之后的日期" + date);
//                if(timeLineData.get(i).getData().indexOf(date) != -1){
//                    for (int j = 0; j < timeLineData.size(); j++){
                list = new ArrayList<String>();
                list.add(timeLineData.get(i).getContent());
                String strone1[] = list.toArray(new String[list.size()]);
                childTimeArray[i] = strone1;
//                    }
//                }
//                printTwo(childTimeArray);
                TimeLineData data = new TimeLineData();
                data.setContent(timeLineData.get(i).getContent());
                data.setData(timeLineData.get(i).getData());
                datas.add(data);
            }
        }else{
            Log.i(PublicUtils.AppName, "时光轴日志未逃离时空隧道(读取失败)");
        }

        statusAdapter = new StatusExpandAdapter(this, getListData());
        timeline_listView.setAdapter(statusAdapter);
        timeline_listView.setGroupIndicator(null); // 去掉默认带的箭头
        timeline_listView.setSelection(0);// 设置默认选中项

        // 遍历所有group,将所有项设置成默认展开
        int groupCount = timeline_listView.getCount();
        for (int i = 0; i < groupCount; i++) {
            timeline_listView.expandGroup(i);
        }

        timeline_listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // TODO Auto-generated method stub
                return true;
            }
        });

    }

    private static void printTwo(String arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + ",");
            }
            System.out.println();
        }
    }

    /**
     * 填充时光轴上的级数据
     * @return
     */
    private List<GroupStatusEntity> getListData() {
        List<GroupStatusEntity> groupList;
        groupList = new ArrayList<GroupStatusEntity>();
//        String[][] childTimeArray = new String[][]{ { "小一", "小二" }, { "二一", "二二" },{ "三一", "三二" }, { "四一", "四儿" } };
        for (int i = 0; i < datas.size(); i++) {
            GroupStatusEntity groupStatusEntity = new GroupStatusEntity();
            groupStatusEntity.setGroupName(datas.get(i).getData());

            List<ChildStatusEntity> childList = new ArrayList<ChildStatusEntity>();

            for (int j = 0; j < childTimeArray[i].length; j++) {
                ChildStatusEntity childStatusEntity = new ChildStatusEntity();
                childStatusEntity.setCompleteTime(childTimeArray[i][j]);
                childStatusEntity.setIsfinished(true);
                childList.add(childStatusEntity);
            }

            groupStatusEntity.setChildList(childList);
            groupList.add(groupStatusEntity);
        }
        return groupList;
    }

    /**
     * 初始化视图
     */
    private void initView() {
        txt_addtime = (TextView) findViewById(R.id.txt_addtime);
        timeline_listView = (ExpandableListView) findViewById(R.id.timeline_listView);

        txt_addtime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int temdId = v.getId();
        if(temdId == R.id.txt_addtime){
            Log.i(PublicUtils.AppName, "txt_addtime-->添加时光日记");
            //添加时光日记
            new AddTimeLineDialog(this, R.style.dialog, "记录时光日志：", new AddTimeLineDialog.OnSubmitListener() {
                @Override
                public void onClick(Dialog dialog, boolean confirm, String content) {
                    if(confirm){
                        Log.i(PublicUtils.AppName, "确定添加时光日志:" + content);
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                        TimeLineData timeLineData = new TimeLineData();
                        timeLineData.setContent(content);
                        timeLineData.setData(df.format(new Date()));
                        int num = SqliteDB.getInstance(TimeLineActivity.this).saveTimeData(timeLineData);
                        if(num == 1){
                            Log.i(PublicUtils.AppName, "时光轴日志已进入时空隧道(保存成功)");
                            refresh();
                        }else{
                            Log.i(PublicUtils.AppName, "时光轴日志已进入黑洞隧道(保存失败)");
                        }
                        dialog.dismiss();
                    }
                }
            }).setTitle("添加时光轴").show();
        }
    }

    /**
     * 刷新
     */
    private void refresh() {
        finish();
        Intent intent = new Intent(TimeLineActivity.this, TimeLineActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
