// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MsgDetailActivity_ViewBinding implements Unbinder {
  private MsgDetailActivity target;

  @UiThread
  public MsgDetailActivity_ViewBinding(MsgDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MsgDetailActivity_ViewBinding(MsgDetailActivity target, View source) {
    this.target = target;

    target.backImg = Utils.findRequiredViewAsType(source, R.id.back_img, "field 'backImg'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MsgDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.backImg = null;
  }
}
