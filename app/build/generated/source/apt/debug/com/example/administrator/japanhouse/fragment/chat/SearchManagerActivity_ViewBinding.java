// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.chat;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchManagerActivity_ViewBinding implements Unbinder {
  private SearchManagerActivity target;

  private View view2131755235;

  private View view2131755544;

  @UiThread
  public SearchManagerActivity_ViewBinding(SearchManagerActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SearchManagerActivity_ViewBinding(final SearchManagerActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.tv_saoyisao, "field 'tvSaoyisao' and method 'onClick'");
    target.tvSaoyisao = Utils.castView(view, R.id.tv_saoyisao, "field 'tvSaoyisao'", TextView.class);
    view2131755544 = view;
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
    SearchManagerActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.backImg = null;
    target.tvSaoyisao = null;

    view2131755235.setOnClickListener(null);
    view2131755235 = null;
    view2131755544.setOnClickListener(null);
    view2131755544 = null;
  }
}
