// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WoshiyezhuActivity_ViewBinding implements Unbinder {
  private WoshiyezhuActivity target;

  private View view2131755258;

  private View view2131755368;

  private View view2131755588;

  private View view2131755589;

  private View view2131755590;

  @UiThread
  public WoshiyezhuActivity_ViewBinding(WoshiyezhuActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WoshiyezhuActivity_ViewBinding(final WoshiyezhuActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.title_message_iv, "field 'titleMessageIv' and method 'onViewClicked'");
    target.titleMessageIv = Utils.castView(view, R.id.title_message_iv, "field 'titleMessageIv'", ImageView.class);
    view2131755368 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.zushou_tv, "field 'zushouTv' and method 'onViewClicked'");
    target.zushouTv = Utils.castView(view, R.id.zushou_tv, "field 'zushouTv'", TextView.class);
    view2131755588 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.fangjiaditu_tv, "field 'fangjiadituTv' and method 'onViewClicked'");
    target.fangjiadituTv = Utils.castView(view, R.id.fangjiaditu_tv, "field 'fangjiadituTv'", TextView.class);
    view2131755589 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.yezhubaike_tv, "field 'yezhubaikeTv' and method 'onViewClicked'");
    target.yezhubaikeTv = Utils.castView(view, R.id.yezhubaike_tv, "field 'yezhubaikeTv'", TextView.class);
    view2131755590 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WoshiyezhuActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleBackIv = null;
    target.titleMessageIv = null;
    target.zushouTv = null;
    target.fangjiadituTv = null;
    target.yezhubaikeTv = null;
    target.recyclerView = null;

    view2131755258.setOnClickListener(null);
    view2131755258 = null;
    view2131755368.setOnClickListener(null);
    view2131755368 = null;
    view2131755588.setOnClickListener(null);
    view2131755588 = null;
    view2131755589.setOnClickListener(null);
    view2131755589 = null;
    view2131755590.setOnClickListener(null);
    view2131755590 = null;
  }
}
