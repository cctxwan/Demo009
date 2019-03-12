package activity.cc.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import activity.cc.com.demo009.R;
import utils.cc.com.PublicUtils;

/**
 * 新人引导界面，本界面只在第一次进入时显示，需手动关闭
 * @author 寇财玮
 * @version 2017年11月29日16:13:17
 */
public class GuidanceActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img_guidance_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guidance);
        Log.i(PublicUtils.AppName, "GuidanceActivity");
        initView();
    }

    /**
     * 用来获取xml中的控件
     */
    private void initView() {
        img_guidance_close = (ImageView) findViewById(R.id.img_guidance_close);

        img_guidance_close.setOnClickListener(this);
    }


    /**
     * 获取到控件的点击事件在这里执行
     * @param v
     */
    @Override
    public void onClick(View v) {
        int temdId = v.getId();
        if(temdId == R.id.img_guidance_close){
            Log.i(PublicUtils.AppName, "img_guidance_close");
            finish();
        }
    }
}
