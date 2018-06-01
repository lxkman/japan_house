package com.example.administrator.japanhouse.fragment.comment;

import android.os.Bundle;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayerStandard;

public class VideoDetailsActivity extends BaseActivity {

    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard videoplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_details);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        videoplayer.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
        videoplayer.thumbImageView.setImageResource(R.drawable.bgxq);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    @Override
    public void onPause() {
        super.onPause();
    }
}
