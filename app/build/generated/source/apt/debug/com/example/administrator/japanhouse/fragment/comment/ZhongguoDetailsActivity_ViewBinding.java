// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.comment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ZhongguoDetailsActivity_ViewBinding implements Unbinder {
  private ZhongguoDetailsActivity target;

  private View view2131755354;

  private View view2131755355;

  private View view2131755235;

  private View view2131755388;

  @UiThread
  public ZhongguoDetailsActivity_ViewBinding(ZhongguoDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ZhongguoDetailsActivity_ViewBinding(final ZhongguoDetailsActivity target, View source) {
    this.target = target;

    View view;
    target.vpVidio = Utils.findRequiredViewAsType(source, R.id.vp_vidio, "field 'vpVidio'", ViewPager.class);
    target.tvToNum = Utils.findRequiredViewAsType(source, R.id.tv_to_num, "field 'tvToNum'", TextView.class);
    target.tvAllNum = Utils.findRequiredViewAsType(source, R.id.tv_all_num, "field 'tvAllNum'", TextView.class);
    target.HuxingRecycler = Utils.findRequiredViewAsType(source, R.id.Huxing_Recycler, "field 'HuxingRecycler'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.img_share, "field 'imgShare' and method 'onClick'");
    target.imgShare = Utils.castView(view, R.id.img_share, "field 'imgShare'", ImageView.class);
    view2131755354 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_start, "field 'imgStart' and method 'onClick'");
    target.imgStart = Utils.castView(view, R.id.img_start, "field 'imgStart'", ImageView.class);
    view2131755355 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.loveRecycler = Utils.findRequiredViewAsType(source, R.id.love_Recycler, "field 'loveRecycler'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.back_img, "field 'backImg' and method 'onClick'");
    target.backImg = Utils.castView(view, R.id.back_img, "field 'backImg'", ImageView.class);
    view2131755235 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mScrollView = Utils.findRequiredViewAsType(source, R.id.mScrollView, "field 'mScrollView'", NestedScrollView.class);
    target.re_top_bg = Utils.findRequiredViewAsType(source, R.id.re_top_bg, "field 're_top_bg'", RelativeLayout.class);
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_See_More, "method 'onClick'");
    view2131755388 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ZhongguoDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vpVidio = null;
    target.tvToNum = null;
    target.tvAllNum = null;
    target.HuxingRecycler = null;
    target.imgShare = null;
    target.imgStart = null;
    target.loveRecycler = null;
    target.backImg = null;
    target.mScrollView = null;
    target.re_top_bg = null;
    target.tv_title = null;

    view2131755354.setOnClickListener(null);
    view2131755354 = null;
    view2131755355.setOnClickListener(null);
    view2131755355 = null;
    view2131755235.setOnClickListener(null);
    view2131755235 = null;
    view2131755388.setOnClickListener(null);
    view2131755388 = null;
  }
}