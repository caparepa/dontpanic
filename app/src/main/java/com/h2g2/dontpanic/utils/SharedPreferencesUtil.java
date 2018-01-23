package com.h2g2.dontpanic.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.h2g2.dontpanic.models.serializables.UserData;
import com.h2g2.dontpanic.services.interfaces.SharedPreferencesConstants;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/18
 */
public class SharedPreferencesUtil implements SharedPreferencesConstants {


    public static boolean saveUserDataPref(UserData mUser, Context mContext)
    {
        Gson gson = new Gson();
        String userString = gson.toJson(mUser);

        String key = mContext.getString(KEY_USER);
        String sharedFile = mContext.getString(SHARED_FILE);

        SharedPreferences prefs = mContext.getSharedPreferences(sharedFile,mContext.MODE_PRIVATE);
        return prefs.edit().putString(key,userString).commit();
    }

    public static UserData getUserDataPref(Context mContext){
        UserData mUser = new UserData();
        Gson gson = new Gson();
        String userString = gson.toJson(mUser);

        String key = mContext.getString(KEY_USER);
        String sharedFile = mContext.getString(SHARED_FILE);
        SharedPreferences prefs = mContext.getSharedPreferences(sharedFile,mContext.MODE_PRIVATE);
        String storedAdvertisementString = prefs.getString(key, ERROR_STRING);
        java.lang.reflect.Type type = new TypeToken<UserData>() {}.getType();
        if (!storedAdvertisementString.equalsIgnoreCase(ERROR_STRING)) {
            mUser = gson.fromJson(storedAdvertisementString, type);
        }
        return mUser;
    }

}
