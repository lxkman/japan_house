// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.login;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ForgetPswActivity_ViewBinding implements Unbinder {
  private ForgetPswActivity target;

  private View view2131755235;

  private View view2131755254;

  private View view2131755257;

  private View view2131755251;

  @UiThread
  public ForgetPswActivity_ViewBinding(ForgetPswActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ForgetPswActivity_ViewBinding(final ForgetPswActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.back_img, "field 'backImg' and method 'onClick'");
    target.backImg = Utils.castView(view, R.id.back_img, "field 'backImg'", ImageView.class);
    view2131755235 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.edtPhone = Utils.findRequiredViewAsType(source, R.id.edt_phone, "field 'edtPhone'", EditText.class);
    target.edtYanzhengCode = Utils.findRequiredViewAsType(source, R.id.edt_yanzheng_code, "field 'edtYanzhengCode'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_get_code, "field 'tvGetCode' and method 'onClick'");
    target.tvGetCode = Utils.castView(view, R.id.tv_get_code, "field 'tvGetCode'", TextView.class);
    view2131755254 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.edtPass = Utils.findRequiredViewAsType(source, R.id.edt_pass, "field 'edtPass'", EditText.class);
    target.edtPassSure = Utils.findRequiredViewAsType(source, R.id.edt_pass_sure, "field 'edtPassSure'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_find_pass, "field 'btnFindPass' and method 'onClick'");
    target.btnFindPass = Utils.castView(view, R.id.btn_find_pass, "field 'btnFindPass'", Button.class);
    view2131755257 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.check_quyu, "field 'checkQuyu' and method 'onClick'");
    target.checkQuyu = Utils.castView(view, R.id.check_quyu, "field 'checkQuyu'", TextView.class);
    view2131755251 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.activityRegister = Utils.findRequiredViewAsType(source, R.id.activity_register, "field 'activityRegister'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ForgetPswActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.backImg = null;
    target.edtPhone = null;
    target.edtYanzhengCode = null;
    target.tvGetCode = null;
    target.edtPass = null;
    target.edtPassSure = null;
    target.btnFindPass = null;
    target.checkQuyu = null;
    target.activityRegister = null;

    view2131755235.setOnClickListener(null);
    view2131755235 = null;
    view2131755254.setOnClickListener(null);
    view2131755254 = null;
    view2131755257.setOnClickListener(null);
    view2131755257 = null;
    view2131755251.setOnClickListener(null);
    view2131755251 = null;
  }
}
