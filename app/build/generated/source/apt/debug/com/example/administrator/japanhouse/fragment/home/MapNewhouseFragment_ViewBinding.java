// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import com.yyydjk.library.DropDownMenu;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MapNewhouseFragment_ViewBinding implements Unbinder {
  private MapNewhouseFragment target;

  @UiThread
  public MapNewhouseFragment_ViewBinding(MapNewhouseFragment target, View source) {
    this.target = target;

    target.dropDownMenu = Utils.findRequiredViewAsType(source, R.id.dropDownMenu, "field 'dropDownMenu'", DropDownMenu.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MapNewhouseFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.dropDownMenu = null;
  }
}
