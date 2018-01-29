package com.h2g2.dontpanic.activities.user;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.activities.main.MainActivity;
import com.h2g2.dontpanic.databinding.ActivityProfileBinding;
import com.h2g2.dontpanic.models.database.AppDatabase;
import com.h2g2.dontpanic.models.entity.User;
import com.h2g2.dontpanic.models.serializables.UserData;
import com.h2g2.dontpanic.services.interfaces.SharedPreferencesConstants;
import com.h2g2.dontpanic.services.interfaces.Validation;
import com.h2g2.dontpanic.services.interfaces.ViewElement;
import com.h2g2.dontpanic.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileActivity extends BaseActivity implements SharedPreferencesConstants {

    ActivityProfileBinding binding;
    RelativeLayout profileLayout;
    SharedPreferences prefs;
    TextView _textViewTitle;

    private AppDatabase userDb;
    private User mUser;
    private UserData mUserData;
    private UpdateUserProfileTask mUpdateProfileTask;

    private TextView mEmailText;
    private TextView mPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_profile);
        profileLayout = binding.profileRelativeLayout;
        userDb = AppDatabase.getAppDatabase(this);

        getUserData();
        getSharedPreferencesData();
        setViewElements();
    }

    private void getUserData() {
        mUserData = SharedPreferencesUtil.getUserDataPref(ProfileActivity.this);
        mUser = mUserData.getUserEntity();
    }

    private void getSharedPreferencesData() {
        Context context = ProfileActivity.this;
        context.getString(SHARED_FILE);
        prefs = context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
    }

    private void setViewElements() {
        ViewElement elements = new ViewElement() {
            @Override
            public void setUpViewTitle() {
                _textViewTitle = binding.includedAppBarTitle.textViewTitle;
                _textViewTitle.setText(R.string.title_profile);
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
                mEmailText = binding.editProfileEmail;
                mPasswordText = binding.editProfilePassword;

                if(mUser !=null){
                    mEmailText.setText(mUser.getEmail());
                    mPasswordText.setText(mUser.getPassword());
                }
            }

            @Override
            public void setUpButtons() {
                binding.cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        navigateToActivity(MainActivity.class);
                    }
                });

                if(mUser != null){
                    binding.saveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            attemptUpdate();
                        }
                    });
                }else{

                }
            }

            @Override
            public void setUpElements() {

            }
        };
        elements.setUpButtons();
        elements.setUpBackButton();
        elements.setUpInputFields();
    }

    public boolean isValidEmail(String email){
        Pattern pattern = Validation.EMAIL_REGEX;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void attemptUpdate() {
        if (mUpdateProfileTask != null) {
            return;
        }

        // Reset errors.
        mEmailText.setError(null);
        mPasswordText.setError(null);

        // Store values at the time of the update attempt.
        String email = mEmailText.getText().toString();
        String password = mPasswordText.getText().toString();

        boolean cancel = false;
        View focusView = null;
        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordText.setError(getString(R.string.error_no_password));
            focusView = mPasswordText;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailText.setError(getString(R.string.error_field_required));
            focusView = mEmailText;
            cancel = true;
        } else if (!isValidEmail(email)) {
            mEmailText.setError(getString(R.string.error_invalid_email));
            focusView = mEmailText;
            cancel = true;
        }

        /*if(validateExistingUser()){
            mEmailText.setError(getString(R.string.error_user_exists));
            focusView = mEmailText;
            cancel = true;
        }*/

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            Integer uid = mUser.getUid();
            mUpdateProfileTask = new ProfileActivity.UpdateUserProfileTask(mUser);
            mUpdateProfileTask.execute((Void) null);
        }
    }

    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mEmailText.setVisibility(show ? View.GONE : View.VISIBLE);
            mEmailText.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mEmailText.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mPasswordText.setVisibility(show ? View.VISIBLE : View.GONE);
            mPasswordText.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mPasswordText.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mPasswordText.setVisibility(show ? View.VISIBLE : View.GONE);
            mEmailText.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private void updateUserData(User pUser){
        System.out.println("CARAJO " + mEmailText.getText() + " " + mPasswordText.getText());
        User user = userDb.userDao().findByUid(pUser.getUid());
        user.setEmail(mEmailText.toString());
        user.setPassword(mPasswordText.toString());
        userDb.userDao().updateUser(user);
    }

    public class UpdateUserProfileTask extends AsyncTask<Void, Void, Boolean> {

        private final User pUser;

        UpdateUserProfileTask(User user) {
            pUser = user;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            //TODO: save user
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            updateUserData(pUser);
            UserData userData = new UserData(true, pUser);

            if(pUser != null){
                System.out.println("pUser != null");
                SharedPreferencesUtil.saveUserDataPref(userData,ProfileActivity.this);
            }

            return pUser != null;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            showProgress(false);
            if (success) {
                navigateToActivity(MainActivity.class);
                finish();
            } else {
                /*mPasswordText.setError(getString(R.string.error_incorrect_password));
                mPasswordText.requestFocus();*/
                //TODO: show message, and then navigate to main activity
                System.out.println("FUCK!");
            }
        }

        @Override
        protected void onCancelled() {
            showProgress(false);
        }
    }
}
