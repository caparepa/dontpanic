package com.h2g2.dontpanic.bean.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/24
 */
public class Data implements Serializable {
    private String token;
    private Account account;
    private Profile profile;
    private Profile currentProfile;
    private List<Profile> profiles;
    public Data() {
    }

    public Data(String token, Account account, Profile profile, List<Profile> profiles) {
        this.token = token;
        this.account = account;
        this.profile = profile;
        this.profiles = profiles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public Profile getCurrentProfile() {
        return currentProfile;
    }

    public void setCurrentProfile(Profile currentProfile) {
        this.currentProfile = currentProfile;
    }
}
