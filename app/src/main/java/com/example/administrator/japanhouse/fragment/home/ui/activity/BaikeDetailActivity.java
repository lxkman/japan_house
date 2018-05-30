package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.bean.BaiKe_Detail_Bean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaikeDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_beak;
    private View xian;
    private TextView title;
    private TextView time;
    private TextView neirong;
    private int wid;
    private boolean isJa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baike_detail);
        initView();
        intdaview();
    }



    private void initView() {
        img_beak = (ImageView) findViewById(R.id.img_beak);
        title = (TextView) findViewById(R.id.title);
        time = (TextView) findViewById(R.id.time);
        neirong = (TextView) findViewById(R.id.neirong);
        img_beak.setOnClickListener(this);
    }
    private void intdaview() {
        String city = CacheUtils.get(Constants.COUNTRY);
        if(city!=null&&city.equals("ja")){
            isJa =true;
        }else{
            isJa =false;
        }
        wid = getIntent().getIntExtra("wid", 0);
        HttpParams params = new HttpParams();
        params.put("wId",wid);
        OkGo.<BaiKe_Detail_Bean>post(MyUrls.BASEURL + "/app/textsource/purchasewikipediaInfo")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<BaiKe_Detail_Bean>(BaikeDetailActivity.this,BaiKe_Detail_Bean.class){
                    @Override
                    public void onSuccess(Response<BaiKe_Detail_Bean> response) {
                        BaiKe_Detail_Bean body = response.body();
                        String code = body.getCode();
                        if(code.equals("200")){
                            BaiKe_Detail_Bean.DatasBean datas = body.getDatas();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                            String format = sdf.format(new Date(response.body().getDatas().getCreateTime()));
                            if(isJa){
                                title.setText(datas.getTitleJpn());
                                neirong.setText(datas.getContentJpn());
                            }else{
                                title.setText(datas.getTitleCn());
                                neirong.setText(datas.getContentCn());
                            }
                            time.setText(format);
                        }else if(code.equals("201")){
                            ToastUtils.getToast(BaikeDetailActivity.this,body.getMsg());
                        }else if(code.equals("500")){
                            ToastUtils.getToast(BaikeDetailActivity.this,body.getMsg());
                        }else if(code.equals("404")){
                            ToastUtils.getToast(BaikeDetailActivity.this,body.getMsg());
                        }else if(code.equals("203")){
                            ToastUtils.getToast(BaikeDetailActivity.this,body.getMsg());
                        }else if(code.equals("204")){
                            ToastUtils.getToast(BaikeDetailActivity.this,body.getMsg());
                        }
                    }
                });
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.img_beak:
                finish();
                break;
        }
    }
}
