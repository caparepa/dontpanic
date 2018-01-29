package com.h2g2.dontpanic.activities.user;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.adapters.ProfileAdapter;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.bean.AccountContainerBean;
import com.h2g2.dontpanic.bean.ResponseDefaultBean;
import com.h2g2.dontpanic.bean.data.Account;
import com.h2g2.dontpanic.bean.data.Data;
import com.h2g2.dontpanic.bean.data.Profile;
import com.h2g2.dontpanic.bean.profile.ProfileDataBean;
import com.h2g2.dontpanic.databinding.ActivitySelectProfileBinding;
import com.h2g2.dontpanic.networking.base.CallbackBase;
import com.h2g2.dontpanic.networking.constants.NetworkCodes;
import com.h2g2.dontpanic.networking.handler.AccountHandler;
import com.h2g2.dontpanic.services.interfaces.GeneralConstants;
import com.h2g2.dontpanic.services.interfaces.SharedPreferencesConstants;
import com.h2g2.dontpanic.services.interfaces.ViewElement;
import com.h2g2.dontpanic.utils.SharedPreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class SelectProfileActivity extends BaseActivity implements
        SharedPreferencesConstants, GeneralConstants, NetworkCodes {

    ActivitySelectProfileBinding binding;
    LinearLayoutManager manager;
    Data currentData;

    private Account account;
    private Profile profile;
    private List<Profile> profiles;

    private int currentProfilePosition;
    private int initProfilePosition;
    private TextView selectProfileTitle;
    private String currentProfileId;


    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static int ENERGY_TO_CONSUME;
    public Context CURRENT_CONTEXT;
    public static int BASE_SIZE;
    public static int WINDOW;

    public static boolean DENSITY_ALLOWED;

    static {
        ENERGY_TO_CONSUME = -10;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_profile);
        setContentView(R.layout.activity_select_profile);

        if(savedInstanceState!= null){
            System.out.println("HALP!");
            currentProfilePosition = savedInstanceState.getInt(CURRENT_PROFILE_POSITION,0);
            initProfilePosition = savedInstanceState.getInt(INIT_PROFILE_POSITION,0);
            Data currentData = SharedPreferencesUtil.getUserDataInfoPref(SelectProfileActivity.this);
            account = currentData.getAccount();
            if(currentProfilePosition<currentData.getProfiles().size()){
                currentProfileId = currentData.getProfiles().get(currentProfilePosition).getId();
            }else{
                for (int i = 0; i < currentData.getProfiles().size(); i++) {
                    if(currentData.getProfiles().get(i).getId().equalsIgnoreCase(
                            currentData.getCurrentProfile().getId())){
                        currentProfilePosition = i;
                        initProfilePosition = i;
                        currentProfileId = currentData.getProfiles().get(i).getId();
                    }
                }
            }


        }else{
            //if there is no profiles loaded
            System.out.println("STUFF");
            final AccountHandler handler = new AccountHandler(this);
            handler.getAccount(this, new CallbackBase(this) {
                @Override
                public void onResponseCore(Call<ResponseBody> call, Response<ResponseBody> response) throws JSONException, FileNotFoundException {
                    if (response.code() == CODE_SUCCESS) {
                        try {
                            String body = "";
                            if (response.body() != null)
                                body = response.body().string();
                            ResponseDefaultBean resp = new Gson().fromJson(body,ResponseDefaultBean.class);
                            //PurchaseBean data = new Gson().fromJson(body,PurchaseBean.class);
                            if (resp!=null && resp.getStatus()!=null && resp.getStatus()==CODE_SUCCESS){
                                JSONObject jsonObject = new JSONObject(body);
                                final Data data = new Gson().fromJson(jsonObject.getString("data").toString(),Data.class);
                                currentData = SharedPreferencesUtil.getUserDataInfoPref(SelectProfileActivity.this);
                                SharedPreferencesUtil.saveInfoPref(data.getAccount().getKeys(),
                                        SelectProfileActivity.this,SharedPreferencesUtil.KEY_COUNT);
                                currentData.setProfiles(data.getProfiles());
                                for (int i = 0; i < data.getProfiles().size(); i++){
                                    if(data.getProfiles().get(i).getId()
                                            .equalsIgnoreCase(currentData.getCurrentProfile().getId())){
                                        currentProfilePosition = i;
                                        initProfilePosition = i;
                                        currentData.setCurrentProfile(data.getProfiles().get(i));
                                        break;
                                    }
                                }
                                SharedPreferencesUtil.saveUserDataInfoPref(currentData, SelectProfileActivity.this);
                            }
                        }catch (Exception e){

                        }
                    }
                    currentData = SharedPreferencesUtil.getUserDataInfoPref(SelectProfileActivity.this);
                    account = currentData.getAccount();
                    //settingUpDataOnView(currentData.getCurrentProfile().getId(), currentData.getProfiles());
                }

                @Override
                public void onRetryResponse() {
                    handler.getAccount(SelectProfileActivity.this, this);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Data currentData = SharedPreferencesUtil.getUserDataInfoPref(SelectProfileActivity.this);
                    account = currentData.getAccount();
                    profiles = currentData.getProfiles();
                    //settingUpDataOnView(currentData.getCurrentProfile().getId(),currentData.getProfiles());
                }
            });
        }


    }

    private void setUpElements() {
        ViewElement elements = new ViewElement() {
            @Override
            public void setUpViewTitle() {
                selectProfileTitle = binding.includedAppBarTitle.textViewTitle;
                selectProfileTitle.setText(R.string.title_profile);
            }

            @Override
            public void setUpBackButton() {
                binding.includedAppBarTitle.fabBackButton.setVisibility(View.VISIBLE);
                if (binding.includedAppBarTitle.fabBackButton.getVisibility() == View.VISIBLE) {
                    binding.includedAppBarTitle.fabBackButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onBackPressed();
                        }
                    });
                }
            }

            @Override
            public void setUpTextFields() {

            }

            @Override
            public void setUpInputFields() {

            }

            @Override
            public void setUpButtons() {

            }

            @Override
            public void setUpElements() {
                //set adapter whatchamacallit here!
            }
        };
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("currentProfilePos", currentProfilePosition);
        outState.putInt("initProfilePos", initProfilePosition);
        super.onSaveInstanceState(outState);
    }

    //set values!
    public void refreshValues(Profile profile) {
        /*setUserName2View(profile.getProfile().getName());
        setDailySteps(profile.getProfile().getTotal_daily_energy()); // daily steps
        setTotalSteps(profile.getProfile().getTotal_energy()); // total steps gained
        setTrackerID(profile.getProfile()); // tracker id
        setCurrentAvailablePowerPoints(profile.getProfile().getPower_points()); // current pp available
        setTotalPPGained(profile.getProfile().getTotal_power_points()); // total pp gained
        updateTotalRutf(profile.getProfile().getTotal_rutf());
        updateTotalBadges(profile.getProfile().getTotal_badges());*/
    }

    private void setDataOnView(){

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        try {
            snapHelper.attachToRecyclerView(binding.listProfilesRecyclerView);
        } catch (Exception e){
            e.printStackTrace();
        }

        final ProfileAdapter profileAdapter = new ProfileAdapter(profiles, this);
        binding.listProfilesRecyclerView.setAdapter(profileAdapter);
        binding.listProfilesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    int pos = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                    if ((pos != -1) && (profiles.get(pos) != null)) {
                        //refreshValues(profiles.get(pos));
                        currentProfilePosition = pos;
                        Data currentData = SharedPreferencesUtil.getUserDataInfoPref(SelectProfileActivity.this);
                        try {
                            SelectProfileActivity.this.currentProfileId = currentData.getProfiles().get(pos).getId();
                        } catch (Exception e){
                            //TODO me dio un indexoutofbound aqui, revisen xD
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });



        if (profiles.get(currentProfilePosition) != null) {
            refreshValues(profiles.get(currentProfilePosition));
        }

        manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.listProfilesRecyclerView.setLayoutManager(manager);
        Resources r = getResources();
        final Float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                150, r.getDisplayMetrics());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.listProfilesRecyclerView.smoothScrollBy((currentProfilePosition==0?0:
                                    ((SCREEN_WIDTH-px.intValue()))/2)+

                                    px.intValue()*(currentProfilePosition-1),0);
                        }
                    });

                    Log.i("CURRENT POSITION", String.valueOf(currentProfilePosition));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
