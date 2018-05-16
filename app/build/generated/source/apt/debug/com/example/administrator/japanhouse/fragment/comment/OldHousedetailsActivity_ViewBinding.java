// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.comment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.baidu.mapapi.map.MapView;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OldHousedetailsActivity_ViewBinding implements Unbinder {
  private OldHousedetailsActivity target;

  private View view2131755354;

  private View view2131755355;

  private View view2131755388;

  private View view2131755235;

  private View view2131755390;

  private View view2131755391;

  private View view2131755392;

  private View view2131755393;

  @UiThread
  public OldHousedetailsActivity_ViewBinding(OldHousedetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OldHousedetailsActivity_ViewBinding(final OldHousedetailsActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.tv_See_More, "field 'tvSeeMore' and method 'onClick'");
    target.tvSeeMore = Utils.castView(view, R.id.tv_See_More, "field 'tvSeeMore'", TextView.class);
    view2131755388 = view;
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
    target.tvSuiyi = Utils.findRequiredViewAsType(source, R.id.tv_suiyi, "field 'tvSuiyi'", TextView.class);
    target.mScrollView = Utils.findRequiredViewAsType(source, R.id.mScrollView, "field 'mScrollView'", NestedScrollView.class);
    target.re_top_bg = Utils.findRequiredViewAsType(source, R.id.re_top_bg, "field 're_top_bg'", RelativeLayout.class);
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.mapView = Utils.findRequiredViewAsType(source, R.id.bmapView, "field 'mapView'", MapView.class);
    view = Utils.findRequiredView(source, R.id.shop_layout, "field 'shopLayout' and method 'onClick'");
    target.shopLayout = Utils.castView(view, R.id.shop_layout, "field 'shopLayout'", LinearLayout.class);
    view2131755390 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.school_layout, "field 'schoolLayout' and method 'onClick'");
    target.schoolLayout = Utils.castView(view, R.id.school_layout, "field 'schoolLayout'", LinearLayout.class);
    view2131755391 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.youeryuan_layout, "field 'youeryuanLayout' and method 'onClick'");
    target.youeryuanLayout = Utils.castView(view, R.id.youeryuan_layout, "field 'youeryuanLayout'", LinearLayout.class);
    view2131755392 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.yiyuan_layout, "field 'yiyuanLayout' and method 'onClick'");
    target.yiyuanLayout = Utils.castView(view, R.id.yiyuan_layout, "field 'yiyuanLayout'", LinearLayout.class);
    view2131755393 = view;
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
    OldHousedetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vpVidio = null;
    target.tvToNum = null;
    target.tvAllNum = null;
    target.HuxingRecycler = null;
    target.imgShare = null;
    target.imgStart = null;
    target.tvSeeMore = null;
    target.loveRecycler = null;
    target.backImg = null;
    target.tvSuiyi = null;
    target.mScrollView = null;
    target.re_top_bg = null;
    target.tv_title = null;
    target.mapView = null;
    target.shopLayout = null;
    target.schoolLayout = null;
    target.youeryuanLayout = null;
    target.yiyuanLayout = null;

    view2131755354.setOnClickListener(null);
    view2131755354 = null;
    view2131755355.setOnClickListener(null);
    view2131755355 = null;
    view2131755388.setOnClickListener(null);
    view2131755388 = null;
    view2131755235.setOnClickListener(null);
    view2131755235 = null;
    view2131755390.setOnClickListener(null);
    view2131755390 = null;
    view2131755391.setOnClickListener(null);
    view2131755391 = null;
    view2131755392.setOnClickListener(null);
    view2131755392 = null;
    view2131755393.setOnClickListener(null);
    view2131755393 = null;
  }
}
