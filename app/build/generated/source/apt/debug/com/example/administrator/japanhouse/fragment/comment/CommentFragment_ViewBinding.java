// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.comment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.view.NoCacheViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CommentFragment_ViewBinding implements Unbinder {
  private CommentFragment target;

  @UiThread
  public CommentFragment_ViewBinding(CommentFragment target, View source) {
    this.target = target;

    target.allErshoufang = Utils.findRequiredViewAsType(source, R.id.all_ershoufang, "field 'allErshoufang'", RadioButton.class);
    target.rbZufang = Utils.findRequiredViewAsType(source, R.id.rb_zufang, "field 'rbZufang'", RadioButton.class);
    target.rbXinfang = Utils.findRequiredViewAsType(source, R.id.rb_xinfang, "field 'rbXinfang'", RadioButton.class);
    target.rbTudi = Utils.findRequiredViewAsType(source, R.id.rb_tudi, "field 'rbTudi'", RadioButton.class);
    target.rgLook = Utils.findRequiredViewAsType(source, R.id.rg_look, "field 'rgLook'", RadioGroup.class);
    target.viewErshoufang = Utils.findRequiredView(source, R.id.view_ershoufang, "field 'viewErshoufang'");
    target.viewZufang = Utils.findRequiredView(source, R.id.view_zufang, "field 'viewZufang'");
    target.viewXinfang = Utils.findRequiredView(source, R.id.view_xinfang, "field 'viewXinfang'");
    target.viewTudi = Utils.findRequiredView(source, R.id.view_tudi, "field 'viewTudi'");
    target.vpLook = Utils.findRequiredViewAsType(source, R.id.vp_look, "field 'vpLook'", NoCacheViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CommentFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.allErshoufang = null;
    target.rbZufang = null;
    target.rbXinfang = null;
    target.rbTudi = null;
    target.rgLook = null;
    target.viewErshoufang = null;
    target.viewZufang = null;
    target.viewXinfang = null;
    target.viewTudi = null;
    target.vpLook = null;
  }
}
