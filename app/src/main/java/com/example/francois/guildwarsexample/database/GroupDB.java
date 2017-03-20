package com.example.francois.guildwarsexample.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.francois.guildwarsexample.pojo.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by François Adam on 17/03/2017.
 */

public class GroupDB {
    private static GroupDB INSTANCE = new GroupDB();
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    public static final String PREFS_NAME = "GroupDB";

    private GroupDB(){}
    public static GroupDB getInstance(){
        return INSTANCE;
    }

    public void initConnection(Context context){
        settings = context.getSharedPreferences(PREFS_NAME,0);
    }

    public void closeConnection(){
        settings = null;
    }

    public List<Group> loadGroups() {
        List<Group> listGroup = new ArrayList<>();
        Group group = new Group();
        if (settings != null){
            for (String key : settings.getAll().keySet()){
                group.setId(settings.getString(key+"id","id"));
                group.setName(settings.getString(key+"name","name"));
                group.setDescription(settings.getString(key+"description","description"));
                listGroup.add(group);
            }
        }
        return listGroup;}

    public void addGroup(Group group) {
        String key = group.getId();
        if (settings != null){
            editor = settings.edit();
            editor.putString(key+"id",key);
            editor.putString(key+"name",group.getName());
            editor.putString(key+"description",group.getDescription());
            editor.apply();
        }
    }

}
