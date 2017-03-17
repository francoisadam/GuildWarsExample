package com.example.francois.guildwarsexample.retrofit;

/**
 * Created by francois on 09/03/2017.
 */

import com.example.francois.guildwarsexample.pojo.Achievement;
import com.example.francois.guildwarsexample.pojo.Category;
import com.example.francois.guildwarsexample.pojo.Group;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiInterface {

    @GET("achievements/groups")
    Observable<ArrayList<String>> getGroupsId();

    @GET("achievements/groups/{id}")
    Observable<Group> getGroup(@Path("id") String id);

    @GET("achievements/groups?ids=all")
    Observable<List<Group>> getAllGroups();

    @GET("achievements/categories/{id}")
    Observable<Category> getCategory(@Path("id") int id);

    @GET("achievements/categories")
    Observable<List<Category>> getMultipleCategories(@Query("ids") String tab);

    @GET("achievements/{id)")
    Observable<Achievement> getAchievement(@Path("id") int id);

    @GET("achievements")
    Observable<List<Achievement>> getMultipleAchivements(@Query("ids") String tab);
}