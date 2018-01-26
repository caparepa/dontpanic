package com.h2g2.dontpanic.bean.channel;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class Waypoint implements Serializable {
    private Integer id;
    private Integer requiredSteps;
    private Integer order;
    private Boolean locked;
    private Integer keysToUnlock;
    private Integer ppToUnlock;
    private String type;
    private Boolean claimed = false;
    private Bitmap reward;
    private String image;
    private String name;
    private String message;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Bitmap getReward() {
        return reward;
    }

    public void setReward(Bitmap reward) {
        this.reward = reward;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRequiredSteps() {
        return requiredSteps;
    }

    public void setRequiredSteps(Integer requiredSteps) {
        this.requiredSteps = requiredSteps;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Integer getKeysToUnlock() {
        return keysToUnlock;
    }

    public void setKeysToUnlock(Integer keysToUnlock) {
        this.keysToUnlock = keysToUnlock;
    }

    public Integer getPpToUnlock() {
        return ppToUnlock;
    }

    public void setPpToUnlock(Integer ppToUnlock) {
        this.ppToUnlock = ppToUnlock;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getClaimed(){return this.claimed;}

    public void setClaimed(boolean b){ this.claimed = b;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Waypoint() {
    }

    public Waypoint(Integer requiredSteps, Integer order, Boolean locked, Integer keysToUnlock, Integer ppToUnlock, String type) {
        this.requiredSteps = requiredSteps;
        this.order = order;
        this.locked = locked;
        this.keysToUnlock = keysToUnlock;
        this.ppToUnlock = ppToUnlock;
        this.type = type;
        this.reward = null;
    }
}
