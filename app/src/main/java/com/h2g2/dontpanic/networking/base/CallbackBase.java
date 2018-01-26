package com.h2g2.dontpanic.networking.base;

import android.widget.Toast;

import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.bean.data.Data;
import com.h2g2.dontpanic.networking.constants.NetworkCodes;
import com.h2g2.dontpanic.networking.handler.AccountHandler;
import com.h2g2.dontpanic.session.SessionUtil;
import com.h2g2.dontpanic.utils.PostCallback;
import com.h2g2.dontpanic.utils.SharedPreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;

import javax.security.auth.callback.Callback;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/23
 */
public abstract class CallbackBase implements retrofit2.Callback<ResponseBody>, NetworkCodes {
    private BaseActivity activity;
    private int maxRetry = 3;

    public CallbackBase(BaseActivity activity){
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if(response.code() == CODE_UNAUTHORIZED){
            reconnect(maxRetry, new PostCallback() {
                @Override
                public void callback() {
                    onRetryResponse();
                }
            });
        }else{
            try {
                onResponseCore(call, response);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void reconnect(final int retries, final PostCallback successCallback){
        if(retries>0){
            final Data mUserData = SharedPreferencesUtil.getUserDataInfoPref(activity);
            AccountHandler networkHandler = new AccountHandler(activity);
            /*networkHandler.refresh(SessionUtil.getHeaderAuthorization(KidpowerApp.getAppContext()),
                    mUserData.getAccount().getId(),
                    new retrofit2.Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.code() == CODE_SUCCESS
                                    || response.code() == CODE_SUCCESS_CREATED){
                                String body = null;
                                try {
                                    body = response.body().string();
                                    JSONObject jsonObject = new JSONObject(body);
                                    String token = jsonObject.getJSONObject("data").getString("token");
                                    mUserData.setToken(token);
                                    SharedPreferencesUtil.saveUserDataInfoPref(mUserData,activity);
                                    successCallback.callback();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    reconnect(retries-1,successCallback);
                                }
                            }else{
                                reconnect(retries-1,successCallback);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            reconnect(retries-1,successCallback);
                        }
                    });*/
        }else{
            activity.logOut();
            Toast.makeText(activity,"token expired",Toast.LENGTH_LONG).show();
        }
    }

    public abstract void onResponseCore(Call<ResponseBody> call, Response<ResponseBody> response) throws JSONException, FileNotFoundException;

    public abstract void onRetryResponse();
}
