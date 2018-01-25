package com.h2g2.dontpanic.networking.handler.response;

import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.activities.user.RegisterUserActivity;
import com.h2g2.dontpanic.bean.AccountContainerBean;
import com.h2g2.dontpanic.bean.RegistryBean;
import com.h2g2.dontpanic.bean.ResponseDefaultBean;
import com.h2g2.dontpanic.bean.ResponseGenericBean;
import com.h2g2.dontpanic.bean.data.Data;
import com.h2g2.dontpanic.networking.constants.NetworkCodes;
import com.h2g2.dontpanic.networking.utils.RequestResponseHandler;
import com.h2g2.dontpanic.utils.SharedPreferencesUtil;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/24
 */
public class ResponseRegisterHandler extends ResponseBaseHandler implements NetworkCodes {
    public RegistryBean mBean;
    RequestResponseHandler requestResponseHandler;

    public ResponseRegisterHandler(BaseActivity activity) {
        super(activity);
        requestResponseHandler = new RequestResponseHandler();
    }

    @Override
    public void processResponseCore(Response<ResponseBody> response) {
        if (activity == null) return;
        try {
            String body = "";
            System.out.println("RESPONSE "+response.code());
            System.out.println(response.body());
            RegisterUserActivity regAct = ((RegisterUserActivity)activity);
            if (response.code() == 500) {
                if (response.errorBody() != null)
                    body = response.errorBody().string();
                //Toast.makeText(activity, "Error en registro", Toast.LENGTH_LONG).show();
                //TODO: SHOW ALERT!
                System.out.println(body);

            }
            if (response.code() == CODE_SUCCESS_CREATED) {
                if (response.body() != null) {
                    body = response.body().string();
                    System.out.println("body: "+body);

                    final Type responseType = new TypeToken<ResponseGenericBean<AccountContainerBean>>() {
                    }.getType();

                    final String finalBody = body;

                    //TODO: ALERT REGISTRATION COMPLETE
                    ResponseGenericBean<AccountContainerBean> resp = new Gson().fromJson(finalBody,responseType);
                    Data data = new Data();
                    data.setAccount(resp.getData().getAccount());
                    data.setToken(resp.getData().getToken());
                    SharedPreferencesUtil.saveUserDataInfoPref(data,activity);

                    //TODO: save user data to shared pref!
                    System.out.println(data);
                    regAct.showProgress(false);
                    regAct.showMessageAlert("Account created", resp.getMessage());

                }
            }
            if (response.code() == CODE_ERROR) {
                if (response.errorBody() != null)
                    body = response.errorBody().string();
                String error = response.message();
                regAct.showProgress(false);
                regAct.showMessageAlert("Error", error);

                /*((RegisterUserActivity)activity).stopAnimationProgressOnArrows();
                ((RegisterUserActivity)activity).hideAnimatedDots();
                ((RegisterUserActivity)activity).changeTextBtn(activity.getString(R.string.btnTextCreate));
                ((RegisterUserActivity)activity).binding.buttonRegisterAccount.setClickable(true);*/
                //TODO: enable edit text
            }
            if (response.code() == CODE_BAD_REQUEST) {
                if (response.errorBody() != null){
                    body = response.errorBody().string();
                    regAct.showProgress(false);
                }
                System.out.println("body: " + body);
                ResponseDefaultBean resp = new Gson().fromJson(body, ResponseDefaultBean.class);
                regAct.showMessageAlert("Error", resp.getMessage());
                System.out.println("error: " + resp.getError());
                System.out.println("message: " + resp.getMessage());
                if (resp.getMessage().contains("email") && resp.getMessage().contains("registered")){
                    regAct.mEmailText.setError(resp.getMessage());
                }else if(resp.getMessage().contains("password") && resp.getMessage().contains("invalid")){
                    regAct.mPasswordText.setError(resp.getMessage());
                }

            }
            Log.d("", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("", "");
    }
}
