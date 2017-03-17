package com.example.francois.guildwarsexample.database;

import com.example.francois.guildwarsexample.pojo.Achievement;
import com.example.francois.guildwarsexample.pojo.Category;
import com.example.francois.guildwarsexample.pojo.Group;

import java.util.List;

/**
 * Created by François Adam on 17/03/2017.
 */

public class DBManager {
    private static DBManager INSTANCE = new DBManager();

    private DBManager(){}

    public static DBManager getInstance(){
        return INSTANCE;
    }

    public void initConnection(){}

    public void closeConnection(){}

    public List<Group> loadGroups() {return null;}

    public List<Category> loadCategories(List<Integer> ids) {return null;}

    public List<Achievement> loadAchievements(List<Integer> ids) {return null;}

    public void addGroup(Group group) {}

    public void addCategory(Category category) {}

    public void addAchievement(Achievement achievement) {}
}
