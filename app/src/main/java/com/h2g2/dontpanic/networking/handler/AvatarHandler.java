package com.h2g2.dontpanic.networking.handler;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.h2g2.dontpanic.networking.base.RetroBase;
import com.h2g2.dontpanic.networking.interfaces.Avatar;
import com.h2g2.dontpanic.session.SessionUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class AvatarHandler extends RetroBase {
    private Avatar routes;

    public AvatarHandler(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
        routes = retrofit.create(Avatar.class);
    }
    public void getAvatars(Callback<ResponseBody> callBack){
        Call<ResponseBody> call=routes.getAvatars();
        call.enqueue(callBack);
    }
    public void getAvatars(Callback<ResponseBody> callBack, Context context){
        Call<ResponseBody> call=routes.getAvatars(SessionUtil.getHeaderAuthorization(context));
        //  Call<ResponseBody> call=routes.getAvatars("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwcm9maWxlX2lkIjo4MSwidXNlcl9pZCI6OTMsInN1YiI6OTMsImlzcyI6Imh0dHA6Ly9kZXYtdW5pY2VmLjQybWF0ZS5jb20vYXBpL3YxL2FjY291bnQvbG9naW4iLCJpYXQiOjE1MTAzNjI0NDgsImV4cCI6MTUxMDM3Njg0OCwibmJmIjoxNTEwMzYyNDQ4LCJqdGkiOiJ4N3REbzVEOGxNUXZTOFdmIn0.cnGtZRTf93BHjxwvyuicUigUd-ojB7aUXaZT80gDE94");
        call.enqueue(callBack);
    }
    public void getAvatar(Callback<ResponseBody> callBack, String avatar,Context context){
        Call<ResponseBody> call=routes.getAvatar(SessionUtil.getHeaderAuthorization(context),avatar);
        call.enqueue(callBack);
    }
}
