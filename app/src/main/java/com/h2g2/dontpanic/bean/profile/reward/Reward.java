package com.h2g2.dontpanic.bean.profile.reward;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class Reward {
    private Integer id;
    private Integer channel_id;
    private Integer waypoint_id;
    private String reward_id;
    private String achieved_at;
    private String type;
    private String name;
    private ConcreteReward mConcrete_reward;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChannelId() {
        return channel_id;
    }

    public void setChannelId(Integer channelId) {
        this.channel_id = channelId;
    }

    public Integer getWaypointId() {
        return waypoint_id;
    }

    public void setWaypointId(Integer waypointId) {
        this.waypoint_id = waypointId;
    }

    public String getRewardId() {
        return reward_id;
    }

    public void setRewardId(String rewardId) {
        this.reward_id = rewardId;
    }

    public String getAchievedAt() {
        return achieved_at;
    }

    public void setAchievedAt(String achievedAt) {
        this.achieved_at = achievedAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConcreteReward getConcrete_reward(){return this.mConcrete_reward;}

    public void setConcrete_reward(ConcreteReward cr){
        this.mConcrete_reward = cr;
    }
}
