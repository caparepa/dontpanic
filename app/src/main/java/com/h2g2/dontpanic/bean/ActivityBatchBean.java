package com.h2g2.dontpanic.bean;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/25
 */
public class ActivityBatchBean {
    private int steps;
    private int distance;
    private int calories;
    private int duration;
    private String activity_timestamp;
    private int tracker_id;
    private int profile_id;
    private int count;

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getActivity_timestamp() {
        return activity_timestamp;
    }

    public void setActivity_timestamp(String activity_timestamp) {
        this.activity_timestamp = activity_timestamp;
    }

    public int getTracker_id() {
        return tracker_id;
    }

    public void setTracker_id(int tracker_id) {
        this.tracker_id = tracker_id;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
