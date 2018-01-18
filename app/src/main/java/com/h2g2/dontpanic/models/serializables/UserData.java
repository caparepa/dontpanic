package com.h2g2.dontpanic.models.serializables;

import com.h2g2.dontpanic.models.entity.User;

import java.io.Serializable;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/18
 */
public class UserData implements Serializable {
    private User userEntity;
    private Boolean loggedIn;

    public UserData() {
    }

    public UserData(Boolean loggedIn, User userEntity) {
        this.loggedIn = loggedIn;
        this.userEntity = userEntity;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public User getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(User userEntity) {
        this.userEntity = userEntity;
    }
}
