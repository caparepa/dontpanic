package com.h2g2.dontpanic.bean.avatar;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class AvatarBeanPrime {
    private AvatarBean avatar;

    public AvatarBeanPrime(AvatarBean avatar) {
        this.avatar = avatar;
    }

    public AvatarBean getAvatar() {
        return avatar;
    }

    public void setAvatar(AvatarBean avatar) {
        this.avatar = avatar;
    }
}
