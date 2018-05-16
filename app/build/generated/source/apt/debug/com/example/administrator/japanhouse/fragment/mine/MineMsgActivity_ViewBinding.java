// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineMsgActivity_ViewBinding implements Unbinder {
  private MineMsgActivity target;

  @UiThread
  public MineMsgActivity_ViewBinding(MineMsgActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MineMsgActivity_ViewBinding(MineMsgActivity target, View source) {
    this.target = target;

    target.backImg = Utils.findRequiredViewAsType(source, R.id.back_img, "field 'backImg'", ImageView.class);
    target.recyclerMsg = Utils.findRequiredViewAsType(source, R.id.recycler_msg, "field 'recyclerMsg'", RecyclerView.class);
    target.activityZuiJin = Utils.findRequiredViewAsType(source, R.id.activity_zui_jin, "field 'activityZuiJin'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MineMsgActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.backImg = null;
    target.recyclerMsg = null;
    target.activityZuiJin = null;
  }
}
