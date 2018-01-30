package com.h2g2.dontpanic.activities.user;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;

import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.activities.main.MainActivity;
import com.h2g2.dontpanic.bean.RegistryBean;
import com.h2g2.dontpanic.databinding.ActivityLoginBinding;
import com.h2g2.dontpanic.models.database.AppDatabase;
import com.h2g2.dontpanic.models.entity.User;
import com.h2g2.dontpanic.networking.handler.AccountHandler;
import com.h2g2.dontpanic.networking.handler.response.ResponseLoginHandler;
import com.h2g2.dontpanic.networking.utils.NetworkValidator;
import com.h2g2.dontpanic.networking.utils.RequestResponseHandler;
import com.h2g2.dontpanic.services.interfaces.SharedPreferencesConstants;
import com.h2g2.dontpanic.services.interfaces.ViewElement;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements SharedPreferencesConstants {

    ActivityLoginBinding binding;
    SharedPreferences loginSettings;

    private static final int REQUEST_READ_CONTACTS = 0;
    private UserLoginTask mAuthTask = null;
    protected Boolean isResponseOk;

    // UI references.
    protected AutoCompleteTextView mEmailView;
    protected EditText mPasswordEditText;
    protected View mProgressView;
    protected View mLoginFormView;
    protected Button mEmailSignInButton;

    protected AccountHandler networkHandler;
    protected RequestResponseHandler requestResponseHandler;
    protected ResponseLoginHandler responseLoginHandler;


    protected User mUser;
    protected AppDatabase userDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        userDb = AppDatabase.getAppDatabase(this);

        networkHandler = new AccountHandler(this);
        requestResponseHandler = new RequestResponseHandler();
        responseLoginHandler = new ResponseLoginHandler(LoginActivity.this);

        mEmailView = binding.email;
        //populateAutoComplete();

        mPasswordEditText= binding.password;
        mPasswordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mEmailSignInButton = binding.emailSignInButton;
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = binding.loginForm;
        mProgressView = binding.loginProgress;

        setUpViewElements();

        Context context = LoginActivity.this;
        context.getString(SHARED_FILE);
        loginSettings = context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
    }

    private void setUpViewElements() {
        ViewElement elements = new ViewElement() {
            @Override
            public void setUpViewTitle() {

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

            }
        };
        elements.setUpBackButton();
    }

    private void attemptLogin() {

        if (!NetworkValidator.isNetworkAvailable(getApplicationContext())){
            //Utils.showNonInternetWarning(LogInActivity.this);
            //TODO: SHOW NO INTERNET ALERT HERE!
            System.out.println("NO INTERNET!");
            return;
        }

        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordEditText.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordEditText.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordEditText.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordEditText;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
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
            /*mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);*/
            RegistryBean bean = new RegistryBean(email,password);
            networkHandler.logIn(callback,requestResponseHandler.requestGetJsonStringFromPojo(bean));
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    public void showMessageAlert(String title, String message, Boolean isOk) {
        isResponseOk = isOk;
        AlertDialog mAlertDialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        redirect();
                    }
                })
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        redirect();
                    }
                }).create();

        mAlertDialog.show();
        mAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                redirect();
            }
        });
    }

    /**
     * if the register response doesn't have errors (true)
     */
    private void redirect() {
        if(isResponseOk){
            navigateToActivity(MainActivity.class);
            finish();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        //private User mUser;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }


            RegistryBean bean = new RegistryBean(mEmail, mPassword);
            networkHandler.logIn(callback,requestResponseHandler.requestGetJsonStringFromPojo(bean));

            /*mUser = userDb.userDao().findByEmail(mEmail);
            UserData userData = new UserData(true, mUser);

            if(mUser != null){
                Boolean a = SharedPreferencesUtil.saveUserDataPref(userData,LoginActivity.this);
                System.out.println("A "+ a);
            }*/

            return mUser != null && mUser.getPassword().equals(mPassword);
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                navigateToActivity(MainActivity.class);
                finish();
            } else {
                mPasswordEditText.setError(getString(R.string.error_incorrect_password));
                mPasswordEditText.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }

    /**
     * TODO: set alerts instead of toasts!
     */
    Callback<ResponseBody> callback = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response!=null) {
                responseLoginHandler.processResponse(response);
                binding.emailSignInButton.setEnabled(true);
                binding.emailSignInButton.setClickable(true);
            }
            else{
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                binding.emailSignInButton.setEnabled(true);
                binding.emailSignInButton.setClickable(true);
            }
        }
        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Toast.makeText(LoginActivity.this,"Try again, later",Toast.LENGTH_LONG).show();
            binding.emailSignInButton.setEnabled(true);
            binding.emailSignInButton.setClickable(true);
        }
    };
}

