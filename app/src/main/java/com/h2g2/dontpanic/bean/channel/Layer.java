package com.h2g2.dontpanic.bean.channel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class Layer implements Serializable {
    private Integer order;
    private String type;
    private List<Section> sections = null;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }


    public Layer(Integer order, String type, List<Section> sections) {
        this.order = order;
        this.type = type;
        this.sections = sections;
    }

    public Layer() {
    }
}
