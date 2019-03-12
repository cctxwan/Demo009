package activity.cc.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import activity.cc.com.demo009.R;
import utils.cc.com.PublicUtils;

public class RefreshActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txt_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_refresh);
        Log.i(PublicUtils.AppName, "RefreshActivity");
        initView();
    }

    /**
     * 获取xml的控件
     */
    private void initView() {
        txt_refresh = (TextView) findViewById(R.id.txt_refresh);

        txt_refresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int temdId = v.getId();
        if(temdId == R.id.txt_refresh){
            Log.i(PublicUtils.AppName, "txt_refresh");
            if(PublicUtils.checkNetworkConnection(this)){
                finish();
            }else{
                initView();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            return true;
        }
        return false;
    }
}
