package com.h2g2.dontpanic.bean.profile;

import com.h2g2.dontpanic.bean.data.Profile;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class ProfileNewDataBean {

    private Profile newProfile;
    private String token;

    public ProfileNewDataBean(Profile newProfile, String token) {
        this.newProfile = newProfile;
        this.token = token;
    }

    public Profile getNewProfile() {
        return newProfile;
    }

    public void setNewProfile(Profile newProfile) {
        this.newProfile = newProfile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
