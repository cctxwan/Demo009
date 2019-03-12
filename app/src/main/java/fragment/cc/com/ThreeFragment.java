package fragment.cc.com;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import activity.cc.com.demo009.R;
import activity.cc.com.TwoFragDetailActivity;
import adapter.cc.com.ReAdapter;
import data.cc.com.lin_two_fragment_refresh_data;
import db.cc.com.SqliteDB;
import info.cc.com.NewsData;
import systemstatusbar.cc.com.StatusBarCompat;
import utils.cc.com.EndLessOnScrollListener;
import utils.cc.com.PublicUtils;

/**
 * 第二个Fragment
 * @author 寇财玮
 * @version 2017年11月30日15:53:07
 */
public class ThreeFragment extends Fragment {

    //全局view
    View view;

    //刷新控件
    SwipeRefreshLayout two_fragment_swipeRefreshLayout;

    //listview控件
    RecyclerView two_fragment_recyclerView;

    //下拉刷新控件的ReAdapter（适配器）
    ReAdapter reAdapter;

    // 加载item显示的数据
    List<lin_two_fragment_refresh_data> datas = new ArrayList<lin_two_fragment_refresh_data>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.three_fragment, container, false);
        initView();
        initData();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getContext(), R.color.blue));
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getContext(), R.color.blue));
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        two_fragment_swipeRefreshLayout.setProgressViewOffset(true, 30, 80);
        two_fragment_swipeRefreshLayout.setColorSchemeResources(R.color.blue);
        two_fragment_swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(PublicUtils.AppName + "TwoFragment下拉刷新", "暂无数据刷新");
                        Toast.makeText(view.getContext(), "暂无数据刷新", Toast.LENGTH_SHORT).show();
                        reAdapter.notifyDataSetChanged();
                        two_fragment_swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        List<NewsData> newsDatas = SqliteDB.getInstance(getContext()).getNews();
        if(newsDatas.size() != 0){
            Log.i(PublicUtils.AppName, "news select succ");
            for (int i = 0; i < newsDatas.size(); i++){
                lin_two_fragment_refresh_data data = new lin_two_fragment_refresh_data();
                data.setId(newsDatas.get(i).getId());
                data.setImg_url(newsDatas.get(i).getImg_url());
                data.setName(newsDatas.get(i).getName());
                data.setTitle(newsDatas.get(i).getTitle());
                data.setDate(newsDatas.get(i).getDate());
                data.setFrom(newsDatas.get(i).getNewsfrom());
                data.setContent(newsDatas.get(i).getContent());
                data.setContent_url(newsDatas.get(i).getContent_url());
                datas.add(data);
            }
        }

        reAdapter = new ReAdapter(datas);
        two_fragment_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        two_fragment_recyclerView.setAdapter(reAdapter);

        reAdapter.setOnRefreshItemClick(new ReAdapter.onRefreshItemClick() {
            @Override
            public void succ(View view, int position, int newid) {
                Log.i(PublicUtils.AppName, "你点击的是第" + position + "个新闻!它在Sqlite数据库中所对应的id是" + newid);
                Intent intent = new Intent(getActivity(), TwoFragDetailActivity.class);
                intent.putExtra("newId", newid);
                startActivity(intent);
            }
        });

        two_fragment_recyclerView.addOnScrollListener(new EndLessOnScrollListener(new LinearLayoutManager(getActivity())) {
            @Override
            public void onLoadMore(int currentPage) {
                loadMoreData();
            }
        });
    }

    /**
     * 每次上拉加载的时候，就加载十条数据到RecyclerView中
     */
    private void loadMoreData(){
        for (int i =0; i < 10; i++){
            Log.i(PublicUtils.AppName, "上拉加载ing...");
            reAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 初始化视图
     */
    private void initView() {
        two_fragment_swipeRefreshLayout = view.findViewById(R.id.two_fragment_swipeRefreshLayout);
        two_fragment_recyclerView = view.findViewById(R.id.two_fragment_recyclerView);
    }

}
