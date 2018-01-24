package com.h2g2.dontpanic.activities.user;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.activities.main.MainActivity;
import com.h2g2.dontpanic.databinding.ActivityRegisterUserBinding;
import com.h2g2.dontpanic.models.database.AppDatabase;
import com.h2g2.dontpanic.models.entity.User;
import com.h2g2.dontpanic.services.interfaces.Validation;
import com.h2g2.dontpanic.services.interfaces.ViewElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterUserActivity extends BaseActivity {

    ActivityRegisterUserBinding binding;

    private static final DateFormat dtf = new SimpleDateFormat("yyyy");

    private AppDatabase userDb;
    private RegisterUserTask mRegisterTask = null;

    protected Button mRegisterButton;
    protected TextView mEmailText;
    protected TextView mPasswordText;
    protected TextView mBirthDateText;

    private View mRegisterFormView;
    private View mRegisterProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_user);
        userDb = AppDatabase.getAppDatabase(this);
        setUpViewElements();
    }

    private boolean validateYear(String year){
        Date now = new Date();
        int currentYear = Integer.valueOf(dtf.format(now));
        int valueYear = Integer.valueOf(year);
        return (valueYear >= 1900 && valueYear <= currentYear);
    }

    private void attemptRegister() {
        if (mRegisterTask != null) {
            return;
        }

        // Reset errors.
        mEmailText.setError(null);
        mPasswordText.setError(null);
        mBirthDateText.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailText.getText().toString();
        String password = mPasswordText.getText().toString();
        String year = mBirthDateText.getText().toString();

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

        if (TextUtils.isEmpty(year)){
            mBirthDateText.setError(getString(R.string.error_field_required));
            focusView = mEmailText;
            cancel = true;
        }else if(!validateYear(year)){
            mBirthDateText.setError(getString(R.string.error_invalid_year));
            focusView = mBirthDateText;
            cancel = true;
        }

        if(validateExistingUser()){
            mEmailText.setError(getString(R.string.error_user_exists));
            focusView = mEmailText;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mRegisterTask = new RegisterUserActivity.RegisterUserTask(email, password, year);
            mRegisterTask.execute((Void) null);
        }
    }

    private void setUpViewElements(){
        ViewElement elements = new ViewElement() {
            @Override
            public void setUpViewText() {
                TextView _textViewTitle = binding.includedAppBarTitle.textViewTitle;
                _textViewTitle.setText(R.string.title_register_user);
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
                mEmailText = binding.emailEditText;
                mBirthDateText = binding.birthdateEditText;
                mPasswordText = binding.passwordEditText;

                mBirthDateText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                        if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                            attemptRegister();
                            return true;
                        }
                        return false;
                    }

                });
            }

            @Override
            public void setUpButtons() {
                mRegisterButton = binding.registerButton;
                mRegisterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        attemptRegister();
                    }
                });
            }

            @Override
            public void setUpElements() {
                mRegisterFormView = binding.registerFormScroll;
                mRegisterProgressView = binding.registerProgressBar;
            }
        };
        elements.setUpBackButton();
        elements.setUpViewText();
        elements.setUpElements();
        elements.setUpInputFields();
        elements.setUpButtons();
    }

    private String getEmailText()
    {
        return binding.emailEditText.getText().toString();
    }

    private void saveUserToDatabase(String email, String password){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userDb.userDao().insertUser(user);
    }

    private boolean validateExistingUser(){
        String mail = getEmailText();
        User user = userDb.userDao().findByEmail(mail);
        return user != null;
    }

    public boolean isValidEmail(String email){
        Pattern pattern = Validation.EMAIL_REGEX;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mRegisterFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mRegisterProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRegisterProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRegisterProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mRegisterProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public class RegisterUserTask extends AsyncTask<Void, Void, Boolean> {

        private User mUser;
        private final String mEmail;
        private final String mPassword;
        private final String mYear;

        RegisterUserTask(String email, String password, String year) {
            mEmail = email;
            mPassword = password;
            mYear = year;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            //TODO: save user
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }
            saveUserToDatabase(mEmail, mPassword);
            return true;
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
