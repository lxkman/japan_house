package com.example.administrator.japanhouse.im;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.example.administrator.japanhouse.R;

import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;

/**
 * Created by   admin on 2018/4/28.
 */

public class CollectPlugin implements IPluginModule {
    @Override
    public Drawable obtainDrawable(Context context) {
        return ContextCompat.getDrawable(context, R.drawable.rc_cs_evaluate_plugin);
    }

    @Override
    public String obtainTitle(Context context) {
//        return context.getString(R.string.plugin_collect);
        return "收藏";
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {

    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }
}
