package com.h2g2.dontpanic.networking.handler;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;


import com.h2g2.dontpanic.networking.base.RetroBase;
import com.h2g2.dontpanic.networking.interfaces.Profile;
import com.h2g2.dontpanic.session.SessionUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class ProfileHandler extends RetroBase {
    private Profile routes;
    public ProfileHandler(AppCompatActivity appCompatActivity){
        super(appCompatActivity);
        routes = retrofit.create(Profile.class);
    }
    public void setCurrentProfile(Callback<ResponseBody> callBack, Context context, String profileId){
        Call<ResponseBody> call =routes.setProfile(SessionUtil.getHeaderAuthorization(context),profileId);
        call.enqueue(callBack);
    }
    public void saveNewProfile(Callback<ResponseBody> callBack, Context context, String body){
        Call<ResponseBody> call =routes.save(SessionUtil.getHeaderAuthorization(context),body);
        //Call<ResponseBody> call =routes.save("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwcm9maWxlX2lkIjoxNjcsInVzZXJfaWQiOjE3Mywic3ViIjoxNzMsImlzcyI6Imh0dHA6Ly9kZXYtYXBwLXVuaWNlZi40Mm1hdGUuY29tL2FwaS92MS9hY2NvdW50L2xvZ2luIiwiaWF0IjoxNTEwMDk3MDUwLCJleHAiOjE1MTAxMTE0NTAsIm5iZiI6MTUxMDA5NzA1MCwianRpIjoiY21BZ3NqdVpvOEpnTkNlQiJ9.MJnpZ8mKT-lKSyHNPG3Ur_xn4g2iWnnSzC51mwmH96w",body);
        call.enqueue(callBack);
    }
    public void getProfileById(Callback<ResponseBody> callBack, Context context, String profileId){
        Call<ResponseBody> call =routes.getProfile(SessionUtil.getHeaderAuthorization(context),profileId);
        call.enqueue(callBack);
    }
    public void updateProfileById(Callback<ResponseBody> callBack, Context context,String profileId, String body){
        Call<ResponseBody> call =routes.update(SessionUtil.getHeaderAuthorization(context),profileId,body);
        call.enqueue(callBack);
    }
    public void updateProfileStepsById(Callback<ResponseBody> callBack, Context context,String profileId){
        Call<ResponseBody> call =routes.updateSteps(SessionUtil.getHeaderAuthorization(context),profileId);
        call.enqueue(callBack);
    }
    public void getProfileStatus(Callback<ResponseBody> callBack, Context context, String profileId, String channelId){
        Call<ResponseBody> call =routes.getProfileStatus(SessionUtil.getHeaderAuthorization(context),profileId, channelId);
        call.enqueue(callBack);
    }
    public void setProfileEnergy(Callback<ResponseBody> callBack, Context context, String profileId, String channelId, String bean){
        Call<ResponseBody> call =routes.setProfileEnergy(SessionUtil.getHeaderAuthorization(context),profileId, channelId,bean);
        call.enqueue(callBack);
    }
    public void updateProfilePassword(Callback<ResponseBody> callBack, Context context, String body){
        Call<ResponseBody> call =routes.updatePassword(SessionUtil.getHeaderAuthorization(context), body);
        call.enqueue(callBack);
    }

    public void deleteProfile(Callback<ResponseBody> callBack, Context context, String profileId, String body){
        Call<ResponseBody> call =routes.deleteProfile(SessionUtil.getHeaderAuthorization(context), profileId, body);
        call.enqueue(callBack);
    }

    public void deleteTracker(Callback<ResponseBody> callBack, Context context, String trackerId){
        Call<ResponseBody> call = routes.deleteTracker(SessionUtil.getHeaderAuthorization(context), trackerId);
        call.enqueue(callBack);
    }
}
