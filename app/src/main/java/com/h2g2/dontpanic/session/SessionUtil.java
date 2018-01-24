package com.h2g2.dontpanic.session;

import android.content.Context;

import com.google.gson.Gson;
import com.h2g2.dontpanic.bean.User;
import com.h2g2.dontpanic.bean.data.Data;
import com.h2g2.dontpanic.utils.SharedPreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/23
 */
public abstract class SessionUtil implements SessionConstants {

    public static User user;

    public SessionUtil() {
    }

    public static void login(String daString, Context context){
        try {
            JSONObject jsonObject = new JSONObject(daString);
            Data data = new Gson().fromJson(jsonObject.getString("data").toString(),Data.class);
            data.setCurrentProfile(data.getProfile());
            SharedPreferencesUtil.saveUserDataInfoPref(data,context);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    public static void logOut(Context context){
        boolean did= SharedPreferencesUtil.remoUserDataFromPref(context);
    }

    public static String getHeaderAuthorization(Context context){
        String authorization="Bearer ";
        authorization += SharedPreferencesUtil.getUserDataInfoPref(context).getToken();
        return authorization;
    }
}
