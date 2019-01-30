package silptech.developerabhi.androidpagination;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

public class ItemViewModel extends ViewModel {
    //creating livedata for PagedList and PagedKeyedDataSource
    LiveData<PagedList<Item>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer,Item>> liveDataSource;

    public ItemViewModel() {
        ItemDataSourceFactory itemDataSourceFactory=new ItemDataSourceFactory();
    //getting live data source from data source library
        liveDataSource=itemDataSourceFactory.getItemLiveDataSource();
        //getting PagedList config
        PagedList.Config pagedListconfig=(new PagedList.Config.Builder()).setEnablePlaceholders(false).setPageSize(ItemDataSource.PAGE_SIZE).build();

        //building the paged list
        itemPagedList=(new LivePagedListBuilder(itemDataSourceFactory,pagedListconfig)).build();
    }
}
