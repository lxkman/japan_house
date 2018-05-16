// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AboutUsActivity_ViewBinding implements Unbinder {
  private AboutUsActivity target;

  @UiThread
  public AboutUsActivity_ViewBinding(AboutUsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AboutUsActivity_ViewBinding(AboutUsActivity target, View source) {
    this.target = target;

    target.backImg = Utils.findRequiredViewAsType(source, R.id.back_img, "field 'backImg'", ImageView.class);
    target.llGomarket = Utils.findRequiredViewAsType(source, R.id.ll_gomarket, "field 'llGomarket'", LinearLayout.class);
    target.llDes = Utils.findRequiredViewAsType(source, R.id.ll_des, "field 'llDes'", LinearLayout.class);
    target.activityZuiJin = Utils.findRequiredViewAsType(source, R.id.activity_zui_jin, "field 'activityZuiJin'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AboutUsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.backImg = null;
    target.llGomarket = null;
    target.llDes = null;
    target.activityZuiJin = null;
  }
}
