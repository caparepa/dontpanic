package com.h2g2.dontpanic.bean.tracker;

import com.h2g2.dontpanic.bean.RegisterBandBean;

import java.util.List;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/25
 */
public class ListTrackerDataBean {
    public ListTrackerDataBean() {
    }

    public List<RegisterBandBean> getTrackers() {
        return trackers;
    }

    public void setTrackers(List<RegisterBandBean> trackers) {
        this.trackers = trackers;
    }

    public List<RegisterBandBean> getTracker() {
        return tracker;
    }

    public void setTracker(List<RegisterBandBean> tracker) {
        this.tracker = tracker;
    }

    private   List<RegisterBandBean> trackers;
    private   List<RegisterBandBean> tracker;
}
