package com.example.francois.guildwarsexample.pojo;

/**
 * Created by francois on 09/03/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Achievement implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("requirement")
    @Expose
    private String requirement;
    @SerializedName("locked_text")
    @Expose
    private String lockedText;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("flags")
    @Expose
    private List<Object> flags = null;
    @SerializedName("tiers")
    @Expose
    private List<Tier> tiers = null;

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

    public String getLockedText() {
        return lockedText;
    }

    public void setLockedText(String lockedText) {
        this.lockedText = lockedText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Object> getFlags() {
        return flags;
    }

    public void setFlags(List<Object> flags) {
        this.flags = flags;
    }

    public List<Tier> getTiers() {
        return tiers;
    }

    public void setTiers(List<Tier> tiers) {
        this.tiers = tiers;
    }


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(requirement);
    }

    private Achievement(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.requirement = in.readString();
    }

    public static final Parcelable.Creator<Achievement> CREATOR = new Parcelable.Creator<Achievement>()
    {
        @Override
        public Achievement createFromParcel(Parcel source)
        {
            return new Achievement(source);
        }

        @Override
        public Achievement[] newArray(int size)
        {
            return new Achievement[size];
        }
    };
}

//TODO prendre en compte l'id