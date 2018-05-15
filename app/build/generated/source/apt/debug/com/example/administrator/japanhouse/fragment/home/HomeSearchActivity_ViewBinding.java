// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.view.FluidLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeSearchActivity_ViewBinding implements Unbinder {
  private HomeSearchActivity target;

  private View view2131755372;

  private View view2131755371;

  private View view2131755373;

  private View view2131755375;

  @UiThread
  public HomeSearchActivity_ViewBinding(HomeSearchActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomeSearchActivity_ViewBinding(final HomeSearchActivity target, View source) {
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
    target.searchEt = Utils.findRequiredViewAsType(source, R.id.search_et, "field 'searchEt'", EditText.class);
    view = Utils.findRequiredView(source, R.id.cancle_tv, "field 'cancleTv' and method 'onViewClicked'");
    target.cancleTv = Utils.castView(view, R.id.cancle_tv, "field 'cancleTv'", TextView.class);
    view2131755371 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.fluidlayout = Utils.findRequiredViewAsType(source, R.id.hotsearch_recycler, "field 'fluidlayout'", FluidLayout.class);
    target.historyRecycler = Utils.findRequiredViewAsType(source, R.id.history_recycler, "field 'historyRecycler'", RecyclerView.class);
    target.view_rl = Utils.findRequiredViewAsType(source, R.id.view_rl, "field 'view_rl'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.hot_refrash_iv, "field 'hotRefrashIv' and method 'onViewClicked'");
    target.hotRefrashIv = Utils.castView(view, R.id.hot_refrash_iv, "field 'hotRefrashIv'", ImageView.class);
    view2131755373 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.history_clear, "field 'historyClear' and method 'onViewClicked'");
    target.historyClear = Utils.castView(view, R.id.history_clear, "field 'historyClear'", ImageView.class);
    view2131755375 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeSearchActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.locationTv = null;
    target.searchEt = null;
    target.cancleTv = null;
    target.fluidlayout = null;
    target.historyRecycler = null;
    target.view_rl = null;
    target.hotRefrashIv = null;
    target.historyClear = null;

    view2131755372.setOnClickListener(null);
    view2131755372 = null;
    view2131755371.setOnClickListener(null);
    view2131755371 = null;
    view2131755373.setOnClickListener(null);
    view2131755373 = null;
    view2131755375.setOnClickListener(null);
    view2131755375 = null;
  }
}
