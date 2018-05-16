// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingActivity_ViewBinding implements Unbinder {
  private SettingActivity target;

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target, View source) {
    this.target = target;

    target.backImg = Utils.findRequiredViewAsType(source, R.id.back_img, "field 'backImg'", ImageView.class);
    target.ivSwitch = Utils.findRequiredViewAsType(source, R.id.iv_switch, "field 'ivSwitch'", ImageView.class);
    target.llLanguage = Utils.findRequiredViewAsType(source, R.id.ll_language, "field 'llLanguage'", LinearLayout.class);
    target.llAbout = Utils.findRequiredViewAsType(source, R.id.ll_about, "field 'llAbout'", LinearLayout.class);
    target.llVersion = Utils.findRequiredViewAsType(source, R.id.ll_version, "field 'llVersion'", LinearLayout.class);
    target.btnLoginout = Utils.findRequiredViewAsType(source, R.id.btn_loginout, "field 'btnLoginout'", Button.class);
    target.activityZuiJin = Utils.findRequiredViewAsType(source, R.id.activity_zui_jin, "field 'activityZuiJin'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.backImg = null;
    target.ivSwitch = null;
    target.llLanguage = null;
    target.llAbout = null;
    target.llVersion = null;
    target.btnLoginout = null;
    target.activityZuiJin = null;
  }
}
