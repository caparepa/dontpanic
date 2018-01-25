package com.h2g2.dontpanic.networking.handler.response;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.activities.user.RegisterUserActivity;
import com.h2g2.dontpanic.bean.AccountContainerBean;
import com.h2g2.dontpanic.bean.RegistryBean;
import com.h2g2.dontpanic.bean.ResponseDefaultBean;
import com.h2g2.dontpanic.bean.ResponseGenericBean;
import com.h2g2.dontpanic.bean.UserAccountContainerBean;
import com.h2g2.dontpanic.bean.data.Data;
import com.h2g2.dontpanic.networking.constants.NetworkCodes;
import com.h2g2.dontpanic.networking.handler.UserAccountHandler;
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
            if (response.code() == 500) {
                if (response.errorBody() != null)
                    body = response.errorBody().string();
                //Toast.makeText(activity, "Error en registro", Toast.LENGTH_LONG).show();
                //TODO: SHOW ALERT!
            }
            if (response.code() == CODE_SUCCESS_CREATED) {
                if (response.body() != null) {
                    body = response.body().string();

                    final Type responseType = new TypeToken<ResponseGenericBean<UserAccountContainerBean>>() {
                    }.getType();

                    final String finalBody = body;

                    //TODO: ALERT REGISTRATION COMPLETE

                    ResponseGenericBean<AccountContainerBean> resp = new Gson().fromJson(finalBody,responseType);
                    Data data = new Data();
                    data.setAccount(resp.getData().getAccount());
                    data.setToken(resp.getData().getToken());
                    SharedPreferencesUtil.saveUserDataInfoPref(data,activity);

                    //TODO: save user data to shared pref!

                    /*DialogUtils.showGenericDialogHostSimple(activity,
                            true,
                            activity.getString(R.string.textTitleDialogRegistrationComplete),
                            activity.getString(R.string.textContentDialogRegistrationComplete),
                            activity.getString(R.string.dialog_button_go),
                            "",
                            R.drawable.gaia_host,
                            new PostCallback() {
                                @Override
                                public void callback() {
                                    *//*UserAccountHandler networkHandler = new UserAccountHandler(activity);
                                    final RegisterOnBoardingBean onBoardingBean = new RegisterOnBoardingBean();
                                    onBoardingBean.setAccount(resp.getData().getAccount());
                                    onBoardingBean.setRegister(mBean);*//*
                                    ResponseGenericBean<AccountContainerBean> resp = new Gson().fromJson(finalBody,responseType);
                                    Data data = new Data();
                                    data.setAccount(resp.getData().getAccount());
                                    data.setToken(resp.getData().getToken());
                                    SharedPreferencesUtil.saveUserDataInfoPref(data,activity);
                                    *//*Intent intent = new Intent(activity, AvatarSelectorActivity.class);
                                    Gson gson = new Gson();
                                    intent.putExtra(BaseActivity.ON_BOARDING_BEAN, gson.toJson(onBoardingBean));
                                    ((RegisterUserActivity)activity).onBoardingData= gson.toJson(onBoardingBean);
                                    ((RegisterUserActivity)activity).binding.buttonRegisterAccount.setClickable(true);
                                    activity.startActivity(intent);*//*
                                }
                            },
                            new PostCallback() {
                                @Override
                                public void callback() {
                                    ResponseGenericBean<AccountContainerBean> resp = new Gson().fromJson(finalBody,responseType);
                                    AccountHandler networkHandler = new AccountHandler(activity);
                                    final RegisterOnBoardingBean onBoardingBean = new RegisterOnBoardingBean();
                                    onBoardingBean.setAccount(resp.getData().getAccount());
                                    onBoardingBean.setRegister(mBean);
                                    Data data = new Data();
                                    data.setAccount(resp.getData().getAccount());
                                    data.setToken(resp.getData().getToken());
                                    SharedPreferencesUtil.saveUserDataInfoPref(data,activity);
                                    Intent intent = new Intent(activity, AvatarSelectorActivity.class);
                                    Gson gson = new Gson();
                                    intent.putExtra(BaseActivity.ON_BOARDING_BEAN, gson.toJson(onBoardingBean));
                                    ((RegisterUserActivity)activity).onBoardingData= gson.toJson(onBoardingBean);
                                    ((RegisterUserActivity)activity).binding.buttonRegisterAccount.setClickable(true);
                                    activity.startActivity(intent);
                                }
                            }
                    );*/


                }
            }
            if (response.code() == 422) {
                if (response.errorBody() != null)
                    body = response.errorBody().string();
                /*((RegisterUserActivity)activity).stopAnimationProgressOnArrows();
                ((RegisterUserActivity)activity).hideAnimatedDots();
                ((RegisterUserActivity)activity).changeTextBtn(activity.getString(R.string.btnTextCreate));
                ((RegisterUserActivity)activity).binding.buttonRegisterAccount.setClickable(true);*/
                //TODO: enable edit text
            }
            if (response.code() == CODE_BAD_REQUEST) {
                if (response.errorBody() != null)
                    body = response.errorBody().string();
                ResponseDefaultBean resp = new Gson().fromJson(body, ResponseDefaultBean.class);
                /*((RegisterUserActivity) activity).showErrorIcon(resp.getMessage(),
                        "API");*/
                //TODO: alert!
                if (resp.getMessage().contains("email")
                        && resp.getMessage().contains("registered")){
                    /*((RegisterUserActivity) activity).showErrorIcon(resp.getMessage(),activity.getString(R.string.email));*/
                    //TODO: alert!

                }else if(resp.getMessage().contains("password")
                        && resp.getMessage().contains("invalid")){
                    /*((RegisterUserActivity) activity).showErrorIcon(resp.getMessage(),activity.getString(R.string.password));*/
                    //TODO: ALERT!
                }
                //((RegisterUserActivity) activity).showErrorIcon(resp.getMessage(),activity.getString(R.string.password));
                /*((RegisterUserActivity)activity).stopAnimationProgressOnArrows();
                ((RegisterUserActivity)activity).hideAnimatedDots();
                ((RegisterUserActivity)activity).changeTextBtn(activity.getString(R.string.btnTextCreate));
                ((RegisterUserActivity)activity).enableEdittext();
                ((RegisterUserActivity)activity).binding.buttonRegisterAccount.setClickable(true);*/

            }
            Log.d("", "");
            //Toast.makeText(getApplicationContext(),"status: "+resp.getStatus(),Toast.LENGTH_LONG).canAnimate();
            //TODO: ALERT!
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: alert!
        }
        Log.d("", "");
    }
}
