// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import com.yyydjk.library.DropDownMenu;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TudiActivity_ViewBinding implements Unbinder {
  private TudiActivity target;

  private View view2131755235;

  private View view2131755300;

  private View view2131755301;

  private View view2131755299;

  @UiThread
  public TudiActivity_ViewBinding(TudiActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TudiActivity_ViewBinding(final TudiActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.back_img, "field 'backImg' and method 'onViewClicked'");
    target.backImg = Utils.castView(view, R.id.back_img, "field 'backImg'", ImageView.class);
    view2131755235 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_dingwei, "field 'imgDingwei' and method 'onViewClicked'");
    target.imgDingwei = Utils.castView(view, R.id.img_dingwei, "field 'imgDingwei'", ImageView.class);
    view2131755300 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_message, "field 'imgMessage' and method 'onViewClicked'");
    target.imgMessage = Utils.castView(view, R.id.img_message, "field 'imgMessage'", ImageView.class);
    view2131755301 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.dropDownMenu = Utils.findRequiredViewAsType(source, R.id.dropDownMenu, "field 'dropDownMenu'", DropDownMenu.class);
    view = Utils.findRequiredView(source, R.id.search_tv, "field 'searchTv' and method 'onViewClicked'");
    target.searchTv = Utils.castView(view, R.id.search_tv, "field 'searchTv'", TextView.class);
    view2131755299 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    TudiActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.backImg = null;
    target.imgDingwei = null;
    target.imgMessage = null;
    target.dropDownMenu = null;
    target.searchTv = null;

    view2131755235.setOnClickListener(null);
    view2131755235 = null;
    view2131755300.setOnClickListener(null);
    view2131755300 = null;
    view2131755301.setOnClickListener(null);
    view2131755301 = null;
    view2131755299.setOnClickListener(null);
    view2131755299 = null;
  }
}
