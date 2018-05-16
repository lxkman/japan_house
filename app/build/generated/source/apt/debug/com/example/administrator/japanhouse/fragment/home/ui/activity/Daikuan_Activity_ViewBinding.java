// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import com.liaoinstan.springview.widget.SpringView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Daikuan_Activity_ViewBinding implements Unbinder {
  private Daikuan_Activity target;

  @UiThread
  public Daikuan_Activity_ViewBinding(Daikuan_Activity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Daikuan_Activity_ViewBinding(Daikuan_Activity target, View source) {
    this.target = target;

    target.tvCall = Utils.findRequiredViewAsType(source, R.id.tv_call, "field 'tvCall'", TextView.class);
    target.kefu = Utils.findRequiredViewAsType(source, R.id.kefu, "field 'kefu'", ImageView.class);
    target.sp_view = Utils.findRequiredViewAsType(source, R.id.sp_view, "field 'sp_view'", SpringView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Daikuan_Activity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvCall = null;
    target.kefu = null;
    target.sp_view = null;
  }
}
