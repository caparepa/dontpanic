package com.h2g2.dontpanic.networking.handler;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.h2g2.dontpanic.networking.base.RetroBase;
import com.h2g2.dontpanic.networking.interfaces.User;
import com.h2g2.dontpanic.session.SessionUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/23
 */
public class AccountHandler extends RetroBase {
    private User routes;

    public AccountHandler(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
        routes = retrofit.create(User.class);
    }
    public void register(Callback<ResponseBody> callBack, String bean){
        Call<ResponseBody> call = routes.register(bean);
        call.enqueue(callBack);
    }
    public void logIn(Callback<ResponseBody> callBack, String bean){
        Call<ResponseBody> call = routes.login(bean);
        call.enqueue(callBack);
    }
    public void getAccount(Context context, Callback<ResponseBody> callBack){
        Call<ResponseBody> call = routes.getAccount(SessionUtil.getHeaderAuthorization(context));
        call.enqueue(callBack);
    }

    public void getKeys(String authorization, Callback<ResponseBody> callBack, String accountId) {
        Call<ResponseBody> call = routes.getKeys(authorization, accountId);
        call.enqueue(callBack);
    }

    public void saveKeys(String authorization, String accountId, Callback<ResponseBody> callBack, String bean){
        Call<ResponseBody> call = routes.saveKeys(authorization, accountId, bean);
        call.enqueue(callBack);
    }

    public void refresh(String authorization, String accountId, Callback<ResponseBody> callBack){
        Call<ResponseBody> call = routes.refresh(authorization);
        call.enqueue(callBack);
    }

    public void getCodeChangePass(Callback<ResponseBody> callBack,String bean){
        Call<ResponseBody> call = routes.getCodeChangePass(bean);
        call.enqueue(callBack);
    }
    public void resetPassword(String authorization, Callback<ResponseBody> callBack,String bean){
        Call<ResponseBody> call = routes.resetPassword(authorization,bean);
        call.enqueue(callBack);
    }
}
