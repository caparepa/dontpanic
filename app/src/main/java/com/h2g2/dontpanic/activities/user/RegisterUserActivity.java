package com.h2g2.dontpanic.activities.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.databinding.ActivityRegisterUserBinding;
import com.h2g2.dontpanic.models.database.AppDatabase;
import com.h2g2.dontpanic.models.entity.User;
import com.h2g2.dontpanic.services.abstracts.UserValidation;
import com.h2g2.dontpanic.services.interfaces.Validation;
import com.h2g2.dontpanic.services.interfaces.ViewElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterUserActivity extends BaseActivity {

    ActivityRegisterUserBinding binding;

    private AppDatabase userDb;
    protected List<User> userList;

    protected TextView mEmailText;
    protected TextView mPasswordText;
    protected Button mRegisterButton;

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
                System.out.println(binding.editTextEmailAddress.getText().toString());
                System.out.println(validateExistingUser());
                if(validateExistingUser()){
                    System.out.println("USER!");
                }else{
                    if(validateEmail(getEmailText())){
                        System.out.println("VALID EMAIL!");
                        saveUserToDatabase();
                    }else{
                        System.out.println("INVALID EMAIL!");
                        mEmailText.setError("Invalid email!");
                    }
                    System.out.println("NO USER!");
                }
            }
        });
    }

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
            }

            @Override
            public void setUpButtons() {

            }
        };
        elements.setUpViewText();
        elements.setUpBackButton();
        elements.setUpTextFields();
    }

    private String getEmailText()
    {
        return binding.editTextEmailAddress.getText().toString();
    }

    private String getPasswordText()
    {
        return binding.editTextUserPassword.getText().toString();
    }

    private void saveUserToDatabase(){
        User user = new User();
        user.setEmail(getEmailText());
        user.setPassword(getPasswordText());
        userDb.userDao().insertUser(user);
    }

    private void getSavedUsers(){

        userList = userDb.userDao().getUsers();
        for (final User user : userList){
            System.out.println(user.getEmail());
        }
    }

    private boolean validateExistingUser(){
        String mail = getEmailText();
        User user = userDb.userDao().findByEmail(mail);
        return user != null;
    }

    public boolean validateEmail(String email){
        Pattern pattern = Validation.EMAIL_REGEX;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
