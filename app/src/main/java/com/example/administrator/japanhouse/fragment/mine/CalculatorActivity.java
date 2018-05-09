package com.example.administrator.japanhouse.fragment.mine;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.OnItemSelectedListener;
import com.example.administrator.japanhouse.MainActivity;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.utils.ToastUtils;
import com.example.administrator.japanhouse.view.BaseDialog;

import java.util.ArrayList;
import java.util.List;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back_img;
    private EditText ed_jiaqian;
    private ImageView cal_xuanze;
    private ImageView cal_xe_nx;
    private TextView nianxian;
    private ImageView cal_xe_nx1;
    private TextView nianxian1;
    private Button cal_jisuan;
    private TextView lu;
    private WheelView wv;
    private OptionsPickerView pickerView;
   List<String> list=new ArrayList<>();
   List<String> money=new ArrayList<>();
    List<String> nian=new ArrayList<>();
    private TextView quxiao;
    private TextView queding;
    private WheelView wheelView;
    private String list_itme;
    private String qian;
    private String age_itm;
    private EditText ed_wan;
    private EditText ed_wan1;
    private int index1;
    private int index2;
    private int index21;
    private int index3;
    private int index4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        initView();
    }



    private void initView() {
            list.add("10%");
            list.add("20%");
            list.add("30%");
            list.add("40%");
            list.add("50%");
            list.add("60%");
            list.add("70%");
            list.add("80%");
            list.add("90%");
            list.add("100%");
            money.add("10");
            money.add("20");
            money.add("30");
            money.add("40");
            money.add("50");
            money.add("60");
            money.add("70");
            money.add("80");
            money.add("90");
            money.add("100");
            nian.add("年限10");
            nian.add("年限20");
            nian.add("年限30");
            nian.add("年限40");
            nian.add("年限50");
            nian.add("年限60");
            nian.add("年限70");
            nian.add("年限80");
            nian.add("年限90");
            nian.add("年限100");

        back_img = (ImageView) findViewById(R.id.back_img);
        ed_jiaqian = (EditText) findViewById(R.id.ed_jiaqian);
        cal_xuanze = (ImageView) findViewById(R.id.cal_xuanze);
        ed_wan = (EditText) findViewById(R.id.ed_wan);
        ed_wan1 = (EditText) findViewById(R.id.ed_wan1);
        cal_xe_nx = (ImageView) findViewById(R.id.cal_xe_nx);
        nianxian = (TextView) findViewById(R.id.nianxian);
        cal_xe_nx1 = (ImageView) findViewById(R.id.cal_xe_nx1);
        nianxian1 = (TextView) findViewById(R.id.nianxian1);
        cal_jisuan = (Button) findViewById(R.id.cal_jisuan);
        lu = (TextView) findViewById(R.id.lu);
        cal_jisuan.setOnClickListener(this);
        back_img.setOnClickListener(this);
        cal_xuanze.setOnClickListener(this);
        cal_xe_nx.setOnClickListener(this);
        cal_xe_nx1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
              case R.id.back_img:
                  finish();
                  break;
               case R.id.cal_xuanze:
                  showFXDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                   break;
            case R.id.cal_xe_nx:
                showFXDialog1(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
            case R.id.cal_xe_nx1:
                showFXDialog2(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
             case R.id.cal_jisuan:
                 Intent intent = new Intent(CalculatorActivity.this, Calculator_DetilsActivity.class);
                 intent.putExtra("shoufu",ed_jiaqian.getText().toString());
                 intent.putExtra("daikuan",ed_wan.getText().toString());
                 intent.putExtra("lixi",ed_wan1.getText().toString());
                 startActivity(intent);
                 break;



        }
    }
    private void showFXDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(CalculatorActivity.this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        final Dialog dialog = builder.setViewId(R.layout.sharing_pop_item_view)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        quxiao = (TextView) dialog.findViewById(R.id.quxiao);
        queding = (TextView) dialog.findViewById(R.id.queding);
        wheelView = (WheelView) dialog.findViewById(R.id.wheelview);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index1=0;
                dialog.dismiss();
            }
        });

        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index1<0||index1==0){
                    list_itme = list.get(0);
                }else{
                    list_itme = list.get(index1);
                }
                lu.setText(list_itme);
                dialog.dismiss();
                index1=0;
            }
        });
        wheelView.setCyclic(false);
        wheelView.setAdapter(new ArrayWheelAdapter(list));
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                index1 = index;
            }
        });

    }
    /*
    * 二级
    * */
    private void showFXDialog1(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(CalculatorActivity.this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        final Dialog dialog = builder.setViewId(R.layout.sharing_pop_item_view1)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
       TextView quxiao1 = (TextView) dialog.findViewById(R.id.quxiao);
       TextView queding1 = (TextView) dialog.findViewById(R.id.queding);
        WheelView price = (WheelView) dialog.findViewById(R.id.price);
        WheelView age = (WheelView) dialog.findViewById(R.id.nianxian);
        quxiao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index21=0;
                index2=0;
                dialog.dismiss();
            }
        });

        queding1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((index2<0||index2==0)&&(index21<0||index21==0)){
                    qian = money.get(0);
                    age_itm = nian.get(0);
                    ed_wan.setText(qian);
                    nianxian.setText(age_itm);
                    dialog.dismiss();
                    index21=0;
                    index2=0;
                }else if((index2<0||index2==0)&&index21!=0){
                    qian = money.get(0);
                    age_itm = nian.get(index21);
                    ed_wan.setText(qian);
                    nianxian.setText(age_itm);
                    dialog.dismiss();
                    index21=0;
                    index2=0;
                }else if((index21<0||index21==0)&&index2!=0){
                    qian = money.get(index2);
                    age_itm = nian.get(0);
                    ed_wan.setText(qian);
                    nianxian.setText(age_itm);
                    dialog.dismiss();
                    index21=0;
                    index2=0;
                }else{
                    qian = money.get(index2);
                    age_itm = nian.get(index21);
                    ed_wan.setText(qian);
                    nianxian.setText(age_itm);
                    dialog.dismiss();
                    index21=0;
                    index2=0;
                }

            }
        });
        price.setCyclic(false);
        price.setAdapter(new ArrayWheelAdapter(money));
        price.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                index2 = index;
            }
        });
        age.setCyclic(false);
        age.setAdapter(new ArrayWheelAdapter(nian));
        age.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                index21 = index;
            }
        });

    }
    /*
    * 二级
    * */
    private void showFXDialog2(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(CalculatorActivity.this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        final Dialog dialog = builder.setViewId(R.layout.sharing_pop_item_view1)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        TextView quxiao1 = (TextView) dialog.findViewById(R.id.quxiao);
        TextView queding1 = (TextView) dialog.findViewById(R.id.queding);
        WheelView price = (WheelView) dialog.findViewById(R.id.price);
        WheelView age = (WheelView) dialog.findViewById(R.id.nianxian);
        quxiao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index4=0;
                index3=0;
                dialog.dismiss();
            }
        });

        queding1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((index3<0||index3==0)&&(index4<0||index4==0)){
                    qian = money.get(0);
                    age_itm = nian.get(0);
                }else if((index3<0||index3==0)&&index4!=0){
                    qian = money.get(0);
                    age_itm = nian.get(index4);
                }else if((index4<0||index4==0)&&index3!=0){
                    qian = money.get(index3);
                    age_itm = nian.get(0);
                }else{
                    qian = money.get(index3);
                    age_itm = nian.get(index4);
                }
                ed_wan.setText(qian);
                nianxian.setText(age_itm);
                dialog.dismiss();
                index4=0;
                index3=0;
            }
        });
        price.setCyclic(false);
        price.setAdapter(new ArrayWheelAdapter(money));
        price.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                index3 = index;
            }
        });
        age.setCyclic(false);
        age.setAdapter(new ArrayWheelAdapter(nian));
        age.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                index4 = index;
            }
        });

    }

}
