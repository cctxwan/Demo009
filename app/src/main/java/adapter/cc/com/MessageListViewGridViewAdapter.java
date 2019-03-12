package adapter.cc.com;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import activity.cc.com.LookImageActivity;
import activity.cc.com.demo009.R;
import utils.cc.com.PublicUtils;

/**
 * Created by admin on 2017/12/18.
 */

public class MessageListViewGridViewAdapter extends BaseAdapter {

    //数据源
    List<String> mList = new ArrayList<>();

    public List<String> getmList() {
        return mList;
    }

    public void setmList(List<String> mList) {
        this.mList = mList;
    }

    Activity mContext;

    GridView message_item_gridView;

    LayoutInflater inflater;

    private OnImageItemGridViewClick onImageItemGridViewClick;

    public void setOnImageItemGridViewClick(OnImageItemGridViewClick onImageItemGridViewClick) {
        this.onImageItemGridViewClick = onImageItemGridViewClick;
    }

    public interface OnImageItemGridViewClick{
        void ImageItemClicksucc(View view, int position);
    }

    public MessageListViewGridViewAdapter(Activity context, GridView message_item_gridView) {
        super();
        this.mContext = context;
        this.message_item_gridView = message_item_gridView;

        inflater = mContext.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.message_listview_item_gridview_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

//        convertView.setTag(position);

        final String info = getItem(position);
        if(position == 4){
            Log.i(PublicUtils.AppName, info.toString());
        }

//        message_listView_gridView_img.setBackgroundResource(R.mipmap.ic_launcher);

        holder.message_listView_gridView_img.setImageBitmap(compressImageFromFile(info));

        holder.message_listView_gridView_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //暂无法使用回调方法，因为经过嵌套的view得到的单击事件是空
//                onImageItemGridViewClick.ImageItemClicksucc(v, position);
//                Toast.makeText(mContext, "你点击的是第" + position + "张图片，图片的地址是：" + info, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, LookImageActivity.class);
                intent.putExtra("img_url", info);
                mContext.startActivity(intent);
                mContext.overridePendingTransition(R.anim.zoomin, R.anim.zoomin);
            }
        });

        return convertView;
    }

    class ViewHolder {

        ImageView message_listView_gridView_img;

        public ViewHolder(View view) {
            message_listView_gridView_img = view.findViewById(R.id.message_listView_gridView_img);

            notifyDataSetChanged();
        }

    }

    /**
     * 压缩本地图片
     * @param srcPath
     * @return
     */
    private Bitmap compressImageFromFile(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;//只读边,不读内容
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = 800f;//
        float ww = 480f;//
        int be = 1;
        if (w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置采样率

        newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;//该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收

        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
//      return compressBmpFromBmp(bitmap);//原来的方法调用了这个方法企图进行二次压缩
        //其实是无效的,大家尽管尝试
        return bitmap;
    }

}
