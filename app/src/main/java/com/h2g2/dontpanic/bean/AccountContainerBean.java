package com.h2g2.dontpanic.bean;

import com.h2g2.dontpanic.bean.data.Account;
import com.h2g2.dontpanic.networking.interfaces.UserAccount;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/25
 */
public class AccountContainerBean {
    private Account account;
    private String token;

    public AccountContainerBean(Account account, String token) {
        this.account = account;
        this.token = token;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
