package com.h2g2.dontpanic.activities.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.h2g2.dontpanic.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
