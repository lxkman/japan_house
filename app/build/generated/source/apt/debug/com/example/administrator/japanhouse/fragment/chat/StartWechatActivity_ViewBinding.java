// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.chat;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StartWechatActivity_ViewBinding implements Unbinder {
  private StartWechatActivity target;

  private View view2131755235;

  @UiThread
  public StartWechatActivity_ViewBinding(StartWechatActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public StartWechatActivity_ViewBinding(final StartWechatActivity target, View source) {
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
    target.Mrecycler = Utils.findRequiredViewAsType(source, R.id.Mrecycler, "field 'Mrecycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StartWechatActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.backImg = null;
    target.Mrecycler = null;

    view2131755235.setOnClickListener(null);
    view2131755235 = null;
  }
}
