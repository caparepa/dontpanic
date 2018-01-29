package com.h2g2.dontpanic.activities.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.activities.miscellaneous.HelpZendeskActivity;
import com.h2g2.dontpanic.activities.user.ProfileActivity;
import com.h2g2.dontpanic.activities.miscellaneous.PrivacyPolicyActivity;
import com.h2g2.dontpanic.activities.miscellaneous.TermsConditionsActivity;
import com.h2g2.dontpanic.activities.user.LoginActivity;
import com.h2g2.dontpanic.activities.user.RegisterUserActivity;
import com.h2g2.dontpanic.bean.data.Account;
import com.h2g2.dontpanic.bean.data.Data;
import com.h2g2.dontpanic.databinding.ActivityMainBinding;
import com.h2g2.dontpanic.models.entity.User;
import com.h2g2.dontpanic.models.serializables.UserData;
import com.h2g2.dontpanic.services.interfaces.SharedPreferencesConstants;
import com.h2g2.dontpanic.services.interfaces.ViewElement;
import com.h2g2.dontpanic.utils.SharedPreferencesUtil;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, SharedPreferencesConstants {

    ActivityMainBinding binding;
    Boolean mLoggedIn;

    User mUser;
    UserData mUserData;

    Data beanData;
    Account beanAccount;

    protected MenuItem mItemRegister;
    protected MenuItem mItemLogin;
    protected MenuItem mItemLogout;

    protected TextView navHeaderUser;
    protected TextView navHeaderEmail;
    protected TextView mainSignalText;

    protected DrawerLayout mDrawerLayout;
    protected NavigationView mNavView;
    protected Toolbar mToolbar;
    protected View mNavHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mDrawerLayout = binding.drawerLayout;
        mNavView = binding.navViewBar;
        mToolbar = binding.includedAppBarMain.mainToolbar;

        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Attach listener and get header
        mNavView.setNavigationItemSelectedListener(MainActivity.this);
        mNavHeader = mNavView.getHeaderView(0);

        getUserData();
        setNavHeaderText();
        setUpViewElements();

        //bring nav view to fron (z-index issue)
        mNavView.bringToFront();
        mDrawerLayout.requestLayout();
    }

    private void getUserData() {

        beanData = SharedPreferencesUtil.getUserDataInfoPref(this);
        beanAccount = beanData.getAccount();

        mUser = SharedPreferencesUtil.getUserDataPref(this).getUserEntity();
        mLoggedIn = SharedPreferencesUtil.getUserDataPref(this).getLoggedIn();
    }

    private void setNavHeaderText() {
        navHeaderUser = mNavHeader.findViewById(R.id.nav_header_user_text_view);
        navHeaderEmail = mNavHeader.findViewById(R.id.nav_header_email_text_view);
        mainSignalText = mNavView.findViewById(R.id.mainSignalText);

        if(beanAccount != null && beanData.getToken() != null){
            navHeaderUser.setText(beanAccount.getEmail());
            navHeaderEmail.setText(beanAccount.getEmail());
        }else{
            navHeaderUser.setText(R.string.guest_user);
            navHeaderEmail.setText(R.string.guest_user);
        }

        /*if(mUser != null && mLoggedIn){
            navHeaderUser.setText(mUser.getEmail());
            navHeaderEmail.setText(mUser.getEmail());
        }else{
            navHeaderUser.setText(R.string.guest_user);
            navHeaderEmail.setText(R.string.guest_user);
        }*/
    }

    private void setUpViewElements() {
        ViewElement elements = new ViewElement() {
            @Override
            public void setUpViewTitle() {

            }

            @Override
            public void setUpBackButton() {

            }

            @Override
            public void setUpTextFields() {

            }

            @Override
            public void setUpInputFields(){

            }

            @Override
            public void setUpButtons() {
                //Including FAB buttons
                binding.includedFooterBar.fabProfileButton.setVisibility(View.VISIBLE);
                if (binding.includedFooterBar.fabProfileButton.getVisibility() == View.VISIBLE) {
                    binding.includedFooterBar.fabProfileButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(mUser != null && mLoggedIn){
                                navigateToActivity(ProfileActivity.class);
                            }else{
                                navigateToActivity(RegisterUserActivity.class);
                            }
                        }
                    });
                }

                binding.includedFooterBar.fabHomeButton.setVisibility(View.VISIBLE);
                if (binding.includedFooterBar.fabHomeButton.getVisibility() == View.VISIBLE) {
                    binding.includedFooterBar.fabHomeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            navigateToActivity(MainActivity.class);
                        }
                    });
                }

                binding.includedFooterBar.fabHelpButton.setVisibility(View.VISIBLE);
                if (binding.includedFooterBar.fabHelpButton.getVisibility() == View.VISIBLE) {
                    binding.includedFooterBar.fabHelpButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            navigateToActivity(HelpZendeskActivity.class);
                        }
                    });
                }
            }

            @Override
            public void setUpElements() {

            }

        };
        elements.setUpButtons();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Really Exit?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            finishAndRemoveTask ();
                        }
                    }).create().show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        mItemRegister = binding.navViewBar.getMenu().findItem(R.id.nav_register_account);
        mItemLogin = binding.navViewBar.getMenu().findItem(R.id.nav_login);
        mItemLogout = binding.navViewBar.getMenu().findItem(R.id.nav_logout);

        if(beanAccount != null && beanData.getToken() != null){
            mItemRegister.setVisible(false);
            mItemLogin.setVisible(false);
            mItemLogout.setVisible(true);
        }else{
            mItemRegister.setVisible(true);
            mItemLogin.setVisible(true);
            mItemLogout.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        System.out.println("OPTION SELECTED!");

        int id = item.getItemId();

        //TODO: change if to switch statement!
        //noinspection SimplifiableIfStatement
        if(id == R.id.action_help){
            navigateToActivity(HelpZendeskActivity.class);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        System.out.println("NAVIGATION SELECTED!");

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        item.setChecked(true);
        mDrawerLayout.closeDrawers();

        if (id == R.id.nav_privacy_policy) {
            System.out.println("PRIVACY POLICY");
            navigateToActivity(PrivacyPolicyActivity.class);
        } else if (id == R.id.nav_terms_conditions) {
            System.out.println("TERMS AND CONDITIONS");
            navigateToActivity(TermsConditionsActivity.class);
        } else if (id == R.id.nav_register_account) {
            System.out.println("REGISTER");
            navigateToActivity(RegisterUserActivity.class);
        } else if (id == R.id.nav_login) {
            System.out.println("LOGIN");
            navigateToActivity(LoginActivity.class);
        } else if (id == R.id.nav_logout) {
            mUser = SharedPreferencesUtil.getUserDataPref(this).getUserEntity();
            mUserData = new UserData(false, mUser);
            Boolean result = SharedPreferencesUtil.saveUserDataPref(mUserData,this);
            //TODO: set loader here!
            setNavHeaderText();
            System.out.println("LOGOUT " + result);
            navigateToActivity(MainActivity.class);
            finish();
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
