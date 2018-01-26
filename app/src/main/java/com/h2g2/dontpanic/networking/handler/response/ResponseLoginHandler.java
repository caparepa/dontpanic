package com.h2g2.dontpanic.networking.handler.response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.activities.user.LoginActivity;
import com.h2g2.dontpanic.bean.RegisterBandBean;
import com.h2g2.dontpanic.bean.ResponseDefaultBean;
import com.h2g2.dontpanic.bean.ResponseDefaultError;
import com.h2g2.dontpanic.bean.data.Data;
import com.h2g2.dontpanic.networking.base.CallbackBase;
import com.h2g2.dontpanic.networking.constants.NetworkCodes;
import com.h2g2.dontpanic.session.SessionUtil;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/24
 */
public class ResponseLoginHandler implements NetworkCodes {
    private AppCompatActivity activity;
    private String body;
    public ResponseLoginHandler(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void processResponse(Response<ResponseBody> response) {
        body = "";

        try {
            LoginActivity logAct = ((LoginActivity)activity);
            if (response.code() == CODE_ALREADY_REGISTERED || response.code() == CODE_ERROR) {
                //((BaseActivity) activity).dismissDialog();
                if (response.errorBody() != null){
                    body = response.errorBody().string();
                    ResponseDefaultError resp = new Gson().fromJson(body,ResponseDefaultError.class);
                    //TODO: show alert
                }
            }else if (response.code() == CODE_SUCCESS) {
                if (response.body() != null)
                    body = response.body().string();
                ResponseDefaultBean resp = new Gson().fromJson(body,ResponseDefaultBean.class);
                //PurchaseBean data = new Gson().fromJson(body,PurchaseBean.class);
                if (resp!=null && resp.getStatus()!=null){
                    logAct.showProgress(false);
                    if (resp.getStatus()==CODE_SUCCESS){

                        //savePreferences(activity,resp.getData().getToken());
                        SessionUtil.login(body,activity);
                        //((BaseActivity)activity).showDialog();

                        JSONObject jsonObject = new JSONObject(body);
                        final Data data = new Gson().fromJson(jsonObject.getString("data").toString(),Data.class);

                        if(data.getProfiles()!=null && data.getProfiles().size()>0){

                            if(data.getProfile().getTrackers()!=null){

                                data.setCurrentProfile(data.getProfile());
                                if(data.getProfile().getTrackers().size()>0){
                                    ResponseTrackerHandler trackerHandler = new ResponseTrackerHandler((BaseActivity)activity);
                                    RegisterBandBean tracker = data.getProfile().getTrackers().get(0);
                                    tracker.setProfile_id(data.getProfile().getId());
                                    trackerHandler.processTracker(data.getProfile().getTrackers().get(0));
                                }
                            }else{
                                //((BaseActivity) activity).dismissDialog();
                                //Toast.makeText(activity,"No profile completed",Toast.LENGTH_LONG).show();
                                //TODO: SHOW ALERT!
                            }
                        }else{
                            body = response.errorBody().toString();
                            //Toast.makeText(activity,"No profile completed",Toast.LENGTH_LONG).show();
                            logAct.showMessageAlert("Error",body,false);
                        }
                    }
                }
            }else{
                //((BaseActivity) activity).dismissDialog();
                if (response.errorBody() != null){
                    logAct.showProgress(false);
                    body = response.errorBody().toString();
                    //TODO: alert!
                    /*((LoginActivity) activity).showErrorIcon(body, "");
                    ((LoginActivity) activity).showLogin();*/
                }
            }
        }catch (Exception e){
            Log.e("Login Error",e.getMessage());
            //TODO: alert!
        }
    }

    public static void savePreferences(Context act, String bearer, String key) {
        SharedPreferences prefs = act.getSharedPreferences(act.getString(R.string.shared_pref_file), act.MODE_PRIVATE);
        Gson gson = new Gson();
        String token = gson.toJson(bearer);
        prefs.edit().putString(key, token).commit();
    }
}
