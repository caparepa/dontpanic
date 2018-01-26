package com.h2g2.dontpanic.networking.handler.response;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.bean.ResponseGenericBean;
import com.h2g2.dontpanic.bean.data.Data;
import com.h2g2.dontpanic.bean.data.Profile;
import com.h2g2.dontpanic.bean.profile.ProfileDataBean;
import com.h2g2.dontpanic.bean.profile.ProfileNewDataBean;
import com.h2g2.dontpanic.bean.profile.ResponseProfileStatusBean;
import com.h2g2.dontpanic.networking.constants.NetworkCodes;
import com.h2g2.dontpanic.utils.SharedPreferencesUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;


/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class ResponseProfileHandler extends ResponseBaseHandler implements NetworkCodes {
    private BaseActivity activity;

    public ResponseProfileHandler(BaseActivity ctx) {
        super(ctx);
        activity=ctx;
    }

    public void processResponseCore(Response<ResponseBody> response){

    }

    public ResponseProfileStatusBean processResponseBean(Response<ResponseBody> response) {
        String body = "";
        if(!tokenExpired(response)){
            try {
                if (response.code() == CODE_SUCCESS) {
                    if (response.body() != null){
                        body = response.body().string();

                        Gson gson = new GsonBuilder()
                                .registerTypeAdapter(ResponseProfileStatusBean.class, new ResponseProfileStatusBean.ResponseProfileStatusBeanDeserilizer())
                                .create();

                        ResponseProfileStatusBean resp = gson.fromJson(body,ResponseProfileStatusBean.class);

                        return  resp;
                    }
                }else{
                    Toast.makeText(activity,"status: "+response.message(),Toast.LENGTH_LONG).show();
                }
            }catch (Exception e){
                Log.e("","");
                return null;
            }
        }else{
            activity.logOut();
            Toast.makeText(activity,"token expired",Toast.LENGTH_LONG).show();
        }
        return null;
    }

    public int processResponseSetEnergy(Response<ResponseBody> response) {
        String body = "";
        if(!tokenExpired(response)){
            try {
                if (response.code() == CODE_SUCCESS || response.code() == CODE_SUCCESS_CREATED) {
                    if (response.body() != null)
                        body = response.body().string();
                    Type responseType = new TypeToken<ResponseGenericBean<ProfileDataBean>>() {}.getType();
                    ResponseGenericBean<ProfileDataBean> resp = new Gson().fromJson(body,responseType);

                    if (resp!=null && resp.getStatus()!=null){
                        if (resp.getStatus()==CODE_SUCCESS || response.code() == CODE_SUCCESS_CREATED){
                            Data userData = SharedPreferencesUtil.getUserDataInfoPref(activity);
                            userData.setCurrentProfile(resp.getData().getProfile());
                            List<Profile> profiles = userData.getProfiles();
                            for (int i = 0; i <profiles.size(); i++){
                                if(profiles.get(i).getId().equalsIgnoreCase(resp.getData().getProfile().getId())){
                                    profiles.set(i, resp.getData().getProfile());
                                    break;
                                }
                            }
                            userData.setProfiles(profiles);
                            SharedPreferencesUtil.saveUserDataInfoPref(userData, activity);
                            /*if(activity instanceof ChannelPagerActivity){
                                ((ChannelPagerActivity)activity).validateDatabaseTrackers(userData.getProfile().getTrackers());
                            }*/
                            return resp.getData().getProfile().getEnergy();
                        }else{
                            Toast.makeText(activity,"status: "+resp.getStatus(),Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }catch (Exception e){
                return -1;
            }
        }else{
            activity.logOut();
            Toast.makeText(activity,"token expired",Toast.LENGTH_LONG).show();
        }
        return -1;
    }


    public void  processResponseSave(Response<ResponseBody> response) {
        String body = "";
        if(!tokenExpired(response)){
            try {
                if (response.code() == CODE_SUCCESS_CREATED){
                    if (response.body() != null){
                        body = response.body().string();
                        Type responseType = new TypeToken<ResponseGenericBean<ProfileNewDataBean>>() {
                        }.getType();
                        ResponseGenericBean<ProfileNewDataBean> resp = new Gson().fromJson(
                                body, responseType);
                        if (resp != null && resp.getStatus() != null) {

                            Data currentUser = SharedPreferencesUtil.getUserDataInfoPref(activity);
                            Profile newProfile = resp.getData().getNewProfile();
                            if(currentUser.getProfile()==null || currentUser.getProfile().getId()==null){
                                currentUser.setProfile(newProfile);
                            }
                            currentUser.setCurrentProfile(newProfile);
                            List<Profile> profiles = currentUser.getProfiles();
                            if(profiles==null){
                                profiles = new ArrayList<>();
                            }
                            profiles.add(newProfile);
                            currentUser.setProfiles(profiles);
                            currentUser.setToken(resp.getData().getToken());
                            SharedPreferencesUtil.saveUserDataInfoPref(currentUser, activity);

                            /*
                            ((AvatarSelectorActivity2)activity).usernameAvailable();
                            ((AvatarSelectorActivity2)activity).stopAnimationProgressOnArrows();
                            ((AvatarSelectorActivity2)activity).showSuccessfullView();
                            ((AvatarSelectorActivity2)activity).disableButtonNext();
                            ((AvatarSelectorActivity2)activity).disableEditTextAfterValidateUsername();
                            ((AvatarSelectorActivity2)activity).binding.includeAvatarSelect.includedAppBarTitle2.fabBackButton.setVisibility(View.GONE);
                            //((AvatarSelectorActivity2)activity).next=true;
                            ((AvatarSelectorActivity2)activity).binding.includeAvatarSelect.textviewcoolname.setVisibility(View.VISIBLE);
                            //((AvatarSelectorActivity2)activity).onProfileCreation();
                            ((AvatarSelectorActivity2)activity).onFinishBand();
                            //((AvatarSelectorActivity2)activity).binding.includeAvatarSelect.buttonAvatarFinishGood.performClick();
                            */
                        }


                    }
                }else{
                    // USERNAME IS TAKEN BY ANOTHER USER
                    /*((AvatarSelectorActivity2)activity).issueRaisedOnUsername(activity.getResources()
                            .getString(R.string.username_allready_taken));
                    ((AvatarSelectorActivity2)activity).enableEditTextAfterValidateUsername();
                    ((AvatarSelectorActivity2)activity).disableButtonNext();*/

                }
            }catch (Exception e){
                Log.e("","");
            }
        }else{
            activity.logOut();
            //Toast.makeText(activity,activity.getString(R.string.error_token_expired),Toast.LENGTH_LONG).show();
        }
    }
}
