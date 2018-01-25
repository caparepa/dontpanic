package com.h2g2.dontpanic.bean;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/25
 */
public class ActivityBean {
    private String date;
    private int steps;
    private int distance;
    private int calories;
    private int duration;
    private boolean has_estimated_calories;
    private String activity_timestamp;
    private int tracker_id;
    private int id;
    private String tz;

    public ActivityBean(String date, int steps, int distance, int calories, int duration, boolean has_estimated_calories, String activity_timestamp, int tracker_id, int id) {
        this.date = date;
        this.steps = steps;
        this.distance = distance;
        this.calories = calories;
        this.duration = duration;
        this.has_estimated_calories = has_estimated_calories;
        this.activity_timestamp = activity_timestamp;
        this.tracker_id = tracker_id;
        this.id = id;
    }

    public ActivityBean(String date, int steps, int distance, int calories, int duration, boolean has_estimated_calories, String activity_timestamp, int tracker_id) {
        this.date = date;
        this.steps = steps;
        this.distance = distance;
        this.calories = calories;
        this.duration = duration;
        this.has_estimated_calories = has_estimated_calories;
        this.activity_timestamp = activity_timestamp;
        this.tracker_id = tracker_id;
    }

    public ActivityBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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

    public boolean isHas_estimated_calories() {
        return has_estimated_calories;
    }

    public void setHas_estimated_calories(boolean has_estimated_calories) {
        this.has_estimated_calories = has_estimated_calories;
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

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }
}
