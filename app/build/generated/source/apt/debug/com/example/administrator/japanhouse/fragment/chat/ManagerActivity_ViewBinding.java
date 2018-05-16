// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.chat;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.view.NoCacheViewPager;
import com.example.administrator.japanhouse.view.RatingBarView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ManagerActivity_ViewBinding implements Unbinder {
  private ManagerActivity target;

  private View view2131755235;

  private View view2131755387;

  private View view2131755420;

  private View view2131755354;

  @UiThread
  public ManagerActivity_ViewBinding(ManagerActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ManagerActivity_ViewBinding(final ManagerActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.back_img, "field 'backImg' and method 'onClick'");
    target.backImg = Utils.castView(view, R.id.back_img, "field 'backImg'", ImageView.class);
    view2131755235 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.ratingBarView = Utils.findRequiredViewAsType(source, R.id.ratingBarView, "field 'ratingBarView'", RatingBarView.class);
    view = Utils.findRequiredView(source, R.id.Mrecycler, "field 'mrecycler' and method 'onClick'");
    target.mrecycler = Utils.castView(view, R.id.Mrecycler, "field 'mrecycler'", RecyclerView.class);
    view2131755387 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
    target.allShop = Utils.findRequiredViewAsType(source, R.id.all_shop, "field 'allShop'", RadioButton.class);
    target.rbPifu = Utils.findRequiredViewAsType(source, R.id.rb_pifu, "field 'rbPifu'", RadioButton.class);
    target.rgLook = Utils.findRequiredViewAsType(source, R.id.rg_look, "field 'rgLook'", RadioGroup.class);
    target.viewShoufang = Utils.findRequiredView(source, R.id.view_shoufang, "field 'viewShoufang'");
    target.viewZufang = Utils.findRequiredView(source, R.id.view_zufang, "field 'viewZufang'");
    target.vpLook = Utils.findRequiredViewAsType(source, R.id.vp_look, "field 'vpLook'", NoCacheViewPager.class);
    view = Utils.findRequiredView(source, R.id.bg_layout, "method 'onClick'");
    view2131755420 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_share, "method 'onClick'");
    view2131755354 = view;
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
    ManagerActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.backImg = null;
    target.ratingBarView = null;
    target.mrecycler = null;
    target.allShop = null;
    target.rbPifu = null;
    target.rgLook = null;
    target.viewShoufang = null;
    target.viewZufang = null;
    target.vpLook = null;

    view2131755235.setOnClickListener(null);
    view2131755235 = null;
    view2131755387.setOnClickListener(null);
    view2131755387 = null;
    view2131755420.setOnClickListener(null);
    view2131755420 = null;
    view2131755354.setOnClickListener(null);
    view2131755354 = null;
  }
}
