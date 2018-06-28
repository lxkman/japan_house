package com.example.administrator.japanhouse.fragment.chat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.im.ImManager;
import com.example.administrator.japanhouse.model.ManagerBean;
import com.example.administrator.japanhouse.presenter.SearchManagerPresenter;
import com.example.administrator.japanhouse.utils.TUtils;
import com.lzy.okgo.model.Response;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchManagerActivity extends BaseActivity implements SearchManagerPresenter.SearchManagerCallBack {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.tv_saoyisao)
    TextView tvSaoyisao;
    int REQUEST_CODE = 1;
    @BindView(R.id.manager_search_content)
    EditText searchContent;
    @BindView(R.id.manager_search_submit)
    TextView searchSubmit;

    private SearchManagerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_manager);
        ButterKnife.bind(this);
        presenter = new SearchManagerPresenter(this, this);
    }

    @OnClick({R.id.back_img, R.id.tv_saoyisao, R.id.manager_search_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;
            case R.id.tv_saoyisao:
                if (isCameraUseable()) {
                    Intent intent1 = new Intent(mContext, MyZxingActivity.class);
                    startActivityForResult(intent1, REQUEST_CODE);
                } else {
                    Toast.makeText(mContext, "开启权限后执行该操作", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.manager_search_submit:
                presenter.getManager(searchContent.getText().toString());
                break;
        }
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

    public void getCameraPermission() {
        if (Build.VERSION.SDK_INT > 22) {
            if (ContextCompat.checkSelfPermission(mContext,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //先判断有没有权限 ，没有就在这里进行权限的申请
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA}, 2);
            } else {
                //说明已经获取到摄像头权限了 想干嘛干嘛
            }
        } else {
            //这个说明系统版本在6.0之下，不需要动态获取权限。
        }
    }

    //是否开启摄像头权限
    public static boolean isCameraUseable() {

        boolean canUse = true;

        Camera mCamera = null;

        try {

            mCamera = Camera.open();

// setParameters 是针对魅族MX5。MX5通过Camera.open()拿到的Camera对象不为null

            Camera.Parameters mParameters = mCamera.getParameters();

            mCamera.setParameters(mParameters);

        } catch (Exception e) {

            canUse = false;

        }

        if (mCamera != null) {

            mCamera.release();

        }
        return canUse;

    }

    @Override
    public void getManager(Response<ManagerBean> response) {
        if (response != null && response.body() != null) {
            if (response.body().getCode() != null && response.body().getCode().equals("205")) {
                TUtils.showFail(SearchManagerActivity.this, "没有搜索到经纪人");
            }

            //做搜索到的操作
            if (response.body().getDatas() != null && response.body().getDatas().getBrokerinfo() != null) {
                Intent intent = new Intent(this, ManagerActivity.class);
                intent.putExtra("ManagerId", response.body().getDatas().getBrokerinfo().getId() + "");
                startActivity(intent);
            }
        }
    }
}
