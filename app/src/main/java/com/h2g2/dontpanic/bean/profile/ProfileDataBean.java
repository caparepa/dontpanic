package com.h2g2.dontpanic.bean.profile;

import com.h2g2.dontpanic.bean.data.Profile;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class ProfileDataBean {

    private Profile profile;

    public ProfileDataBean(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
