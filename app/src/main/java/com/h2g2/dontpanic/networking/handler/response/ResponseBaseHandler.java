package com.h2g2.dontpanic.networking.handler.response;

import android.widget.Toast;

import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.networking.constants.NetworkCodes;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/23
 */
public abstract class ResponseBaseHandler implements NetworkCodes {
    public BaseActivity activity;

    public ResponseBaseHandler(BaseActivity activity) {
        this.activity = activity;
    }

    public abstract void processResponseCore(Response<ResponseBody> response);

    public void processResponse(Response<ResponseBody> response){
        if(!tokenExpired(response)){
            processResponseCore(response);
        }else{
            activity.logOut();
            Toast.makeText(activity,"token expired",Toast.LENGTH_LONG).show();
        }
    }

    public boolean tokenExpired(Response<ResponseBody> response){
        return response.code() == CODE_UNAUTHORIZED;
    }
}
