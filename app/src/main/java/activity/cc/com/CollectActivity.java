package activity.cc.com;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import activity.cc.com.demo009.R;
import adapter.cc.com.CollectAdapter;
import db.cc.com.SqliteDB;
import info.cc.com.NewsData;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import utils.cc.com.PublicUtils;
import utils.cc.com.QQListView;

public class CollectActivity extends SwipeBackActivity {

    QQListView collect_listview;

    CollectAdapter collectAdapter;

    List<NewsData> datas = new ArrayList<NewsData>();

    List<NewsData> newsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView();
        initData();
    }

    private void initData() {
        newsData = SqliteDB.getInstance(this).getNews();
        if(newsData.size() != 0){
            for (int i = 0; i < newsData.size(); i++){
                if(newsData.get(i).getCollectnew().equals("1")){
                    NewsData data = new NewsData();
                    data.setId(newsData.get(i).getId());
                    data.setImg_url(newsData.get(i).getImg_url());
                    data.setName(newsData.get(i).getName());
                    data.setTitle(newsData.get(i).getTitle());
                    data.setDate(newsData.get(i).getDate());
                    data.setNewsfrom(newsData.get(i).getNewsfrom());
                    data.setContent(newsData.get(i).getContent());
                    data.setContent_detail(newsData.get(i).getContent_detail());
                    data.setContent_url(newsData.get(i).getContent_url());
                    data.setCollectnew(newsData.get(i).getCollectnew());
                    datas.add(data);
                }
            }
        }else{
            Log.i(PublicUtils.AppName, "收藏页面请求失败");
        }

        collectAdapter = new CollectAdapter(this, datas, collect_listview);
        collect_listview.setAdapter(collectAdapter);
    }

    private void initView() {
        collect_listview = (QQListView) findViewById(R.id.collect_listview);

        collect_listview.setDelButtonClickListener(new QQListView.DelButtonClickListener() {
            @Override
            public void clickHappend(int position) {
                Log.i(PublicUtils.AppName, "position" + String.valueOf(datas.get(position).getId()));
                SqliteDB.getInstance(CollectActivity.this).updateNew("0", String.valueOf(datas.get(position).getId()));
                datas.remove(position);
                collectAdapter.notifyDataSetChanged();
                Toast.makeText(CollectActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
