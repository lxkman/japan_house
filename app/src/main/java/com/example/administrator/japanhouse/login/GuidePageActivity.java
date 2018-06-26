package com.example.administrator.japanhouse.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.LancherBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.zackratos.ultimatebar.UltimateBar;

import java.util.HashMap;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;


/**
 * Created by lxk on 2017/6/30.
 */

public class GuidePageActivity extends BaseActivity {
    private ViewPager vp;
    private GuidePagerAdapter mGuidePagerAdapter;
    private int[] imgurls = {R.drawable.yindaoye1, R.drawable.yindaoye2, R.drawable.yindaoye3};
    private List<LancherBean.DatasBean> datas;
    private String location;
    private int languageType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //拉伸图片覆盖标题栏
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar(false);
        setContentView(R.layout.activity_guide);
        vp = (ViewPager) findViewById(R.id.vp);
        location = CacheUtils.get(Constants.COUNTRY);
        if (location.equals("ja")) {
//                iv_launcher.setBackground(getResources().getDrawable(R.drawable.start_bg));
            languageType = 1;
        } else {
            languageType = 0;
        }
        initGuideNet();

    }

    private void initGuideNet() {
        HttpParams params = new HttpParams();
        params.put("imageType", "1");
        params.put("languageType", languageType);
        OkGo.<LancherBean>post(MyUrls.BASEURL + "/app/image/images")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<LancherBean>(this, LancherBean.class) {
                    @Override
                    public void onSuccess(Response<LancherBean> response) {
                        int code = response.code();
                        LancherBean LancherBean = response.body();
                        if (LancherBean != null) {
                            if (LancherBean.getCode().equals("200")) {
                                datas = LancherBean.getDatas();
                                if (datas.size() <= 0) {
                                    SharedPreferencesUtils.getInstace(GuidePageActivity.this).setBooleanPreference("guide", true);
                                    HashMap<String, Boolean> hashMap = new HashMap<>();
                                    //会话类型 以及是否聚合显示
                                    hashMap.put(Conversation.ConversationType.PRIVATE.getName(), false);
//        hashMap.put(Conversation.ConversationType.PUSH_SERVICE.getName(),true);
//        hashMap.put(Conversation.ConversationType.SYSTEM.getName(),true);
                                    RongIM.getInstance().startConversationList(GuidePageActivity.this, hashMap);
                                    finish();
                                }
                                if (mGuidePagerAdapter == null) {
                                    mGuidePagerAdapter = new GuidePagerAdapter();
                                }
                                vp.setAdapter(mGuidePagerAdapter);
                            }
                        }
                    }
                });

    }


    private class GuidePagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(GuidePageActivity.this, R.layout.item_guide, null);
            TextView tv_jinru = (TextView) view.findViewById(R.id.tv_jinru);
            Log.d("GuidePagerAdapter", "datas.size():" + datas.size()+"---------");
           if (position == datas.size() - 1) {
                tv_jinru.setVisibility(View.VISIBLE);
            } else {
                tv_jinru.setVisibility(View.GONE);
            }
            tv_jinru.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferencesUtils.getInstace(GuidePageActivity.this).setBooleanPreference("guide", true);
                    HashMap<String, Boolean> hashMap = new HashMap<>();
                    //会话类型 以及是否聚合显示
                    hashMap.put(Conversation.ConversationType.PRIVATE.getName(), false);
//        hashMap.put(Conversation.ConversationType.PUSH_SERVICE.getName(),true);
//        hashMap.put(Conversation.ConversationType.SYSTEM.getName(),true);
                    RongIM.getInstance().startConversationList(GuidePageActivity.this, hashMap);
                    finish();
                }
            });
            ImageView iv_guide = (ImageView) view.findViewById(R.id.iv_guide);
            Glide.with(GuidePageActivity.this).load(datas.get(position).getImageUrl()).into(iv_guide);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
