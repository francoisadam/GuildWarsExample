package com.example.francois.guildwarsexample.database;

import com.example.francois.guildwarsexample.pojo.Category;

import java.util.List;

/**
 * Created by François Adam on 17/03/2017.
 */

public class CategoryDB {
    private static CategoryDB INSTANCE = new CategoryDB();

    private CategoryDB(){}
    public static CategoryDB getInstance(){
        return INSTANCE;
    }

    public void initConnection(){}

    public void closeConnection(){}

    public List<Category> loadCategories(List<Integer> ids) {return null;}

    public void addCategory(Category category) {}

}
