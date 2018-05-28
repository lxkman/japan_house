package com.example.administrator.japanhouse.im;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.example.administrator.japanhouse.view.BaseDialog;
import com.example.administrator.japanhouse.view.RatingBarView;

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

    private TextView tvAppraise;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rong_chat);

        title = (TextView) findViewById(R.id.activity_chat_title);
        back = (ImageView) findViewById(R.id.activity_chat_back);
        phone = (ImageView) findViewById(R.id.activity_chat_phone);
        star = (ImageView) findViewById(R.id.activity_chat_star);
        tvAppraise = (TextView) findViewById(R.id.act_rongChat_appraise);

        //会话界面 对方id
        final String targetId = getIntent().getData().getQueryParameter("targetId");
        //对方 昵称
        String title = getIntent().getData().getQueryParameter("title");

        String chat = SharedPreferencesUtils.getInstace(this).getStringPreference(Constants.CHAT, "");
        if (!TextUtils.isEmpty(chat)) {
            if (chat.equals(Constants.CHAT_DETAILS)){
                if (!TextUtils.isEmpty(title)){
                    this.title.setText(title);
                }
                phone.setVisibility(View.GONE);
                star.setVisibility(View.VISIBLE);
                tvAppraise.setVisibility(View.VISIBLE);
            } else if (chat.equals(Constants.CHAT_FEEDBACK)) {
                this.title.setText(getString(R.string.mine_userfeedback));
                phone.setVisibility(View.GONE);
                star.setVisibility(View.GONE);
                tvAppraise.setVisibility(View.GONE);
            } else {
                if (!TextUtils.isEmpty(title)){
                    this.title.setText(title);
                }
                phone.setVisibility(View.VISIBLE);
                star.setVisibility(View.GONE);
                tvAppraise.setVisibility(View.GONE);
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
//                RongCallClient.getInstance().startCall(Conversation.ConversationType.PRIVATE, targetId, null, RongCallCommon.CallMediaType.AUDIO, "");
            }
        });

        final String country = CacheUtils.get(Constants.COUNTRY);

        tvAppraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(country) && TextUtils.equals(country, "ja")) {
                    showJaDialog();
                } else {
                    showZhDialog();
                }
            }
        });
    }

    private void showJaDialog() {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog
                dialog = builder.setViewId(R.layout.dialog_chat_details_ja)
                .setGravity(Gravity.CENTER)
                .setAnimation(R.style.bottom_tab_style)
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                .isOnTouchCanceled(true)
                .builder();
        CheckBox rbReply = dialog.getView(R.id.dialog_chat_huanman);
        CheckBox rbNoMajor = dialog.getView(R.id.dialog_chat_zuanye);
        CheckBox rbColdness = dialog.getView(R.id.dialog_chat_lengdan);
        CheckBox rbMsgPartial = dialog.getView(R.id.dialog_chat_buquan);

        RatingBarView ratingBarView = dialog.getView(R.id.dialog_chat_tatingbar);
        ratingBarView.setRatingCount(5);
        ratingBarView.setSelectedCount(1);
        ratingBarView.setSelectedIconResId(R.drawable.start_check);
        ratingBarView.setNormalIconResId(R.drawable.start_nocheck);
        ratingBarView.setClickable(true);
        ratingBarView.setChildPadding(0);
        ratingBarView.setChildMargin(12);
        ratingBarView.setChildDimension(28);

        dialog.getView(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getView(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void showZhDialog() {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog
                dialog = builder.setViewId(R.layout.dialog_chat_details_zh)
                .setGravity(Gravity.CENTER)
                .setAnimation(R.style.bottom_tab_style)
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                .isOnTouchCanceled(true)
                .builder();
        CheckBox rbReply = dialog.getView(R.id.dialog_chat_huanman);
        CheckBox rbNoMajor = dialog.getView(R.id.dialog_chat_zuanye);
        CheckBox rbColdness = dialog.getView(R.id.dialog_chat_lengdan);
        CheckBox rbMsgPartial = dialog.getView(R.id.dialog_chat_buquan);

        RatingBarView ratingBarView = dialog.getView(R.id.dialog_chat_tatingbar);
        ratingBarView.setRatingCount(5);
        ratingBarView.setSelectedCount(1);
        ratingBarView.setSelectedIconResId(R.drawable.start_check);
        ratingBarView.setNormalIconResId(R.drawable.start_nocheck);
        ratingBarView.setClickable(true);
        ratingBarView.setChildPadding(0);
        ratingBarView.setChildMargin(12);
        ratingBarView.setChildDimension(28);

        dialog.getView(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getView(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    @Override
    public void finish() {
        SharedPreferencesUtils.getInstace(this).setStringPreference(Constants.CHAT, "abc");
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
