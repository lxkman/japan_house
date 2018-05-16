// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BusinessDichanActivity_ViewBinding implements Unbinder {
  private BusinessDichanActivity target;

  private View view2131755258;

  private View view2131755260;

  @UiThread
  public BusinessDichanActivity_ViewBinding(BusinessDichanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BusinessDichanActivity_ViewBinding(final BusinessDichanActivity target, View source) {
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
    target.titleContentTv = Utils.findRequiredViewAsType(source, R.id.title_content_tv, "field 'titleContentTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.search_et, "field 'searchEt' and method 'onViewClicked'");
    target.searchEt = Utils.castView(view, R.id.search_et, "field 'searchEt'", TextView.class);
    view2131755260 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.fenleiRecycler = Utils.findRequiredViewAsType(source, R.id.fenlei_recycler, "field 'fenleiRecycler'", RecyclerView.class);
    target.likeRecycler = Utils.findRequiredViewAsType(source, R.id.like_recycler, "field 'likeRecycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BusinessDichanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleBackIv = null;
    target.titleContentTv = null;
    target.searchEt = null;
    target.fenleiRecycler = null;
    target.likeRecycler = null;

    view2131755258.setOnClickListener(null);
    view2131755258 = null;
    view2131755260.setOnClickListener(null);
    view2131755260 = null;
  }
}
