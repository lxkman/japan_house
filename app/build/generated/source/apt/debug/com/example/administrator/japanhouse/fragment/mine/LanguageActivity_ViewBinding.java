// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LanguageActivity_ViewBinding implements Unbinder {
  private LanguageActivity target;

  private View view2131755235;

  private View view2131755378;

  private View view2131755379;

  private View view2131755381;

  @UiThread
  public LanguageActivity_ViewBinding(LanguageActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LanguageActivity_ViewBinding(final LanguageActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.tv_baocun, "field 'tvBaocun' and method 'onClick'");
    target.tvBaocun = Utils.castView(view, R.id.tv_baocun, "field 'tvBaocun'", TextView.class);
    view2131755378 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.language_layout, "field 'languageLayout' and method 'onClick'");
    target.languageLayout = Utils.castView(view, R.id.language_layout, "field 'languageLayout'", LinearLayout.class);
    view2131755379 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.about_layout, "field 'aboutLayout' and method 'onClick'");
    target.aboutLayout = Utils.castView(view, R.id.about_layout, "field 'aboutLayout'", LinearLayout.class);
    view2131755381 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.imgCheckZhongwen = Utils.findRequiredViewAsType(source, R.id.img_check_zhongwen, "field 'imgCheckZhongwen'", ImageView.class);
    target.imgCheckRiwen = Utils.findRequiredViewAsType(source, R.id.img_check_riwen, "field 'imgCheckRiwen'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LanguageActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.backImg = null;
    target.tvBaocun = null;
    target.languageLayout = null;
    target.aboutLayout = null;
    target.imgCheckZhongwen = null;
    target.imgCheckRiwen = null;

    view2131755235.setOnClickListener(null);
    view2131755235 = null;
    view2131755378.setOnClickListener(null);
    view2131755378 = null;
    view2131755379.setOnClickListener(null);
    view2131755379 = null;
    view2131755381.setOnClickListener(null);
    view2131755381 = null;
  }
}
