package com.example.administrator.japanhouse.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;

import java.util.List;

/**
 * Created by   admin on 2018/5/28.
 */

public class SimpleBaseAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public SimpleBaseAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SimpleViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_simple_adapter_view, parent, false);
            viewHolder = new SimpleViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.item_simple_view_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SimpleViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(list.get(position));
        return convertView;
    }

    class SimpleViewHolder {
        TextView textView;
    }
}
