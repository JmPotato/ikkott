package com.bupt.ikkott;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerFragment extends Fragment implements RecyclerViewAdapter.ListItemClickListener {
    private static final int NUM_LIST_ITEMS = 10;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView mNumbersListView; //整个recyclerview的布局文件

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View settingLayout = inflater.inflate(R.layout.activity_recycler_view, container, false);
        mNumbersListView = settingLayout.findViewById(R.id.RecyclerviewList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(settingLayout.getContext());//给recylerview设定布局
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mNumbersListView.setLayoutManager(layoutManager);
        mNumbersListView.setHasFixedSize(true);


        mAdapter = new RecyclerViewAdapter(settingLayout.getContext(), NUM_LIST_ITEMS, this);//创建recyclerview的适应器adapter

        mNumbersListView.setAdapter(mAdapter);
        mNumbersListView.addOnScrollListener(new RecyclerView.OnScrollListener() { //滑动事件处理

            // 最后一个完全可见项的位置
            private int lastCompletelyVisibleItemPosition;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) { //滑动状态改变
                //滚动事件
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) { //滑到最底下的处理
                    if (visibleItemCount > 0 && lastCompletelyVisibleItemPosition >= totalItemCount - 1) {
                        Toast.makeText(settingLayout.getContext(), "已经到底了哦", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) { //滚动处理
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    lastCompletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                }
            }
        });
        return settingLayout;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }
}
