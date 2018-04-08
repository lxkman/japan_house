package com.example.administrator.japanhouse;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.fragment.chat.ChatFragment;
import com.example.administrator.japanhouse.fragment.comment.CommentFragment;
import com.example.administrator.japanhouse.fragment.home.HomeFragment;
import com.example.administrator.japanhouse.fragment.mine.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_chat)
    RadioButton rbChat;
    @BindView(R.id.rb_comment)
    RadioButton rbComment;
    @BindView(R.id.rb_mine)
    RadioButton rbMine;
    @BindView(R.id.rgp)
    RadioGroup rgp;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private BaseFragment baseFragment;
    private HomeFragment homeFragment;
    private ChatFragment chatFragment;
    private CommentFragment commentFragment;
    private MineFragment mineFragment;
    private long preTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void addFragments(BaseFragment f) {
        // 第一步：得到fragment管理类
        FragmentManager manager = getSupportFragmentManager();
        // 第二步：开启一个事务
        FragmentTransaction transaction = manager.beginTransaction();

        if (baseFragment != null) {
            //每次把前一个fragment给隐藏了
            transaction.hide(baseFragment);
        }
        //isAdded:判断当前的fragment对象是否被加载过
        if (!f.isAdded()) {
            // 第三步：调用添加fragment的方法 第一个参数：容器的id 第二个参数：要放置的fragment的一个实例对象
            transaction.add(R.id.fl_content, f);
        }
        //显示当前的fragment
        transaction.show(f);
        // 第四步：提交
        transaction.commit();
        baseFragment = f;
    }

    private void initView() {
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
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                addFragments(homeFragment);
                break;
            case R.id.rb_chat:
                if (chatFragment == null) {
                    chatFragment = new ChatFragment();
                }
                addFragments(chatFragment);
                break;
            case R.id.rb_comment:
                if (commentFragment == null) {
                    commentFragment = new CommentFragment();
                }
                addFragments(commentFragment);
                break;
            case R.id.rb_mine:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                }
                addFragments(mineFragment);
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
}
