// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WenJuanActivity_ViewBinding implements Unbinder {
  private WenJuanActivity target;

  @UiThread
  public WenJuanActivity_ViewBinding(WenJuanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WenJuanActivity_ViewBinding(WenJuanActivity target, View source) {
    this.target = target;

    target.backImg = Utils.findRequiredViewAsType(source, R.id.back_img, "field 'backImg'", ImageView.class);
    target.recyclerQuestion = Utils.findRequiredViewAsType(source, R.id.recycler_question, "field 'recyclerQuestion'", RecyclerView.class);
    target.activityMyData = Utils.findRequiredViewAsType(source, R.id.activity_my_data, "field 'activityMyData'", LinearLayout.class);
    target.tvSubmit = Utils.findRequiredViewAsType(source, R.id.tv_submit, "field 'tvSubmit'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WenJuanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.backImg = null;
    target.recyclerQuestion = null;
    target.activityMyData = null;
    target.tvSubmit = null;
  }
}
