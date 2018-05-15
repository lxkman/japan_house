// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.comment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.jzvd.JZVideoPlayerStandard;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VidioFragment_ViewBinding implements Unbinder {
  private VidioFragment target;

  @UiThread
  public VidioFragment_ViewBinding(VidioFragment target, View source) {
    this.target = target;

    target.videoplayer = Utils.findRequiredViewAsType(source, R.id.videoplayer, "field 'videoplayer'", JZVideoPlayerStandard.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VidioFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.videoplayer = null;
  }
}
