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

import java.util.ArrayList;
import java.util.List;

import activity.cc.com.demo009.R;
import info.cc.com.NewsData;
import utils.cc.com.AsyncImageLoader;
import utils.cc.com.PublicUtils;

/**
 * Created by admin on 2017/12/6.
 */

public class NewDetailItemAdapter extends BaseAdapter {

    Activity activity;

    List<NewsData> data = new ArrayList<NewsData>();

    ListView new_detail_listview;

    LayoutInflater inflater;

    ImageView img_url, img_content_url, img_about;

    TextView txt_content, txt_newname, txt_title, txt_date, txt_from, txt_content_detail;

    AsyncImageLoader asyncImageLoader;

    public NewDetailItemAdapter(Activity activity, List<NewsData> data, ListView new_detail_listview){
        this.activity = activity;
        this.data = data;
        this.new_detail_listview = new_detail_listview;

        asyncImageLoader = new AsyncImageLoader();
        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public NewsData getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.new_detail_list_item, null);
        }
        convertView.setTag(position);

        NewsData info = getItem(position);
        img_url = convertView.findViewById(R.id.img_url);
        img_content_url = convertView.findViewById(R.id.img_content_url);

        txt_content = convertView.findViewById(R.id.txt_content);
        txt_newname = convertView.findViewById(R.id.txt_newname);
        txt_title = convertView.findViewById(R.id.txt_title);
        txt_date = convertView.findViewById(R.id.txt_date);
        txt_from = convertView.findViewById(R.id.txt_from);
        txt_content_detail = convertView.findViewById(R.id.txt_content_detail);
        asyncImageLoader = new AsyncImageLoader();
        if(data.size() != 0){
            Log.i(PublicUtils.AppName, "新闻详情页查询成功");
            for (int i = 0; i < data.size(); i++){
                asyncImageLoader.loadDrawable(i, info.img_url, new AsyncImageLoader.ImageCallback() {
                    @Override
                    public void onImageLoad(Integer t, Bitmap bitmap) {
                        Log.i(PublicUtils.AppName + "新闻详情页加载网络图片", "加载成功...");
                        img_url.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Integer t) {
                        Log.i(PublicUtils.AppName + "第二个页面refresh的item加载网络图片", "加载失败...");
                        img_url.setBackgroundResource(R.mipmap.ic_launcher);
                    }
                });
                txt_content.setText(info.getContent());
                txt_newname.setText(info.getName());
                txt_title.setText(info.getTitle());
                txt_date.setText(info.getDate());
                txt_from.setText(info.getNewsfrom());
                asyncImageLoader.loadDrawable(i, info.content_url, new AsyncImageLoader.ImageCallback() {
                    @Override
                    public void onImageLoad(Integer t, Bitmap bitmap) {
                        Log.i(PublicUtils.AppName + "新闻详情页加载网络图片", "加载成功...");
                        img_content_url.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Integer t) {
                        Log.i(PublicUtils.AppName + "第二个页面refresh的item加载网络图片", "加载失败...");
                        img_content_url.setBackgroundResource(R.mipmap.ic_launcher);
                    }
                });
                txt_content_detail.setText(info.getContent_detail());
            }
        }
        return convertView;
    }
}
