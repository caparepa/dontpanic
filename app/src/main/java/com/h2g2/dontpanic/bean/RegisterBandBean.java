package com.h2g2.dontpanic.bean;

import java.io.Serializable;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/25
 */
public class RegisterBandBean implements Serializable {
    String name;
    String type;
    String version;
    String tracker_factory_id;
    String start_date;
    String last_sync_date;
    boolean linked;
    String end_date;
    String profile_id;
    int id;
    private ActivityBean last_activity;
    private ActivityBatchBean last_batch;

    public RegisterBandBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTracker_factory_id() {
        return tracker_factory_id;
    }

    public void setTracker_factory_id(String tracker_factory_id) {
        this.tracker_factory_id = tracker_factory_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getLast_sync_date() {
        return last_sync_date;
    }

    public void setLast_sync_date(String last_sync_date) {
        this.last_sync_date = last_sync_date;
    }

    public boolean isLinked() {
        return linked;
    }

    public void setLinked(boolean linked) {
        this.linked = linked;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(String profile_id) {
        this.profile_id = profile_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ActivityBean getLast_activity() {
        return last_activity;
    }

    public void setLast_activity(ActivityBean last_activity) {
        this.last_activity = last_activity;
    }

    public ActivityBatchBean getLast_batch() {
        return last_batch;
    }

    public void setLast_batch(ActivityBatchBean last_batch) {
        this.last_batch = last_batch;
    }
}
