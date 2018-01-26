package com.h2g2.dontpanic.bean.profile.reward;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class Unlocked {
    private int id;
    private String payment_type;
    private int amount;
    private int channel_id;
    private int waypoint_id;
    private int profile_channel_id;
    private int profile_id;
    private int account_id;
    private String achieved_at;

    public int getId() {
        return id;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public int getAmount() {
        return amount;
    }

    public int getChannel_id() {
        return channel_id;
    }

    public int getWaypoint_id() {
        return waypoint_id;
    }

    public int getProfile_channel_id() {
        return profile_channel_id;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public String getAchieved_at() {
        return achieved_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setChannel_id(int channel_id) {
        this.channel_id = channel_id;
    }

    public void setWaypoint_id(int waypoint_id) {
        this.waypoint_id = waypoint_id;
    }

    public void setProfile_channel_id(int profile_channel_id) {
        this.profile_channel_id = profile_channel_id;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public void setAchieved_at(String achieved_at) {
        this.achieved_at = achieved_at;
    }
}
