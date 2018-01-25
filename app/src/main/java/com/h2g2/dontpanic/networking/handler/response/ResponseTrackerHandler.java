package com.h2g2.dontpanic.networking.handler.response;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.bean.RegisterBandBean;
import com.h2g2.dontpanic.bean.ResponseDefaultError;
import com.h2g2.dontpanic.bean.ResponseGenericBean;
import com.h2g2.dontpanic.bean.data.Data;
import com.h2g2.dontpanic.bean.data.Profile;
import com.h2g2.dontpanic.bean.data.RegisterBandResponseBean;
import com.h2g2.dontpanic.bean.tracker.ListTrackerDataBean;
import com.h2g2.dontpanic.utils.SharedPreferencesUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/25
 */
public class ResponseTrackerHandler extends ResponseBaseHandler {
    private String body;
    public ResponseTrackerHandler(BaseActivity activity) {
        super(activity);
    }

    public void processResponseCore(Response<ResponseBody> response) {
        body = "";
        try {
            if (response.code() == CODE_ALREADY_REGISTERED || response.code() == CODE_ERROR) {
                if (response.errorBody() != null) {
                    body = response.errorBody().string();
                    ResponseDefaultError resp = new Gson().fromJson(body, ResponseDefaultError.class);
                    Toast.makeText(activity, "" + resp.getError(), Toast.LENGTH_LONG).show();
                }
            }
            if (response.code() == CODE_SUCCESS || response.code() == CODE_SUCCESS_CREATED) {
                if (response.body() != null)
                    body = response.body().string();

                Type responseType = new TypeToken<ResponseGenericBean<RegisterBandResponseBean>>() {
                }.getType();
                ResponseGenericBean<RegisterBandResponseBean> resp = new Gson().fromJson(body, responseType);
                if (resp!=null && resp.getStatus()!=null){
                    if (resp.getStatus()==CODE_SUCCESS || response.code() == CODE_SUCCESS_CREATED){
                        Data currentUser = SharedPreferencesUtil.getUserDataInfoPref(activity);
                        Profile profile = currentUser.getCurrentProfile();
                        RegisterBandBean trackerBean = resp.getData().getNewTracker();
                        trackerBean.setProfile_id(profile.getId());
                        processTracker(trackerBean);
                        List<RegisterBandBean> trackers = new ArrayList<>();
                        trackers.add(trackerBean);
                        profile.setTrackers(trackers);

                        List<Profile> profiles = currentUser.getProfiles();
                        if(profiles.size()>0){
                            for (int i = 0; i <profiles.size(); i++){
                                if(profiles.get(i).getId().equalsIgnoreCase(profile.getId())){
                                    profiles.get(i).setTrackers(profile.getTrackers());
                                    break;
                                }
                            }
                        }else{
                            profiles.add(profile);
                        }

                        currentUser.setCurrentProfile(profile);
                        currentUser.setProfiles(profiles);
                        SharedPreferencesUtil.saveUserDataInfoPref(currentUser, activity);
                    }
                }

            }
        }catch(Exception e){
            Log.e("Traker Error", e.getMessage());
        }
    }

    public void processTracker(RegisterBandBean trackerBean) {

        //TODO: SEE WHAT DOES THIS SHIT DO!
        /*DBTrackers trackerDb =  new DBTrackers(
                trackerBean.getId(),
                trackerBean.getName(),
                trackerBean.getType(),
                trackerBean.getVersion(),
                trackerBean.getTracker_factory_id(),
                Integer.valueOf(trackerBean.getProfile_id()),
                trackerBean.getStart_date(),
                trackerBean.getLast_sync_date(),
                trackerBean.getEnd_date(),
                true
        );
        trackerDb.save();
        if(trackerBean.getType().equalsIgnoreCase(KPHTracker.TRACKER_TYPE_NAME_KPBAND)){
            SharedPreferencesUtil.saveInfoPref(new Boolean(false), activity,activity.getString(R.string.flag_is_googlefit_connected));
            String mostRecentActTimeStamp;

            if(trackerBean.getLast_batch()!=null &&
                    trackerBean.getLast_batch().getActivity_timestamp()!=null) {
                mostRecentActTimeStamp = trackerBean.getLast_batch().getActivity_timestamp();
            }else{
                mostRecentActTimeStamp = trackerBean.getLast_sync_date();
            }

            DBBandActivity dbBandActivity = new DBBandActivity(mostRecentActTimeStamp,
                    mostRecentActTimeStamp,
                    OSDate.fromStringWithFormat(mostRecentActTimeStamp, OSDate.API_FORMAT, true).getTime(),
                    0,0,0);
            dbBandActivity.save();
        }else if(trackerBean.getType().equalsIgnoreCase(KPHTracker.TRACKER_TYPE_NAME_GOOGLEFIT)){

            SharedPreferencesUtil.saveInfoPref(new Boolean(true), activity,activity.getString(R.string.flag_is_googlefit_connected));

        }*/
    }


    public boolean processResponseAvailability(Response<ResponseBody> response) {
        body = "";
        boolean availability = false;
        if(!tokenExpired(response)){
            try {
                if (response.code() == CODE_SUCCESS) {
                    if (response.body() != null)
                        body = response.body().string();
                    Type responseType = new TypeToken<ResponseGenericBean<ListTrackerDataBean>>() {}.getType();
                    ResponseGenericBean<ListTrackerDataBean> resp = new Gson().fromJson(body,responseType);

                    if (resp!=null && resp.getStatus()!=null) {
                        if (resp.getStatus() == CODE_SUCCESS) {

                            availability = resp.getData().getTracker().size()==0;

                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            activity.logOut();
            Toast.makeText(activity,"token expired",Toast.LENGTH_LONG).show();
        }
        return availability;
    }
}
