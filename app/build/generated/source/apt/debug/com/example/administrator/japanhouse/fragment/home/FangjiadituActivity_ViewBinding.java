// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.baidu.mapapi.map.MapView;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FangjiadituActivity_ViewBinding implements Unbinder {
  private FangjiadituActivity target;

  private View view2131755339;

  private View view2131755340;

  private View view2131755235;

  @UiThread
  public FangjiadituActivity_ViewBinding(FangjiadituActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FangjiadituActivity_ViewBinding(final FangjiadituActivity target, View source) {
    this.target = target;

    View view;
    target.mapview = Utils.findRequiredViewAsType(source, R.id.mapview, "field 'mapview'", MapView.class);
    view = Utils.findRequiredView(source, R.id.btn_location, "field 'btnLocation' and method 'onClick'");
    target.btnLocation = Utils.castView(view, R.id.btn_location, "field 'btnLocation'", Button.class);
    view2131755339 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_pop, "field 'layoutPop' and method 'onClick'");
    target.layoutPop = Utils.castView(view, R.id.layout_pop, "field 'layoutPop'", LinearLayout.class);
    view2131755340 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.back_img, "field 'back_img' and method 'onClick'");
    target.back_img = Utils.castView(view, R.id.back_img, "field 'back_img'", ImageView.class);
    view2131755235 = view;
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
    FangjiadituActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mapview = null;
    target.btnLocation = null;
    target.layoutPop = null;
    target.back_img = null;

    view2131755339.setOnClickListener(null);
    view2131755339 = null;
    view2131755340.setOnClickListener(null);
    view2131755340 = null;
    view2131755235.setOnClickListener(null);
    view2131755235 = null;
  }
}
