package com.vinsuan.ppjoke;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public abstract class AbsViewModel<T> extends ViewModel {
    private final LiveData<PagedList<T>> liveData;
    private DataSource dataSource;

    private MutableLiveData<Boolean> boundaryPageData = new MutableLiveData<>();

    public AbsViewModel(){
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(10)//分页加载的数量
                .setInitialLoadSizeHint(12)//第一次加载的数据数量
//                .setMaxSize(100)总共多少条数据
//                .setEnablePlaceholders(false)
//                .setPrefetchDistance(2)  //类似预加载
                .build();

        liveData = new LivePagedListBuilder(factory, config)
                .setInitialLoadKey(0)
//                .setFetchExecutor()
                .setBoundaryCallback(callback)
                .build();

    }
    public LiveData<PagedList<T>> getPagedData(){
        return liveData;
    }

    public DataSource getDataSource(){
        return dataSource;
    }

    public MutableLiveData<Boolean> getBoundaryPageData() {
        return boundaryPageData;
    }

    PagedList.BoundaryCallback<T> callback = new PagedList.BoundaryCallback<T>() {
        @Override
        public void onZeroItemsLoaded() {
            //是否展示空布局
            boundaryPageData.postValue(false);
        }

        @Override
        public void onItemAtFrontLoaded(@NonNull T itemAtFront) {
            //列表的第一条数据被加载 前面没有更多数据
            boundaryPageData.postValue(true);
        }

        @Override
        public void onItemAtEndLoaded(@NonNull T itemAtEnd) {
            //最后一条数据被加载，后面没有更多数据
            super.onItemAtEndLoaded(itemAtEnd);
        }
    };

    DataSource.Factory factory = new DataSource.Factory() {
        @NonNull
        @Override
        public DataSource create() {
            dataSource = createDataSource();
            return dataSource;
        }
    };

    public abstract DataSource createDataSource() ;

}
