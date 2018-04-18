package com.example.administrator.japanhouse.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;

import org.w3c.dom.Text;

/**
 * Created by   admin on 2018/4/17.
 */

public class FreeApartmentAdapter extends RecyclerView.Adapter{

    private Activity activity;

    private onClickListener onClickListener;

    public FreeApartmentAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setOnClickListener(onClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_free_apartment, null);
        return new FreeApartmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof FreeApartmentViewHolder) {
            FreeApartmentViewHolder holder = (FreeApartmentViewHolder) viewHolder;

            //实现平方米的显示
            SpannableString m2 = new SpannableString("m2");
            m2.setSpan(new RelativeSizeSpan(0.5f), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            m2.setSpan(new SuperscriptSpan(), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("17500元/");
            spannableStringBuilder.append(m2);
            holder.tvPrices.setText(spannableStringBuilder);

            SpannableStringBuilder builder = new SpannableStringBuilder(holder.tvTime.getText().toString());
            ForegroundColorSpan span1 = new ForegroundColorSpan(Color.parseColor("#FE972A"));
            ForegroundColorSpan span2 = new ForegroundColorSpan(Color.parseColor("#FE972A"));
            ForegroundColorSpan span3 = new ForegroundColorSpan(Color.parseColor("#FE972A"));
            builder.setSpan(span1, 2, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(span2, 5, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(span3, 9, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.tvTime.setText(builder);

            holder.tvSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onSignUpClickListener();
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class FreeApartmentViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView tvNum;
        TextView tvTitle;
        TextView tvTime;
        TextView tvPrices;
        TextView tvPhone;
        TextView tvSignUp;

        public FreeApartmentViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.item_apartment_Img);
            tvNum = (TextView) itemView.findViewById(R.id.item_apartment_num);
            tvTitle = (TextView) itemView.findViewById(R.id.item_apartment_title);
            tvTime = (TextView) itemView.findViewById(R.id.item_apartment_time);
            tvPrices = (TextView) itemView.findViewById(R.id.item_apartment_prices);
            tvPhone = (TextView) itemView.findViewById(R.id.item_apartment_phone);
            tvSignUp = (TextView) itemView.findViewById(R.id.item_apartment_signUp);
        }
    }

    public interface onClickListener{
        void onSignUpClickListener();
    }
}
