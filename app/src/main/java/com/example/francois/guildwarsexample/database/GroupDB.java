package com.example.francois.guildwarsexample.database;

import com.example.francois.guildwarsexample.pojo.Group;

import java.util.List;

import io.realm.RealmObject;

/**
 * Created by François Adam on 17/03/2017.
 */

public class GroupDB extends RealmObject {

    private String id;
    private String name;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
