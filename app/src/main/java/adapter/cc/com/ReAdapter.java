package adapter.cc.com;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.cc.com.demo009.R;
import data.cc.com.lin_two_fragment_refresh_data;
import utils.cc.com.AsyncImageLoader;
import utils.cc.com.PublicUtils;

/**
 * TwoFragment页面下拉刷新的Adapter
 * @author 寇财玮
 * @version 2017年11月30日16:05:15
 */
public class ReAdapter extends RecyclerView.Adapter<ReAdapter.ViewHolder> {

    List<lin_two_fragment_refresh_data> datas = new ArrayList<lin_two_fragment_refresh_data>();

    AsyncImageLoader asyncImageLoader;

    public ReAdapter(List<lin_two_fragment_refresh_data> datas){
        this.datas = datas;

        this.asyncImageLoader = new AsyncImageLoader();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.three_fragment_refresh_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        Log.i(PublicUtils.AppName, "refresh" + i);
//        viewHolder.txt.setText("哈哈哈");
        asyncImageLoader.loadDrawable(i, datas.get(i).img_url, new AsyncImageLoader.ImageCallback() {
            @Override
            public void onImageLoad(Integer t, Bitmap bitmap) {
                Log.i(PublicUtils.AppName + "第二个页面refresh的item加载网络图片", "加载成功...");
                viewHolder.two_fragment_refresh_item_img.setImageBitmap(bitmap);
            }

            @Override
            public void onError(Integer t) {
                Log.i(PublicUtils.AppName + "第二个页面refresh的item加载网络图片", "加载失败...");
                viewHolder.two_fragment_refresh_item_img.setBackgroundResource(R.mipmap.ic_launcher);
            }
        });
        viewHolder.two_fragment_refresh_item_txt_name.setText(datas.get(i).name);
        viewHolder.two_fragment_refresh_item_title.setText(datas.get(i).title);
        viewHolder.two_fragment_refresh_item_date.setText(datas.get(i).date);
        viewHolder.two_fragment_refresh_item_from.setText(datas.get(i).from);
        viewHolder.two_fragment_refresh_item_content.setText(datas.get(i).content);
        asyncImageLoader.loadDrawable(i, datas.get(i).content_url, new AsyncImageLoader.ImageCallback() {
            @Override
            public void onImageLoad(Integer t, Bitmap bitmap) {
                Log.i(PublicUtils.AppName + "第二个页面refresh的item加载网络图片", "加载成功...");
                viewHolder.two_fragment_refresh_item_content_img.setImageBitmap(bitmap);
            }

            @Override
            public void onError(Integer t) {
                Log.i(PublicUtils.AppName + "第二个页面refresh的item加载网络图片", "加载失败...");
                viewHolder.two_fragment_refresh_item_content_img.setBackgroundResource(R.mipmap.ic_launcher);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout lin_two_fragment_refresh_item_lin;

        ImageView two_fragment_refresh_item_img, two_fragment_refresh_item_content_img;

        //item中的text
        TextView two_fragment_refresh_item_txt_name
                , two_fragment_refresh_item_title
                , two_fragment_refresh_item_date
                , two_fragment_refresh_item_from
                , two_fragment_refresh_item_content;

        public ViewHolder(View view) {
            super(view);
//            txt = view.findViewById(R.id.two_fragment_refresh_itwm_text);
            //整体布局
            lin_two_fragment_refresh_item_lin = view.findViewById(R.id.lin_two_fragment_refresh_item_lin);

            //内容左上角小图标
            two_fragment_refresh_item_img = view.findViewById(R.id.two_fragment_refresh_item_img);
            //内容名称
            two_fragment_refresh_item_txt_name = view.findViewById(R.id.two_fragment_refresh_item_txt_name);
            //内容标题
            two_fragment_refresh_item_title = view.findViewById(R.id.two_fragment_refresh_item_title);
            //内容日期
            two_fragment_refresh_item_date = view.findViewById(R.id.two_fragment_refresh_item_date);
            //内容出处
            two_fragment_refresh_item_from = view.findViewById(R.id.two_fragment_refresh_item_from);

            //内容详情
            two_fragment_refresh_item_content = view.findViewById(R.id.two_fragment_refresh_item_content);
            //详情大图片
            two_fragment_refresh_item_content_img = view.findViewById(R.id.two_fragment_refresh_item_content_img);

            lin_two_fragment_refresh_item_lin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onrefreshItemClick.succ(v, getPosition(), datas.get(getPosition()).getId());
                }
            });
        }

    }

    private onRefreshItemClick onrefreshItemClick;

    public interface onRefreshItemClick{
        void succ(View view, int position, int id);
    }

    public void setOnRefreshItemClick(onRefreshItemClick click){
        this.onrefreshItemClick = click;
    }

}