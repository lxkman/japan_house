package com.example.administrator.japanhouse.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.administrator.japanhouse.R;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by   admin on 2018/4/18.
 */

public class FullScreenActivity extends Activity {

    private JZVideoPlayerStandard videoPlayer;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fullscreen);

        String path = getIntent().getStringExtra("path");
        videoPlayer = (JZVideoPlayerStandard) findViewById(R.id.act_rental_details_videoPlayer);
        videoPlayer.setUp(path, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
        videoPlayer.fullscreenButton.setVisibility(View.INVISIBLE);
        videoPlayer.startButton.performClick();
    }

    public static void invoke(Context context, String path){
        Intent intent = new Intent(context, FullScreenActivity.class);
        intent.putExtra("path", path);
        context.startActivity(intent);
    }
}
