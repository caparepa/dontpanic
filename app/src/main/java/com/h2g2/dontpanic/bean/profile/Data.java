package com.h2g2.dontpanic.bean.profile;

import com.h2g2.dontpanic.bean.profile.reward.Reward;
import com.h2g2.dontpanic.bean.profile.reward.Unlocked;

import java.util.List;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class Data {
    private Integer activity_applied;
    private String last_applied_date;

    public Integer getActivityApplied() { return activity_applied; }
    public String getLastAppliedDate() { return last_applied_date; }
    public void setActivityApplied(Integer activityApplied) { this.activity_applied = activityApplied; }
    public void setLastAppliedDate(String lastAppliedDate) { this.last_applied_date = lastAppliedDate; }

    private List<Reward> mRewards = null;
    private List<Unlocked> unlocked = null;

    public List<Reward> getRewards() { return mRewards; }
    public void setRewards(List<Reward> rewards) { this.mRewards = rewards; }

    public List<Unlocked> getUnlocked() { return unlocked; }
    public void setUnlocked(List<Unlocked> unlocked) { this.unlocked = unlocked; }
}
