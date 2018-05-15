// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.mine;

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

public class LianxirenActivity_ViewBinding implements Unbinder {
  private LianxirenActivity target;

  private View view2131755235;

  private View view2131755386;

  @UiThread
  public LianxirenActivity_ViewBinding(LianxirenActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LianxirenActivity_ViewBinding(final LianxirenActivity target, View source) {
    this.target = target;

    View view;
    target.Mrecycler = Utils.findRequiredViewAsType(source, R.id.Mrecycler, "field 'Mrecycler'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.back_img, "field 'backImg' and method 'onClick'");
    target.backImg = Utils.castView(view, R.id.back_img, "field 'backImg'", ImageView.class);
    view2131755235 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_add_people, "field 'tvAddPeople' and method 'onClick'");
    target.tvAddPeople = Utils.castView(view, R.id.tv_add_people, "field 'tvAddPeople'", TextView.class);
    view2131755386 = view;
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
    LianxirenActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.Mrecycler = null;
    target.backImg = null;
    target.tvAddPeople = null;

    view2131755235.setOnClickListener(null);
    view2131755235 = null;
    view2131755386.setOnClickListener(null);
    view2131755386 = null;
  }
}
