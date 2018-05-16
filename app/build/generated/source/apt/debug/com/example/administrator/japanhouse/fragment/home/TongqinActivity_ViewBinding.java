// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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

public class TongqinActivity_ViewBinding implements Unbinder {
  private TongqinActivity target;

  private View view2131755258;

  private View view2131755569;

  private View view2131755571;

  private View view2131755572;

  @UiThread
  public TongqinActivity_ViewBinding(TongqinActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TongqinActivity_ViewBinding(final TongqinActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.title_back_iv, "field 'titleBackIv' and method 'onViewClicked'");
    target.titleBackIv = Utils.castView(view, R.id.title_back_iv, "field 'titleBackIv'", ImageView.class);
    view2131755258 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.titleContentTv = Utils.findRequiredViewAsType(source, R.id.title_content_tv, "field 'titleContentTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.location_rl, "field 'locationRl' and method 'onViewClicked'");
    target.locationRl = Utils.castView(view, R.id.location_rl, "field 'locationRl'", RelativeLayout.class);
    view2131755569 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.typeRecycler = Utils.findRequiredViewAsType(source, R.id.type_recycler, "field 'typeRecycler'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.time_tv, "field 'timeTv' and method 'onViewClicked'");
    target.timeTv = Utils.castView(view, R.id.time_tv, "field 'timeTv'", TextView.class);
    view2131755571 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.start_find_tv, "field 'startFindTv' and method 'onViewClicked'");
    target.startFindTv = Utils.castView(view, R.id.start_find_tv, "field 'startFindTv'", TextView.class);
    view2131755572 = view;
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
    TongqinActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleBackIv = null;
    target.titleContentTv = null;
    target.locationRl = null;
    target.typeRecycler = null;
    target.timeTv = null;
    target.startFindTv = null;

    view2131755258.setOnClickListener(null);
    view2131755258 = null;
    view2131755569.setOnClickListener(null);
    view2131755569 = null;
    view2131755571.setOnClickListener(null);
    view2131755571 = null;
    view2131755572.setOnClickListener(null);
    view2131755572 = null;
  }
}
