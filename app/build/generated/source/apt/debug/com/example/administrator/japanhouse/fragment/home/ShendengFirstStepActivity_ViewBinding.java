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

public class ShendengFirstStepActivity_ViewBinding implements Unbinder {
  private ShendengFirstStepActivity target;

  private View view2131755258;

  private View view2131755561;

  @UiThread
  public ShendengFirstStepActivity_ViewBinding(ShendengFirstStepActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShendengFirstStepActivity_ViewBinding(final ShendengFirstStepActivity target,
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
    target.yusuanRecycler = Utils.findRequiredViewAsType(source, R.id.yusuan_recycler, "field 'yusuanRecycler'", RecyclerView.class);
    target.huxingRecycler = Utils.findRequiredViewAsType(source, R.id.huxing_recycler, "field 'huxingRecycler'", RecyclerView.class);
    target.weizhiRecycler = Utils.findRequiredViewAsType(source, R.id.weizhi_recycler, "field 'weizhiRecycler'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.next_tv, "field 'nextTv' and method 'onViewClicked'");
    target.nextTv = Utils.castView(view, R.id.next_tv, "field 'nextTv'", TextView.class);
    view2131755561 = view;
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
    ShendengFirstStepActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleBackIv = null;
    target.titleContentTv = null;
    target.yusuanRecycler = null;
    target.huxingRecycler = null;
    target.weizhiRecycler = null;
    target.nextTv = null;

    view2131755258.setOnClickListener(null);
    view2131755258 = null;
    view2131755561.setOnClickListener(null);
    view2131755561 = null;
  }
}
