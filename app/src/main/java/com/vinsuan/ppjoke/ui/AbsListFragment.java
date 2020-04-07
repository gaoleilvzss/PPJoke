package com.vinsuan.ppjoke.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinsuan.libcommon.view.EmptyView;
import com.vinsuan.ppjoke.databinding.LayoutRefreshViewBinding;

public abstract class AbsListFragment<T> extends Fragment implements OnRefreshListener, OnLoadMoreListener {
    private LayoutRefreshViewBinding binding;
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private EmptyView emptyView;
    private PagedListAdapter<T, RecyclerView.ViewHolder> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LayoutRefreshViewBinding.inflate(inflater, container, false);
        recyclerView = binding.recyclerView;
        refreshLayout = binding.refreshLayout;
        emptyView = binding.emptyView;

        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);


        adapter = getAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(null);

        return binding.getRoot();
    }

    public void submitList(PagedList<T> pagedlist) {
        if (pagedlist.size() > 0) {
            adapter.submitList(pagedlist);
        }
        finishRefresh(pagedlist.size() > 0);
    }

    public void finishRefresh(boolean hasData) {
        PagedList<T> currentList = adapter.getCurrentList();
        hasData = hasData || currentList != null && currentList.size() > 0;
        RefreshState state = refreshLayout.getState();
        if (state.isFooter && state.isOpening) {
            refreshLayout.finishLoadMore();
        } else if (state.isHeader && state.isOpening) {
            refreshLayout.finishRefresh();
        }

        if (hasData) {
            emptyView.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    public abstract PagedListAdapter<T, RecyclerView.ViewHolder> getAdapter();


}
