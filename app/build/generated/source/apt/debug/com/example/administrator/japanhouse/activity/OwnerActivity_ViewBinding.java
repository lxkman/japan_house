// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OwnerActivity_ViewBinding implements Unbinder {
  private OwnerActivity target;

  private View view2131755454;

  private View view2131755455;

  private View view2131755456;

  private View view2131755457;

  private View view2131755458;

  @UiThread
  public OwnerActivity_ViewBinding(OwnerActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OwnerActivity_ViewBinding(final OwnerActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.act_owner_back, "field 'back' and method 'onViewClicked'");
    target.back = Utils.castView(view, R.id.act_owner_back, "field 'back'", ImageView.class);
    view2131755454 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.act_owner_message, "field 'ivMessage' and method 'onViewClicked'");
    target.ivMessage = Utils.castView(view, R.id.act_owner_message, "field 'ivMessage'", ImageView.class);
    view2131755455 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.act_owner_rental, "field 'tvRental' and method 'onViewClicked'");
    target.tvRental = Utils.castView(view, R.id.act_owner_rental, "field 'tvRental'", RelativeLayout.class);
    view2131755456 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.act_owner_wikipedia, "field 'tvWikipedia' and method 'onViewClicked'");
    target.tvWikipedia = Utils.castView(view, R.id.act_owner_wikipedia, "field 'tvWikipedia'", RelativeLayout.class);
    view2131755457 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.act_owner_prices, "field 'tvPrices' and method 'onViewClicked'");
    target.tvPrices = Utils.castView(view, R.id.act_owner_prices, "field 'tvPrices'", RelativeLayout.class);
    view2131755458 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.recyclerview = Utils.findRequiredViewAsType(source, R.id.act_owner_recyclerView, "field 'recyclerview'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OwnerActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.back = null;
    target.ivMessage = null;
    target.tvRental = null;
    target.tvWikipedia = null;
    target.tvPrices = null;
    target.recyclerview = null;

    view2131755454.setOnClickListener(null);
    view2131755454 = null;
    view2131755455.setOnClickListener(null);
    view2131755455 = null;
    view2131755456.setOnClickListener(null);
    view2131755456 = null;
    view2131755457.setOnClickListener(null);
    view2131755457 = null;
    view2131755458.setOnClickListener(null);
    view2131755458 = null;
  }
}
