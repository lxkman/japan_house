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
import com.example.administrator.japanhouse.view.FluidLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShendengListActivity_ViewBinding implements Unbinder {
  private ShendengListActivity target;

  private View view2131755258;

  @UiThread
  public ShendengListActivity_ViewBinding(ShendengListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShendengListActivity_ViewBinding(final ShendengListActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.title_back_iv, "field 'titleBackIv' and method 'onViewClicked'");
    target.titleBackIv = Utils.castView(view, R.id.title_back_iv, "field 'titleBackIv'", ImageView.class);
    view2131755258 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.titleContentTv = Utils.findRequiredViewAsType(source, R.id.title_content_tv, "field 'titleContentTv'", TextView.class);
    target.resultFluidlayout = Utils.findRequiredViewAsType(source, R.id.result_fluidlayout, "field 'resultFluidlayout'", FluidLayout.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShendengListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleBackIv = null;
    target.titleContentTv = null;
    target.resultFluidlayout = null;
    target.recyclerView = null;

    view2131755258.setOnClickListener(null);
    view2131755258 = null;
  }
}
