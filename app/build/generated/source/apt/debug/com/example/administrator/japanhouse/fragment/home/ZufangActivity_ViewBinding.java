// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
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

public class ZufangActivity_ViewBinding implements Unbinder {
  private ZufangActivity target;

  private View view2131755235;

  private View view2131755299;

  private View view2131755300;

  private View view2131755301;

  private View view2131755609;

  private View view2131755612;

  private View view2131755614;

  @UiThread
  public ZufangActivity_ViewBinding(ZufangActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ZufangActivity_ViewBinding(final ZufangActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.search_tv, "field 'searchTv' and method 'onViewClicked'");
    target.searchTv = Utils.castView(view, R.id.search_tv, "field 'searchTv'", TextView.class);
    view2131755299 = view;
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
    target.fenleiRecycler = Utils.findRequiredViewAsType(source, R.id.fenlei_recycler, "field 'fenleiRecycler'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.look_more_tv, "field 'lookMoreTv' and method 'onViewClicked'");
    target.lookMoreTv = Utils.castView(view, R.id.look_more_tv, "field 'lookMoreTv'", TextView.class);
    view2131755609 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.yanjiuRecycler = Utils.findRequiredViewAsType(source, R.id.yanjiu_recycler, "field 'yanjiuRecycler'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.shendeng_tv, "field 'shendengTv' and method 'onViewClicked'");
    target.shendengTv = Utils.castView(view, R.id.shendeng_tv, "field 'shendengTv'", TextView.class);
    view2131755612 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.view12 = Utils.findRequiredViewAsType(source, R.id.view_12, "field 'view12'", TextView.class);
    target.tuijianRecycler = Utils.findRequiredViewAsType(source, R.id.tuijian_recycler, "field 'tuijianRecycler'", RecyclerView.class);
    target.notShendengLl = Utils.findRequiredViewAsType(source, R.id.not_shendeng_ll, "field 'notShendengLl'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.shendeng_more_tv, "field 'shendengMoreTv' and method 'onViewClicked'");
    target.shendengMoreTv = Utils.castView(view, R.id.shendeng_more_tv, "field 'shendengMoreTv'", TextView.class);
    view2131755614 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.shendengRecycler = Utils.findRequiredViewAsType(source, R.id.shendeng_recycler, "field 'shendengRecycler'", RecyclerView.class);
    target.alreadyShendengLl = Utils.findRequiredViewAsType(source, R.id.already_shendeng_ll, "field 'alreadyShendengLl'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ZufangActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.backImg = null;
    target.searchTv = null;
    target.imgDingwei = null;
    target.imgMessage = null;
    target.fenleiRecycler = null;
    target.lookMoreTv = null;
    target.yanjiuRecycler = null;
    target.shendengTv = null;
    target.view12 = null;
    target.tuijianRecycler = null;
    target.notShendengLl = null;
    target.shendengMoreTv = null;
    target.shendengRecycler = null;
    target.alreadyShendengLl = null;

    view2131755235.setOnClickListener(null);
    view2131755235 = null;
    view2131755299.setOnClickListener(null);
    view2131755299 = null;
    view2131755300.setOnClickListener(null);
    view2131755300 = null;
    view2131755301.setOnClickListener(null);
    view2131755301 = null;
    view2131755609.setOnClickListener(null);
    view2131755609 = null;
    view2131755612.setOnClickListener(null);
    view2131755612 = null;
    view2131755614.setOnClickListener(null);
    view2131755614 = null;
  }
}
