package com.example.francois.guildwarsexample.database;

import android.content.Context;

import com.example.francois.guildwarsexample.pojo.Achievement;
import com.example.francois.guildwarsexample.pojo.Category;
import com.example.francois.guildwarsexample.pojo.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by François Adam on 17/03/2017.
 */

public class DBManager {
    private static DBManager INSTANCE = new DBManager();
    AchievementDB achievementDB;
    CategoryDB categoryDB;
    GroupDB groupDB;

    private DBManager(){}

    public static DBManager getInstance(){
        return INSTANCE;
    }

    public void initConnection(Context context){
        achievementDB = AchievementDB.getInstance();
        categoryDB = CategoryDB.getInstance();
        groupDB = GroupDB.getInstance();

        achievementDB.initConnection(context);
        categoryDB.initConnection(context);
        groupDB.initConnection(context);
    }

    public void closeConnection(){
        achievementDB.closeConnection();
        categoryDB.closeConnection();
        groupDB.closeConnection();
    }

    public List<Group> loadGroups() {
        List<Group> listGroup = new ArrayList<>();
        if (groupDB != null) {
            listGroup = groupDB.loadGroups();
        }
        return listGroup;
    }

    public List<Category> loadCategories(List<Integer> ids) {
        List<Category> listCategory = new ArrayList<>();
        if (categoryDB != null) {
            listCategory = categoryDB.loadCategories(ids);
        }
        return listCategory;
    }

    public List<Achievement> loadAchievements(List<Integer> ids) {
        List<Achievement> listAchievement = new ArrayList<>();
        if (achievementDB != null) {
            listAchievement = achievementDB.loadAchievements(ids);
        }
        return listAchievement;
    }

    public void addGroups(List<Group> groups) {
        if (groupDB != null) {
            for (Group group : groups){
                groupDB.addGroup(group);
            }
        }
    }

    public void addCategories(List<Category> categories) {
        if (categoryDB != null) {
            for (Category category : categories){
                categoryDB.addCategory(category);
            }
        }
    }

    public void addAchievements(List<Achievement> achievements) {
        if (achievementDB != null) {
            for (Achievement achievement : achievements){
                achievementDB.addAchievement(achievement);
            }
        }
    }
}
