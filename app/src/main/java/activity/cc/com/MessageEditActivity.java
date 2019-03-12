package activity.cc.com;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.lqr.imagepicker.ImagePicker;
import com.lqr.imagepicker.bean.ImageItem;
import com.lqr.imagepicker.ui.ImageGridActivity;
import com.lqr.imagepicker.ui.ImagePreviewActivity;

import java.util.ArrayList;
import java.util.List;

import activity.cc.com.demo009.R;
import adapter.cc.com.EditMessageAdapter;
import data.cc.com.ImageData;
import db.cc.com.SqliteDB;
import info.cc.com.MessageInfo;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import systemstatusbar.cc.com.StatusBarCompat;
import utils.cc.com.PublicUtils;

public class MessageEditActivity extends SwipeBackActivity implements View.OnClickListener, AdapterView.OnItemClickListener, ImagePicker.OnImageSelectedListener, AdapterView.OnItemLongClickListener {

    TextView txt_edit_message_submit, txt_edit_message_canal, txt_edit_message_content;

    GridView gv_edit_message;

    List<ImageData> datas = new ArrayList<>();

    EditMessageAdapter editMessageAdapter;

    public static final int IMAGE_PICKER = 100;

    ImageData imageData;

    ImagePicker imagePicker;

    /** 状态栏颜色 */
    private boolean isHide = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_edit);

        //判断当前系统版本是否>=Andoird4.4
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //设置状态栏背景状态
            //true：表明当前Android系统版本>=4.4
//            setTranslucentStatus(true);
        }
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.blue));

        imageData = new ImageData("default");
        datas.add(imageData);


        initView();
        initData();

    }

    private void initData() {
        //初始化图片加载库
        imagePicker = ImagePicker.getInstance();
        imagePicker.clear();
        //实现图片点击监听事件
        imagePicker.addOnImageSelectedListener(this);

        //创建数据源并填充adapter适配器加载GridView
        editMessageAdapter = new EditMessageAdapter(this, datas, gv_edit_message);
        gv_edit_message.setAdapter(editMessageAdapter);
        editMessageAdapter.notifyDataSetChanged();
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void initView() {
        txt_edit_message_submit = (TextView) findViewById(R.id.txt_edit_message_submit);
        txt_edit_message_canal = (TextView) findViewById(R.id.txt_edit_message_canal);
        txt_edit_message_content = (TextView) findViewById(R.id.txt_edit_message_content);

        //获取GridView控件
        gv_edit_message = (GridView) findViewById(R.id.gv_edit_message);
        gv_edit_message.setOnItemClickListener(this);
        gv_edit_message.setOnItemLongClickListener(this);

        txt_edit_message_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int temdId = v.getId();
        if(temdId == R.id.txt_edit_message_submit){
            Log.i(PublicUtils.AppName, "txt_edit_message_submit");
            String content = txt_edit_message_content.getText().toString().trim();
            if(content.length() == 0){
                Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            String AllImgPath = "";
            //将图片的地址拼起来存入Sqlite数据库中
            for(int i = 0; i < datas.size(); i++){
                AllImgPath += datas.get(i).url + ",";
            }
            System.out.println(AllImgPath);
            //将数据存入数据库
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setContent(content);
            messageInfo.setImgUrl(AllImgPath);
            //保存
            int saveResult = SqliteDB.getInstance(this).saveMessage(messageInfo);
            if(saveResult == 1){
                Log.i(PublicUtils.AppName, "Message存储成功！");
                datas.clear();
                Toast.makeText(this, "publish succ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MessageListActivity.class);
                startActivity(intent);
                this.finish();
            }else{
                Log.i(PublicUtils.AppName, "Message存储失败！");
            }
        }else if(temdId == R.id.txt_edit_message_canal){
            Log.i(PublicUtils.AppName, "txt_edit_message_canal");
        }else if(temdId == R.id.txt_edit_message_content){
            Log.i(PublicUtils.AppName, "txt_edit_message_content");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(this, "您点击的是第" + position + "个", Toast.LENGTH_SHORT).show();
        if(position == 0){
            if(datas.get(position).url.equals("default")){
                Log.i(PublicUtils.AppName, "Default添加按钮");
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, IMAGE_PICKER);
            }
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(this, "您长按的是第" + position + "个", Toast.LENGTH_SHORT).show();
        Log.i(PublicUtils.AppName, "触发长按事件");
        if(position == 0){
            if(datas.get(position).url.equals("default")){
                Log.i(PublicUtils.AppName, "触发长按添加按钮事件");
            }else{
                editMessageAdapter.setImageClose(null, true);
                editMessageAdapter.setDeleteImage(new EditMessageAdapter.DeleteImage() {
                    @Override
                    public void deleteSucc(View view, int position) {
//                        datas.remove(position);
                        Log.i(PublicUtils.AppName, "delete succ");
                        editMessageAdapter.notifyDataSetChanged();
                    }
                });
            }
        }else{
            editMessageAdapter.setImageClose(null, true);
            editMessageAdapter.setDeleteImage(new EditMessageAdapter.DeleteImage() {
                @Override
                public void deleteSucc(View view, int position) {
//                    datas.remove(position);
                    Log.i(PublicUtils.AppName, "delete succ");
                    editMessageAdapter.notifyDataSetChanged();
                }
            });
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {//返回多张照片
            if (data != null) {
                //是否发送原图
                boolean isOrig = data.getBooleanExtra(ImagePreviewActivity.ISORIGIN, false);
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                Log.e("CSDN_LQR", isOrig ? "发原图" : "不发原图");//若不发原图的话，需要在自己在项目中做好压缩图片算法
                datas.clear();
                for (ImageItem imageItem : images) {
                    Log.e("CSDN_LQR", imageItem.path);
                    String ImgUrl = imageItem.path;
                    imageData = new ImageData(ImgUrl);
                    datas.add(imageData);
                }
                initData();
            }
        }
    }

    @Override
    public void onImageSelected(int position, ImageItem item, boolean isAdd) {

    }
}
