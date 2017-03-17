package com.example.francois.guildwarsexample.database;

import com.example.francois.guildwarsexample.pojo.Group;

import java.util.List;

/**
 * Created by François Adam on 17/03/2017.
 */

public class GroupDB {
    private static GroupDB INSTANCE = new GroupDB();

    private GroupDB(){}
    public static GroupDB getInstance(){
        return INSTANCE;
    }

    public void initConnection(){}

    public void closeConnection(){}

    public List<Group> loadGroups() {return null;}

    public void addGroup(Group group) {}

}
