package com.h2g2.dontpanic.activities.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.databinding.ActivityRegisterUserBinding;
import com.h2g2.dontpanic.services.interfaces.ViewElement;

public class RegisterUserActivity extends BaseActivity {

    ActivityRegisterUserBinding binding;

    private Button mRegisterButton;
    private TextView mEmailField;
    private TextView mPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_user);
        setViewElements();
        mRegisterButton = findViewById(R.id.createUserButton);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEmailText();
                getPasswordText();
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
        };
        elements.setUpViewText();
        elements.setUpBackButton();
    }

    private void getEmailText()
    {
        String email = binding.editTextEmailAddress.getText().toString();
        System.out.println(email);
    }

    private void getPasswordText()
    {
        String password = binding.editTextUserPassword.getText().toString();
        System.out.println(password);
    }

}
