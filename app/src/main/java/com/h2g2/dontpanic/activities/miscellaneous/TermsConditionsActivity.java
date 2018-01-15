package com.h2g2.dontpanic.activities.miscellaneous;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.databinding.ActivityTermsConditionsBinding;

public class TermsConditionsActivity extends BaseActivity {

    ActivityTermsConditionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_terms_conditions);
        TextView loremIpsumText = findViewById(R.id.termsContent);
        loremIpsumText.setMovementMethod(new ScrollingMovementMethod());

    }
}
