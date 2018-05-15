// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AgentListActivity_ViewBinding implements Unbinder {
  private AgentListActivity target;

  @UiThread
  public AgentListActivity_ViewBinding(AgentListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AgentListActivity_ViewBinding(AgentListActivity target, View source) {
    this.target = target;

    target.back = Utils.findRequiredViewAsType(source, R.id.act_agentList_back, "field 'back'", ImageView.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.act_agentList_recyclerView, "field 'mRecyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AgentListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.back = null;
    target.mRecyclerView = null;
  }
}
