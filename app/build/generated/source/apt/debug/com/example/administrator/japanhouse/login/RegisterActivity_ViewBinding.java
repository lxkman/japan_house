// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.login;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterActivity_ViewBinding implements Unbinder {
  private RegisterActivity target;

  private View view2131755235;

  private View view2131755476;

  private View view2131755254;

  private View view2131755407;

  private View view2131755408;

  private View view2131755409;

  private View view2131755410;

  private View view2131755411;

  private View view2131755477;

  private View view2131755478;

  private View view2131755251;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(final RegisterActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.tv_login, "field 'tvLogin' and method 'onClick'");
    target.tvLogin = Utils.castView(view, R.id.tv_login, "field 'tvLogin'", TextView.class);
    view2131755476 = view;
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
    view = Utils.findRequiredView(source, R.id.btn_login, "field 'btnLogin' and method 'onClick'");
    target.btnLogin = Utils.castView(view, R.id.btn_login, "field 'btnLogin'", Button.class);
    view2131755407 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_weixin, "field 'imgWeixin' and method 'onClick'");
    target.imgWeixin = Utils.castView(view, R.id.img_weixin, "field 'imgWeixin'", ImageView.class);
    view2131755408 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_weibo, "field 'imgWeibo' and method 'onClick'");
    target.imgWeibo = Utils.castView(view, R.id.img_weibo, "field 'imgWeibo'", ImageView.class);
    view2131755409 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_qq, "field 'imgQq' and method 'onClick'");
    target.imgQq = Utils.castView(view, R.id.img_qq, "field 'imgQq'", ImageView.class);
    view2131755410 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_line, "field 'imgLine' and method 'onClick'");
    target.imgLine = Utils.castView(view, R.id.img_line, "field 'imgLine'", ImageView.class);
    view2131755411 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.people_xieyi, "field 'peopleXieyi' and method 'onClick'");
    target.peopleXieyi = Utils.castView(view, R.id.people_xieyi, "field 'peopleXieyi'", TextView.class);
    view2131755477 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.private_xieyi, "field 'privateXieyi' and method 'onClick'");
    target.privateXieyi = Utils.castView(view, R.id.private_xieyi, "field 'privateXieyi'", TextView.class);
    view2131755478 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.activityRegister = Utils.findRequiredViewAsType(source, R.id.activity_register, "field 'activityRegister'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.check_quyu, "field 'checkQuyu' and method 'onClick'");
    target.checkQuyu = Utils.castView(view, R.id.check_quyu, "field 'checkQuyu'", TextView.class);
    view2131755251 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.backImg = null;
    target.tvLogin = null;
    target.edtPhone = null;
    target.edtYanzhengCode = null;
    target.tvGetCode = null;
    target.edtPass = null;
    target.edtPassSure = null;
    target.btnLogin = null;
    target.imgWeixin = null;
    target.imgWeibo = null;
    target.imgQq = null;
    target.imgLine = null;
    target.peopleXieyi = null;
    target.privateXieyi = null;
    target.activityRegister = null;
    target.checkQuyu = null;

    view2131755235.setOnClickListener(null);
    view2131755235 = null;
    view2131755476.setOnClickListener(null);
    view2131755476 = null;
    view2131755254.setOnClickListener(null);
    view2131755254 = null;
    view2131755407.setOnClickListener(null);
    view2131755407 = null;
    view2131755408.setOnClickListener(null);
    view2131755408 = null;
    view2131755409.setOnClickListener(null);
    view2131755409 = null;
    view2131755410.setOnClickListener(null);
    view2131755410 = null;
    view2131755411.setOnClickListener(null);
    view2131755411 = null;
    view2131755477.setOnClickListener(null);
    view2131755477 = null;
    view2131755478.setOnClickListener(null);
    view2131755478 = null;
    view2131755251.setOnClickListener(null);
    view2131755251 = null;
  }
}
