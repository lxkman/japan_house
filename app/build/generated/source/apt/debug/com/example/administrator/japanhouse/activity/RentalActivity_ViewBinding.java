// Generated code from Butter Knife. Do not modify!
package com.example.administrator.japanhouse.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.administrator.japanhouse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RentalActivity_ViewBinding implements Unbinder {
  private RentalActivity target;

  private View view2131755479;

  private View view2131755494;

  private View view2131755495;

  private View view2131755500;

  private View view2131755480;

  private View view2131755483;

  @UiThread
  public RentalActivity_ViewBinding(RentalActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RentalActivity_ViewBinding(final RentalActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.act_rental_back, "field 'back' and method 'onViewClicked'");
    target.back = Utils.castView(view, R.id.act_rental_back, "field 'back'", ImageView.class);
    view2131755479 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tabRent = Utils.findRequiredViewAsType(source, R.id.act_rental_rent, "field 'tabRent'", TextView.class);
    target.tabRentIndicator = Utils.findRequiredView(source, R.id.act_rental_rentView, "field 'tabRentIndicator'");
    target.tabSell = Utils.findRequiredViewAsType(source, R.id.act_rental_sell, "field 'tabSell'", TextView.class);
    target.tabSellIndicator = Utils.findRequiredView(source, R.id.act_rental_sellView, "field 'tabSellIndicator'");
    target.etCall = Utils.findRequiredViewAsType(source, R.id.act_rental_call, "field 'etCall'", EditText.class);
    target.etContact = Utils.findRequiredViewAsType(source, R.id.act_rental_contact, "field 'etContact'", EditText.class);
    target.etLocation = Utils.findRequiredViewAsType(source, R.id.act_rental_location, "field 'etLocation'", EditText.class);
    target.etDistance = Utils.findRequiredViewAsType(source, R.id.act_rental_distance, "field 'etDistance'", EditText.class);
    target.etFloor = Utils.findRequiredViewAsType(source, R.id.act_rental_floor, "field 'etFloor'", EditText.class);
    target.etArea = Utils.findRequiredViewAsType(source, R.id.act_rental_area, "field 'etArea'", EditText.class);
    target.tvAreaSpan = Utils.findRequiredViewAsType(source, R.id.act_rental_areaSpan, "field 'tvAreaSpan'", TextView.class);
    target.etPattern = Utils.findRequiredViewAsType(source, R.id.act_rental_pattern, "field 'etPattern'", EditText.class);
    view = Utils.findRequiredView(source, R.id.act_rental_check, "field 'rbCheck' and method 'onViewClicked'");
    target.rbCheck = Utils.castView(view, R.id.act_rental_check, "field 'rbCheck'", RadioButton.class);
    view2131755494 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.act_rental_checked, "field 'rbChecked' and method 'onViewClicked'");
    target.rbChecked = Utils.castView(view, R.id.act_rental_checked, "field 'rbChecked'", RadioButton.class);
    view2131755495 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etToward = Utils.findRequiredViewAsType(source, R.id.act_rental_toward, "field 'etToward'", EditText.class);
    target.etEquipment = Utils.findRequiredViewAsType(source, R.id.act_rental_equipment, "field 'etEquipment'", EditText.class);
    target.picRecyclerView = Utils.findRequiredViewAsType(source, R.id.act_rental_picRecyclerView, "field 'picRecyclerView'", GridView.class);
    target.imgVideo = Utils.findRequiredViewAsType(source, R.id.act_rental_videoImg, "field 'imgVideo'", GridView.class);
    view = Utils.findRequiredView(source, R.id.act_rental_entrust, "field 'tvEntrust' and method 'onViewClicked'");
    target.tvEntrust = Utils.castView(view, R.id.act_rental_entrust, "field 'tvEntrust'", TextView.class);
    view2131755500 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.act_rental_rent_lt, "method 'onViewClicked'");
    view2131755480 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.act_rental_sell_lt, "method 'onViewClicked'");
    view2131755483 = view;
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
    RentalActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.back = null;
    target.tabRent = null;
    target.tabRentIndicator = null;
    target.tabSell = null;
    target.tabSellIndicator = null;
    target.etCall = null;
    target.etContact = null;
    target.etLocation = null;
    target.etDistance = null;
    target.etFloor = null;
    target.etArea = null;
    target.tvAreaSpan = null;
    target.etPattern = null;
    target.rbCheck = null;
    target.rbChecked = null;
    target.etToward = null;
    target.etEquipment = null;
    target.picRecyclerView = null;
    target.imgVideo = null;
    target.tvEntrust = null;

    view2131755479.setOnClickListener(null);
    view2131755479 = null;
    view2131755494.setOnClickListener(null);
    view2131755494 = null;
    view2131755495.setOnClickListener(null);
    view2131755495 = null;
    view2131755500.setOnClickListener(null);
    view2131755500 = null;
    view2131755480.setOnClickListener(null);
    view2131755480 = null;
    view2131755483.setOnClickListener(null);
    view2131755483 = null;
  }
}
