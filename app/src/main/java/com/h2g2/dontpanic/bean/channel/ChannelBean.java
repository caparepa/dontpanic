package com.h2g2.dontpanic.bean.channel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class ChannelBean implements Serializable {
    private List<Channel> channels;
    public List<Channel> getChannels() {
        return channels;
    }
    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public ChannelBean(List<Channel> channels) {
        this.channels = channels;
    }
    public ChannelBean() {
    }
}
