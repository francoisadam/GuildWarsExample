package com.example.francois.guildwarsexample.database;

import com.example.francois.guildwarsexample.pojo.Achievement;

import java.util.List;

import io.realm.RealmObject;

/**
 * Created by François Adam on 17/03/2017.
 */

public class AchievementDB extends RealmObject {

    private Integer id;
    private String name;
    private String description;
    private String requirement;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }
}
