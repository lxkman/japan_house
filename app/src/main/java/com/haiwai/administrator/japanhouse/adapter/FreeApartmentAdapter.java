package com.haiwai.administrator.japanhouse.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.model.FreeApartmentBean;
import com.haiwai.administrator.japanhouse.utils.TUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by   admin on 2018/4/17.
 */

public class FreeApartmentAdapter extends RecyclerView.Adapter {

    private Activity activity;

    private String charsStr = "0123456789";

    private onClickListener onClickListener;

    private List<FreeApartmentBean.DatasBean> list;

    private Map<TextView, CountDownTimer> map;

    public FreeApartmentAdapter(Activity activity, List<FreeApartmentBean.DatasBean> list) {
        this.activity = activity;
        this.list = list;
        map = new HashMap<>();

    }

    public void setOnClickListener(onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_free_apartment, null);
        return new FreeApartmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof FreeApartmentViewHolder && list != null) {
            final FreeApartmentViewHolder holder = (FreeApartmentViewHolder) viewHolder;
//            holder.setIsRecyclable(false);

            long l = list.get(i).getEndTime() - list.get(i).getCurrentTime();

            if (l <= 0) {
                onClickListener.onItemDeteleClickListener(i);
            }

            final long days = l / (1000 * 60 * 60 * 24);
            long hours = (l - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (l - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            SpannableStringBuilder builder = new SpannableStringBuilder(
                    String.format(activity.getString(R.string.item_apartment_time), days, hours, minutes));

            char[] chars = builder.toString().toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (charsStr.contains(String.valueOf(chars[j]))) {
                    builder.setSpan(new ForegroundColorSpan(Color.parseColor("#FE972A")), j, j + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
            holder.tvTime.setText(builder);

            //实现平方米的显示
            SpannableString m2 = new SpannableString("m2");
            m2.setSpan(new RelativeSizeSpan(0.5f), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            m2.setSpan(new SuperscriptSpan(), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(
                    MyApplication.isJapanese() ? list.get(i).getPriceJpn() + "元/" : list.get(i).getPriceCn() + "元/");
            spannableStringBuilder.append(m2);
            holder.tvPrices.setText(spannableStringBuilder);

            Glide.with(activity)
                    .load(getList(list.get(i).getImages()).get(0))
                    .into(holder.icon);

            holder.tvNum.setText(String.format(activity.getString(R.string.item_apartment_people), list.get(i).getPeopleCount()));

            holder.tvTitle.setText(MyApplication.isJapanese() ? list.get(i).getActivityNameJpn() : list.get(i).getActivityNameCn());

            holder.tvSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(i).getIsbm() == 1) {
                        TUtils.showFail(activity, activity.getString(R.string.not_repeat_singUp));
                    } else {
                        onClickListener.onSignUpClickListener(list.get(i).getId());
                    }

                }
            });

            holder.tvPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onCallClickListener(list.get(i).getKfPhone());
                }
            });

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("tag", "------------------------- - " + list.get(i).getIsbm());
                    Log.e("tag", "------------------------- -  " + list.get(i).getId());
                    onClickListener.onItemClickListener(list.get(i));
                }
            });

        }
    }

    private List<String> getList(String pic) {
        String d[] = pic.split(",");
        List<String> picList = new ArrayList();

        for (int i = 0; i < d.length; i++) {
            picList.add(d[i]);
        }
        return picList;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class FreeApartmentViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView tvNum;
        TextView tvTitle;
        TextView tvTime;
        TextView tvPrices;
        TextView tvPhone;
        TextView tvSignUp;
        LinearLayout layout;

        public FreeApartmentViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.item_apartment_Img);
            tvNum = (TextView) itemView.findViewById(R.id.item_apartment_num);
            tvTitle = (TextView) itemView.findViewById(R.id.item_apartment_title);
            tvTime = (TextView) itemView.findViewById(R.id.item_apartment_time);
            tvPrices = (TextView) itemView.findViewById(R.id.item_apartment_prices);
            tvPhone = (TextView) itemView.findViewById(R.id.item_apartment_phone);
            tvSignUp = (TextView) itemView.findViewById(R.id.item_apartment_signUp);
            layout = (LinearLayout) itemView.findViewById(R.id.item_apartment_layout);
        }
    }

    public interface onClickListener {
        void onSignUpClickListener(int actionId);

        void onCallClickListener(String tel);

        void onItemDeteleClickListener(int position);

        void onItemClickListener(FreeApartmentBean.DatasBean datasBean);
    }

}
