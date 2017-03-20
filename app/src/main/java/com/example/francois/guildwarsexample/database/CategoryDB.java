package com.example.francois.guildwarsexample.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.francois.guildwarsexample.pojo.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by François Adam on 17/03/2017.
 */

public class CategoryDB {
    private static CategoryDB INSTANCE = new CategoryDB();
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    public static final String PREFS_NAME = "CategoryDB";

    private CategoryDB(){}
    public static CategoryDB getInstance(){
        return INSTANCE;
    }

    public void initConnection(Context context){
        settings = context.getSharedPreferences(PREFS_NAME,0);
    }

    public void closeConnection(){
        settings = null;
    }

    public List<Category> loadCategories(List<Integer> ids) {
        List<Category> listCategory = new ArrayList<>();
        Category category = new Category();
        if (settings != null){
            for (Integer key : ids){
                category.setId(settings.getInt(key+"id",1));
                category.setName(settings.getString(key+"name","name"));
                category.setDescription(settings.getString(key+"description","description"));
                listCategory.add(category);
            }
        }
        return listCategory;}

    public void addCategory(Category category) {
        int key = category.getId();
        if (settings != null){
            editor = settings.edit();
            editor.putInt(key+"id",key);
            editor.putString(key+"name",category.getName());
            editor.putString(key+"description",category.getDescription());
            editor.apply();
        }
    }

}
