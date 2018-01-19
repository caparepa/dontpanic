package com.h2g2.dontpanic.activities.user;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.databinding.ActivityProfileBinding;
import com.h2g2.dontpanic.services.interfaces.ViewElement;

public class ProfileActivity extends BaseActivity {

    ActivityProfileBinding binding;
    RelativeLayout profileLayout;
    TextView _textViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_profile);
        profileLayout = binding.profileRelativeLayout;
        setViewElements();
    }

    private void setViewElements()
    {
        ViewElement elements = new ViewElement() {
            @Override
            public void setUpViewText() {
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
            public void setUpButtons() {

            }

            @Override
            public void setUpElements() {

            }
        };
        elements.setUpBackButton();
    }
}
