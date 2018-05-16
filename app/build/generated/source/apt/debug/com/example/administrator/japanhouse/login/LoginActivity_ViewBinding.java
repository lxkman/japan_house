// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.login;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131755235;

  private View view2131755250;

  private View view2131755406;

  private View view2131755407;

  private View view2131755408;

  private View view2131755409;

  private View view2131755410;

  private View view2131755411;

  private View view2131755405;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.tv_register, "field 'tvRegister' and method 'onClick'");
    target.tvRegister = Utils.castView(view, R.id.tv_register, "field 'tvRegister'", TextView.class);
    view2131755250 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.edtPhone = Utils.findRequiredViewAsType(source, R.id.edt_phone, "field 'edtPhone'", EditText.class);
    target.edtPass = Utils.findRequiredViewAsType(source, R.id.edt_pass, "field 'edtPass'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_forget_pass, "field 'tvForgetPass' and method 'onClick'");
    target.tvForgetPass = Utils.castView(view, R.id.tv_forget_pass, "field 'tvForgetPass'", TextView.class);
    view2131755406 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
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
    view = Utils.findRequiredView(source, R.id.tv_show_pop, "field 'tvShowPop' and method 'onClick'");
    target.tvShowPop = Utils.castView(view, R.id.tv_show_pop, "field 'tvShowPop'", TextView.class);
    view2131755405 = view;
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
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.backImg = null;
    target.tvRegister = null;
    target.edtPhone = null;
    target.edtPass = null;
    target.tvForgetPass = null;
    target.btnLogin = null;
    target.imgWeixin = null;
    target.imgWeibo = null;
    target.imgQq = null;
    target.imgLine = null;
    target.tvShowPop = null;

    view2131755235.setOnClickListener(null);
    view2131755235 = null;
    view2131755250.setOnClickListener(null);
    view2131755250 = null;
    view2131755406.setOnClickListener(null);
    view2131755406 = null;
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
    view2131755405.setOnClickListener(null);
    view2131755405 = null;
  }
}
