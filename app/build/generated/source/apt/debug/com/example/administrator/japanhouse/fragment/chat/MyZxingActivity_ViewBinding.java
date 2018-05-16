// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.chat;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyZxingActivity_ViewBinding implements Unbinder {
  private MyZxingActivity target;

  @UiThread
  public MyZxingActivity_ViewBinding(MyZxingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyZxingActivity_ViewBinding(MyZxingActivity target, View source) {
    this.target = target;

    target.mFlMyContainer = Utils.findRequiredViewAsType(source, R.id.fl_my_container, "field 'mFlMyContainer'", FrameLayout.class);
    target.mIvBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'mIvBack'", ImageView.class);
    target.mActivitySecond = Utils.findRequiredViewAsType(source, R.id.activity_second, "field 'mActivitySecond'", FrameLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyZxingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mFlMyContainer = null;
    target.mIvBack = null;
    target.mActivitySecond = null;
  }
}
