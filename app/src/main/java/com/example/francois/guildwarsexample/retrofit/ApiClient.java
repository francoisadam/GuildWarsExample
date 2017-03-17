package com.example.francois.guildwarsexample.retrofit;

/**
 * Created by francois on 09/03/2017.
 */

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient{
    private static ApiClient INSTANCE = new ApiClient();
    private static Retrofit retrofit = null;

    private ApiClient(){}

    public static ApiClient getApiClient(){
        return INSTANCE;
    }

    public static Retrofit getClient() {
        String BASE_URL = "https://api.guildwars2.com/v2/";
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }

}