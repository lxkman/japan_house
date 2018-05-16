// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.map;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MapActivity_ViewBinding implements Unbinder {
  private MapActivity target;

  private View view2131755235;

  private View view2131755426;

  @UiThread
  public MapActivity_ViewBinding(MapActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MapActivity_ViewBinding(final MapActivity target, View source) {
    this.target = target;

    View view;
    target.view1 = Utils.findRequiredView(source, R.id.view1, "field 'view1'");
    target.view2 = Utils.findRequiredView(source, R.id.view2, "field 'view2'");
    target.view3 = Utils.findRequiredView(source, R.id.view3, "field 'view3'");
    target.view4 = Utils.findRequiredView(source, R.id.view4, "field 'view4'");
    view = Utils.findRequiredView(source, R.id.back_img, "field 'backImg' and method 'onClick'");
    target.backImg = Utils.castView(view, R.id.back_img, "field 'backImg'", ImageView.class);
    view2131755235 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.message_char, "field 'messageChar' and method 'onClick'");
    target.messageChar = Utils.castView(view, R.id.message_char, "field 'messageChar'", ImageView.class);
    view2131755426 = view;
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
    MapActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.view1 = null;
    target.view2 = null;
    target.view3 = null;
    target.view4 = null;
    target.backImg = null;
    target.messageChar = null;

    view2131755235.setOnClickListener(null);
    view2131755235 = null;
    view2131755426.setOnClickListener(null);
    view2131755426 = null;
  }
}
