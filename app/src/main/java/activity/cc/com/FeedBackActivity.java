package activity.cc.com;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import activity.cc.com.demo009.R;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import utils.cc.com.PublicUtils;

public class FeedBackActivity extends SwipeBackActivity implements View.OnClickListener {

    RelativeLayout feed_back_rel_title;

    TextView txt_feed_back_detail, txt_detail_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //获取接收的值
        Intent getIntent = getIntent();
        String title = getIntent.getStringExtra("title");
        Log.i(PublicUtils.AppName, title);

        txt_feed_back_detail.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                String content = txt_feed_back_detail.getText().toString().trim();
                txt_detail_size.setText(content.length() + "/"
                        + "200");
            }

        });
    }

    /**
     * 初始化视图
     */
    private void initView() {
        feed_back_rel_title = (RelativeLayout) findViewById(R.id.feed_back_rel_title);
        txt_feed_back_detail = (TextView) findViewById(R.id.txt_feed_back_detail);
        txt_detail_size = (TextView) findViewById(R.id.txt_detail_size);

        feed_back_rel_title.setOnClickListener(this);
        txt_feed_back_detail.setOnClickListener(this);
    }

    /**
     * xml中控件的单击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        int temdId = v.getId();
        if(temdId == R.id.txt_feed_back_detail){
            Log.i(PublicUtils.AppName, "txt_feed_back_detail");
        }
    }
}
