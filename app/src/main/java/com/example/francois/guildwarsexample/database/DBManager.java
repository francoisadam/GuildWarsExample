package com.example.francois.guildwarsexample.database;

import android.content.Context;

import com.example.francois.guildwarsexample.pojo.Achievement;
import com.example.francois.guildwarsexample.pojo.Category;
import com.example.francois.guildwarsexample.pojo.Group;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by François Adam on 17/03/2017.
 */

public class DBManager {
    private static DBManager INSTANCE = new DBManager();
    private Realm realm;

    private DBManager(){}

    public static DBManager getInstance(){
        return INSTANCE;
    }

    public void initConnection(Context context){
        realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    public void closeConnection(){
        realm.close();
    }

    public List<Group> loadGroups() {
        List<GroupDB> list = realm.where(GroupDB.class).findAll();
        List<Group> result = new ArrayList<>();
        Group group;
        for (GroupDB elem : list){
            group = new Group();
            group.setId(elem.getId());
            group.setName(elem.getName());
            group.setDescription(elem.getDescription());
            result.add(group);
        }
        return result;
    }

    public List<Category> loadCategories(List<Integer> ids) {
        List<CategoryDB> list = realm.where(CategoryDB.class).findAll();
        List<Category> result = new ArrayList<>();
        Category category;
        for (CategoryDB elem : list){
            category = new Category();
            category.setId(elem.getId());
            category.setName(elem.getName());
            category.setDescription(elem.getDescription());
            result.add(category);
        }
        return result;
    }

    public List<Achievement> loadAchievements(List<Integer> ids) {
        List<AchievementDB> list = realm.where(AchievementDB.class).findAll();
        List<Achievement> result = new ArrayList<>();
        Achievement achievement;
        for (AchievementDB elem : list){
            achievement = new Achievement();
            achievement.setId(elem.getId());
            achievement.setName(elem.getName());
            achievement.setDescription(elem.getDescription());
            achievement.setRequirement(elem.getRequirement());
            result.add(achievement);
        }
        return result;
    }

    public void addGroups(List<Group> groups) {
        realm.executeTransaction((Realm realm) -> {
            GroupDB groupdb;
            for (Group group : groups){
                groupdb = realm.createObject(GroupDB.class);
                groupdb.setId(group.getId());
                groupdb.setName(group.getName());
                groupdb.setDescription(group.getDescription());
            }
        });
    }

    public void addCategories(List<Category> categories) {
        realm.executeTransaction((Realm realm) -> {
            CategoryDB categorydb;
            for (Category category : categories) {
                categorydb = realm.createObject(CategoryDB.class);
                categorydb.setId(category.getId());
                categorydb.setName(category.getName());
                categorydb.setDescription(category.getDescription());
            }
        });
    }

    public void addAchievements(List<Achievement> achievements) {
        realm.executeTransaction((Realm realm) -> {
            AchievementDB achievementdb;
            for (Achievement achievement : achievements){
                achievementdb = realm.createObject(AchievementDB.class);
                achievementdb.setId(achievement.getId());
                achievementdb.setName(achievement.getName());
                achievementdb.setDescription(achievement.getDescription());
                achievementdb.setRequirement(achievement.getRequirement());
            }
        });
    }
}
