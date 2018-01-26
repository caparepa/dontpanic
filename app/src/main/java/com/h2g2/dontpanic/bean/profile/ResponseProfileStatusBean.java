package com.h2g2.dontpanic.bean.profile;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.h2g2.dontpanic.bean.profile.reward.ConcreteReward;
import com.h2g2.dontpanic.bean.profile.reward.Reward;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class ResponseProfileStatusBean {
    String message;
    String error;
    Integer status;

    public String getMessage() {
        return message;
    }
    public String getError() {
        return error;
    }
    public Integer getStatus() {
        return status;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setError(String error) {
        this.error = error;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    private Data mData;

    public Data getData() {
        return mData;
    }
    public void setData(Data data) {
        this.mData = data;
    }


    public static class ResponseProfileStatusBeanDeserilizer implements JsonDeserializer<ResponseProfileStatusBean> {

        @Override
        public ResponseProfileStatusBean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            ResponseProfileStatusBean profileStatusBean = new Gson().fromJson(json, ResponseProfileStatusBean.class);
            JsonObject jsonObject = json.getAsJsonObject();

            if (jsonObject.has("data")) {
                JsonElement elem = jsonObject.get("data");
                if (elem != null && !elem.isJsonNull()){
                    profileStatusBean.setData(new Gson().fromJson(elem, Data.class));
                    if(elem.getAsJsonObject().has("rewards")){
                        JsonElement rewards = elem.getAsJsonObject().get("rewards");
                        List<Reward> listReward = new ArrayList<>();
                        for (JsonElement r : rewards.getAsJsonArray()){
                            Reward reward = new Gson().fromJson(r,Reward.class);
                            if(r.getAsJsonObject().has("concrete_reward")){
                                JsonElement concrete = r.getAsJsonObject().get("concrete_reward");
                                ConcreteReward c = null;
                                if(concrete.isJsonObject()){
                                    c = new Gson().fromJson(concrete, ConcreteReward.class);
                                }
                                reward.setConcrete_reward(c);
                            }
                            listReward.add(reward);
                        }
                        profileStatusBean.getData().setRewards(listReward);
                    }
                }
            }
            return profileStatusBean ;
        }
    }
}
