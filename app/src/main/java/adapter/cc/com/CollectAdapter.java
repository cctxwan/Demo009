package adapter.cc.com;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import activity.cc.com.demo009.R;
import info.cc.com.NewsData;
import utils.cc.com.AsyncImageLoader;

/**
 * Created by admin on 2017/12/6.
 */

public class CollectAdapter extends BaseAdapter {

    Activity activity;

    List<NewsData> datas;

    ListView collect_listview;

    LayoutInflater inflater;

    AsyncImageLoader asyncImageLoader;

    public CollectAdapter(Activity activity, List<NewsData> datas, ListView collect_listview){
        this.activity = activity;
        this.datas = datas;
        this.collect_listview = collect_listview;

        inflater = activity.getLayoutInflater();
        asyncImageLoader = new AsyncImageLoader();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public NewsData getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.collect_listview_item, null);
        }
        convertView.setTag(position);

        NewsData info = getItem(position);

        TextView txt_collect_item_content = convertView.findViewById(R.id.txt_collect_item_content);
        TextView txt_collect_item_deta = convertView.findViewById(R.id.txt_collect_item_deta);
        TextView txt_collect_item_from = convertView.findViewById(R.id.txt_collect_item_from);

        txt_collect_item_content.setText(info.content);
        txt_collect_item_deta.setText(info.date);
        txt_collect_item_from.setText(info.newsfrom);

        return convertView;
    }
}
