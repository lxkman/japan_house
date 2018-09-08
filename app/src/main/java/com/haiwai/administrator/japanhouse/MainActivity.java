package com.haiwai.administrator.japanhouse;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.haiwai.administrator.japanhouse.activity.NoScrollViewPager;
import com.haiwai.administrator.japanhouse.activity.adapter.FragPagerAdapter;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.bean.EventBean;
import com.haiwai.administrator.japanhouse.fragment.chat.ChatFragment;
import com.haiwai.administrator.japanhouse.fragment.comment.CommentFragment;
import com.haiwai.administrator.japanhouse.fragment.home.HomeFragment;
import com.haiwai.administrator.japanhouse.fragment.mine.MineFragment;
import com.haiwai.administrator.japanhouse.login.LoginActivity;
import com.haiwai.administrator.japanhouse.utils.Constants;
import com.haiwai.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.haiwai.administrator.japanhouse.utils.TUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.zackratos.ultimatebar.UltimateBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.haiwai.administrator.japanhouse.R.id.rb_chat;

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

    private int page = 1;

    private NoScrollViewPager mViewPager;
    private FragPagerAdapter pagerAdapter;
    private List<Fragment> fragments;
    private String[] permissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
            , Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA};
    private static final int CODE_FOR_WRITE_PERMISSION = 1001;

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
        String umpushid = SharedPreferencesUtils.getInstace(this).getStringPreference("UMPUSHID", "");
        Log.d("MainActivity", umpushid + "-------------");
        initView();
        for (int i = 0; i < permissions.length; i++) {
            checkPermission(permissions[i]);
        }
    }

    private void checkPermission(String permission) {
        //使用兼容库就无需判断系统版本
        int hasWriteStoragePermission = ContextCompat.checkSelfPermission(getApplication(),
                permission);
        if (hasWriteStoragePermission == PackageManager.PERMISSION_GRANTED) {
            //拥有权限，执行操作
        } else {
            //没有权限，向用户请求权限
            ActivityCompat.requestPermissions(this, new String[]{permission}, CODE_FOR_WRITE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //通过requestCode来识别是否同一个请求
        if (requestCode == CODE_FOR_WRITE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户同意，执行操作
            } else {
                //用户不同意，向用户展示该权限作用
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    TUtils.showFail(MainActivity.this, getString(R.string.yingyongxuyaoquanxian));
                }
            }
        }
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
                        page = 1;
                        rbHome.setChecked(true);
                        break;
                    case 1:
                        page = 2;
                        rbChat.setChecked(true);
                        break;
                    case 2:
                        page = 3;
                        rbComment.setChecked(true);
                        break;
                    case 3:
                        page = 4;
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
                page = 1;
                mViewPager.setCurrentItem(0);
                break;
            case rb_chat:
                if (!MyApplication.isLogin()) {
                    switch (page) {
                        case 1:
                            rbHome.setChecked(true);
                            break;
                        case 2:
                            rbChat.setChecked(true);
                            break;
                        case 3:
                            rbComment.setChecked(true);
                            break;
                        case 4:
                            rbMine.setChecked(true);
                            break;
                    }

                    startActivity(new Intent(this, LoginActivity.class));
                    return;
                }

                page = 2;
                mViewPager.setCurrentItem(1);
                break;
            case R.id.rb_comment:
                page = 3;
                mViewPager.setCurrentItem(2);
                break;
            case R.id.rb_mine:
                EventBus.getDefault().postSticky(new EventBean("minescrolltotop"));
                if (!MyApplication.isLogin()) {
                    switch (page) {
                        case 1:
                            rbHome.setChecked(true);
                            break;
                        case 2:
                            rbChat.setChecked(true);
                            break;
                        case 3:
                            rbComment.setChecked(true);
                            break;
                        case 4:
                            rbMine.setChecked(true);
                            break;
                    }

                    startActivity(new Intent(this, LoginActivity.class));
                    return;
                }
                EventBus.getDefault().post(new EventBean(Constants.EVENT_MINE));
                page = 4;
                mViewPager.setCurrentItem(3);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > preTime + 2000) {
            TUtils.showFail(this, getResources().getString(R.string.zaianyicituichu));
            preTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();//相当于finish()
            removeAllActivitys();//删除所有引用
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void msgEvent(EventBean eventBean) {
        if (TextUtils.equals(Constants.EVENT_CHAT, eventBean.getMsg())) {
            if (!MyApplication.isLogin()) {
                switch (page) {
                    case 1:
                        rbHome.setChecked(true);
                        break;
                    case 2:
                        rbChat.setChecked(true);
                        break;
                    case 3:
                        rbComment.setChecked(true);
                        break;
                    case 4:
                        rbMine.setChecked(true);
                        break;
                }
                startActivity(new Intent(this, LoginActivity.class));
                return;
            }
            mViewPager.setCurrentItem(1);
        } else if (TextUtils.equals(Constants.EVENT_MAIN, eventBean.getMsg())) {
            mViewPager.setCurrentItem(0);
        }
    }

}
