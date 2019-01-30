package silptech.developerabhi.androidpagination;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

public class ItemDataSourceFactory extends DataSource.Factory {
   //creating mutable live data
    private MutableLiveData<PageKeyedDataSource<Integer,Item>> itemLiveDataSource=new MutableLiveData<>();

    @Override
    public DataSource create() {
        //getting source data object
       ItemDataSource itemDataSource=new ItemDataSource();
       //posting datasource to get values
       itemLiveDataSource.postValue(itemDataSource);
       return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Item>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
