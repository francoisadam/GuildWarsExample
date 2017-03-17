package com.example.francois.guildwarsexample.retrofit;

import com.example.francois.guildwarsexample.pojo.Achievement;
import com.example.francois.guildwarsexample.pojo.Category;
import com.example.francois.guildwarsexample.pojo.Group;

import java.util.Arrays;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@SuppressWarnings("WeakerAccess")
public class RestManager {

    private ApiInterface restApi;
    private static RestManager INSTANCE;

    private RestManager() {
        String BASE_URL = "https://api.guildwars2.com/v2/";
        if (restApi == null) {
            restApi = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                    .create(ApiInterface.class);
        }
    }

    public synchronized static RestManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RestManager();
        }
        return INSTANCE;
    }

    public Observable<List<Group>> loadGroups() {
        return restApi.getAllGroups()
                .compose(asNetworkRequest());
    }

    public Observable<List<Category>> loadCategories(List<Integer> tab) {
        return restApi.getMultipleCategories(Arrays.toString(tab.toArray()).replace("[", "").replace("]", ""))
                .compose(asNetworkRequest());
    }


    public Observable<List<Achievement>> loadAchievements(List<Integer> tab) {
        return restApi.getMultipleAchivements(Arrays.toString(tab.toArray()).replace("[", "").replace("]", ""))
                .compose(asNetworkRequest());
    }

    private <T> Observable.Transformer<T, T> asNetworkRequest() {
        return tObservable -> tObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
