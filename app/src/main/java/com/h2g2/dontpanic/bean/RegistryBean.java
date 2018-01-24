package com.h2g2.dontpanic.bean;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/24
 */
public class RegistryBean {
    private String email;
    private String password;
    private String dob;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public RegistryBean(String email, String password, String dob) {
        this.email = email;
        this.password = password;
        this.dob = dob;
    }
    public RegistryBean(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
