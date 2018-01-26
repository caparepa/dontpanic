package com.h2g2.dontpanic.bean.channel;

import java.io.Serializable;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class Section implements Serializable {
    private String order1;
    private String image;
    private String color;

    public Section() {
    }

    public Section(String order, String image, String color) {
        this.order1 = order;
        this.image = image;
        this.color = color;
    }

    public String getOrder() {
        return order1;
    }

    public void setOrder(String order) {
        this.order1 = order;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
