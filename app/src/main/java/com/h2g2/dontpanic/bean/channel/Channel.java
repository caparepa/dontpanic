package com.h2g2.dontpanic.bean.channel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class Channel {
    private String id;
    private String name;
    private String hostGreeting;
    private String description;
    private Boolean _private;
    private Boolean published;
    private String trophyRoomImage;
    private String updatedAt;
    private String avatar_image;
    private List<Layer> layers = null;
    private List<Waypoint> waypoints = null;

    public Channel() {
    }
    public Channel(String id, String name, String hostGreeting, String description, Boolean _private, Boolean published, String trophyRoomImage, String updatedAt, String avatarImage, List<Layer> layers, List<Waypoint> waypoints) {
        this.id = id;
        this.name = name;
        this.hostGreeting = hostGreeting;
        this.description = description;
        this._private = _private;
        this.published = published;
        this.trophyRoomImage = trophyRoomImage;
        this.updatedAt = updatedAt;
        this.avatar_image = avatarImage;
        this.layers = layers;
        this.waypoints = waypoints;
    }

    public String getId() {
        return id;
    }

    public String getName() { return name; }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) { this.name = name; }

    public String getHostGreeting() {
        return hostGreeting;
    }

    public void setHostGreeting(String hostGreeting) {
        this.hostGreeting = hostGreeting;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean get_private() {
        return _private;
    }

    public void set_private(Boolean _private) {
        this._private = _private;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public String getTrophyRoomImage() {
        return trophyRoomImage;
    }

    public void setTrophyRoomImage(String trophyRoomImage) {
        this.trophyRoomImage = trophyRoomImage;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAvatarImage() {
        return avatar_image;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatar_image = avatarImage;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }
}
