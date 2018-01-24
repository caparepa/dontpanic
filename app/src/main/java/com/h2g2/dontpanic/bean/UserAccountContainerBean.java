package com.h2g2.dontpanic.bean;

import com.h2g2.dontpanic.networking.interfaces.UserAccount;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/24
 */
public class UserAccountContainerBean {
    private UserAccount account;
    private String token;

    public UserAccountContainerBean(UserAccount account, String token) {
        this.account = account;
        this.token = token;
    }

    public UserAccount getAccount() {
        return account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
