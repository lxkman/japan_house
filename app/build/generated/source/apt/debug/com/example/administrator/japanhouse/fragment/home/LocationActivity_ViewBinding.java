// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LocationActivity_ViewBinding implements Unbinder {
  private LocationActivity target;

  private View view2131755403;

  private View view2131755299;

  @UiThread
  public LocationActivity_ViewBinding(LocationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LocationActivity_ViewBinding(final LocationActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.item_title_back, "field 'itemTitleBack' and method 'onViewClicked'");
    target.itemTitleBack = Utils.castView(view, R.id.item_title_back, "field 'itemTitleBack'", ImageView.class);
    view2131755403 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.search_tv, "field 'searchTv' and method 'onViewClicked'");
    target.searchTv = Utils.castView(view, R.id.search_tv, "field 'searchTv'", EditText.class);
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
    LocationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.itemTitleBack = null;
    target.searchTv = null;

    view2131755403.setOnClickListener(null);
    view2131755403 = null;
    view2131755299.setOnClickListener(null);
    view2131755299 = null;
  }
}
