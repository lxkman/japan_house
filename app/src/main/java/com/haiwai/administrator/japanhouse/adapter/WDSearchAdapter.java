package com.haiwai.administrator.japanhouse.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.fragment.home.ui.activity.QuestionActivity;
import com.haiwai.administrator.japanhouse.model.SimpleBean;

import java.util.List;

/**
 * admin  2018/6/29
 */
public class WDSearchAdapter  extends RecyclerView.Adapter<WDSearchAdapter.ViewHolder> {
    private Context context;
    List<SimpleBean.DatasBean> list;
    private String searchContent;

    public WDSearchAdapter(Context context, List<SimpleBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if (list != null && list.size() > 0 && searchContent != null) {
            int indexOf = list.get(position).getVal().indexOf(searchContent);

            if (indexOf != -1) {
                SpannableStringBuilder builder = new SpannableStringBuilder(list.get(position).getVal());
                ForegroundColorSpan span1 = new ForegroundColorSpan(Color.parseColor("#FE972A"));
                builder.setSpan(span1, indexOf, indexOf + searchContent.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.tv_Search_list.setText(builder);
            } else {
                holder.tv_Search_list.setText(list.get(position).getVal());
            }

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ImManager.enterChat(context, list.get(position).getId() + "", list.get(position).getBrokerName(), list.get(position).getPic());
                    Intent intent = new Intent(context, QuestionActivity.class);
                    intent.putExtra("searchText", list.get(position).getVal());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView tv_Search_list;
        public LinearLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tv_Search_list = (TextView) itemView.findViewById(R.id.tv_Search_list);
            layout = (LinearLayout) itemView.findViewById(R.id.layout_Search_list);
        }
    }
}
