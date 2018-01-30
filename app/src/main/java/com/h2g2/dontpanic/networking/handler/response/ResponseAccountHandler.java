package com.h2g2.dontpanic.networking.handler.response;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.h2g2.dontpanic.activities.user.SelectProfileActivity;
import com.h2g2.dontpanic.bean.ResponseGenericBean;
import com.h2g2.dontpanic.bean.data.Account;
import com.h2g2.dontpanic.bean.data.Data;
import com.h2g2.dontpanic.bean.data.Profile;
import com.h2g2.dontpanic.networking.constants.NetworkCodes;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/30
 */
public class ResponseAccountHandler implements NetworkCodes {
    private AppCompatActivity activity;
    private String body;
    public ResponseAccountHandler(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void processResponse(Response<ResponseBody> response, SelectProfileActivity profileActivity){
        body = "";
        try {
            if (response.code() == CODE_SUCCESS) {
                if (response.body() != null)
                    body = response.body().string();
                Type responseType = new TypeToken<ResponseGenericBean<Account>>() {}.getType();
                ResponseGenericBean<Account> resp = new Gson().fromJson(body,responseType);
                if (resp!=null && resp.getStatus()!=null){
                    if (resp.getStatus()==CODE_SUCCESS){
                        Log.e("Log Log","Log");
                        System.out.println("processResponse@ResponseAccountHandler");
                        System.out.println(resp.getData().toString());
                        //avatarSelectorActivity.setViews(resp.getData());
                    }
                }
            }
        }catch (Exception e){
            Log.e("Login Error",e.getMessage());
        }
    }

    public Profile getProfile(Response<ResponseBody> response ){
        body = "";
        Profile profile=null;
        try {
            if (response.code() == CODE_SUCCESS) {
                if (response.body() != null)
                    body = response.body().string();
                Type responseType = new TypeToken<ResponseGenericBean<Data>>() {}.getType();
                ResponseGenericBean<Data> resp = new Gson().fromJson(body,responseType);
                if (resp!=null && resp.getStatus()!=null){
                    profile = resp.getData().getProfiles().get(0);
                }
            }
        }catch (Exception e){
            Log.e("Login Error",e.getMessage());
        }
        return profile;
    }
}
