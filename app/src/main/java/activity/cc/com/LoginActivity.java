package activity.cc.com;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import activity.cc.com.demo009.R;
import db.cc.com.SqliteDB;
import info.cc.com.LoginInfo;
import utils.cc.com.PublicUtils;
import utils.cc.com.StringUtil;
import utils.cc.com.SystemBarTintManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_username, et_password;

    TextView txt_submit, txt_regist;

    ImageView img_close, img_lookpwd;

    CheckBox checkBox;

    LoginInfo info;

    SharedPreferences sf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //判断当前系统版本是否>=Andoird4.4
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //设置状态栏背景状态
            //true：表明当前Android系统版本>=4.4
            setTranslucentStatus(true);
        }
        //实例化SystemBarTintManager
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        // 通知标题栏所需颜色
        tintManager.setStatusBarTintResource(R.color.blue);

        initView();
        initData();
        showData();
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

    /**
     * 显示数据
     */
    private void showData() {
        sf = getSharedPreferences("userinfo", Context.MODE_WORLD_READABLE);
        OutPut();

        List<LoginInfo> user = SqliteDB.getInstance(getApplicationContext()).loadUser();
        if(user != null){
//            et_Name.setText(user.get(0).getUsername());
//            et_Psd.setText(user.get(0).getPassword());
        }
    }

    /**
     * 取之前保存的值
     */
    private void OutPut() {
        //第一个参数是文件名，第二个参数是模式（不明白可以去补习一下SharedPreferences的知识）
        SharedPreferences shared = getSharedPreferences("mypsd", MODE_PRIVATE);
        //第一个参数就是关键字，第二个参数为默认值，意思是说如果没找到值就用默认值代替
        String name1 = shared.getString("name", "");//同上，若没找到就让它为空""
        String psd1 = shared.getString("psd", "");
        boolean ischecked1 = shared.getBoolean("isChecked", false);
        et_username.setText(name1);
        et_password.setText(psd1);
        checkBox.setChecked(ischecked1);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        et_username.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                if (StringUtil.isEmpty(et_username.getText().toString())) {
                    img_close.setVisibility(View.GONE);
                } else {
                    img_close.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringUtil.isEmpty(et_username.getText().toString())) {
                    img_close.setVisibility(View.GONE);
                } else {
                    img_close.setVisibility(View.VISIBLE);
                }
            }
        });
        et_password.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                if (StringUtil.isEmpty(et_password.getText().toString())) {
                    img_lookpwd.setVisibility(View.GONE);
                } else {
                    img_lookpwd.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringUtil.isEmpty(et_password.getText().toString())) {
                    img_lookpwd.setVisibility(View.GONE);
                } else {
                    img_lookpwd.setVisibility(View.VISIBLE);
                }

            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    Log.i(PublicUtils.AppName, "已选中");
                }else{
                    Log.i(PublicUtils.AppName, "未选中");
                }
            }
        });
    }

    /**
     * 初始化视图
     */
    private void initView() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        txt_submit = (TextView) findViewById(R.id.txt_submit);
        txt_regist = (TextView) findViewById(R.id.txt_regist);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        img_close = (ImageView) findViewById(R.id.img_close);
        img_lookpwd = (ImageView) findViewById(R.id.img_lookpwd);

        txt_submit.setOnClickListener(this);
        txt_regist.setOnClickListener(this);

        img_close.setOnClickListener(this);
        img_lookpwd.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            return true;
        }
        return false;
    }

    /**
     * xml控件的单击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        int temdId = v.getId();
        if(temdId == R.id.txt_submit){
            Log.i(PublicUtils.AppName, "txt_submit");
            String username = et_username.getText().toString().trim();
            String password = et_password.getText().toString().trim();
            if(username.length() == 0){
                Toast.makeText(this, "账号不能为空！", Toast.LENGTH_SHORT).show();
                return;
            }
            if(username.length() < 6){
                Toast.makeText(this, "账号不能小于6位！", Toast.LENGTH_SHORT).show();
                return;
            }
            if(password.length() == 0){
                Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                return;
            }
            if(password.length() < 6){
                Toast.makeText(this, "密码不能小于6位！", Toast.LENGTH_SHORT).show();
                return;
            }


//            AbDialogUtil.showProgressDialog(this, 0, "正在登录");
            //认证通过，将数据保存在本地数据库
            int loginResult = SqliteDB.getInstance(LoginActivity.this).Quer(username, password);
            if(loginResult == 1){
//                AbDialogUtil.removeDialog(this);
                //判断当前是否已登录
                SaveInfoData();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(LoginActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
//                AbDialogUtil.removeDialog(LoginActivity.this);
            }

        } else if(temdId == R.id.txt_regist){
            Log.i(PublicUtils.AppName, "txt_regist");
            if(SqliteDB.getInstance(LoginActivity.this).loadUser().size() == 0){
                LoginInfo loginInfo = new LoginInfo();
                loginInfo.setUsername("123456");
                loginInfo.setPassword("123456");
                SqliteDB.getInstance(this).saveUser(loginInfo);
            }else{
                Toast.makeText(LoginActivity.this, "已有注册账号", Toast.LENGTH_SHORT).show();
                return;
            }
//            Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
//            startActivity(intent);
        } else if(temdId == R.id.img_close){
            Log.i(PublicUtils.AppName, "img_close");
            et_username.setText("");
            et_password.setText("");
        } else if(temdId == R.id.img_lookpwd){
            Log.i(PublicUtils.AppName, "img_lookpwd");
            // 显隐密码
            changeInputTransMethod(et_password);
        }
    }

        /**
         * 登录成功，保存账号密码
         */
        private void SaveInfoData(){
            SharedPreferences.Editor edit = getSharedPreferences("mypsd", MODE_PRIVATE).edit();
            //判断选择框的状态   被选中isChecked或……
            if (checkBox.isChecked()) {
                edit.putString("name", et_username.getText().toString());
                edit.putString("psd", et_password.getText().toString());
                edit.putBoolean("isChecked", true);
            } else {
//            edit.clear();              //若选择全部清除就保留这行代码，注释以下三行
                edit.putString("name", et_username.getText().toString());//只存用户名
                edit.putString("psd", "");
                edit.putBoolean("isChecked", false);
            }
            edit.commit();
        }

    /**
     * 显示|隐藏密码
     * @param et
     */
    public void changeInputTransMethod(EditText et) {
        TransformationMethod md = et.getTransformationMethod();
        if (md instanceof HideReturnsTransformationMethod) {
            et.setTransformationMethod(PasswordTransformationMethod
                    .getInstance());
            img_lookpwd.setImageResource(R.mipmap.opg);
        } else if (md instanceof PasswordTransformationMethod) {
            et.setTransformationMethod(HideReturnsTransformationMethod
                    .getInstance());
            img_lookpwd.setImageResource(R.mipmap.opf);
        }
    }

}
