package com.example.administrator.japanhouse.fragment.chat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.rong.imkit.RongIM;

/**
 * Created by Administrator on 2018/4/8.
 */

public class ChatFragment extends BaseFragment {
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.img_dialog)
    ImageView imgDialog;
    @BindView(R.id.mrecycler)
    RecyclerView mrecycler;
    Unbinder unbinder;
    private List<String> mList=new ArrayList();
    private LiebiaoAdapter liebiaoAdapter;
    private View popupView;
    private PopupWindow basePopupWindow;
    int REQUEST_CODE = 1;



    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_weichart, null);
        unbinder = ButterKnife.bind(this, view);
        getCameraPermission();
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initAdapter();
    }

    private void initAdapter() {
        if (mList.size()<=0){
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
        }
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        liebiaoAdapter = new LiebiaoAdapter(R.layout.weiliao_item,mList);
        mrecycler.setAdapter(liebiaoAdapter);
        liebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(mContext,ManagerActivity.class));
                if (RongIM.getInstance() != null) {
                    Log.e("MainActivity", "创建单聊");
                    RongIM.getInstance().startPrivateChat(getActivity(), "123456", getString(R.string.act_chat_title));
                }
            }
        });
    }



    class LiebiaoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId,data);
        }

        @Override
        protected void convert(BaseViewHolder helper,String item) {
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.img_search, R.id.img_dialog})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_search:
                startActivity(new Intent(mContext,SearchActivity.class));
                break;
            case R.id.img_dialog:
                initPop();
                basePopupWindow.showAsDropDown(view);
                break;
        }
    }

    private void initPop() {
        //屏幕变暗
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);

        String city = SharedPreferencesUtils.getInstace(mContext).getStringPreference("city", "");
        if (city.equals("ja")){
            popupView = View.inflate(mContext,R.layout.layout_popupwindow_japan, null);
        }else {
            popupView = View.inflate(mContext,R.layout.layout_popupwindow, null);
        }

        //pop控件监听
        initListener(popupView);

        basePopupWindow = (PopupWindow) new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        basePopupWindow.setTouchable(true);
        basePopupWindow.setOutsideTouchable(true);
//        basePopupWindow.setAnimationStyle(R.style.animTranslate);
        basePopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //消失的监听，屏幕还原
        basePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1.0f;
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getActivity().getWindow().setAttributes(lp);
            }
        });
    }

    private void initListener(View popupView) {
        TextView tv_saoyisao = (TextView) popupView.findViewById(R.id.tv_saoyisao);
        TextView tv_weiliao = (TextView) popupView.findViewById(R.id.tv_weiliao);
        TextView tv_search_manager = (TextView) popupView.findViewById(R.id.tv_search_manager);
        //扫一扫
        tv_saoyisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                basePopupWindow.dismiss();
                if (isCameraUseable()){
                    Intent intent1 = new Intent(mContext, MyZxingActivity.class);
                    startActivityForResult(intent1, REQUEST_CODE);
                }else {
                    Toast.makeText(mContext, "开启权限后执行该操作", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //发起微聊
        tv_weiliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                basePopupWindow.dismiss();
                Intent intent=new Intent(mContext,StartWechatActivity.class);
                startActivity(intent);

            }
        });
        //找经纪人
        tv_search_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                basePopupWindow.dismiss();
                Intent intent=new Intent(mContext,SearchManagerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Log.d("ChatFragment", result);
//                    Intent intent=new Intent(mContext,WebActivity.class);
//                    intent.putExtra("result",result);
//                    startActivity(intent);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void getCameraPermission()
    {
        if (Build.VERSION.SDK_INT>22){
            if (ContextCompat.checkSelfPermission(mContext,
                    android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                //先判断有没有权限 ，没有就在这里进行权限的申请
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.CAMERA},2);
            }else {
                //说明已经获取到摄像头权限了 想干嘛干嘛
            }
        }else {
            //这个说明系统版本在6.0之下，不需要动态获取权限。
        }
    }

    //是否开启摄像头权限
    public static boolean isCameraUseable() {

        boolean canUse =true;

        Camera mCamera =null;

        try{

            mCamera = Camera.open();

// setParameters 是针对魅族MX5。MX5通过Camera.open()拿到的Camera对象不为null

            Camera.Parameters mParameters = mCamera.getParameters();

            mCamera.setParameters(mParameters);

        }catch(Exception e) {

            canUse =false;

        }

        if(mCamera !=null) {

            mCamera.release();

        }
        return canUse;

    }


}
