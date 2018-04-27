package com.example.administrator.japanhouse.im;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;


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
        if (!TextUtils.isEmpty(title)){
            this.title.setText(title);
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
