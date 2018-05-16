// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.fragment.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.view.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyDataActivity_ViewBinding implements Unbinder {
  private MyDataActivity target;

  @UiThread
  public MyDataActivity_ViewBinding(MyDataActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyDataActivity_ViewBinding(MyDataActivity target, View source) {
    this.target = target;

    target.backImg = Utils.findRequiredViewAsType(source, R.id.back_img, "field 'backImg'", ImageView.class);
    target.ivHead = Utils.findRequiredViewAsType(source, R.id.iv_head, "field 'ivHead'", CircleImageView.class);
    target.ivSelectPhoto = Utils.findRequiredViewAsType(source, R.id.iv_select_photo, "field 'ivSelectPhoto'", ImageView.class);
    target.et_name = Utils.findRequiredViewAsType(source, R.id.et_name, "field 'et_name'", EditText.class);
    target.cbMan = Utils.findRequiredViewAsType(source, R.id.cb_man, "field 'cbMan'", CheckBox.class);
    target.llMan = Utils.findRequiredViewAsType(source, R.id.ll_man, "field 'llMan'", LinearLayout.class);
    target.cbWoman = Utils.findRequiredViewAsType(source, R.id.cb_woman, "field 'cbWoman'", CheckBox.class);
    target.llWoman = Utils.findRequiredViewAsType(source, R.id.ll_woman, "field 'llWoman'", LinearLayout.class);
    target.tvBirthday = Utils.findRequiredViewAsType(source, R.id.tv_birthday, "field 'tvBirthday'", TextView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.activityMyData = Utils.findRequiredViewAsType(source, R.id.activity_my_data, "field 'activityMyData'", LinearLayout.class);
    target.llName = Utils.findRequiredViewAsType(source, R.id.ll_name, "field 'llName'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyDataActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.backImg = null;
    target.ivHead = null;
    target.ivSelectPhoto = null;
    target.et_name = null;
    target.cbMan = null;
    target.llMan = null;
    target.cbWoman = null;
    target.llWoman = null;
    target.tvBirthday = null;
    target.tvPhone = null;
    target.activityMyData = null;
    target.llName = null;
  }
}
