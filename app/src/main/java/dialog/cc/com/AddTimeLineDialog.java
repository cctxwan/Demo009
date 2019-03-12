package dialog.cc.com;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import activity.cc.com.demo009.R;

public class AddTimeLineDialog extends Dialog implements View.OnClickListener{

    private String content;
    private Context mContext;
    private OnSubmitListener listener;
    private String positiveName;
    private String negativeName;
    private String title;

    EditText et_timeline_content;

    TextView time_line_cancel, time_line_submit;

    public AddTimeLineDialog(Context context, int dialog, OnSubmitListener onCloseListener) {
        super(context);
        this.mContext = context;
    }

    public AddTimeLineDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }

    public AddTimeLineDialog(Context context, int themeResId, String content, OnSubmitListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }

    protected AddTimeLineDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public AddTimeLineDialog setTitle(String title){
        this.title = title;
        return this;
    }

    public AddTimeLineDialog setPositiveButton(String name){
        this.positiveName = name;
        return this;
    }

    public AddTimeLineDialog setNegativeButton(String name){
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtimeline);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView(){
        et_timeline_content = findViewById(R.id.et_timeline_content);
        time_line_submit = findViewById(R.id.time_line_submit);
        time_line_cancel = findViewById(R.id.time_line_cancel);

        time_line_submit.setOnClickListener(this);
        time_line_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int tempId = v.getId();
        if (tempId == R.id.time_line_submit) {
            String content = et_timeline_content.getText().toString().trim();
            if(content.length() == 0){
                Toast.makeText(mContext, "还没想好说什么呢", Toast.LENGTH_SHORT).show();
                return;
            }
            if(listener != null){
                listener.onClick(this, true, content);
            }
//            dismiss();
        }else if(tempId == R.id.time_line_cancel){
            dismiss();
        }
    }

    public interface OnSubmitListener{
        void onClick(Dialog dialog, boolean confirm, String content);
    }
}
