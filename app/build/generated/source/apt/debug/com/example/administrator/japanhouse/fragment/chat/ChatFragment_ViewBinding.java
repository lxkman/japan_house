// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.chat;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChatFragment_ViewBinding implements Unbinder {
  private ChatFragment target;

  private View view2131755758;

  private View view2131755759;

  @UiThread
  public ChatFragment_ViewBinding(final ChatFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.img_search, "field 'imgSearch' and method 'onClick'");
    target.imgSearch = Utils.castView(view, R.id.img_search, "field 'imgSearch'", ImageView.class);
    view2131755758 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_dialog, "field 'imgDialog' and method 'onClick'");
    target.imgDialog = Utils.castView(view, R.id.img_dialog, "field 'imgDialog'", ImageView.class);
    view2131755759 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mrecycler = Utils.findRequiredViewAsType(source, R.id.mrecycler, "field 'mrecycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChatFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imgSearch = null;
    target.imgDialog = null;
    target.mrecycler = null;

    view2131755758.setOnClickListener(null);
    view2131755758 = null;
    view2131755759.setOnClickListener(null);
    view2131755759 = null;
  }
}
