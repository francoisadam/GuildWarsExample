package com.example.francois.guildwarsexample.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.francois.guildwarsexample.pojo.Achievement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by François Adam on 17/03/2017.
 */

public class AchievementDB {
    private static AchievementDB INSTANCE = new AchievementDB();
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    public static final String PREFS_NAME = "AchievementDB";

    private AchievementDB(){}
    public static AchievementDB getInstance(){
        return INSTANCE;
    }

    public void initConnection(Context context){
        settings = context.getSharedPreferences(PREFS_NAME,0);
    }

    public void closeConnection(){
        settings = null;
    }

    public List<Achievement> loadAchievements(List<Integer> ids) {
        List<Achievement> listAchievement = new ArrayList<>();
        Achievement achievement = new Achievement();
        if (settings != null){
            for (Integer key : ids){
                achievement.setId(settings.getInt(key+"id",1));
                achievement.setName(settings.getString(key+"name","name"));
                achievement.setDescription(settings.getString(key+"description","description"));
                achievement.setRequirement(settings.getString(key+"requirement","requirement"));
                listAchievement.add(achievement);
            }
        }
        return listAchievement;
    }

    public void addAchievement(Achievement achievement) {
        int key = achievement.getId();
        if (settings != null){
            editor = settings.edit();
            editor.putInt(key+"id",key);
            editor.putString(key+"name",achievement.getName());
            editor.putString(key+"description",achievement.getDescription());
            editor.putString(key+"requirement",achievement.getRequirement());
            editor.apply();
        }
    }
}
