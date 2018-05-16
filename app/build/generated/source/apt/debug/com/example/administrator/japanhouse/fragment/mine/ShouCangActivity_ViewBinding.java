// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShouCangActivity_ViewBinding implements Unbinder {
  private ShouCangActivity target;

  private View view2131755235;

  @UiThread
  public ShouCangActivity_ViewBinding(ShouCangActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShouCangActivity_ViewBinding(final ShouCangActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.back_img, "field 'backImg' and method 'onClick'");
    target.backImg = Utils.castView(view, R.id.back_img, "field 'backImg'", ImageView.class);
    view2131755235 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
    target.mrecycler = Utils.findRequiredViewAsType(source, R.id.mrecycler, "field 'mrecycler'", SwipeMenuRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShouCangActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.backImg = null;
    target.mrecycler = null;

    view2131755235.setOnClickListener(null);
    view2131755235 = null;
  }
}
