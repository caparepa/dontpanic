package com.h2g2.dontpanic.bean;

import com.h2g2.dontpanic.bean.RegisterBandBean;

import java.io.Serializable;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/25
 */
public class RegisterBandResponseBean implements Serializable {

    RegisterBandBean newTracker;

    public RegisterBandResponseBean(RegisterBandBean newTracker) {
        this.newTracker = newTracker;
    }

    public RegisterBandBean getNewTracker() {
        return newTracker;
    }

    public void setNewTracker(RegisterBandBean newTracker) {
        this.newTracker = newTracker;
    }
}
