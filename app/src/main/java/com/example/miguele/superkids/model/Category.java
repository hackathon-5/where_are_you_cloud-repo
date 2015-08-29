package com.example.miguele.superkids.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by miguele on 8/29/15.
 */
public class Category extends RealmObject {
    @SerializedName("name")private String name;
    @SerializedName("id")private int id;
    @SerializedName("intervals") private int intervals;
    @SerializedName("increments")private int increments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntervals() {
        return intervals;
    }

    public void setIntervals(int intervals) {
        this.intervals = intervals;
    }

    public int getIncrements() {
        return increments;
    }

    public void setIncrements(int increments) {
        this.increments = increments;
    }
}
