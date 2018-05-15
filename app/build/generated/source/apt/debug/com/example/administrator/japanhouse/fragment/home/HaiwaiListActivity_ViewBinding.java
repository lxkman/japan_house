// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import com.yyydjk.library.DropDownMenu;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HaiwaiListActivity_ViewBinding implements Unbinder {
  private HaiwaiListActivity target;

  private View view2131755235;

  private View view2131755301;

  @UiThread
  public HaiwaiListActivity_ViewBinding(HaiwaiListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HaiwaiListActivity_ViewBinding(final HaiwaiListActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.img_message, "field 'imgMessage' and method 'onClick'");
    target.imgMessage = Utils.castView(view, R.id.img_message, "field 'imgMessage'", ImageView.class);
    view2131755301 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.dropDownMenu = Utils.findRequiredViewAsType(source, R.id.dropDownMenu, "field 'dropDownMenu'", DropDownMenu.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HaiwaiListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.backImg = null;
    target.imgMessage = null;
    target.dropDownMenu = null;

    view2131755235.setOnClickListener(null);
    view2131755235 = null;
    view2131755301.setOnClickListener(null);
    view2131755301 = null;
  }
}
