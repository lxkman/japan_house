// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.view.FluidLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SydcSearchActivity_ViewBinding implements Unbinder {
  private SydcSearchActivity target;

  private View view2131755371;

  @UiThread
  public SydcSearchActivity_ViewBinding(SydcSearchActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SydcSearchActivity_ViewBinding(final SydcSearchActivity target, View source) {
    this.target = target;

    View view;
    target.searchEt = Utils.findRequiredViewAsType(source, R.id.search_et, "field 'searchEt'", EditText.class);
    view = Utils.findRequiredView(source, R.id.cancle_tv, "field 'cancleTv' and method 'onViewClicked'");
    target.cancleTv = Utils.castView(view, R.id.cancle_tv, "field 'cancleTv'", TextView.class);
    view2131755371 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.fluidlayout = Utils.findRequiredViewAsType(source, R.id.hotsearch_recycler, "field 'fluidlayout'", FluidLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SydcSearchActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.searchEt = null;
    target.cancleTv = null;
    target.fluidlayout = null;

    view2131755371.setOnClickListener(null);
    view2131755371 = null;
  }
}
