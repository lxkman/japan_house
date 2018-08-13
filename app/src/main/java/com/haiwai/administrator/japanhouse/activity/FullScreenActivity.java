package com.haiwai.administrator.japanhouse.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.haiwai.administrator.japanhouse.R;

import cn.jzvd.JZVideoPlayer;
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
        videoPlayer.setUp(path, JZVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN, " ");
        videoPlayer.fullscreenButton.setVisibility(View.INVISIBLE);
        videoPlayer.startButton.performClick();
        videoPlayer.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public static void invoke(Context context, String path){
        Intent intent = new Intent(context, FullScreenActivity.class);
        intent.putExtra("path", path);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        videoPlayer.onStatePause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onStatePause();
    }
}
