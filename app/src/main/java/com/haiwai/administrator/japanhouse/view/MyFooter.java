package com.haiwai.administrator.japanhouse.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.haiwai.administrator.japanhouse.R;
import com.liaoinstan.springview.container.BaseFooter;

/**
 * Created by Administrator on 2016/3/21.
 */
public class MyFooter extends BaseFooter {
    private Context context;
    private int rotationSrc;
    private TextView footerTitle;
    private ProgressBar footerProgressbar;

    public MyFooter(Context context) {
        this(context, R.drawable.progress_small);
    }

    public MyFooter(Context context, int rotationSrc) {
        this.context = context;
        this.rotationSrc = rotationSrc;
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(com.liaoinstan.springview.R.layout.default_footer, viewGroup, true);
        footerTitle = (TextView) view.findViewById(com.liaoinstan.springview.R.id.default_footer_title);
        footerProgressbar = (ProgressBar) view.findViewById(com.liaoinstan.springview.R.id.default_footer_progressbar);
        footerProgressbar.setIndeterminateDrawable(ContextCompat.getDrawable(context, rotationSrc));
        return view;
    }

    @Override
    public void onPreDrag(View rootView) {
    }

    @Override
    public void onDropAnim(View rootView, int dy) {
    }

    @Override
    public void onLimitDes(View rootView, boolean upORdown) {
        if (upORdown) {
            footerTitle.setText(R.string.songkaizairumore);
        } else {
            footerTitle.setText(R.string.lookmore);
        }
    }

    @Override
    public void onStartAnim() {
        footerTitle.setVisibility(View.INVISIBLE);
        footerProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinishAnim() {
        footerTitle.setText(R.string.lookmore);
        footerTitle.setVisibility(View.VISIBLE);
        footerProgressbar.setVisibility(View.INVISIBLE);
    }
}