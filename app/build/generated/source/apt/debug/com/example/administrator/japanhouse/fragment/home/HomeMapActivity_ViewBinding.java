// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.view.NoScrollViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeMapActivity_ViewBinding implements Unbinder {
  private HomeMapActivity target;

  private View view2131755258;

  private View view2131755360;

  private View view2131755368;

  @UiThread
  public HomeMapActivity_ViewBinding(HomeMapActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomeMapActivity_ViewBinding(final HomeMapActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.title_back_iv, "field 'titleBackIv' and method 'onViewClicked'");
    target.titleBackIv = Utils.castView(view, R.id.title_back_iv, "field 'titleBackIv'", ImageView.class);
    view2131755258 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.allErshoufang = Utils.findRequiredViewAsType(source, R.id.all_ershoufang, "field 'allErshoufang'", RadioButton.class);
    target.rbZufang = Utils.findRequiredViewAsType(source, R.id.rb_zufang, "field 'rbZufang'", RadioButton.class);
    target.rbXinfang = Utils.findRequiredViewAsType(source, R.id.rb_xinfang, "field 'rbXinfang'", RadioButton.class);
    target.rgLook = Utils.findRequiredViewAsType(source, R.id.rg_look, "field 'rgLook'", RadioGroup.class);
    target.viewErshoufang = Utils.findRequiredView(source, R.id.view_ershoufang, "field 'viewErshoufang'");
    target.viewZufang = Utils.findRequiredView(source, R.id.view_zufang, "field 'viewZufang'");
    target.viewXinfang = Utils.findRequiredView(source, R.id.view_xinfang, "field 'viewXinfang'");
    view = Utils.findRequiredView(source, R.id.title_search_iv, "field 'titleSearchIv' and method 'onViewClicked'");
    target.titleSearchIv = Utils.castView(view, R.id.title_search_iv, "field 'titleSearchIv'", ImageView.class);
    view2131755360 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.title_message_iv, "field 'titleMessageIv' and method 'onViewClicked'");
    target.titleMessageIv = Utils.castView(view, R.id.title_message_iv, "field 'titleMessageIv'", ImageView.class);
    view2131755368 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.vpLook = Utils.findRequiredViewAsType(source, R.id.vp_look, "field 'vpLook'", NoScrollViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeMapActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleBackIv = null;
    target.allErshoufang = null;
    target.rbZufang = null;
    target.rbXinfang = null;
    target.rgLook = null;
    target.viewErshoufang = null;
    target.viewZufang = null;
    target.viewXinfang = null;
    target.titleSearchIv = null;
    target.titleMessageIv = null;
    target.vpLook = null;

    view2131755258.setOnClickListener(null);
    view2131755258 = null;
    view2131755360.setOnClickListener(null);
    view2131755360 = null;
    view2131755368.setOnClickListener(null);
    view2131755368 = null;
  }
}
