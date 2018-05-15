// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import com.youth.banner.Banner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  private View view2131755372;

  private View view2131755299;

  private View view2131755721;

  private View view2131755692;

  private View view2131755693;

  private View view2131755694;

  private View view2131755695;

  private View view2131755704;

  private View view2131755708;

  private View view2131755711;

  private View view2131755714;

  private View view2131755718;

  private View view2131755699;

  @UiThread
  public HomeFragment_ViewBinding(final HomeFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.location_tv, "field 'locationTv' and method 'onViewClicked'");
    target.locationTv = Utils.castView(view, R.id.location_tv, "field 'locationTv'", TextView.class);
    view2131755372 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.search_tv, "field 'searchTv' and method 'onViewClicked'");
    target.searchTv = Utils.castView(view, R.id.search_tv, "field 'searchTv'", TextView.class);
    view2131755299 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.map_tv, "field 'mapTv' and method 'onViewClicked'");
    target.mapTv = Utils.castView(view, R.id.map_tv, "field 'mapTv'", TextView.class);
    view2131755721 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.jrdk_tv, "field 'jrdkTv' and method 'onViewClicked'");
    target.jrdkTv = Utils.castView(view, R.id.jrdk_tv, "field 'jrdkTv'", TextView.class);
    view2131755692 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.gfbk_tv, "field 'gfbkTv' and method 'onViewClicked'");
    target.gfbkTv = Utils.castView(view, R.id.gfbk_tv, "field 'gfbkTv'", TextView.class);
    view2131755693 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.fcwd_tv, "field 'fcwdTv' and method 'onViewClicked'");
    target.fcwdTv = Utils.castView(view, R.id.fcwd_tv, "field 'fcwdTv'", TextView.class);
    view2131755694 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.znmf_tv, "field 'znmfTv' and method 'onViewClicked'");
    target.znmfTv = Utils.castView(view, R.id.znmf_tv, "field 'znmfTv'", TextView.class);
    view2131755695 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.viewpager = Utils.findRequiredViewAsType(source, R.id.viewpager, "field 'viewpager'", ViewPager.class);
    target.points = Utils.findRequiredViewAsType(source, R.id.points, "field 'points'", LinearLayout.class);
    target.tantanTv = Utils.findRequiredViewAsType(source, R.id.tantan_tv, "field 'tantanTv'", TextView.class);
    target.banner = Utils.findRequiredViewAsType(source, R.id.banner, "field 'banner'", Banner.class);
    view = Utils.findRequiredView(source, R.id.tjxf_more_tv, "field 'tjxfMoreTv' and method 'onViewClicked'");
    target.tjxfMoreTv = Utils.castView(view, R.id.tjxf_more_tv, "field 'tjxfMoreTv'", TextView.class);
    view2131755704 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tjxfRecycler = Utils.findRequiredViewAsType(source, R.id.tjxf_recycler, "field 'tjxfRecycler'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.tjesf_more_tv, "field 'tjesfMoreTv' and method 'onViewClicked'");
    target.tjesfMoreTv = Utils.castView(view, R.id.tjesf_more_tv, "field 'tjesfMoreTv'", TextView.class);
    view2131755708 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tjesfRecycler = Utils.findRequiredViewAsType(source, R.id.tjesf_recycler, "field 'tjesfRecycler'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.tjyxjjr_more_tv, "field 'tjyxjjrMoreTv' and method 'onViewClicked'");
    target.tjyxjjrMoreTv = Utils.castView(view, R.id.tjyxjjr_more_tv, "field 'tjyxjjrMoreTv'", TextView.class);
    view2131755711 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tjyxjjrRecycler = Utils.findRequiredViewAsType(source, R.id.tjyxjjr_recycler, "field 'tjyxjjrRecycler'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.tjzf_more_tv, "field 'tjzfMoreTv' and method 'onViewClicked'");
    target.tjzfMoreTv = Utils.castView(view, R.id.tjzf_more_tv, "field 'tjzfMoreTv'", TextView.class);
    view2131755714 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tjzfRecycler = Utils.findRequiredViewAsType(source, R.id.tjzf_recycler, "field 'tjzfRecycler'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.tjtd_more_tv, "field 'tjtdMoreTv' and method 'onViewClicked'");
    target.tjtdMoreTv = Utils.castView(view, R.id.tjtd_more_tv, "field 'tjtdMoreTv'", TextView.class);
    view2131755718 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tjtdRecycler = Utils.findRequiredViewAsType(source, R.id.tjtd_recycler, "field 'tjtdRecycler'", RecyclerView.class);
    target.cnxhRecycler = Utils.findRequiredViewAsType(source, R.id.cnxh_recycler, "field 'cnxhRecycler'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.view_011, "field 'view011' and method 'onViewClicked'");
    target.view011 = Utils.castView(view, R.id.view_011, "field 'view011'", LinearLayout.class);
    view2131755699 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.scrollView = Utils.findRequiredViewAsType(source, R.id.srcollview, "field 'scrollView'", NestedScrollView.class);
    target.reTopBg = Utils.findRequiredViewAsType(source, R.id.re_top_bg, "field 'reTopBg'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.locationTv = null;
    target.searchTv = null;
    target.mapTv = null;
    target.jrdkTv = null;
    target.gfbkTv = null;
    target.fcwdTv = null;
    target.znmfTv = null;
    target.viewpager = null;
    target.points = null;
    target.tantanTv = null;
    target.banner = null;
    target.tjxfMoreTv = null;
    target.tjxfRecycler = null;
    target.tjesfMoreTv = null;
    target.tjesfRecycler = null;
    target.tjyxjjrMoreTv = null;
    target.tjyxjjrRecycler = null;
    target.tjzfMoreTv = null;
    target.tjzfRecycler = null;
    target.tjtdMoreTv = null;
    target.tjtdRecycler = null;
    target.cnxhRecycler = null;
    target.view011 = null;
    target.scrollView = null;
    target.reTopBg = null;

    view2131755372.setOnClickListener(null);
    view2131755372 = null;
    view2131755299.setOnClickListener(null);
    view2131755299 = null;
    view2131755721.setOnClickListener(null);
    view2131755721 = null;
    view2131755692.setOnClickListener(null);
    view2131755692 = null;
    view2131755693.setOnClickListener(null);
    view2131755693 = null;
    view2131755694.setOnClickListener(null);
    view2131755694 = null;
    view2131755695.setOnClickListener(null);
    view2131755695 = null;
    view2131755704.setOnClickListener(null);
    view2131755704 = null;
    view2131755708.setOnClickListener(null);
    view2131755708 = null;
    view2131755711.setOnClickListener(null);
    view2131755711 = null;
    view2131755714.setOnClickListener(null);
    view2131755714 = null;
    view2131755718.setOnClickListener(null);
    view2131755718 = null;
    view2131755699.setOnClickListener(null);
    view2131755699 = null;
  }
}
