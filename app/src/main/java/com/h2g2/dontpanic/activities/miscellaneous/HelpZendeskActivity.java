package com.h2g2.dontpanic.activities.miscellaneous;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.databinding.ActivityHelpZendeskBinding;
import com.zopim.android.sdk.api.ZopimChat;
import com.zopim.android.sdk.prechat.ZopimChatActivity;

public class HelpZendeskActivity extends BaseActivity {

    ActivityHelpZendeskBinding binding;

    Button mChatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_zendesk);

        binding = DataBindingUtil.setContentView(HelpZendeskActivity.this, R.layout.activity_help_zendesk);

        //init zendesk
        ZopimChat.init("5Ql9NgVwNfaAFd4kP1U5EuQx86BAwep9");

        mChatButton = binding.chatButton;
        mChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start chat
                startActivity(new Intent(getApplicationContext(), ZopimChatActivity.class));
            }
        });
    }
}
