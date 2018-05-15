// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.flContent = Utils.findRequiredViewAsType(source, R.id.frag_content, "field 'flContent'", FrameLayout.class);
    target.rbHome = Utils.findRequiredViewAsType(source, R.id.rb_home, "field 'rbHome'", RadioButton.class);
    target.rbChat = Utils.findRequiredViewAsType(source, R.id.rb_chat, "field 'rbChat'", RadioButton.class);
    target.rbComment = Utils.findRequiredViewAsType(source, R.id.rb_comment, "field 'rbComment'", RadioButton.class);
    target.rbMine = Utils.findRequiredViewAsType(source, R.id.rb_mine, "field 'rbMine'", RadioButton.class);
    target.rgp = Utils.findRequiredViewAsType(source, R.id.rgp, "field 'rgp'", RadioGroup.class);
    target.activityMain = Utils.findRequiredViewAsType(source, R.id.activity_main, "field 'activityMain'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.flContent = null;
    target.rbHome = null;
    target.rbChat = null;
    target.rbComment = null;
    target.rbMine = null;
    target.rgp = null;
    target.activityMain = null;
  }
}
