// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangePhoneActivity_ViewBinding implements Unbinder {
  private ChangePhoneActivity target;

  @UiThread
  public ChangePhoneActivity_ViewBinding(ChangePhoneActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangePhoneActivity_ViewBinding(ChangePhoneActivity target, View source) {
    this.target = target;

    target.backImg = Utils.findRequiredViewAsType(source, R.id.back_img, "field 'backImg'", ImageView.class);
    target.edtPhone = Utils.findRequiredViewAsType(source, R.id.edt_phone, "field 'edtPhone'", EditText.class);
    target.edtCode = Utils.findRequiredViewAsType(source, R.id.edt_code, "field 'edtCode'", EditText.class);
    target.tvGetcode = Utils.findRequiredViewAsType(source, R.id.tv_getcode, "field 'tvGetcode'", TextView.class);
    target.btnNext = Utils.findRequiredViewAsType(source, R.id.btn_next, "field 'btnNext'", Button.class);
    target.activityZuiJin = Utils.findRequiredViewAsType(source, R.id.activity_zui_jin, "field 'activityZuiJin'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChangePhoneActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.backImg = null;
    target.edtPhone = null;
    target.edtCode = null;
    target.tvGetcode = null;
    target.btnNext = null;
    target.activityZuiJin = null;
  }
}
