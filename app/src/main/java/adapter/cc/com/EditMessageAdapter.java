package adapter.cc.com;

import android.app.Activity;
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

import activity.cc.com.demo009.R;
import data.cc.com.ImageData;
import utils.cc.com.PublicUtils;

/**
 * Created by admin on 2017/12/15.
 */

public class EditMessageAdapter extends BaseAdapter {

    Activity activity;

    List<ImageData> datas = new ArrayList<>();

    GridView gv_edit_message;

    LayoutInflater inflater;

    ImageView gv_edit_message_item, gv_edit_message_item_close;

    boolean showImageClose = false;

    ImageClose imageClose;

    public void setImageClose(ImageClose imageClose, boolean showImageClose) {
        this.imageClose = imageClose;
        this.showImageClose = showImageClose;
        notifyDataSetChanged();
    }

    interface ImageClose{

    };

    DeleteImage deleteImage;

    public void setDeleteImage(DeleteImage deleteImage) {
        this.deleteImage = deleteImage;
    }

    public interface DeleteImage{
        void deleteSucc(View view,int position);
    };

    public EditMessageAdapter(Activity activity, List<ImageData> datas, GridView gv_edit_message){
        this.activity = activity;
        this.datas = datas;
        this.gv_edit_message = gv_edit_message;

        this.inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public ImageData getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        //当回收的view为空，重新获取
        if(convertView == null){
            convertView = inflater.inflate(R.layout.gv_edit_message_item, null);
        }
        convertView.setTag(position);
        //拿到每一个item的值
        ImageData info = getItem(position);

        gv_edit_message_item = convertView.findViewById(R.id.gv_edit_message_item);
        gv_edit_message_item_close = convertView.findViewById(R.id.gv_edit_message_item_close);

        if(showImageClose){
            gv_edit_message_item_close.setVisibility(View.VISIBLE);
        }else{
            gv_edit_message_item_close.setVisibility(View.GONE);
        }

        gv_edit_message_item_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datas.remove(position);
                if(datas.size() == 0){
                    Log.i(PublicUtils.AppName, "delete结束，默认加载Add按钮！");
                    datas.add(new ImageData("default"));
                    showImageClose = false;
                    gv_edit_message_item.setImageResource(R.mipmap.qq_groupmanager_edit_add_icon);
                }
                deleteImage.deleteSucc(v, position);
                Log.i(PublicUtils.AppName, "delete succ");
            }
        });

        if(info.url.equals("default")){
            Log.i(PublicUtils.AppName, "默认加载Add按钮！");
        }else{
            gv_edit_message_item.setImageBitmap(compressImageFromFile(info.url));
        }

        return convertView;
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
