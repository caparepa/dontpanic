package com.h2g2.dontpanic.networking.handler.response;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.bean.ResponseGenericBean;
import com.h2g2.dontpanic.bean.avatar.Avatar;
import com.h2g2.dontpanic.bean.avatar.AvatarBean;
import com.h2g2.dontpanic.bean.data.Profile;
import com.h2g2.dontpanic.networking.constants.NetworkCodes;
import com.h2g2.dontpanic.utils.SharedPreferencesUtil;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public class ResponseAvatarHandler implements NetworkCodes {
    private AppCompatActivity activity;
    private String body;
    public ResponseAvatarHandler(AppCompatActivity activity) {
        this.activity = activity;
    }
    public void processResponse(Response<ResponseBody> response){
        body = "";
        try {
            if (response.code() == CODE_SUCCESS) {
                if (response.body() != null)
                    body = response.body().string();
                Type responseType = new TypeToken<ResponseGenericBean<Avatar>>() {}.getType();
                ResponseGenericBean<Avatar> resp = new Gson().fromJson(body,responseType);
                if (resp!=null && resp.getStatus()!=null){
                    if (resp.getStatus()==CODE_SUCCESS){

                        Avatar avatars = resp.getData();
                        /*SharedPreferencesUtil.saveInfoPref(avatars.getAvatars(), activity,
                                activity.getString(R.string.avatar_list));*/

                        Log.e("Log Log","Log");
                        //avatarSelectorActivity.setViews(resp.getData());
                        downloadImages(resp.getData(), true);
                    }
                }
            }
        }catch (Exception e){
            Log.e("Login Error",e.getMessage());
        }
    }


    public void processResponseLogin(Response<ResponseBody> response,
                                     BaseActivity activity, Profile profile){
        body = "";
        try {
            if (response.code() == CODE_SUCCESS) {
                if (response.body() != null)
                    body = response.body().string();
                Type responseType = new TypeToken<ResponseGenericBean<Avatar>>() {}.getType();
                ResponseGenericBean<Avatar> resp = new Gson().fromJson(body,responseType);
                if (resp!=null && resp.getStatus()!=null){
                    if (resp.getStatus()==CODE_SUCCESS){
                        Avatar avatars = resp.getData();
                        /*SharedPreferencesUtil.saveInfoPref(avatars.getAvatars(), activity,
                                activity.getString(R.string.avatar_list));*/
                        for (AvatarBean avatarBean:avatars.getAvatars()){
                            if(profile.getAvatar_id()==avatarBean.getId()){
                                //SharedPreferencesUtil.saveAvatar(Utils.getFilenameFromUrl(avatarBean.getImage()), activity);
                            }

                        }
                    }
                }
            }
        }catch (Exception e){
            Log.e("Login Error",e.getMessage());
        }
    }

    public static void savePreferences(Context act, String bearer, String key) {
        /*SharedPreferences prefs = act.getSharedPreferences(act.getString(R.string.shared_preferences_file), act.MODE_PRIVATE);
        Gson gson = new Gson();
        String longString = gson.toJson(bearer);
        prefs.edit().putString(key, longString).commit();*/
    }

    Integer countLoads=0;
    int size=0;

    private void downloadImages(final Avatar avatar, final boolean dismiss) throws FileNotFoundException {
        countLoads=0;
        size=avatar.avatars.size();

        /*for (final AvatarBean bean: avatar.avatars)
            bean.bitmap= BitmapFactory.decodeStream(activity.openFileInput(Utils.getFilenameFromUrl(bean.getImage())));
        ((AvatarSelectorActivity)activity).setViews(avatar);
        ((AvatarSelectorActivity)activity).dismissDialog();*/
    }
}
