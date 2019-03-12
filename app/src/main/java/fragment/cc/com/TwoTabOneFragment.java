package fragment.cc.com;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;

import java.util.ArrayList;
import java.util.List;

import activity.cc.com.demo009.R;

/**
 * Created by admin on 2017/12/12.
 */

public class TwoTabOneFragment extends Fragment {

    View view;

    HorizontalStepView stepview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.two_tab_one_fragment, container, false);

        initView();
        initData();

        return view;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        List<StepBean> stepsBeanList = new ArrayList<>();
        StepBean stepBean0 = new StepBean("请求",1);
        StepBean stepBean1 = new StepBean("录制",1);
        StepBean stepBean2 = new StepBean("提交",1);
        StepBean stepBean3 = new StepBean("审核",0);
        StepBean stepBean4 = new StepBean("结果",-1);
        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);
        stepsBeanList.add(stepBean3);
        stepsBeanList.add(stepBean4);


        stepview
                .setStepViewTexts(stepsBeanList)//总步骤
                .setTextSize(12)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getActivity(), android.R.color.white))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getActivity(), R.color.uncompleted_text_color))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(getActivity(), android.R.color.white))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(getActivity(), R.color.uncompleted_text_color))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getActivity(), R.drawable.complted))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(getActivity(), R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(getActivity(), R.drawable.attention));//设置StepsViewIndicator AttentionIcon
    }

    /**
     * 初始化视图
     */
    private void initView() {
        stepview = view.findViewById(R.id.stepview);
    }

}
