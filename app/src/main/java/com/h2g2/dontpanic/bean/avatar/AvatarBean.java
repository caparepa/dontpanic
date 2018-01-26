package com.h2g2.dontpanic.bean.avatar;

import android.graphics.Bitmap;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class AvatarBean {
    private int id;
    private String image;
    public boolean empty;
    public transient Bitmap bitmap;

    public AvatarBean(){}

    public AvatarBean(int id, String image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
