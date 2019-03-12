package dialog.cc.com;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import activity.cc.com.demo009.R;

/**
 * Created by admin on 2017/12/25.
 */

public class DeleteMessageDialog extends Dialog implements View.OnClickListener{

    private TextView delete_message_cancel, delete_message_submit;
    private Context mContext;
    private String content;
    private DeleteMessageDialog.OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;

    public DeleteMessageDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public DeleteMessageDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }

    public DeleteMessageDialog(Context context, int themeResId, String content, DeleteMessageDialog.OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }

    protected DeleteMessageDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public DeleteMessageDialog setTitle(String title){
        this.title = title;
        return this;
    }

    public DeleteMessageDialog setPositiveButton(String name){
        this.positiveName = name;
        return this;
    }

    public DeleteMessageDialog setNegativeButton(String name){
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletemessage);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView(){
        delete_message_cancel = findViewById(R.id.delete_message_cancel);
        delete_message_submit = findViewById(R.id.delete_message_submit);

        delete_message_cancel.setOnClickListener(this);
        delete_message_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.delete_message_cancel:
                dismiss();
                break;
            case R.id.delete_message_submit:
                listener.onClick(this, true);
                break;
            default:
                break;
        }
    }

    public interface OnCloseListener{
        void onClick(Dialog dialog, boolean confirm);
    }

}