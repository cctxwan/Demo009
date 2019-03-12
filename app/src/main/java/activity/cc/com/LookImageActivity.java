package activity.cc.com;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

import java.io.File;

import activity.cc.com.demo009.R;
import utils.cc.com.PublicUtils;

public class LookImageActivity extends AppCompatActivity implements OnClickListener {

    ImageView img_look;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_look_image);
        initView();
        initData();
    }

    private void initData() {
        Intent getIntent = getIntent();
        String img_url = getIntent.getStringExtra("img_url");
        if(!img_url.equals("")){
            Log.i(PublicUtils.AppName, "LookImageActivity加载图片");
            img_look.setImageBitmap(getDiskBitmap(img_url));
        }
    }

    private Bitmap getDiskBitmap(String pathString) {
        Bitmap bitmap = null;
        try {
            File file = new File(pathString);
            if(file.exists()) {
                bitmap = BitmapFactory.decodeFile(pathString);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }


        return bitmap;
    }

    private void initView() {
        img_look = (ImageView) findViewById(R.id.img_look);

        img_look.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int temdId = v.getId();
        if(temdId == R.id.img_look){
            Log.i(PublicUtils.AppName, "img_look");
            finish();
            overridePendingTransition(0, 0);
        }
    }

    /**
     * 取消Activity关闭动画
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(0, 0);
        }
        return super.onKeyDown(keyCode, event);
    }

}
