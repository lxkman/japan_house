package com.example.administrator.japanhouse;

import android.app.ActivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.japanhouse.activity.FragPagerAdapter;
import com.example.administrator.japanhouse.activity.NoScrollViewPager;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.EventBean;
import com.example.administrator.japanhouse.fragment.chat.ChatFragment;
import com.example.administrator.japanhouse.fragment.comment.CommentFragment;
import com.example.administrator.japanhouse.fragment.home.HomeFragment;
import com.example.administrator.japanhouse.fragment.mine.MineFragment;
import com.example.administrator.japanhouse.utils.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.zackratos.ultimatebar.UltimateBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.administrator.japanhouse.R.id.rb_chat;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(rb_chat)
    RadioButton rbChat;
    @BindView(R.id.rb_comment)
    RadioButton rbComment;
    @BindView(R.id.rb_mine)
    RadioButton rbMine;
    @BindView(R.id.rgp)
    RadioGroup rgp;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private long preTime;

    private NoScrollViewPager mViewPager;
    private FragPagerAdapter pagerAdapter;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //拉伸图片覆盖标题栏
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar(false);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        mViewPager = (NoScrollViewPager) findViewById(R.id.act_main_vp);
        mViewPager.setNoScroll(true);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //        super.onSaveInstanceState(outState);
    }

    private void initView() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ChatFragment());
        fragments.add(new CommentFragment());
        fragments.add(new MineFragment());
        pagerAdapter = new FragPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(fragments.size());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        rbHome.setChecked(true);
                        break;
                    case 1:
                        rbChat.setChecked(true);
                        break;
                    case 2:
                        rbComment.setChecked(true);
                        break;
                    case 3:
                        rbMine.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rgp.check(R.id.rb_home);
        rbHome.setOnClickListener(this);
        rbChat.setOnClickListener(this);
        rbComment.setOnClickListener(this);
        rbMine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_home:
                mViewPager.setCurrentItem(0);
                break;
            case rb_chat:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.rb_comment:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.rb_mine:
                mViewPager.setCurrentItem(3);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > preTime + 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            preTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();//相当于finish()
            removeAllActivitys();//删除所有引用
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void msgEvent(EventBean eventBean) {
        if (TextUtils.equals(Constants.EVENT_CHAT, eventBean.getMsg())) {
            mViewPager.setCurrentItem(1);
        }
    }

}
