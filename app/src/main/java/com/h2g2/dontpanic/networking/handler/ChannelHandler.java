package com.h2g2.dontpanic.networking.handler;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.h2g2.dontpanic.networking.base.RetroBase;
import com.h2g2.dontpanic.networking.interfaces.Channel;
import com.h2g2.dontpanic.session.SessionUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class ChannelHandler extends RetroBase {
    public static final int MAX_HEIGHT = 1200;
    private Channel routes;
    public ChannelHandler(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
        routes = retrofit.create(Channel.class);
    }

    public void getChannels(Callback<ResponseBody> callBack, Context context){
        DisplayMetrics dm = new DisplayMetrics();
        ((AppCompatActivity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);

//        Call<ResponseBody> call =routes.getChannels(SessionUtil.getHeaderAuthorization(context));
        Call<ResponseBody> call =routes.getChannels(SessionUtil.getHeaderAuthorization(context),
                String.valueOf((dm.heightPixels> MAX_HEIGHT ? MAX_HEIGHT : dm.heightPixels)));
        call.enqueue(callBack);
    }

    public void getAllChannels(Callback<ResponseBody> callBack, Context context){
        DisplayMetrics dm = new DisplayMetrics();
        ((AppCompatActivity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        Call<ResponseBody> call =routes.getAllChannels(String.valueOf(dm.heightPixels));
        call.enqueue(callBack);
    }

    public void getChannelById(Callback<ResponseBody> callBack,Context context, int channelId){
        Call<ResponseBody> call =routes.getChannelById(SessionUtil.getHeaderAuthorization(context),channelId);
        call.enqueue(callBack);
    }
}
