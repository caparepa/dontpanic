package com.h2g2.dontpanic.bean.data;

import java.io.Serializable;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/24
 */
public class Pivot implements Serializable {
    private String user_id;
    private String profile_id;

    public Pivot() {
    }

    public Pivot(String user_id, String profile_id) {
        this.user_id = user_id;
        this.profile_id = profile_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(String profile_id) {
        this.profile_id = profile_id;
    }
}
