package com.h2g2.dontpanic.activities.user;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.databinding.ActivityRegisterUserBinding;
import com.h2g2.dontpanic.models.database.AppDatabase;
import com.h2g2.dontpanic.models.entity.User;
import com.h2g2.dontpanic.services.interfaces.Validation;
import com.h2g2.dontpanic.services.interfaces.ViewElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterUserActivity extends BaseActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    ActivityRegisterUserBinding binding;

    private AppDatabase userDb;
    private RegisterUserTask mRegisterTask = null;

    protected TextView mEmailText;
    protected TextView mPasswordText;
    protected Button mRegisterButton;

    private View mRegisterFormView;
    private View mRegisterProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_user);
        userDb = AppDatabase.getAppDatabase(this);
        setViewElements();
        setUpRegisterButton();
    }

    private void setUpRegisterButton() {
        mRegisterButton = findViewById(R.id.createUserButton);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegister();
            }
        });
    }

    private void attemptRegister()
    {
        if (mRegisterTask != null) {
            return;
        }

        // Reset errors.
        mEmailText.setError(null);
        mPasswordText.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailText.getText().toString();
        String password = mPasswordText.getText().toString();

        System.out.println("Email: "+email+" Password: "+password);

        boolean cancel = false;
        View focusView = null;
        System.out.println(TextUtils.isEmpty(password));
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
            mRegisterTask = new RegisterUserActivity.RegisterUserTask(email, password);
            mRegisterTask.execute((Void) null);
        }
    }

    /**
     * Abstract stuff
     */
    private void setViewElements(){
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
                mEmailText = findViewById(R.id.editTextEmailAddress);
                mPasswordText = findViewById(R.id.editTextUserPassword);
                /**
                 * dunno what the fuck this does, but whatever
                 */
                mPasswordText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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

            }

            @Override
            public void setUpElements() {
                mRegisterFormView = findViewById(R.id.register_form);
                mRegisterProgressView = findViewById(R.id.register_progress);
            }
        };
        elements.setUpViewText();
        elements.setUpBackButton();
        elements.setUpTextFields();
        elements.setUpElements();
    }

    /**
     * Welp!
     * @return String string
     */
    private String getEmailText()
    {
        return binding.editTextEmailAddress.getText().toString();
    }

    /**
     * Save entitiy to Room db
     */
    private void saveUserToDatabase(String email, String password){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userDb.userDao().insertUser(user);
    }

    /**
     *
     * @return boolean
     */
    private boolean validateExistingUser(){
        String mail = getEmailText();
        User user = userDb.userDao().findByEmail(mail);
        return user != null;
    }

    /**
     * Welp!
     * @param email asd
     * @return boolean
     */
    public boolean isValidEmail(String email){
        Pattern pattern = Validation.EMAIL_REGEX;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
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

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), RegisterUserActivity.ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //emails.add(cursor.getString(LoginActivity.ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        //addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class RegisterUserTask extends AsyncTask<Void, Void, Boolean> {

        private User mUser;
        private final String mEmail;
        private final String mPassword;

        RegisterUserTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            //TODO: save user
            try {
                saveUserToDatabase(mEmail, mPassword);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            showProgress(false);
            if (success) {
                finish();
            } else {
                /*mPasswordText.setError(getString(R.string.error_incorrect_password));
                mPasswordText.requestFocus();*/
                System.out.println("FUCK!");
            }
        }

        @Override
        protected void onCancelled() {
            showProgress(false);
        }
    }
}
