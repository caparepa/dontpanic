package com.h2g2.dontpanic.bean.avatar;

import java.util.List;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class Avatar {
    public List<AvatarBean> avatars;
    public Avatar(List<AvatarBean> avatars) {
        this.avatars = avatars;
    }
    public List<AvatarBean> getAvatars() {
        return avatars;
    }
    public void setAvatars(List<AvatarBean> avatars) {
        this.avatars = avatars;
    }
}
