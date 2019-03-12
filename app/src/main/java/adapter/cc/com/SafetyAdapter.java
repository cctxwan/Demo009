package adapter.cc.com;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.cc.com.demo009.R;
import data.cc.com.SafetyData;
import utils.cc.com.AsyncImageLoader;

/**
 * Created by admin on 2017/12/6.
 */

public class SafetyAdapter extends BaseAdapter {

    Activity activity;

    List<SafetyData> datas = new ArrayList<SafetyData>();

    ListView safety_listview;

    LayoutInflater inflater;

    AsyncImageLoader asyncImageLoader;

    public SafetyAdapter(Activity activity, List<SafetyData> datas, ListView safety_listview){
        this.activity = activity;
        this.datas = datas;
        this.safety_listview = safety_listview;

        inflater = activity.getLayoutInflater();
        asyncImageLoader = new AsyncImageLoader();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public SafetyData getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.safety_listview_item, null);
        }
        convertView.setTag(position);

        SafetyData info = getItem(position);

        TextView txt_title = convertView.findViewById(R.id.txt_safety_listview_item_title);
        txt_title.setText(info.name);


        return convertView;
    }
}
