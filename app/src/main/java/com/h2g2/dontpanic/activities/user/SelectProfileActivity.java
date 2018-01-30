package com.h2g2.dontpanic.activities.user;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
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
import com.h2g2.dontpanic.networking.handler.response.ResponseAccountHandler;
import com.h2g2.dontpanic.networking.utils.RequestResponseHandler;
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
    RecyclerView recyclerView;
    ProfileAdapter profileAdapter;

    protected Data beanData;
    protected Account account;
    protected Profile profile;
    protected List<Profile> profiles;

    private int currentProfilePosition;
    private int initProfilePosition;

    private TextView selectProfileTitle;
    private String currentProfileId;

    public static int SCREEN_WIDTH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_profile);
        setContentView(R.layout.activity_select_profile);

        beanData = SharedPreferencesUtil.getUserDataInfoPref(SelectProfileActivity.this);
        profile = beanData.getProfile();
        profiles = beanData.getProfiles();

        setUpElements();
    }

    public void setUpElements() {
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
                setDataOnView();
            }
        };
        elements.setUpBackButton();
        elements.setUpViewTitle();
        elements.setUpElements();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("currentProfilePos", currentProfilePosition);
        outState.putInt("initProfilePos", initProfilePosition);
        super.onSaveInstanceState(outState);
    }

    //set values!
    public void refreshValues(Profile profile) {

    }

    private void setDataOnView() {

        recyclerView = findViewById(R.id.list_profiles_recycler_view);
        profileAdapter = new ProfileAdapter(profiles, SelectProfileActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                System.out.println("onInterceptTouchEvent@setdataOnView@SelectProfileActivity");
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                System.out.println("onTouchEvent@setdataOnView@SelectProfileActivity");
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                System.out.println("onRequestDisallowInterceptTouchEvent@setdataOnView@SelectProfileActivity");
            }
        });
        recyclerView.setAdapter(profileAdapter);
        profileAdapter.notifyDataSetChanged();
    }
}
