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

import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;


/**
 * Created by   admin on 2018/4/25.
 */

public class RongChatActivity extends BaseActivity {
    private TextView title;
    private ImageView back;
    private ImageView phone;
    private ImageView star;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rong_chat);

        title = (TextView) findViewById(R.id.activity_chat_title);
        back = (ImageView) findViewById(R.id.activity_chat_back);
        phone = (ImageView) findViewById(R.id.activity_chat_phone);
        star = (ImageView) findViewById(R.id.activity_chat_star);

        //会话界面 对方id
        final String targetId = getIntent().getData().getQueryParameter("targetId");
        //对方 昵称
        String title = getIntent().getData().getQueryParameter("title");

        String chat = SharedPreferencesUtils.getInstace(this).getStringPreference(Constant.CHAT, "");
        if (!TextUtils.isEmpty(chat)) {
            if (chat.equals(Constant.CHAT_DETAILS)){
                if (!TextUtils.isEmpty(title)){
                    this.title.setText(title);
                }
                phone.setVisibility(View.GONE);
                star.setVisibility(View.VISIBLE);
            } else if (chat.equals(Constant.CHAT_FEEDBACK)) {
                this.title.setText(getString(R.string.mine_userfeedback));
                phone.setVisibility(View.GONE);
                star.setVisibility(View.GONE);
            } else {
                if (!TextUtils.isEmpty(title)){
                    this.title.setText(title);
                }
                phone.setVisibility(View.VISIBLE);
                star.setVisibility(View.GONE);
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

    @Override
    public void finish() {
        SharedPreferencesUtils.getInstace(this).setStringPreference(Constant.CHAT, "abc");
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
                RongExtensionManager.getInstance().registerExtensionModule(new TalkExtensionModule());
            }
        }
        super.finish();
    }
}
