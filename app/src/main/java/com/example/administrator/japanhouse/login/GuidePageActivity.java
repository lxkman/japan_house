package com.example.administrator.japanhouse.login;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.example.administrator.japanhouse.utils.TUtils;
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
    private String[] permissions={Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE
            ,Manifest.permission.READ_PHONE_STATE,Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.CAMERA
    };
    private static final int CODE_FOR_WRITE_PERMISSION =1001 ;

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
        }else{
            //没有权限，向用户请求权限
            ActivityCompat.requestPermissions(this, new String[]{permission}, CODE_FOR_WRITE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //通过requestCode来识别是否同一个请求
        if (requestCode == CODE_FOR_WRITE_PERMISSION){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //用户同意，执行操作
            }else{
                //用户不同意，向用户展示该权限作用
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    TUtils.showFail(GuidePageActivity.this,"应用需要此权限");
                }
            }
        }
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
            Log.d("GuidePagerAdapter", "datas.size():" + datas.size() + "---------");
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
