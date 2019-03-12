package dialog.cc.com;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import activity.cc.com.demo009.R;

/**
 * Created by admin on 2017/12/1.
 */

public class BindPhoneDialog extends Dialog implements View.OnClickListener{

    private TextView contentTxt;
    private TextView titleTxt;
    private TextView submitTxt;
    private EditText install_et_phone;
    private Context mContext;
    private String content;
    private BindPhoneDialog.OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;

    public BindPhoneDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public BindPhoneDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }

    public BindPhoneDialog(Context context, int themeResId, String content, BindPhoneDialog.OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }

    protected BindPhoneDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public BindPhoneDialog setTitle(String title){
        this.title = title;
        return this;
    }

    public BindPhoneDialog setPositiveButton(String name){
        this.positiveName = name;
        return this;
    }

    public BindPhoneDialog setNegativeButton(String name){
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.installbindphone);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView(){
        contentTxt = findViewById(R.id.content);
        titleTxt = findViewById(R.id.title);
        install_et_phone = findViewById(R.id.install_et_phone);
        submitTxt = findViewById(R.id.submit);
        submitTxt.setOnClickListener(this);

        contentTxt.setText(content);
        if(!TextUtils.isEmpty(positiveName)){
            submitTxt.setText(positiveName);
        }

        if(!TextUtils.isEmpty(title)){
            titleTxt.setText(title);
        }

    }

    /**
     * 验证手机号
     * @return
     */
    private boolean CheckEtPhone(){
        if(install_et_phone.getText().toString().trim().length() == 0){
            Toast.makeText(mContext, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return true;
        }else if (install_et_phone.getText().toString().trim().toString().length() > 11){
            Toast.makeText(mContext, "请输入合法的手机号", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.submit:
                if(!CheckEtPhone()){
                    if(listener != null){
                        listener.onClick(this, true);
                    }
                }
                break;
            default:
                break;
        }
    }

    public interface OnCloseListener{
        void onClick(Dialog dialog, boolean confirm);
    }

}
