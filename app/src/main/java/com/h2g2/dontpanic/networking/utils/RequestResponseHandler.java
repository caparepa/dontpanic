package com.h2g2.dontpanic.networking.utils;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/23
 */
public class RequestResponseHandler {
    public RequestResponseHandler() {
    }

    public String requestGetJsonStringFromPojo(Object obj){
        return  new Gson().toJson(obj);
    }

    public JSONObject responseGetJSONObject(Response<ResponseBody> responseBody){
        if (responseBody==null)
            return null;
        String daString= null;
        try {
            daString = responseBody.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject object =null;
        try {
            object = new JSONObject(daString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
