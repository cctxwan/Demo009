package adapter.cc.com;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.cc.com.demo009.R;
import info.cc.com.InstallListViewInfo;

/**
 * Created by admin on 2017/11/30.
 */

public class InstallListViewAdapter extends BaseAdapter {

    Activity activity;

    List<InstallListViewInfo> datas = new ArrayList<InstallListViewInfo>();

    ListView install_listview;

    LayoutInflater inflater;


    public InstallListViewAdapter(Activity activity, List<InstallListViewInfo> datas, ListView install_listview){
        this.activity = activity;
        this.datas = datas;
        this.install_listview = install_listview;

        this.inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public InstallListViewInfo getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.install_listview_item, null);
        }
        convertView.setTag(position);

        InstallListViewInfo info = getItem(position);

        TextView txt_title = convertView.findViewById(R.id.txt_install_listview_item_title);
        TextView txt_detail = convertView.findViewById(R.id.txt_install_listview_item_detail);
        txt_title.setText(info.title);
        if(info.detail != null && !info.detail.equals("")){
            txt_detail.setText(info.detail);
            if(info.detail.equals("未绑定")){
                txt_detail.setTextColor(Color.GRAY);
            }
        }else{
            txt_detail.setVisibility(View.GONE);
        }
        return convertView;
    }
}