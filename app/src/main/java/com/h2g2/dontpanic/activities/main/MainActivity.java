package com.h2g2.dontpanic.activities.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import com.h2g2.dontpanic.databinding.ActivityMainBinding;
import com.h2g2.dontpanic.models.entity.User;
import com.h2g2.dontpanic.models.serializables.UserData;
import com.h2g2.dontpanic.services.interfaces.SharedPreferencesConstants;
import com.h2g2.dontpanic.services.interfaces.ViewElement;
import com.h2g2.dontpanic.utils.SharedPreferencesUtil;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, SharedPreferencesConstants {

    User mUser;
    UserData mUserData;

    protected TextView navHeaderUser;
    protected TextView navHeaderEmail;
    protected TextView mainSignalText;

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavView;
    private Toolbar mToolbar;
    private View mNavHeader;

    ActivityMainBinding binding;

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

        setNavHeaderText();
        setUpViewElements();

        //bring nav view to fron (z-index issue)
        mNavView.bringToFront();
        mDrawerLayout.requestLayout();
    }

    private void setNavHeaderText() {
        navHeaderUser = mNavHeader.findViewById(R.id.nav_header_user_text_view);
        navHeaderEmail = mNavHeader.findViewById(R.id.nav_header_email_text_view);

        mUser = SharedPreferencesUtil.getUserDataPref(this).getUserEntity();
        mainSignalText = mNavView.findViewById(R.id.mainSignalText);

        if(mUser != null){
            navHeaderUser.setText(mUser.getEmail());
            navHeaderEmail.setText(mUser.getEmail());
        }else{
            navHeaderUser.setText("HELLO");
            navHeaderEmail.setText("HELLO");
        }
    }

    private void setUpViewElements() {
        ViewElement elements = new ViewElement() {
            @Override
            public void setUpViewText() {

            }

            @Override
            public void setUpBackButton() {

            }

            @Override
            public void setUpTextFields() {

            }

            @Override
            public void setUpButtons() {
                //Including FAB buttons
                binding.includedFooterBar.fabProfileButton.setVisibility(View.VISIBLE);
                if (binding.includedFooterBar.fabProfileButton.getVisibility() == View.VISIBLE) {
                    binding.includedFooterBar.fabProfileButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            navigateToActivity(ProfileActivity.class);
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
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        System.out.println("OPTION SELECTED!");

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
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
            System.out.println("LOGOUT " + result);
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
