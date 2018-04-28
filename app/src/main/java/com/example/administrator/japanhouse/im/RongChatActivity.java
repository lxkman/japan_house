package com.example.administrator.japanhouse.im;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.utils.Constant;
import com.example.administrator.japanhouse.utils.SharedPreferencesUtils;


/**
 * Created by   admin on 2018/4/25.
 */

public class RongChatActivity extends BaseActivity {
    private TextView title;
    private ImageView back;
    private ImageView phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rong_chat);

        title = (TextView) findViewById(R.id.activity_chat_title);
        back = (ImageView) findViewById(R.id.activity_chat_back);
        phone = (ImageView) findViewById(R.id.activity_chat_phone);

        //会话界面 对方id
        final String targetId = getIntent().getData().getQueryParameter("targetId");
        //对方 昵称
        String title = getIntent().getData().getQueryParameter("title");

        String chat = SharedPreferencesUtils.getInstace(this).getStringPreference(Constant.CHAT, "");
        if (!TextUtils.isEmpty(chat)) {
            if (chat.equals(Constant.CHAT_DETAILS)){

            } else if (chat.equals(Constant.CHAT_FEEDBACK)) {
                this.title.setText(getString(R.string.mine_userfeedback));
                phone.setVisibility(View.GONE);
            } else if (chat.equals(Constant.CHAT_TALK)) {
                if (!TextUtils.isEmpty(title)){
                    this.title.setText(title);
                }
                phone.setVisibility(View.VISIBLE);
            }
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImManager.sendImgAndText(targetId);
//                RongCallClient.getInstance().startCall(Conversation.ConversationType.PRIVATE, targetId, null, RongCallCommon.CallMediaType.AUDIO, "");
            }
        });
    }
}
