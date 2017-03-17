package com.example.francois.guildwarsexample.database;

import com.example.francois.guildwarsexample.pojo.Achievement;

import java.util.List;

/**
 * Created by François Adam on 17/03/2017.
 */

public class AchievementDB {
    private static AchievementDB INSTANCE = new AchievementDB();

    private AchievementDB(){}
    public static AchievementDB getInstance(){
        return INSTANCE;
    }

    public void initConnection(){}

    public void closeConnection(){}

    public List<Achievement> loadAchievements(List<Integer> ids) {return null;}

    public void addAchievement(Achievement achievement) {}
}
