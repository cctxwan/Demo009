package activity.cc.com;

import android.app.Dialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.cc.com.demo009.R;
import adapter.cc.com.G_Image_Adapter;
import adapter.cc.com.R_Image_Adapter;
import adapter.cc.com.Y_Image_Adapter;
import dialog.cc.com.UpgradeRuleDialog;
import info.cc.com.VIP_G_Image;
import info.cc.com.VIP_R_Image;
import info.cc.com.VIP_Y_Image;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import utils.cc.com.PublicUtils;

public class VIPActivity extends SwipeBackActivity implements View.OnClickListener {

    TextView upgrade_rule;

    ListView r_listview, g_listview, y_listview;

    List<VIP_R_Image> r_datas = new ArrayList<VIP_R_Image>();

    List<VIP_G_Image> g_datas = new ArrayList<VIP_G_Image>();

    List<VIP_Y_Image> y_datas = new ArrayList<VIP_Y_Image>();

    R_Image_Adapter r_Image_Adapter;

    G_Image_Adapter g_Image_Adapter;

    Y_Image_Adapter y_Image_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView();
        initData();
    }

    private void initData() {

        r_datas.add(new VIP_R_Image(R.mipmap.ra));
        r_datas.add(new VIP_R_Image(R.mipmap.rb));
        r_datas.add(new VIP_R_Image(R.mipmap.rc));
        r_datas.add(new VIP_R_Image(R.mipmap.rd));
        r_datas.add(new VIP_R_Image(R.mipmap.re));
        r_datas.add(new VIP_R_Image(R.mipmap.rf));
        r_datas.add(new VIP_R_Image(R.mipmap.rg));
        r_datas.add(new VIP_R_Image(R.mipmap.rh));
        r_datas.add(new VIP_R_Image(R.mipmap.ri));

        g_datas.add(new VIP_G_Image(R.mipmap.ga));
        g_datas.add(new VIP_G_Image(R.mipmap.gb));
        g_datas.add(new VIP_G_Image(R.mipmap.gc));
        g_datas.add(new VIP_G_Image(R.mipmap.gd));
        g_datas.add(new VIP_G_Image(R.mipmap.ge));
        g_datas.add(new VIP_G_Image(R.mipmap.gf));
        g_datas.add(new VIP_G_Image(R.mipmap.gg));
        g_datas.add(new VIP_G_Image(R.mipmap.gh));
        g_datas.add(new VIP_G_Image(R.mipmap.gi));

        y_datas.add(new VIP_Y_Image(R.mipmap.ya));
        y_datas.add(new VIP_Y_Image(R.mipmap.yb));
        y_datas.add(new VIP_Y_Image(R.mipmap.yc));
        y_datas.add(new VIP_Y_Image(R.mipmap.yd));
        y_datas.add(new VIP_Y_Image(R.mipmap.ye));
        y_datas.add(new VIP_Y_Image(R.mipmap.yf));
        y_datas.add(new VIP_Y_Image(R.mipmap.yh));
        y_datas.add(new VIP_Y_Image(R.mipmap.yi));

        r_Image_Adapter = new R_Image_Adapter(this, r_datas, r_listview);
        g_Image_Adapter = new G_Image_Adapter(this, g_datas, g_listview);
        y_Image_Adapter = new Y_Image_Adapter(this, y_datas, y_listview);


        r_listview.setAdapter(r_Image_Adapter);
        g_listview.setAdapter(g_Image_Adapter);
        y_listview.setAdapter(y_Image_Adapter);

    }

    private void initView() {

        upgrade_rule = (TextView) findViewById(R.id.upgrage_rule);

        upgrade_rule.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        r_listview = (ListView) findViewById(R.id.r_listview);
        g_listview = (ListView) findViewById(R.id.g_listview);
        y_listview = (ListView) findViewById(R.id.y_listview);

        upgrade_rule.setOnClickListener(this);
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        int temdId = v.getId();
        if(temdId == R.id.upgrage_rule){
            Log.i(PublicUtils.AppName, "upgrage_rule");
            new UpgradeRuleDialog(this, R.style.dialog, new UpgradeRuleDialog.OnCloseListener() {

                @Override
                public void onClick(Dialog dialog, boolean confirm) {
                    if(confirm){
                        dialog.dismiss();
                    }
                }
            }).setTitle("拇指游玩征信授权书").show();
        }
    }
}
