// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FreeApartmentActivity_ViewBinding implements Unbinder {
  private FreeApartmentActivity target;

  private View view2131755341;

  private View view2131755342;

  @UiThread
  public FreeApartmentActivity_ViewBinding(FreeApartmentActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FreeApartmentActivity_ViewBinding(final FreeApartmentActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.act_apartment_back, "field 'actApartmentBack' and method 'onViewClicked'");
    target.actApartmentBack = Utils.castView(view, R.id.act_apartment_back, "field 'actApartmentBack'", ImageView.class);
    view2131755341 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.act_apartment_msg, "field 'actApartmentMsg' and method 'onViewClicked'");
    target.actApartmentMsg = Utils.castView(view, R.id.act_apartment_msg, "field 'actApartmentMsg'", ImageView.class);
    view2131755342 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.act_apartment_recyclerView, "field 'mRecyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FreeApartmentActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.actApartmentBack = null;
    target.actApartmentMsg = null;
    target.mRecyclerView = null;

    view2131755341.setOnClickListener(null);
    view2131755341 = null;
    view2131755342.setOnClickListener(null);
    view2131755342 = null;
  }
}
