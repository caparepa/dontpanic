package com.h2g2.dontpanic.networking.handler.response;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.h2g2.dontpanic.networking.base.RetroBase;
import com.h2g2.dontpanic.networking.interfaces.Avatar;
import com.h2g2.dontpanic.networking.interfaces.Tracker;
import com.h2g2.dontpanic.session.SessionUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class TrackerHandler extends RetroBase{
    private Tracker routes;

    public TrackerHandler(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
        routes = retrofit.create(Tracker.class);
    }
    public void getTrackerList(Context context, Callback<ResponseBody> callBack){
        Call<ResponseBody> call = routes.getTrackerList(SessionUtil.getHeaderAuthorization(context));
        call.enqueue(callBack);
    }
    public void saveTracker(Callback<ResponseBody> callBack, String bean, Context context){
        Call<ResponseBody> call = routes.savetTracker(SessionUtil.getHeaderAuthorization(context),bean);
        call.enqueue(callBack);
    }
    public void getTrackerById(Callback<ResponseBody> callBack,String id,Context context){
        Call<ResponseBody> call = routes.getTracker(SessionUtil.getHeaderAuthorization(context),id);
        call.enqueue(callBack);
    }
    public void updateTrackerById(Callback<ResponseBody> callBack,String trackerId,String bean,Context context){
        Call<ResponseBody> call = routes.updateTracker(SessionUtil.getHeaderAuthorization(context),trackerId,bean);
        call.enqueue(callBack);
    }
    public void deleteTracker(Callback<ResponseBody> callBack,String trackerId,Context context){
        Call<ResponseBody> call = routes.deleteTracker(SessionUtil.getHeaderAuthorization(context),trackerId);
        call.enqueue(callBack);
    }
    public void getTrackerAvailability(Callback<ResponseBody> callBack,String id,Context context){
        Call<ResponseBody> call = routes.getAvailabilityTracker(SessionUtil.getHeaderAuthorization(context),id);
        call.enqueue(callBack);
    }
}
