package adapter.cc.com;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import activity.cc.com.demo009.R;
import info.cc.com.VIP_R_Image;

/**
 * Created by admin on 2017/12/1.
 */

public class R_Image_Adapter extends BaseAdapter {

    Activity activity;

    List<VIP_R_Image> datas;

    ListView r_listview;

    LayoutInflater inflater;

    public R_Image_Adapter(Activity activity, List<VIP_R_Image> datas, ListView r_listview){
        this.activity = activity;
        this.datas = datas;
        this.r_listview = r_listview;

        this.inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public VIP_R_Image getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.vip_image_item, null);
        }
        convertView.setTag(position);

        VIP_R_Image info = getItem(position);

        ImageView img = convertView.findViewById(R.id.vip_image_item_img);
        img.setImageResource(info.img_id);

        return convertView;
    }
}
