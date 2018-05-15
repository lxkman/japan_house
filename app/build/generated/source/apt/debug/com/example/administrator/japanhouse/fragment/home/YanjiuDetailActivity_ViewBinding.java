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
import java.lang.IllegalStateException;
import java.lang.Override;

public class YanjiuDetailActivity_ViewBinding implements Unbinder {
  private YanjiuDetailActivity target;

  private View view2131755258;

  @UiThread
  public YanjiuDetailActivity_ViewBinding(YanjiuDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public YanjiuDetailActivity_ViewBinding(final YanjiuDetailActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.title_back_iv, "field 'titleBackIv' and method 'onViewClicked'");
    target.titleBackIv = Utils.castView(view, R.id.title_back_iv, "field 'titleBackIv'", ImageView.class);
    view2131755258 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.titleContentTv = Utils.findRequiredViewAsType(source, R.id.title_content_tv, "field 'titleContentTv'", TextView.class);
    target.titleTv = Utils.findRequiredViewAsType(source, R.id.title_tv, "field 'titleTv'", TextView.class);
    target.dateTv = Utils.findRequiredViewAsType(source, R.id.date_tv, "field 'dateTv'", TextView.class);
    target.contentTv = Utils.findRequiredViewAsType(source, R.id.content_tv, "field 'contentTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    YanjiuDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleBackIv = null;
    target.titleContentTv = null;
    target.titleTv = null;
    target.dateTv = null;
    target.contentTv = null;

    view2131755258.setOnClickListener(null);
    view2131755258 = null;
  }
}
