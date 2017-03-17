package com.example.francois.guildwarsexample.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by francois on 09/03/2017.
 */

public class Tier {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("points")
    @Expose
    private Integer points;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

}