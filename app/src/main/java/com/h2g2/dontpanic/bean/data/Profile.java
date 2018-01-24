package com.h2g2.dontpanic.bean.data;

import android.util.Log;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/24
 */
public class Profile implements Serializable {
    private String id;
    private String name;
    //private Boolean default;
    private String created_at;
    private String updated_at;
    private int avatar_id;
    private Pivot pivot;
    private int energy;
    private int total_energy;
    private int total_daily_energy;

    private String energy_expire_date;
    private int energy_to_expire;

    private String last_apply_date;
    private int power_points;
    private int total_power_points;
    private boolean is_child;
    private int total_rutf;
    private  int total_badges;

    public Profile() {

        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(10);
        char tempChar;

        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }

        Log.i("lala", randomStringBuilder.toString());

        name = randomStringBuilder.toString();
        power_points = (int)(Math.random()*3000);
        energy = (int)(Math.random()*5000);
    }

    public Profile(boolean creation) {
        //TODO quitar esto

    }

    public Profile(
            String id,
            String name,
            String created_at,
            String updated_at,
            int avatar_id,
            Pivot pivot,
            int energy,
            int total_energy,
            String energy_expire_date,
            int energy_to_expire,
            int total_daily_energy,
            String last_apply_date,
            int power_points,
            int total_power_points,
            boolean is_child) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.avatar_id = avatar_id;
        this.pivot = pivot;
        this.energy = energy;
        this.total_energy = total_energy;
        this.total_daily_energy = total_daily_energy;
        this.energy_expire_date = energy_expire_date;
        this.energy_to_expire = energy_to_expire;
        this.last_apply_date = last_apply_date;
        this.power_points = power_points;
        this.total_power_points = total_power_points;
        this.is_child = is_child;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getAvatar_id() {
        return avatar_id;
    }

    public void setAvatar_id(int avatar_id) {
        this.avatar_id = avatar_id;
    }

    public Pivot getPivot() {
        return pivot;
    }

    public void setPivot(Pivot pivot) {
        this.pivot = pivot;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public String getLast_apply_date() {
        return last_apply_date;
    }

    public void setLast_apply_date(String last_apply_date) {
        this.last_apply_date = last_apply_date;
    }

    public int getPower_points() {
        return power_points;
    }

    public void setPower_points(int power_points) {
        this.power_points = power_points;
    }

    public boolean is_child() {
        return is_child;
    }

    public void setIs_child(boolean is_child) {
        this.is_child = is_child;
    }

    public int getTotal_daily_energy() {
        return total_daily_energy;
    }

    public void setTotal_daily_energy(int total_daily_energy) {
        this.total_daily_energy = total_daily_energy;
    }

    public int getTotal_power_points() {
        return total_power_points;
    }

    public void setTotal_power_points(int total_power_points) {
        this.total_power_points = total_power_points;
    }

    public int getTotal_energy() {
        return total_energy;
    }

    public void setTotal_energy(int total_energy) {
        this.total_energy = total_energy;
    }

    public String getEnergy_expire_date() {
        return energy_expire_date;
    }

    public void setEnergy_expire_date(String energy_expire_date) {
        this.energy_expire_date = energy_expire_date;
    }

    public int getEnergy_to_expire() {
        return energy_to_expire;
    }

    public void setEnergy_to_expire(int energy_to_expire) {
        this.energy_to_expire = energy_to_expire;
    }

    public int getTotal_rutf() {
        return total_rutf;
    }

    public void setTotal_rutf(int total_rutf) {
        this.total_rutf = total_rutf;
    }

    public int getTotal_badges() {
        return total_badges;
    }

    public void setTotal_badges(int total_badges) {
        this.total_badges = total_badges;
    }
}
