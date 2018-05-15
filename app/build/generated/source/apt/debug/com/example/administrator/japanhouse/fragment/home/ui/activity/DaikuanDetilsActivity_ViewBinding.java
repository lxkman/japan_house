// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DaikuanDetilsActivity_ViewBinding implements Unbinder {
  private DaikuanDetilsActivity target;

  @UiThread
  public DaikuanDetilsActivity_ViewBinding(DaikuanDetilsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DaikuanDetilsActivity_ViewBinding(DaikuanDetilsActivity target, View source) {
    this.target = target;

    target.btnCall = Utils.findRequiredViewAsType(source, R.id.btn_call, "field 'btnCall'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DaikuanDetilsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnCall = null;
  }
}
