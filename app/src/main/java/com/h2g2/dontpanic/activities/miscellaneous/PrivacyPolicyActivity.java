package com.h2g2.dontpanic.activities.miscellaneous;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.databinding.ActivityPrivacyPolicyBinding;

public class PrivacyPolicyActivity extends BaseActivity {

    ActivityPrivacyPolicyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_privacy_policy);
        TextView privacyContent = findViewById(R.id.privacyContent);
        privacyContent.setMovementMethod(new ScrollingMovementMethod());
    }
}
