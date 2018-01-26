package com.h2g2.dontpanic.activities.miscellaneous;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.databinding.ActivityHelpZendeskBinding;
import com.h2g2.dontpanic.services.interfaces.ViewElement;
import com.zendesk.sdk.model.access.AnonymousIdentity;
import com.zendesk.sdk.model.access.Identity;
import com.zendesk.sdk.network.impl.ZendeskConfig;
import com.zendesk.sdk.support.SupportActivity;
import com.zopim.android.sdk.api.ZopimChat;
import com.zopim.android.sdk.prechat.ZopimChatActivity;

public class HelpZendeskActivity extends BaseActivity {

    ActivityHelpZendeskBinding binding;
    Button mChatButton;
    TextView _textViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_zendesk);

        binding = DataBindingUtil.setContentView(HelpZendeskActivity.this, R.layout.activity_help_zendesk);

        //init zendesk
        /*ZopimChat.init("V8vJJVKzgBKxHA7vqmJqjTKwjtsawQap");*/
        ZendeskConfig.INSTANCE.init(
                this,
                "https://cserrano-teravisiontechhelp.zendesk.com",
                "aeef0fd8640d67064c4f819970e1a3e4a70da1694ba2bfe1",
                "mobile_sdk_client_d9d81110c76023d6729d"
        );
        Identity identity = new AnonymousIdentity.Builder().build();
        ZendeskConfig.INSTANCE.setIdentity(identity);

        setUpViewElements();

    }

    private void setUpViewElements() {
        ViewElement elements = new ViewElement() {
            @Override
            public void setUpViewText() {
                _textViewTitle = binding.includedAppBarTitle.textViewTitle;
                _textViewTitle.setText(R.string.title_help_zendesk);
            }

            @Override
            public void setUpBackButton() {
                binding.includedAppBarTitle.fabBackButton.setVisibility(View.VISIBLE);
                if (binding.includedAppBarTitle.fabBackButton.getVisibility() == View.VISIBLE) {
                    binding.includedAppBarTitle.fabBackButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("SHIT");
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
                mChatButton = binding.chatButton;
                mChatButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //start chat
                        /*startActivity(new Intent(getApplicationContext(), ZopimChatActivity.class));*/
                        new SupportActivity.Builder().show(HelpZendeskActivity.this);
                    }
                });
            }

            @Override
            public void setUpElements() {

            }
        };
        elements.setUpBackButton();
        elements.setUpViewText();
        elements.setUpButtons();
    }
}
