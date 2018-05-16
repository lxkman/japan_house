// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.view.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineFragment_ViewBinding implements Unbinder {
  private MineFragment target;

  private View view2131755755;

  private View view2131755756;

  private View view2131755441;

  private View view2131755723;

  private View view2131755744;

  private View view2131755740;

  private View view2131755741;

  private View view2131755743;

  private View view2131755745;

  private View view2131755739;

  private View view2131755746;

  private View view2131755754;

  private View view2131755735;

  private View view2131755737;

  private View view2131755752;

  private View view2131755748;

  private View view2131755749;

  private View view2131755750;

  private View view2131755751;

  private View view2131755726;

  private View view2131755728;

  private View view2131755731;

  private View view2131755733;

  @UiThread
  public MineFragment_ViewBinding(final MineFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_msg, "field 'ivMsg' and method 'onClick'");
    target.ivMsg = Utils.castView(view, R.id.iv_msg, "field 'ivMsg'", ImageView.class);
    view2131755755 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_setting, "field 'ivSetting' and method 'onClick'");
    target.ivSetting = Utils.castView(view, R.id.iv_setting, "field 'ivSetting'", ImageView.class);
    view2131755756 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_head, "field 'ivHead' and method 'onClick'");
    target.ivHead = Utils.castView(view, R.id.iv_head, "field 'ivHead'", CircleImageView.class);
    view2131755441 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_name, "field 'tvName' and method 'onClick'");
    target.tvName = Utils.castView(view, R.id.tv_name, "field 'tvName'", TextView.class);
    view2131755723 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvPlatformTime = Utils.findRequiredViewAsType(source, R.id.tv_platform_time, "field 'tvPlatformTime'", TextView.class);
    target.tvCollectCount = Utils.findRequiredViewAsType(source, R.id.tv_collect_count, "field 'tvCollectCount'", TextView.class);
    target.tvSubscriptionCount = Utils.findRequiredViewAsType(source, R.id.tv_subscription_count, "field 'tvSubscriptionCount'", TextView.class);
    target.tvContactsCount = Utils.findRequiredViewAsType(source, R.id.tv_contacts_count, "field 'tvContactsCount'", TextView.class);
    target.tvHistroyCount = Utils.findRequiredViewAsType(source, R.id.tv_histroy_count, "field 'tvHistroyCount'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_myhouse_price, "field 'tvMyhousePrice' and method 'onClick'");
    target.tvMyhousePrice = Utils.castView(view, R.id.tv_myhouse_price, "field 'tvMyhousePrice'", TextView.class);
    view2131755744 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_myorder, "field 'tvMyorder' and method 'onClick'");
    target.tvMyorder = Utils.castView(view, R.id.tv_myorder, "field 'tvMyorder'", TextView.class);
    view2131755740 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_sellinghouse, "field 'tvSellinghouse' and method 'onClick'");
    target.tvSellinghouse = Utils.castView(view, R.id.tv_sellinghouse, "field 'tvSellinghouse'", TextView.class);
    view2131755741 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_calculator, "field 'tvCalculator' and method 'onClick'");
    target.tvCalculator = Utils.castView(view, R.id.tv_calculator, "field 'tvCalculator'", TextView.class);
    view2131755743 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_myask, "field 'tvMyask' and method 'onClick'");
    target.tvMyask = Utils.castView(view, R.id.tv_myask, "field 'tvMyask'", TextView.class);
    view2131755745 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_mysignup, "field 'tvMysignup' and method 'onClick'");
    target.tvMysignup = Utils.castView(view, R.id.tv_mysignup, "field 'tvMysignup'", TextView.class);
    view2131755739 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_feedback, "field 'tvFeedback' and method 'onClick'");
    target.tvFeedback = Utils.castView(view, R.id.tv_feedback, "field 'tvFeedback'", TextView.class);
    view2131755746 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.recyclerFoot = Utils.findRequiredViewAsType(source, R.id.recycler_foot, "field 'recyclerFoot'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.tv_qustion, "field 'tvQustion' and method 'onClick'");
    target.tvQustion = Utils.castView(view, R.id.tv_qustion, "field 'tvQustion'", TextView.class);
    view2131755754 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rel_lianxiren_layout, "field 'relLianxirenLayout' and method 'onClick'");
    target.relLianxirenLayout = Utils.castView(view, R.id.rel_lianxiren_layout, "field 'relLianxirenLayout'", RelativeLayout.class);
    view2131755735 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rel_lishi_layout, "field 'relLishiLayout' and method 'onClick'");
    target.relLishiLayout = Utils.castView(view, R.id.rel_lishi_layout, "field 'relLishiLayout'", LinearLayout.class);
    view2131755737 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.nestScroll = Utils.findRequiredViewAsType(source, R.id.nestScroll, "field 'nestScroll'", NestedScrollView.class);
    target.llZh = Utils.findRequiredViewAsType(source, R.id.ll_zh, "field 'llZh'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_wenjuan, "field 'tvWenjuan' and method 'onClick'");
    target.tvWenjuan = Utils.castView(view, R.id.tv_wenjuan, "field 'tvWenjuan'", TextView.class);
    view2131755752 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.llJa = Utils.findRequiredViewAsType(source, R.id.ll_ja, "field 'llJa'", LinearLayout.class);
    target.reTopBg = Utils.findRequiredViewAsType(source, R.id.re_top_bg, "field 'reTopBg'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_calculator1, "field 'tvCalculator1' and method 'onClick'");
    target.tvCalculator1 = Utils.castView(view, R.id.tv_calculator1, "field 'tvCalculator1'", TextView.class);
    view2131755748 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_myhouse_price1, "field 'tvMyhousePrice1' and method 'onClick'");
    target.tvMyhousePrice1 = Utils.castView(view, R.id.tv_myhouse_price1, "field 'tvMyhousePrice1'", TextView.class);
    view2131755749 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_myask1, "field 'tvMyask1' and method 'onClick'");
    target.tvMyask1 = Utils.castView(view, R.id.tv_myask1, "field 'tvMyask1'", TextView.class);
    view2131755750 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_feedback1, "field 'tvFeedback1' and method 'onClick'");
    target.tvFeedback1 = Utils.castView(view, R.id.tv_feedback1, "field 'tvFeedback1'", TextView.class);
    view2131755751 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.fl_lxk = Utils.findRequiredViewAsType(source, R.id.fl_lxk, "field 'fl_lxk'", FrameLayout.class);
    target.fl_lxk2 = Utils.findRequiredViewAsType(source, R.id.fl_lxk2, "field 'fl_lxk2'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.ll_collect, "field 'llCollect' and method 'onClick'");
    target.llCollect = Utils.castView(view, R.id.ll_collect, "field 'llCollect'", LinearLayout.class);
    view2131755726 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_dingyue, "field 'llDingyue' and method 'onClick'");
    target.llDingyue = Utils.castView(view, R.id.ll_dingyue, "field 'llDingyue'", LinearLayout.class);
    view2131755728 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvCollectCount2 = Utils.findRequiredViewAsType(source, R.id.tv_collect_count2, "field 'tvCollectCount2'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_collect2, "field 'llCollect2' and method 'onClick'");
    target.llCollect2 = Utils.castView(view, R.id.ll_collect2, "field 'llCollect2'", LinearLayout.class);
    view2131755731 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvSubscriptionCount2 = Utils.findRequiredViewAsType(source, R.id.tv_subscription_count2, "field 'tvSubscriptionCount2'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_dingyue2, "field 'llDingyue2' and method 'onClick'");
    target.llDingyue2 = Utils.castView(view, R.id.ll_dingyue2, "field 'llDingyue2'", LinearLayout.class);
    view2131755733 = view;
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
    MineFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivMsg = null;
    target.ivSetting = null;
    target.ivHead = null;
    target.tvName = null;
    target.tvPlatformTime = null;
    target.tvCollectCount = null;
    target.tvSubscriptionCount = null;
    target.tvContactsCount = null;
    target.tvHistroyCount = null;
    target.tvMyhousePrice = null;
    target.tvMyorder = null;
    target.tvSellinghouse = null;
    target.tvCalculator = null;
    target.tvMyask = null;
    target.tvMysignup = null;
    target.tvFeedback = null;
    target.recyclerFoot = null;
    target.tvQustion = null;
    target.relLianxirenLayout = null;
    target.relLishiLayout = null;
    target.nestScroll = null;
    target.llZh = null;
    target.tvWenjuan = null;
    target.llJa = null;
    target.reTopBg = null;
    target.tvCalculator1 = null;
    target.tvMyhousePrice1 = null;
    target.tvMyask1 = null;
    target.tvFeedback1 = null;
    target.fl_lxk = null;
    target.fl_lxk2 = null;
    target.llCollect = null;
    target.llDingyue = null;
    target.tvCollectCount2 = null;
    target.llCollect2 = null;
    target.tvSubscriptionCount2 = null;
    target.llDingyue2 = null;

    view2131755755.setOnClickListener(null);
    view2131755755 = null;
    view2131755756.setOnClickListener(null);
    view2131755756 = null;
    view2131755441.setOnClickListener(null);
    view2131755441 = null;
    view2131755723.setOnClickListener(null);
    view2131755723 = null;
    view2131755744.setOnClickListener(null);
    view2131755744 = null;
    view2131755740.setOnClickListener(null);
    view2131755740 = null;
    view2131755741.setOnClickListener(null);
    view2131755741 = null;
    view2131755743.setOnClickListener(null);
    view2131755743 = null;
    view2131755745.setOnClickListener(null);
    view2131755745 = null;
    view2131755739.setOnClickListener(null);
    view2131755739 = null;
    view2131755746.setOnClickListener(null);
    view2131755746 = null;
    view2131755754.setOnClickListener(null);
    view2131755754 = null;
    view2131755735.setOnClickListener(null);
    view2131755735 = null;
    view2131755737.setOnClickListener(null);
    view2131755737 = null;
    view2131755752.setOnClickListener(null);
    view2131755752 = null;
    view2131755748.setOnClickListener(null);
    view2131755748 = null;
    view2131755749.setOnClickListener(null);
    view2131755749 = null;
    view2131755750.setOnClickListener(null);
    view2131755750 = null;
    view2131755751.setOnClickListener(null);
    view2131755751 = null;
    view2131755726.setOnClickListener(null);
    view2131755726 = null;
    view2131755728.setOnClickListener(null);
    view2131755728 = null;
    view2131755731.setOnClickListener(null);
    view2131755731 = null;
    view2131755733.setOnClickListener(null);
    view2131755733 = null;
  }
}
