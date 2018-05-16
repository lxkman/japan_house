// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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

public class ShendengSecondStepActivity_ViewBinding implements Unbinder {
  private ShendengSecondStepActivity target;

  private View view2131755258;

  private View view2131755566;

  @UiThread
  public ShendengSecondStepActivity_ViewBinding(ShendengSecondStepActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShendengSecondStepActivity_ViewBinding(final ShendengSecondStepActivity target,
      View source) {
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
    target.wantFluidlayout = Utils.findRequiredViewAsType(source, R.id.want_fluidlayout, "field 'wantFluidlayout'", FluidLayout.class);
    target.notwantFluidlayout = Utils.findRequiredViewAsType(source, R.id.notwant_fluidlayout, "field 'notwantFluidlayout'", FluidLayout.class);
    view = Utils.findRequiredView(source, R.id.finish_tv, "field 'finishTv' and method 'onViewClicked'");
    target.finishTv = Utils.castView(view, R.id.finish_tv, "field 'finishTv'", TextView.class);
    view2131755566 = view;
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
    ShendengSecondStepActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleBackIv = null;
    target.titleContentTv = null;
    target.wantFluidlayout = null;
    target.notwantFluidlayout = null;
    target.finishTv = null;

    view2131755258.setOnClickListener(null);
    view2131755258 = null;
    view2131755566.setOnClickListener(null);
    view2131755566 = null;
  }
}
