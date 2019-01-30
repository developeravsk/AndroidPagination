package silptech.developerabhi.androidpagination;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, Item> {

    //size of page that we want
    public static final int PAGE_SIZE = 50;

    //we will start from first page which is 1
    private static final int FIRST_PAGE = 1;

    //we need to fetch from stackoverflow
    private static final String SITE_NAME = "stackoverflow";

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Item> callback) {
        RetrofitClient.getmInstance().getApi().getAnswers(FIRST_PAGE, PAGE_SIZE, SITE_NAME).enqueue(new Callback<StackApiResponse>() {
            @Override
            public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                if (response.body() != null) {
                    callback.onResult(response.body().items, null, FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<StackApiResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RetrofitClient.getmInstance().getApi().getAnswers(params.key, PAGE_SIZE, SITE_NAME).enqueue(new Callback<StackApiResponse>() {
            @Override
            public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                //if current page is greater than one, we are decrementing the page number , else no previous page
                Integer adjacentkey = (params.key > 1) ? params.key - 1 : null;
                if (response.body() != null) {
                    //passing the loaded data and the previous page key
                    callback.onResult(response.body().items, adjacentkey);
                }
            }

            @Override
            public void onFailure(Call<StackApiResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RetrofitClient.getmInstance().getApi().getAnswers(params.key, PAGE_SIZE, SITE_NAME).enqueue(new Callback<StackApiResponse>() {
            @Override
            public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                if (response.body() != null) {
                    Integer key = response.body().has_more ? params.key + 1 : null;
                    callback.onResult(response.body().items,key);
                }
            }

            @Override
            public void onFailure(Call<StackApiResponse> call, Throwable t) {

            }
        });
    }
}
