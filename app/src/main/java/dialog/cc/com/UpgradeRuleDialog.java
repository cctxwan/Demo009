package dialog.cc.com;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import activity.cc.com.demo009.R;

public class UpgradeRuleDialog extends Dialog implements android.view.View.OnClickListener{

    ImageView img;
    private Context mContext;
    private String content;
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;

    public UpgradeRuleDialog(Context context, int dialog, OnCloseListener onCloseListener) {
        super(context);
        this.mContext = context;
    }

    public UpgradeRuleDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }

    public UpgradeRuleDialog(Context context, int themeResId, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }

    protected UpgradeRuleDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public UpgradeRuleDialog setTitle(String title){
        this.title = title;
        return this;
    }

    public UpgradeRuleDialog setPositiveButton(String name){
        this.positiveName = name;
        return this;
    }

    public UpgradeRuleDialog setNegativeButton(String name){
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upgrade_rule);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView(){
        img = findViewById(R.id.img_upgrade_rule_close);

        img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int tempId = v.getId();
        if (tempId == R.id.img_upgrade_rule_close) {
//            if(listener != null){
//                listener.onClick(this, true);
//            }
            dismiss();
        }
    }

    public interface OnCloseListener{
        void onClick(Dialog dialog, boolean confirm);
    }
}
