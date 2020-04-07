package com.vinsuan.ppjoke.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;

import com.vinsuan.ppjoke.AbsViewModel;
import com.vinsuan.ppjoke.model.Feed;

public class HomeViewModel extends AbsViewModel<Feed> {

    @Override
    public DataSource createDataSource() {
        return null;
    }
}