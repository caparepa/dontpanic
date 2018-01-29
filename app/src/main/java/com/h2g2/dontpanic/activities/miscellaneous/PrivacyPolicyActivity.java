package com.h2g2.dontpanic.activities.miscellaneous;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.databinding.ActivityPrivacyPolicyBinding;
import com.h2g2.dontpanic.services.interfaces.ViewElement;

public class PrivacyPolicyActivity extends BaseActivity {

    ActivityPrivacyPolicyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_privacy_policy);
        TextView privacyContent = findViewById(R.id.privacyContent);
        privacyContent.setMovementMethod(new ScrollingMovementMethod());
        setViewElements();
    }

    private void setViewElements() {
        ViewElement elements = new ViewElement() {
            @Override
            public void setUpViewTitle() {
                TextView _textViewTitle = binding.includedAppBarTitle.textViewTitle;
                _textViewTitle.setText(R.string.title_privacy_policy);
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
            public void setUpElements(){

            }
        };
        elements.setUpViewTitle();
        elements.setUpBackButton();
    }
}
