package adapter.cc.com;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import activity.cc.com.demo009.R;
import info.cc.com.FourFragmentListViewDataInfo;
import utils.cc.com.AsyncImageLoader;
import utils.cc.com.CleanMessageUtil;
import utils.cc.com.PublicUtils;

/**
 * Created by admin on 2017/11/30.
 */

public class FourFragmentListAdapter extends BaseAdapter {

    Activity activity;

    List<FourFragmentListViewDataInfo> datas;

    ListView four_lin_listview;

    LayoutInflater inflater;

    AsyncImageLoader asyncImageLoader;

    public FourFragmentListAdapter(Activity activity, List<FourFragmentListViewDataInfo> datas, ListView four_lin_listview){
        this.activity = activity;
        this.datas = datas;
        this.four_lin_listview = four_lin_listview;

        this.inflater = activity.getLayoutInflater();
        this.asyncImageLoader = new AsyncImageLoader();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public FourFragmentListViewDataInfo getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.four_fragment_list_item, null);
        }
        convertView.setTag(position);

        FourFragmentListViewDataInfo info = getItem(position);

        TextView txt_title = convertView.findViewById(R.id.four_fragment_list_item_title);
        TextView txt_detail = convertView.findViewById(R.id.four_fragment_list_item_detail);
        txt_title.setText(info.title);
        try {
            if(info.detail != null && !info.detail.equals("")){
                txt_detail.setText(info.detail + CleanMessageUtil.getTotalCacheSize(activity));
            }else{
                txt_detail.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        asyncImageLoader.loadDrawable(position, info.img_url, new AsyncImageLoader.ImageCallback() {
            @Override
            public void onImageLoad(Integer t, Bitmap bitmap) {
                View view = four_lin_listview.findViewWithTag(t);
                if(view != null){
                    Log.i(PublicUtils.AppName + "第四个页面的listview加载网络图片", "加载成功...");
                    ImageView img = view.findViewById(R.id.img_RoundPickect);
                    img.setImageBitmap(bitmap);
                }
            }

            @Override
            public void onError(Integer t) {
                View view = four_lin_listview.findViewWithTag(t);
                if(view != null){
                    Log.i(PublicUtils.AppName + "第四个页面的listview加载网络图片", "加载失败...");
                    ImageView iv = view.findViewById(R.id.img_RoundPickect);
                    iv.setBackgroundResource(R.mipmap.ic_launcher);
                }
            }
        });
        return convertView;
    }
}
