package fragment.cc.com;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import activity.cc.com.demo009.R;

/**
 * Created by admin on 2017/12/12.
 */

public class TwoTabFourFragment extends Fragment {

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.two_tab_four_fragment, container, false);
        return view;
    }

}
