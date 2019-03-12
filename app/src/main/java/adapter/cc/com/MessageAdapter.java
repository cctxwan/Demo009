package adapter.cc.com;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import activity.cc.com.demo009.R;
import db.cc.com.SqliteDB;
import dialog.cc.com.DeleteMessageDialog;
import info.cc.com.MessageInfo;
import utils.cc.com.PublicUtils;

/**
 * Created by admin on 2017/12/18.
 */

public class MessageAdapter extends BaseAdapter {

    Activity activity;

    //用于接收动态信息
    List<MessageInfo> messages = new ArrayList<>();

    ListView message_listView;

    LayoutInflater inflater;

    private OnImageItemClick onImageItemClick;

    public void setOnImageItemClick(OnImageItemClick onImageItemClick) {
        this.onImageItemClick = onImageItemClick;
    }

    public interface OnImageItemClick{
        void ImageItemClicksucc(View view, int position);
    }

    public MessageAdapter(Activity activity, List<MessageInfo> messages, ListView message_listView){
        this.activity = activity;
        this.messages = messages;
        this.message_listView = message_listView;

        this.inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        int count = messages.size() / 1;
        if (messages.size() % 1 > 0) {
            count++;
        }
        return count;
    }

    @Override
    public MessageInfo getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.message_listview_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.listView_img_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(PublicUtils.AppName, "listView_img_message展开Message框");
                showMessagepop(v);
            }
        });


        final ViewHolder finalHolder = holder;
        holder.message_item_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                finalHolder.messageListViewGridViewAdapter.setOnImageItemGridViewClick(new MessageListViewGridViewAdapter.OnImageItemGridViewClick() {
                    @Override
                    public void ImageItemClicksucc(View view, int position) {
                        onImageItemClick.ImageItemClicksucc(view, position);
                    }
                });
            }
        });

        final MessageInfo info = getItem(position);
        Log.i(PublicUtils.AppName, info.toString());


        holder.txt_message_delete.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //delete
                 new DeleteMessageDialog(activity, R.style.dialog, "", new DeleteMessageDialog.OnCloseListener() {
                     @Override
                     public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm){
                            dialog.dismiss();
                            Log.i(PublicUtils.AppName, "delete Message");
                            int isdeletenum = SqliteDB.getInstance(activity).deleteById(info.getMid());
                            if(isdeletenum == 1){
//                                messages.remove(info.getMid());
//                                messages.
                                Log.i(PublicUtils.AppName, "删除成功！");
                                //刷新item
                                notifyDataSetChanged();
//                                holder.messageListViewGridViewAdapter.notifyDataSetChanged();
                            }
                        }
                     }
                 }).setTitle("确认删除？").show();
             }
        });

        List<String> imgurl = new ArrayList<>();
        imgurl.clear();

        String[] strs = info.getImgUrl().split(",");
        for(int i = 0,len = strs.length; i < len; i++){
            System.out.println(strs[i].toString());
            imgurl.add(strs[i].toString());
        }

        if(imgurl.size() == 0 || imgurl.get(0).equals("default")){
            holder.message_item_gridView.setVisibility(View.GONE);
        }

        holder.messageListViewGridViewAdapter.setmList(imgurl);
        /** 加上这句话滑动时会卡顿（很卡），不加的话滑动的时候就不会动态的赋值给GridView，导致GridView的图片与内容不一致，真尼玛事多，劳资真服了，f**k..... */
        /** 先注释，我宁愿内容与图片不一致 */
        holder.message_item_gridView.setAdapter(holder.messageListViewGridViewAdapter);
        holder.txt_content.setText(info.getContent());

        return convertView;
    }

    class ViewHolder {

        GridView message_item_gridView;
        TextView txt_content, txt_message_delete;
        ImageView listView_img_message;
        MessageListViewGridViewAdapter messageListViewGridViewAdapter;

        public ViewHolder(View view) {

            txt_content = view.findViewById(R.id.message_item_txt_content);
            txt_message_delete = view.findViewById(R.id.txt_message_delete);
            message_item_gridView = view.findViewById(R.id.message_item_gridView);
            listView_img_message = view.findViewById(R.id.listView_img_message);

            messageListViewGridViewAdapter = new MessageListViewGridViewAdapter(activity, message_item_gridView);
            message_item_gridView.setAdapter(messageListViewGridViewAdapter);
        }

    }
    /**
     * listView_img_message展开Message框
     */
    private void showMessagepop(View v) {
        View view = View.inflate(activity, R.layout.messagepop, null);
        LinearLayout lin_message_yee = view.findViewById(R.id.lin_message_yee);
        LinearLayout lin_message_comment = view.findViewById(R.id.lin_message_comment);

        //获取PopupWindow中View的宽高
        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT
                , LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);//popupwindow设置焦点

        popupWindow.setAnimationStyle(R.style.popupwindow_message_style);

        popupWindow.setBackgroundDrawable(new ColorDrawable(00000000));//设置背景
        popupWindow.setOutsideTouchable(true);//点击外面窗口消失
        // popupWindow.showAsDropDown(v,0,0);
        //获取点击View的坐标
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        popupWindow.showAsDropDown(v);
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
        popupWindow.setAnimationStyle(android.R.style.Animation_Translucent);//设置动画

        /**
         * 点赞
         */
        lin_message_yee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        /**
         * 评论
         */
        lin_message_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                Toast.makeText(activity, "比较复杂，暂不开发此功能！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
