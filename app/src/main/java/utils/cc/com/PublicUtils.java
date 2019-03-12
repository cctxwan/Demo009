package utils.cc.com;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import activity.cc.com.demo009.R;

/**
 * Created by admin on 2017/11/29.
 */

public class PublicUtils {

    public static String AppName = "Demo_009";

    private static Toast mToast;

    public static int islogin = 0;

    /**
     * 网络是否连接
     * @param context
     * @return
     */
    public static boolean checkNetworkConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null)
            return false;
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if (netinfo == null) {
            return false;
        }
        if (netinfo.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * 提示网络的自定的Toast
     * @param activity
     */
    public static void showToast(Activity activity){
        //获取布局xml
        View tempView = LayoutInflater.from(activity).inflate(R.layout.network_refresh ,null);
        //获取text
        TextView textView = tempView.findViewById(R.id.error);
        //获取图片
        Drawable drawable1 = activity.getResources().getDrawable(R.mipmap.deg);
        //设置图片大小
        drawable1.setBounds(0, 0, 80, 80);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        //把图片给textview加到左边
        textView.setCompoundDrawables(drawable1, null, null, null);//只放左边
        //添加需要显示的信息
        textView.setText("    世界上最遥远的距离就是没网");
        Toast toast = new Toast(activity);
        //显示在屏幕的中间
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(tempView);
        toast.show();
    }

    /**
     * 修改登录状态
     * @param activity
     * @param islogin
     */
//    public static void isLogin(Activity activity, String islogin){
//        //实例化SharedPreferences对象（第一步）
//        SharedPreferences mySharedPreferences = activity.getSharedPreferences("test",
//                Activity.MODE_PRIVATE);
//        //实例化SharedPreferences.Editor对象（第二步）
//        SharedPreferences.Editor editor = mySharedPreferences.edit();
//        //用putString的方法保存数据
//        editor.putString("islogin", islogin);
//        //提交当前数据
//        editor.commit();
//    }

    /**
     * 新闻详情页面收藏toast
     * @param text
     * @param img
     */
    public static void collectToast(Activity activity, String text, int img) {
        //获取布局xml
        View tempView = LayoutInflater.from(activity).inflate(R.layout.new_detail_collect ,null);
        //获取控件
        ImageView img_collect_msg = tempView.findViewById(R.id.img_collect_msg);
        TextView txt_collect_msg = tempView.findViewById(R.id.txt_collect_msg);


        //添加需要显示的信息
        img_collect_msg.setImageResource(img);
        txt_collect_msg.setText(text);
        Toast toast = new Toast(activity);

        //显示在屏幕的中间
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(tempView);
        toast.show();
    }

}
